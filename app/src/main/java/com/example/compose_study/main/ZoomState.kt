package com.example.compose_study.main

import android.content.Context
import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.util.VelocityTracker
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Float.max
import kotlin.math.abs

@Stable
class ZoomState(
    private var contentSize: Size = Size.Zero,
    private val velocityDecay: DecayAnimationSpec<Float> = exponentialDecay(),
    private val context : Context
) {

    private val _isDragging = MutableStateFlow(false)
    val isDragging = _isDragging.asStateFlow()

    private var isShortHeightDevice = false

    private var _scale = Animatable(1f).apply {
        updateBounds(0.1f, 100f)
    }

    private var maxScale = 1f
    var minScale = 1f

    var leftTop = Offset(0f,0f)

    val scale: Float
        get() = _scale.value

    private var _offsetX = Animatable(0f)

    val offsetX: Float
        get() = _offsetX.value

    private var _offsetY = Animatable(0f)

    val offsetY: Float
        get() = _offsetY.value

    private var layoutSize = Size.Zero
    private var screenWidth = 0f

    fun setLayoutSize(size: Size) {
        layoutSize = size
        updateFitContentSize()
    }

    fun setContentSize(size: Size) {
        contentSize = size
        leftTop = Offset(contentSize.width/2 - screenWidth/2,contentSize.height/2 - screenWidth/2)
        updateFitContentSize()
    }

    fun setScreenWidth(width : Float){
        screenWidth = width
    }

    fun setDragging(isDragging : Boolean){
        _isDragging.value = isDragging
    }

    fun setShortHeightDevice(){
        isShortHeightDevice = true
    }

    fun getContentSize() = contentSize

    private var fitContentSize = Size.Zero
    private fun updateFitContentSize() {
        if (layoutSize == Size.Zero) {
            fitContentSize = Size.Zero
            return
        }

        if (contentSize == Size.Zero) {
            fitContentSize = layoutSize
            return
        }

        fitContentSize = contentSize
    }

    suspend fun reset(size : Size) = coroutineScope {
        launch { _offsetX.snapTo(0f) }
        launch { _offsetY.snapTo(0f) }
        launch {
            setContentSize(size)
            val newBounds = calculateNewBounds(minScale)
            _offsetX.updateBounds(newBounds.left, newBounds.right)
            _offsetY.updateBounds(newBounds.top, newBounds.bottom)
        }
        launch {setScale(minScale)}
    }

    private var shouldConsumeEvent: Boolean? = null
    private val velocityTracker = VelocityTracker()

    internal fun startGesture() {
        shouldConsumeEvent = null
        velocityTracker.resetTracking()
    }

    suspend fun setScale(scale : Float){
        _scale.snapTo(scale)
        minScale = scale
        maxScale = scale * 5f
        leftTop = leftTop.copy(x = ((contentSize.width * scale)/2)-(screenWidth/2),y = ((contentSize.height * scale)/2)-(screenWidth/2))
    }

    internal fun canConsumeGesture(pan: Offset, zoom: Float): Boolean {
        return shouldConsumeEvent ?: run {
            var consume = true
            if (zoom == 1f) {
                val ratio = (abs(pan.x) / abs(pan.y))
                if (ratio > 3) {
                    if ((pan.x < 0) && (_offsetX.value == _offsetX.lowerBound)) {
                        consume = false
                    }
                    if ((pan.x > 0) && (_offsetX.value == _offsetX.upperBound)) {
                        consume = false
                    }
                } else if (ratio < 0.33) {
                    if ((pan.y < 0) && (_offsetY.value == _offsetY.lowerBound)) {
                        consume = false
                    }
                    if ((pan.y > 0) && (_offsetY.value == _offsetY.upperBound)) {
                        consume = false
                    }
                }
            }
            shouldConsumeEvent = consume
            consume
        }
    }

    internal suspend fun applyGesture(
        pan: Offset,
        zoom: Float,
        position: Offset,
        timeMillis: Long
    ) = coroutineScope {
        val newScale = (scale * zoom).coerceIn(minScale, maxScale)
        val newOffset = calculateNewOffset(newScale, position, pan)
        val newBounds = calculateNewBounds(newScale)

        _offsetX.updateBounds(newBounds.left, newBounds.right)
        launch {
            _offsetX.snapTo(newOffset.x)
        }

        _offsetY.updateBounds(newBounds.top, newBounds.bottom)
        launch {
            _offsetY.snapTo(newOffset.y)
        }

        launch {
            _scale.snapTo(newScale)
        }

        leftTop = leftTop.copy(x = ((contentSize.width * newScale)/2)-(screenWidth/2),y = ((contentSize.height * newScale)/2)-(screenWidth/2))

        if (zoom == 1f) {
            velocityTracker.addPosition(timeMillis, position)
        } else {
            velocityTracker.resetTracking()
        }
    }


    suspend fun changeScale(
        targetScale: Float,
        position: Offset,
        animationSpec: AnimationSpec<Float> = spring(),
    ) = coroutineScope {
        val newScale = targetScale.coerceIn(1f, maxScale)
        val newOffset = calculateNewOffset(newScale, position, Offset.Zero)
        val newBounds = calculateNewBounds(newScale)

        val x = newOffset.x.coerceIn(newBounds.left, newBounds.right)
        launch {
            _offsetX.updateBounds(null, null)
            _offsetX.animateTo(x, animationSpec)
            _offsetX.updateBounds(newBounds.left, newBounds.right)
        }

        val y = newOffset.y.coerceIn(newBounds.top, newBounds.bottom)
        launch {
            _offsetY.updateBounds(null, null)
            _offsetY.animateTo(y, animationSpec)
            _offsetY.updateBounds(newBounds.top, newBounds.bottom)
        }

        launch {
            _scale.animateTo(newScale, animationSpec)
        }
    }

    private fun calculateNewOffset(
        newScale: Float,
        position: Offset,
        pan: Offset,
    ): Offset {
        val size = fitContentSize * scale
        val newSize = fitContentSize * newScale
        val deltaWidth = newSize.width - size.width
        val deltaHeight = newSize.height - size.height

        val xInContent = position.x - offsetX + (size.width - layoutSize.width) * 0.5f
        val yInContent = position.y - offsetY + (size.height - layoutSize.height) * 0.5f

        val deltaX = (deltaWidth * 0.5f) - (deltaWidth * xInContent / size.width)
        val deltaY = (deltaHeight * 0.5f) - (deltaHeight * yInContent / size.height)

        val x = offsetX + pan.x + deltaX
        val y = offsetY + pan.y + deltaY


        return Offset(x, y)
    }

    private fun calculateNewBounds(
        newScale: Float,
    ): Rect {
        val newSize = fitContentSize * newScale
        val boundX = max((newSize.width - if(isShortHeightDevice) layoutSize.width - 280.dpToPixels(context) else layoutSize.width), 0f) * 0.5f
        val boundY = max((newSize.height - screenWidth), 0f) * 0.5f

        return Rect(-boundX, -boundY, boundX, boundY)
    }

    internal suspend fun endGesture() = coroutineScope {
        val velocity = velocityTracker.calculateVelocity()
        if (velocity.x != 0f) {
            launch {
                _offsetX.animateDecay(velocity.x, velocityDecay)
            }
        }
        if (velocity.y != 0f) {
            launch {
                _offsetY.animateDecay(velocity.y, velocityDecay)
            }
        }

    }
}

@Composable
fun rememberZoomState(
    contentSize: Size = Size.Zero,
    velocityDecay: DecayAnimationSpec<Float> = exponentialDecay(),
    context : Context
) = remember {
    ZoomState(contentSize, velocityDecay, context)
}