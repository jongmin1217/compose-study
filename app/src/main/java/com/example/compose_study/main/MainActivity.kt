package com.example.compose_study.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Picture
import android.graphics.RectF
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.Typeface
import android.media.ExifInterface
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import androidx.core.graphics.scale
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Font
import com.kpstv.compose.kapture.attachController
import com.kpstv.compose.kapture.rememberScreenshotController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import soup.compose.photo.ExperimentalPhotoApi
import soup.compose.photo.PhotoBox
import soup.compose.photo.rememberPhotoState
import java.io.IOException
import java.io.InputStream
import java.net.URI
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.math.*


const val START_PADDING = 40
const val POINT_PADDING = 14
const val BOTTOM_PADDING = 22

class MainActivity : ComponentActivity() {

    val list30 = listOf(
        GraphPoint(0, "09-01", 300),
        GraphPoint(1, "09-02", 370),
        GraphPoint(2, "09-03", 220),
        GraphPoint(3, "09-04", 240),
        GraphPoint(4, "09-05", 500),
        GraphPoint(5, "09-06", 112),
        GraphPoint(6, "09-07", 250),
        GraphPoint(7, "09-08", 770),
        GraphPoint(8, "09-09", 500),
        GraphPoint(9, "09-10", 1000),
        GraphPoint(10, "09-11", 250),
        GraphPoint(11, "09-12", 370),
        GraphPoint(12, "09-13", 230),
        GraphPoint(13, "09-14", 113),
        GraphPoint(14, "09-15", 500),
        GraphPoint(15, "09-16", 130),
        GraphPoint(16, "09-17", 250),
        GraphPoint(17, "09-18", 240),
        GraphPoint(18, "09-19", 500),
        GraphPoint(19, "09-20", 1320),
        GraphPoint(20, "09-21", 250),
        GraphPoint(21, "09-22", 370),
        GraphPoint(22, "09-23", 178),
        GraphPoint(23, "09-24", 240),
        GraphPoint(24, "09-25", 500),
        GraphPoint(25, "09-26", 510),
        GraphPoint(26, "09-27", 250),
        GraphPoint(27, "09-28", 240),
        GraphPoint(28, "09-29", 250),
        GraphPoint(29, "09-30", 240)
    )

    val list7_2 = listOf(
        GraphPoint(0, "09-01", null),
        GraphPoint(1, "09-02", 500),
        GraphPoint(2, "09-03", null),
        GraphPoint(3, "09-04", null),
        GraphPoint(4, "09-05", null),
        GraphPoint(5, "09-06", null),
        GraphPoint(6, "09-07", null)
    )

    val list30_2 = listOf(
        GraphPoint(0, "09-01", null),
        GraphPoint(1, "09-02", 37),
        GraphPoint(2, "09-03", 22),
        GraphPoint(3, "09-04", null),
        GraphPoint(4, "09-05", null),
        GraphPoint(5, "09-06", null),
        GraphPoint(6, "09-07", 25),
        GraphPoint(7, "09-08", null),
        GraphPoint(8, "09-09", 50),
        GraphPoint(9, "09-10", 70),
        GraphPoint(10, "09-11", 25),
        GraphPoint(11, "09-12", 37),
        GraphPoint(12, "09-13", null),
        GraphPoint(13, "09-14", 24),
        GraphPoint(14, "09-15", 50),
        GraphPoint(15, "09-16", 13),
        GraphPoint(16, "09-17", 25),
        GraphPoint(17, "09-18", 24),
        GraphPoint(18, "09-19", 50),
        GraphPoint(19, "09-20", 70),
        GraphPoint(20, "09-21", 25),
        GraphPoint(21, "09-22", 37),
        GraphPoint(22, "09-23", null),
        GraphPoint(23, "09-24", 24),
        GraphPoint(24, "09-25", 50),
        GraphPoint(25, "09-26", 51),
        GraphPoint(26, "09-27", 25),
        GraphPoint(27, "09-28", 24),
        GraphPoint(28, "09-29", null),
        GraphPoint(29, "09-30", 24)
    )

    val list7 = listOf(
        GraphPoint(0, "09-13", 30),
        GraphPoint(1, "09-14", 37),
        GraphPoint(2, "09-15", 120),
        GraphPoint(3, "09-16", 20),
        GraphPoint(4, "09-17", 50),
        GraphPoint(5, "09-18", 65),
        GraphPoint(6, "09-19", 25)
    )

    val list7_3 = listOf(
        GraphPoint(0, "09-01", null),
        GraphPoint(1, "09-02", null),
        GraphPoint(2, "09-03", null),
        GraphPoint(3, "09-04", null),
        GraphPoint(4, "09-05", null),
        GraphPoint(5, "09-06", null),
        GraphPoint(6, "09-07", null)
    )

    val list30_3 = listOf(
        GraphPoint(0, "09-01", null),
        GraphPoint(1, "09-02", null),
        GraphPoint(2, "09-03", null),
        GraphPoint(3, "09-04", null),
        GraphPoint(4, "09-05", null),
        GraphPoint(5, "09-06", null),
        GraphPoint(6, "09-07", null),
        GraphPoint(7, "09-08", null),
        GraphPoint(8, "09-09", null),
        GraphPoint(9, "09-10", null),
        GraphPoint(10, "09-11", null),
        GraphPoint(11, "09-12", null),
        GraphPoint(12, "09-13", null),
        GraphPoint(13, "09-14", null),
        GraphPoint(14, "09-15", null),
        GraphPoint(15, "09-16", null),
        GraphPoint(16, "09-17", null),
        GraphPoint(17, "09-18", null),
        GraphPoint(18, "09-19", null),
        GraphPoint(19, "09-20", null),
        GraphPoint(20, "09-21", null),
        GraphPoint(21, "09-22", null),
        GraphPoint(22, "09-23", null),
        GraphPoint(23, "09-24", null),
        GraphPoint(24, "09-25", null),
        GraphPoint(25, "09-26", null),
        GraphPoint(26, "09-27", null),
        GraphPoint(27, "09-28", null),
        GraphPoint(28, "09-29", null),
        GraphPoint(29, "09-30", null)
    )



    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val context = LocalContext.current

            val screenWidth = LocalConfiguration.current.screenWidthDp.dpToPixels(context).toInt().toFloat()
            var screenHeight by remember { mutableStateOf(0f) }
            var bitmap by remember { mutableStateOf<Bitmap?>(null) }
            var isVisibleBaseLine by remember { mutableStateOf(false) }

            val test = WindowInsets.Companion.captionBar.asPaddingValues()
            val test2 = WindowInsets.Companion.statusBars.asPaddingValues()
            var imageUri by remember {
                mutableStateOf<Uri?>(null)
            }


            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult(),
                onResult = {
                    if (it.resultCode == Activity.RESULT_OK) {
                        val data = it.data
                        imageUri = data?.data
                    }
                }
            )

            val scope = rememberCoroutineScope()
            val screenshotState = rememberScreenshotController()




            Surface(modifier = Modifier.fillMaxSize()) {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(onClick = {
                        launcher.launch(
                            Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                        )
                    }) {
                        Text(text = "asdf")
                    }

                    bitmap?.let {

                        AsyncImage(model = bitmap, contentDescription = "", modifier = Modifier
                            .fillMaxWidth()
                            .align(
                                Center
                            ), contentScale = ContentScale.FillWidth)
                    }

                }

                imageUri?.let {
                    val rotation = remember { Animatable(0f) }
                    val zoomState = rememberZoomState(screenWidth = screenWidth)

                    LaunchedEffect(Unit){
                        zoomState.isDragging.collect{
                            isVisibleBaseLine = it
                        }
                    }
                    CustomBottomSheetDialog(
                        onDismissRequest = {
                            imageUri = null
                        },
                        properties = BottomSheetDialogProperties(
                            navigationBarProperties = NavigationBarProperties(
                                color = Color.White
                            ),
                            behaviorProperties = BottomSheetBehaviorProperties(
                                state = BottomSheetBehaviorProperties.State.Expanded,
                                skipCollapsed = true,
                                isDraggable = true
                            )
                        )
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(top = 24.dp),
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                            ){

                                BlurImage(
                                    screenWidth,screenHeight
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0xff000000))
                                            .onGloballyPositioned {
                                                screenHeight = it.size.height.toFloat()
                                            }

                                    ) {


                                        AsyncImage(
                                            model = it,
                                            contentDescription = "",
                                            onSuccess = {
                                                val size = if (it.result.isSampled) {
                                                    it.painter.intrinsicSize
                                                } else {
                                                    if (it.painter.intrinsicSize.width >= it.painter.intrinsicSize.height) {
                                                        it.painter.intrinsicSize * (screenWidth / it.painter.intrinsicSize.width)
                                                    } else {
                                                        it.painter.intrinsicSize * (screenHeight / it.painter.intrinsicSize.height)
                                                    }
                                                }

                                                zoomState.setContentSize(size)
                                                when {
                                                    size.width < screenWidth -> {
                                                        scope.launch {
                                                            zoomState.setScale(screenWidth / size.width)
                                                        }
                                                    }

                                                    size.height < screenWidth -> {
                                                        scope.launch {
                                                            zoomState.setScale(screenWidth / size.height)
                                                        }
                                                    }

                                                    else -> {
                                                        scope.launch {
                                                            zoomState.setScale(1f)
                                                        }
                                                    }
                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .zoomable(zoomState)
                                                .rotate(rotation.value)
                                        )

                                    }
                                }


                                Canvas(modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer {
                                        compositingStrategy = CompositingStrategy.Offscreen
                                    }
                                ){
                                    drawRect(
                                        color = Color.Black.copy(alpha = 0.8f),
                                    )
                                    drawCircle(
                                        color = Color.Transparent,
                                        radius = (screenWidth/2) - 15.dp.toPx(),
                                        center = Offset(screenWidth/2,screenHeight/2),
                                        blendMode = BlendMode.Clear,
                                    )
                                }

                                AnimatedVisibility(
                                    visible = isVisibleBaseLine,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Canvas(modifier = Modifier
                                        .fillMaxSize()
                                    ){
                                        val offsetList = listOf(
                                            Offset(15.dp.toPx(),screenHeight/2 - ((screenWidth/2) - 15.dp.toPx())),
                                            Offset(screenWidth - 15.dp.toPx(),screenHeight/2 - ((screenWidth/2) - 15.dp.toPx())),
                                            Offset(screenWidth - 15.dp.toPx(),screenHeight/2 + ((screenWidth/2) - 15.dp.toPx())),
                                            Offset(15.dp.toPx(),screenHeight/2 + ((screenWidth/2) - 15.dp.toPx()))
                                        )

                                        val zxc = (screenWidth - 30.dp.toPx())/3

                                        for(i in 0..3){
                                            drawLine(
                                                color = Color.White.copy(alpha = 0.5f),
                                                start = offsetList[i],
                                                end = offsetList[if(i == 3) 0 else i+1],
                                                strokeWidth = 1.dp.toPx()
                                            )

                                            if(i == 0){
                                                for(j in 0..1){
                                                    drawLine(
                                                        color = Color.White.copy(alpha = 0.5f),
                                                        start = offsetList[i].copy(y = offsetList[i].y + (zxc * (j+1))),
                                                        end = offsetList[i+1].copy(y = offsetList[i].y + (zxc * (j+1))),
                                                        strokeWidth = 1.dp.toPx()
                                                    )
                                                }
                                            }else if(i == 3){
                                                for(j in 0..1){
                                                    drawLine(
                                                        color = Color.White.copy(alpha = 0.5f),
                                                        start = offsetList[i].copy(x = offsetList[i].x + (zxc * (j+1))),
                                                        end = offsetList[0].copy(x = offsetList[i].x + (zxc * (j+1))),
                                                        strokeWidth = 1.dp.toPx()
                                                    )
                                                }
                                            }

                                            val resourceId = when(i){
                                                0 -> R.drawable.img_top_left
                                                1 -> R.drawable.img_top_right
                                                2 -> R.drawable.img_bottom_right
                                                else -> R.drawable.img_bottom_left
                                            }

                                            val resourceBitmap =
                                                BitmapFactory.decodeResource(resources,resourceId)
                                                    .scale(20.dp.toPx().toInt(),20.dp.toPx().toInt())
                                            drawImage(
                                                resourceBitmap.asImageBitmap(),
                                                when(i){
                                                    1 -> offsetList[i].copy(x = offsetList[i].x - 20.dp.toPx())
                                                    2 -> offsetList[i].copy(x = offsetList[i].x - 20.dp.toPx(),y = offsetList[i].y - 20.dp.toPx())
                                                    3 -> offsetList[i].copy(y = offsetList[i].y - 20.dp.toPx())
                                                    else -> offsetList[i]
                                                }

                                            )
                                        }
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .align(BottomCenter)
                                        .padding(bottom = 86.dp)
                                        .width(68.dp)
                                        .height(34.dp)
                                        .border(
                                            BorderStroke(1.dp, Color(0x80dddddd)),
                                            RoundedCornerShape(3.dp)
                                        )
                                        .clickable {
                                            if (!rotation.isRunning) {
                                                scope.launch {
                                                    rotation.animateTo(rotation.value + 90f)
                                                }
                                                scope.launch {
                                                    val preSize = zoomState.getContentSize()
                                                    val newSize =
                                                        Size(preSize.height, preSize.width)

                                                    zoomState.reset(newSize)
                                                }
                                            }
                                        }
                                ) {
                                    Text(
                                        text = "회전",
                                        style = TextStyle(
                                            fontSize = 13.dp.textSp,
                                            fontWeight = FontWeight.W700,
                                            color = Color(0xffffffff),
                                            fontFamily = Font.nanumSquareRoundFont
                                        ),
                                        modifier = Modifier.align(Center)
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(54.dp)
                                        .background(Color(0xffffffff))
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        text = "자르기 및 회전",
                                        style = TextStyle(
                                            fontSize = 17.dp.textSp,
                                            fontWeight = FontWeight.W700,
                                            color = Color(0xff111111),
                                            fontFamily = Font.nanumSquareRoundFont
                                        ),
                                        modifier = Modifier.align(Center)
                                    )

                                    Image(
                                        painter = painterResource(id = R.drawable.btn_del),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(54.dp)
                                            .align(CenterEnd)
                                            .padding(10.dp)
                                            .clickable {
                                                imageUri = null
                                            }
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .background(Color(0xffff4857))
                                        .align(BottomCenter)
                                        .clickable {
                                            scope.launch {
                                                val asd = screenshotState.captureToBitmap()
                                                bitmap = asd.getOrNull()
                                                log("qweqwe", test)
                                            }
                                        }
                                ) {
                                    Text(
                                        text = "확인",
                                        style = TextStyle(
                                            fontSize = 16.dp.textSp,
                                            fontWeight = FontWeight.W700,
                                            color = Color(0xffffffff),
                                            fontFamily = Font.nanumSquareRoundFont
                                        ),
                                        modifier = Modifier.align(Center)
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun cropImageToPixels(context: Context, imageUri: Uri, params : CropParams): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(imageUri)

            inputStream?.let {
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val originalBitmap = BitmapFactory.decodeStream(inputStream, null, options)

                val orientation = getOrientationFromExif(context,imageUri)

                originalBitmap?.let {
                    val rotateBitmap = rotateBitmap(originalBitmap, orientation)

                    val bitmapScale = rotateBitmap.width / params.originalSize.width
//
//                    val center = rotateBitmap.width/2 to rotateBitmap.height/2

                    val qwe = Bitmap.createBitmap(
                        rotateBitmap,
                        0,
                        0,
                        ((params.originalSize.width * bitmapScale) / params.scale).toInt(),
                        ((params.originalSize.width * bitmapScale) / params.scale).toInt()
                    )
//
//                    val pixels = IntArray(qwe.width * qwe.height)
//                    qwe.getPixels(pixels, 0, qwe.width, 0, 0, qwe.width, qwe.height)
//
                    inputStream.close()

                    qwe
                }

//                val croppedBitmap = Bitmap.createBitmap(
//                    originalBitmap,
//                    cropRect.left.toInt(),
//                    cropRect.top.toInt(),
//                    cropRect.width.toInt(),
//                    cropRect.height.toInt()
//                )
//
//                val pixels = IntArray(croppedBitmap.width * croppedBitmap.height)
//                croppedBitmap.getPixels(pixels, 0, croppedBitmap.width, 0, 0, croppedBitmap.width, croppedBitmap.height)
//
//                inputStream?.close()
//
//                pixels
            }?:run{
                null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
data class CropParams(val offsetX: Float, val offsetY: Float, val scale: Float, val originalSize: Size)
fun calculateCropArea(cropParams: CropParams): Rect {
    val originalWidth = cropParams.originalSize.width
    val originalHeight = cropParams.originalSize.height

    // Calculate the scaled dimensions
    val scaledWidth = (originalWidth * cropParams.scale).toInt()
    val scaledHeight = (originalHeight * cropParams.scale).toInt()

    // Calculate the crop area position after scaling
    val scaledOffsetX = cropParams.offsetX * cropParams.scale
    val scaledOffsetY = cropParams.offsetY * cropParams.scale

    // Calculate the left, top, right, and bottom coordinates of the crop area
    val left = max(0f, scaledOffsetX).toInt()
    val top = max(0f, scaledOffsetY).toInt()
    val right = min(scaledWidth, scaledOffsetX.toInt() + scaledWidth)
    val bottom = min(scaledHeight, scaledOffsetY.toInt() + scaledHeight)

    // Calculate the width and height of the crop area
    val width = max(0, right - left)
    val height = max(0, bottom - top)

    return Rect(left.toFloat(), top.toFloat(), (left + width).toFloat(), (top + height).toFloat())
}


private fun getOrientationFromExif(context: Context,imageUri: Uri): Int {
    var inputStream: InputStream? = null
    try {
        // Open an input stream from the content resolver
        inputStream = context.contentResolver.openInputStream(imageUri)

        // Check if the input stream is not null
        if (inputStream != null) {
            // Get the orientation from Exif data
            val exifInterface = ExifInterface(inputStream)
            return exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        // Close the input stream
        inputStream?.close()
    }

    return ExifInterface.ORIENTATION_UNDEFINED
}

private fun rotateBitmap(bitmap: Bitmap, orientation: Int): Bitmap {
    val matrix = Matrix()

    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        ExifInterface.ORIENTATION_NORMAL -> return bitmap
    }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

@Composable
fun BlurImage(
    screenWidth : Float,
    screenHeight : Float,
    content: @Composable () -> Unit
) {
    Box {
        content()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp, edgeTreatment = BlurredEdgeTreatment.Rectangle)
                .drawWithContent {
                    with(drawContext.canvas.nativeCanvas) {
                        val checkPoint = saveLayer(null, null)

                        drawContent()

                        drawCircle(
                            color = Color.Transparent,
                            radius = (screenWidth / 2) - 15.dp.toPx(),
                            center = Offset(screenWidth / 2, screenHeight / 2),
                            blendMode = BlendMode.Clear
                        )
                        restoreToCount(checkPoint)
                    }
                }

        ) {
            content()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextFieldTest() {
    var text by remember { mutableStateOf("") }
    var hasFocus by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        placeholder = {
            if (!hasFocus) {
                Text(
                    text = "직접 입력해 주세요. (최대 100자)",
                    style = TextStyle(
                        fontSize = 16.dp.textSp,
                        color = Color(0xffdddddd),
                        fontWeight = FontWeight.W400,
                        lineHeight = 24.dp.textSp
                    )
                )
            }


        },
        textStyle = TextStyle(
            fontSize = 16.dp.textSp,
            color = Color(0xff111111),
            fontWeight = FontWeight.W400,
            lineHeight = 24.dp.textSp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color(0xFFFFFFFF))
            .border(
                width = 1.dp,
                color = Color(0xffdddddd),
                shape = RoundedCornerShape(size = 3.dp)
            )
            .onFocusChanged { focusState ->
                hasFocus = focusState.hasFocus
            },
        shape = RoundedCornerShape(size = 3.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xff111111),
            backgroundColor = Color.White,
            placeholderColor = Color(0xff111111),
            cursorColor = Color(0xff111111),
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified,
            leadingIconColor = Color(0xff111111),
            trailingIconColor = Color(0xff111111)

        )
    )

}

@Composable
fun GaugeTest() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffafafa))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            repeat(103) {
                val full = 9000f + Math.random().toFloat() * (10000f - 9000f)
                val current = Math.random().toFloat() * (12000f)

                Spacer(modifier = Modifier.height(50.dp))

                CustomGauge(
                    full,
                    if (it % 2 == 0) 0f else current,
                    "고지가 얼마 안남았다 멍",
                    if (it % 2 == 0) 0 else 2
                )
            }
        }
    }
}

@Composable
fun CustomGauge(
    fullGaugeValue: Float?,
    currentGaugeValue: Float,
    title: String,
    count: Int
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x4A000000),
                ambientColor = Color(0x4A000000)
            )
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {

        GaugeHeader(
            title = title,
            count = count,
            currentGaugeValue = currentGaugeValue,
            isEmptyGoal = fullGaugeValue == null
        )

        fullGaugeValue?.let {
            GaugeMiddle(
                currentGaugeValue = currentGaugeValue,
                percent = currentGaugeValue / fullGaugeValue
            )

            Gauge(fullGaugeValue, currentGaugeValue)

            GaugeBottomValue(fullGaugeValue)
        } ?: run {
            GaugeBottomBtn(currentGaugeValue)
        }

    }
}

@Composable
fun GaugeMiddle(
    currentGaugeValue: Float,
    percent: Float
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dpToPixels(context)

    if (currentGaugeValue != 0f) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.5.dp)
        ) {
            GaugeSpeechBubble(
                ((width - 80.dpToPixels(context)) * if (percent > 0.98f) 0.98f else percent) + 20.dpToPixels(
                    context
                ),
                this,
                "${((percent * 100) * 10).roundToInt() / 10f}%"
            )
        }
    } else {
        Spacer(modifier = Modifier.height(32.5.dp))
    }
}

@Composable
fun GaugeBottomBtn(
    currentGaugeValue: Float
) {
    Spacer(modifier = Modifier.height(if (currentGaugeValue == 0f) 21.dp else 15.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(36.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFDDDDDD),
                shape = RoundedCornerShape(size = 3.dp)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 3.dp))
    ) {
        Text(
            text = if (currentGaugeValue == 0f) "식사 기록하기" else "반복 식단 등록하기",
            style = TextStyle(
                fontSize = 13.dp.textSp,
                fontWeight = FontWeight.W400,
                color = Color(0xff111111),
                fontFamily = Font.nanumSquareRoundFont
            ),
            modifier = Modifier.align(Center)
        )
    }

    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
fun GaugeHeader(
    title: String,
    count: Int,
    currentGaugeValue: Float,
    isEmptyGoal: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 17.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(5.5.dp))
            Row {
                CenterText(
                    text = title,
                    textStyle = TextStyle(
                        fontSize = 18.dp.textSp,
                        fontWeight = FontWeight.W700,
                        color = Color(0xff111111),
                        fontFamily = Font.nanumSquareRoundFont
                    ),
                    modifier = Modifier.height(26.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                if (count != 0) {
                    Box(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(100.dp),
                                color = Color(0xFFEBFAF6)
                            )
                            .align(CenterVertically)
                    ) {
                        Text(
                            text = "${count}회",
                            modifier = Modifier
                                .padding(horizontal = 7.dp, vertical = 4.dp)
                                .align(Center),
                            style = TextStyle(
                                fontSize = 10.dp.textSp,
                                color = Color(0xFF04BF8A),
                                fontWeight = FontWeight.W700,
                                fontFamily = Font.nanumSquareRoundFont
                            ),
                            textAlign = TextAlign.Center,
                            lineHeight = 10.dp.textSp
                        )
                    }
                }

            }

            val annotatedString = if (isEmptyGoal && currentGaugeValue == 0f) {
                buildAnnotatedString { append("반복식단을 등록하고 식단 기록을 관리하세요.") }
            } else {
                buildAnnotatedString {
                    append("오늘 총")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF04BF8A),
                            fontWeight = FontWeight.W700,
                            fontSize = 14.dp.textSp,
                            fontFamily = Font.nanumSquareRoundFont
                        )
                    ) {
                        append(" ${currentGaugeValue.toInt().decimal()} kcal")
                    }
                    append("를 섭취했어요")
                }
            }


            CenterText(
                text = annotatedString,
                textStyle = TextStyle(
                    fontSize = 14.dp.textSp,
                    color = Color(0xFF777777),
                    fontWeight = FontWeight.W400,
                    fontFamily = Font.nanumSquareRoundFont
                ),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .height(16.dp)
            )
        }

        val imageModifier = if (isEmptyGoal && currentGaugeValue == 0f) {
            Modifier
                .padding(top = 10.dp)
                .width(16.dp)
                .height(15.dp)
        } else {
            Modifier.size(58.dp)
        }


        Image(
            painter = painterResource(
                id = if (isEmptyGoal && currentGaugeValue == 0f) {
                    R.drawable.btn_go
                } else {
                    if (currentGaugeValue == 0f) {
                        R.drawable.test1
                    } else {
                        R.drawable.test2
                    }
                }
            ),
            contentDescription = "",
            modifier = imageModifier
        )
    }
}

@Composable
fun GaugeBottomValue(
    fullGaugeValue: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = GAUGE_HEIGHT + 6.dp, bottom = 13.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        AppText(
            text = "하루 목표 칼로리",
            style = TextStyle(
                fontSize = 10.dp.textSp,
                fontWeight = FontWeight.W400,
                color = Color(0xffaaaaaa),
                fontFamily = Font.nanumSquareRoundFont
            ),
            modifier = Modifier.align(CenterVertically),
            lineHeight = 11.dp.textSp
        )

        AppText(
            text = "${fullGaugeValue.toInt().decimal()} kcal",
            style = TextStyle(
                fontSize = 11.dp.textSp,
                fontWeight = FontWeight.W700,
                color = Color(0xff444444),
                fontFamily = Font.nanumSquareRoundFont
            ),
            modifier = Modifier.align(CenterVertically),
            lineHeight = 12.dp.textSp
        )

    }
}

@Composable
fun Gauge(
    fullGaugeValue: Float,
    currentGaugeValue: Float
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        onDraw = {
            fullGaugeBar(Color(if (currentGaugeValue == 0f) 0xffF2F2F2 else 0xfff8f0dc))
            if (currentGaugeValue != 0f) currentGaugeBar(fullGaugeValue, currentGaugeValue)
            bulkhead()
        }
    )
}

val GAUGE_HEIGHT = 12.dp
val GAUGE_RADIUS = GAUGE_HEIGHT / 2

fun DrawScope.bulkhead() {
    this.run {
        repeat(3) {
            val xValue = size.width * ((it + 1).toFloat() / 4f)

            drawLine(
                color = Color(0xffd9d9d9),
                Offset(xValue, 0f),
                Offset(xValue, GAUGE_HEIGHT.toPx()),
                strokeWidth = 1.dp.toPx(),
                blendMode = BlendMode.Multiply
            )
        }
    }
}

fun DrawScope.currentGaugeBar(
    fullGaugeValue: Float,
    currentGaugeValue: Float
) {
    val gauge = currentGaugeValue / fullGaugeValue
    val barGauge = size.width * gauge

    val color = when (gauge) {
        in 0f..0.25f -> Color(0xffffca42)
        in 0.25f..0.5f -> Color(0xffD4E012)
        in 0.5f..0.75f -> Color(0xff84DA7C)
        in 0.5f..1f -> Color(0xff04BF8A)
        else -> Color(0xffff0000)
    }

    var startAngle = 90f
    var startSweepAngle = 180f

    var endAngle = 0f
    var endSweepAngle = -360f

    if (barGauge <= GAUGE_RADIUS.toPx()) {
        val test = barGauge / GAUGE_RADIUS.toPx()
        startAngle *= (2 - test)
        startSweepAngle *= test
    }

    if (barGauge >= size.width - GAUGE_RADIUS.toPx()) {
        val test = barGauge - (size.width - GAUGE_RADIUS.toPx())
        val test2 = test / GAUGE_RADIUS.toPx()
        endAngle = 90f - (90f * test2)
        endSweepAngle = (180 + (180 * test2))
    }

    this.run {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = startSweepAngle,
            useCenter = false,
            topLeft = Offset(0f, 0f),
            size = Size(GAUGE_HEIGHT.toPx(), GAUGE_HEIGHT.toPx())
        )

        if (barGauge > GAUGE_RADIUS.toPx()) {
            drawRect(
                color = color,
                topLeft = Offset(GAUGE_RADIUS.toPx(), 0f),
                size = Size(
                    if (barGauge >= size.width - GAUGE_RADIUS.toPx()) size.width - GAUGE_HEIGHT.toPx() else barGauge - GAUGE_RADIUS.toPx(),
                    GAUGE_HEIGHT.toPx()
                )
            )
        }

        if (barGauge >= size.width - GAUGE_RADIUS.toPx()) {
            drawArc(
                color = color,
                startAngle = endAngle,
                sweepAngle = endSweepAngle,
                useCenter = false,
                topLeft = Offset(size.width - GAUGE_HEIGHT.toPx(), 0f),
                size = Size(GAUGE_HEIGHT.toPx(), GAUGE_HEIGHT.toPx())
            )
        }
    }
}

fun DrawScope.fullGaugeBar(color: Color) {
    this.run {
        drawLine(
            color = color,
            Offset(GAUGE_RADIUS.toPx(), GAUGE_RADIUS.toPx()),
            Offset(size.width - GAUGE_RADIUS.toPx(), GAUGE_RADIUS.toPx()),
            strokeWidth = GAUGE_HEIGHT.toPx(),
            cap = StrokeCap.Round
        )
    }
}


@Composable
fun GaugeSpeechBubble(
    x: Float,
    boxScope: BoxScope,
    text: String
) {
    var layoutWidthOffset by remember { mutableStateOf(0f) }


    boxScope.run {
        DimensionSubComposeLayout(
            modifier = Modifier.align(TopStart),
            mainContent = {
                Column(
                    modifier = Modifier
                        .offset {
                            IntOffset((x - layoutWidthOffset).roundToInt(), 0)
                        }
                ) {
                    GaugeTextBox(text)

                    Image(
                        painter = painterResource(id = R.drawable.polygon_4),
                        contentDescription = "",
                        modifier = Modifier
                            .width(8.dp)
                            .height(5.dp)
                            .align(CenterHorizontally)
                            .padding(bottom = 1.dp)
                    )
                }
            }
        ) {
            layoutWidthOffset = it.width / 2
        }
    }

}
//
//@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
//@Composable
//fun PagerTest() {
//    val scope = rememberCoroutineScope()
//    val state = rememberPagerState(initialPage = 500)
//    val width = LocalConfiguration.current.screenWidthDp
//
//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        HorizontalPager(
//            pageCount = 1000,
//            state = state,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            val page = it % 7
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                val image = painterResource(
//                    id = when (page) {
//                        0 -> R.drawable.image_1
//                        1 -> R.drawable.image_2
//                        2 -> R.drawable.image_3
//                        3 -> R.drawable.image_4
//                        4 -> R.drawable.image_5
//                        5 -> R.drawable.image_6
//                        else -> R.drawable.image_7
//                    }
//                )
//
//                Image(
//                    painter = image,
//                    contentDescription = "",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(400.dp)
//                        .slidePager(it, state)
//                        .align(TopCenter),
//                    contentScale = ContentScale.Companion.Crop
//                )
//
//                Text(
//                    text = "타이틀 입니다. $page",
//                    style = TextStyle(
//                        fontSize = 20.dp.textSp,
//                        color = Color(0xffffffff),
//                        fontWeight = FontWeight.W700
//                    ),
//                    modifier = Modifier
//                        .align(
//                            BottomCenter
//                        )
//                        .padding(bottom = 70.dp)
//                        .graphicsLayer {
//
//                            val pageOffset = state.offsetForPage(it)
//                            translationX = -(width * pageOffset)
//                        }
//                )
//
//                Text(
//                    text = "서브정보 입니다. $page",
//                    style = TextStyle(
//                        fontSize = 14.dp.textSp,
//                        color = Color(0xffffffff),
//                        fontWeight = FontWeight.W700
//                    ),
//                    modifier = Modifier
//                        .align(
//                            BottomCenter
//                        )
//                        .padding(bottom = 40.dp)
//                )
//            }
//
//
//        }
//
//        Spacer(modifier = Modifier.height(50.dp))
//
//        Row {
//            Button(onClick = {
//                scope.launch {
//                    state.animateScrollToPage(state.currentPage - 1)
//                }
//            }) {
//                Text(text = "-")
//            }
//
//            Button(onClick = {
//                scope.launch {
//                    state.animateScrollToPage(state.currentPage + 1)
//                }
//            }) {
//                Text(text = "+")
//            }
//        }
//    }
//
//}

@SuppressLint("UnnecessaryComposedModifier")
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.slidePager(page: Int, state: PagerState) = composed {
    this.graphicsLayer {
        val pageOffset = state.offsetForPage(page)
        translationX = size.width * pageOffset

        val endOffset = state.endOffsetForPage(page)

        shape = RectPath(progress = 1f - endOffset.absoluteValue)
        clip = true
    }
}

class RectPath(private val progress: Float) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(Path().apply {
            addRect(
                Rect(
                    size.width - (size.width * progress), 0f, size.width, size.height
                )
            )
        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}

@Composable
fun ScrollTest() {
    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
    val offsetY = collapsingToolbarScaffoldState.offsetY
    val progress = collapsingToolbarScaffoldState.toolbarState.progress

    var isVisibleBtn by remember { mutableStateOf(true) }

    LaunchedEffect(progress) {
        isVisibleBtn = progress > 0.9f
    }

    val tabTitles = listOf("식사", "산책")
    var tabIndex by rememberSaveable { mutableStateOf(0) }


    Box(modifier = Modifier.fillMaxSize()) {
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize(),
            state = collapsingToolbarScaffoldState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.verticalScroll(rememberScrollState()),
            toolbar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(168.dp)
                        .parallax(1f)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .road(
                            whenCollapsed = Center,
                            whenExpanded = BottomCenter
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((54 + (60 - 54) * progress).dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "12월 25일 - 12월 31일",
                            style = TextStyle(
                                fontSize = (15 + (24 - 15) * progress).dp.textSp
                            ),
                            modifier = Modifier.align(Center)
                        )

                        this@Column.AnimatedVisibility(
                            visible = isVisibleBtn,
                            modifier = Modifier.align(CenterStart),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.qwe),
                                contentDescription = "",
                                modifier = Modifier
                                    .width((15 * progress).dp)
                                    .height((30 * progress).dp)
                            )
                        }

                        this@Column.AnimatedVisibility(
                            visible = isVisibleBtn,
                            modifier = Modifier.align(CenterEnd),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.asd),
                                contentDescription = "",
                                modifier = Modifier
                                    .width((15 * progress).dp)
                                    .height((30 * progress).dp)
                            )
                        }

                    }

                    TabRow(
                        selectedTabIndex = tabIndex,
                        modifier = Modifier
                            .height(54.dp)
                            .fillMaxWidth(),
                        indicator = { tabPositions ->
                            Spacer(
                                modifier = Modifier
                                    .tabIndicatorOffset(
                                        currentTabPosition = tabPositions[tabIndex]
                                    )
                                    .height(2.dp)
                                    .background(Color(0xffff4857))
                            )
                        }
                    ) {
                        tabTitles.forEachIndexed { index, s ->
                            val isSelected = index == tabIndex
                            Tab(
                                selected = isSelected,
                                onClick = { tabIndex = index },
                                modifier = Modifier.background(Color(0xffffffff))
                            ) {
                                Text(
                                    text = s,
                                    style = TextStyle(
                                        fontSize = 15.dp.textSp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color(0xFFFF4857) else Color(
                                            0xff111111
                                        ),
                                        fontFamily = Font.nanumSquareRoundFont
                                    )
                                )
                            }
                        }
                    }
                }

            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())

            ) {
                repeat(20) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                    ) {
                        Text(
                            text = it.toString(),
                            style = TextStyle(
                                fontSize = 24.dp.textSp
                            ),
                            modifier = Modifier.align(Center)
                        )
                    }
                }
            }


        }

    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        IconButton({
            Log.d("qweqwe", "click")
        }, modifier = Modifier.size(54.dp)) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow_svg),
                contentDescription = "back"
            )

        }
    }
}

data class MonthlyCount(
    val month: Int,
    val count: Int
)

data class BarData(
    val index: Int,
    val top: Float,
    val bottom: Float,
    val left: Float,
    val right: Float
)

@Composable
fun BarGraph(
    modifier: Modifier,
    valueList: List<Int>,
    selectIndex: Int,
    monthlyCount: List<MonthlyCount>,
    textColor: Int = android.graphics.Color.parseColor("#FF777777"),
    lineTextSize: Int = 12,
    onClick: (Int) -> Unit
) {
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = textColor
            textAlign = Paint.Align.CENTER
            textSize = density.run { lineTextSize.dp.toPx() }
        }
    }

    var viewHeight by remember { mutableStateOf(0.dp) }
    var clickOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    Box(
        modifier = modifier.then(
            Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
        ),
        contentAlignment = Center
    ) {
        Canvas(
            modifier = modifier
                .height(viewHeight)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            clickOffset = it
                        }
                    )
                }
        ) {
            val barWidth = (size.width - (7 * (valueList.size - 1)).dp.toPx()) / valueList.size
            val barList = mutableListOf<BarData>()

            val yBottom = (barWidth * 4.5).toFloat()
            val barHalfWidth = (barWidth / 2)
            val yTextBottom = yBottom + 21.dp.toPx() + barHalfWidth

            valueList.forEachIndexed { index, value ->
                val xCenter = ((barWidth + 7.dp.toPx()) * index) + (barWidth / 2)

                drawCircle(
                    color = Color(if (selectIndex == index) 0xffff4857 else 0xffececec),
                    radius = barWidth / 2,
                    center = Offset(xCenter, yBottom)
                )

                barList.add(
                    BarData(
                        index,
                        yBottom - barHalfWidth,
                        yBottom + barHalfWidth,
                        xCenter - barHalfWidth,
                        xCenter + barHalfWidth
                    )
                )

                if (value != 0) {
                    drawRect(
                        color = Color(if (selectIndex == index) 0xffff4857 else 0xffececec),
                        topLeft = Offset(
                            xCenter - (barWidth / 2),
                            (yBottom - (barHalfWidth * value.barGraphMaxValue()))
                        ),
                        size = Size(
                            barWidth,
                            yBottom - (yBottom - (barHalfWidth * value.barGraphMaxValue()))
                        )
                    )

                    drawCircle(
                        color = Color(if (selectIndex == index) 0xffff4857 else 0xffececec),
                        radius = barWidth / 2,
                        center = Offset(
                            xCenter,
                            (yBottom - (barHalfWidth * value.barGraphMaxValue()))
                        )
                    )
                }

                barList[index] = barList[index].copy(
                    top = ((yBottom - (barHalfWidth * value.barGraphMaxValue())) - barHalfWidth)
                )
            }

            var index = 0
            monthlyCount.forEach {
                drawContext.canvas.nativeCanvas.drawText(
                    "${it.month}월",
                    ((barWidth + 7.dp.toPx()) * index) + barHalfWidth,
                    yTextBottom,
                    textPaint
                )
                index += it.count
            }

            if (clickOffset.x != 0f) {
                for (i in barList) {
                    if (clickOffset.x < i.right && clickOffset.x > i.left && clickOffset.y > i.top && clickOffset.y < i.bottom) {
                        clickOffset = Offset(0f, 0f)
                        onClick(i.index)
                        break
                    }
                }
            }

            viewHeight = yTextBottom.toDp()
        }
    }
}

fun Int.barGraphMaxValue() = if (this > 7) 7 else this

@Composable
fun baseTextPaint(
    lineTextSize: Int,
    textColor: Int,
    align: Paint.Align,
    density: Density,
    context: Context,
    typeFace: Int
) = remember(density) {
    Paint().apply {
        color = textColor
        textAlign = align
        textSize = density.run { lineTextSize.dp.toPx() }
        isAntiAlias = true
        Typeface.createFromAsset(context.resources.assets, "nanum_square_round.ttf").run {
            typeface = Typeface.create(this, typeFace)
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun Graph(
    modifier: Modifier,
    xSize: Int,
    ySize: Int,
    points: List<GraphPoint>,
    baseLineColor: Color = Color(0xFFF1F1F1),
    lineColor: Color = Color(0xFFFF4857),
    dragLineColor: Color = Color(0xFFFF8B95),
    textColor: Int = android.graphics.Color.parseColor("#FF777777"),
    lineTextSize: Int = 11,
    enableVibrator: Boolean = true,
    isVisibleXClickLabel: Boolean = false,
    onVerticalScroll: ((Float) -> Unit)? = null
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dpToPixels(context)

    val isEmptyList = points.find { it.y != null } == null

    val xTextPaint = baseTextPaint(
        lineTextSize,
        textColor,
        Paint.Align.CENTER,
        density,
        context,
        Typeface.NORMAL
    )
    val xTodayTextPaint = baseTextPaint(
        lineTextSize,
        android.graphics.Color.parseColor("#FFFF4857"),
        Paint.Align.CENTER,
        density,
        context,
        Typeface.BOLD
    )
    val yTextPaint =
        baseTextPaint(lineTextSize, textColor, Paint.Align.LEFT, density, context, Typeface.NORMAL)
    val emptyTextPaint = baseTextPaint(
        13,
        android.graphics.Color.parseColor("#FF999999"),
        Paint.Align.CENTER,
        density,
        context,
        Typeface.NORMAL
    )

    var clickBarOffsetX by remember { mutableStateOf(0f) }
    var selectPoint by remember {
        mutableStateOf(SelectPoint())
    }

    val maxValue = points.maxOfOrNull { it.y ?: 0 } ?: 0
    val minValue = points.filter { it.y != null }.minOfOrNull { it.y ?: 0 } ?: 0

    val maxYValue =
        if (maxValue.getCeil() == minValue.getFloor()) maxValue * 2 else maxValue.getCeil()
    val minYValue = if (maxValue.getCeil() == minValue.getFloor()) 0 else minValue.getFloor()

    val yRange = maxYValue - minYValue

    val yValueList = mutableListOf<Int>()

    for (i in 0 until ySize) {
        val space = yRange.toFloat() / (ySize - 1)
        yValueList.add((minYValue + (space * i)).toInt())
    }

    LaunchedEffect(selectPoint) {
        if (selectPoint.x != 0f && enableVibrator) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                (context.getSystemService(ComponentActivity.VIBRATOR_MANAGER_SERVICE) as VibratorManager).run {
                    defaultVibrator.vibrate(
                        VibrationEffect.createOneShot(10, 70)
                    )
                }
            } else {
                val vibrator =
                    context.getSystemService(ComponentActivity.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(10)
            }
        }
    }

    Box(
        modifier = modifier
            .height(198.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        contentAlignment = Center
    ) {
        Text(
            text = "kcal",
            modifier = Modifier
                .align(if (isEmptyList) BottomStart else TopStart)
                .padding(bottom = if (isEmptyList) 40.dp else 0.dp),
            style = TextStyle(
                fontSize = 11.dp.textSp,
                fontWeight = FontWeight.W400,
                color = Color(0xff777777),
                fontFamily = Font.nanumSquareRoundFont
            )
        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .align(BottomCenter)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            clickBarOffsetX = when {
                                it.x < (START_PADDING + POINT_PADDING).dp.toPx() -> 0f
                                it.x > width - 54.dp.toPx() -> 0f
                                else -> it.x
                            }
                        },
                        onTap = {
                            clickBarOffsetX = 0f
                            selectPoint = SelectPoint()
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            clickBarOffsetX = 0f
                            selectPoint = SelectPoint()
                        },
                        onDragCancel = {
                            clickBarOffsetX = 0f
                            selectPoint = SelectPoint()
                        },
                        onDrag = { change, dragAmount ->
                            clickBarOffsetX = when {
                                change.position.x < (START_PADDING + POINT_PADDING).dp.toPx() -> (START_PADDING + POINT_PADDING).dp.toPx()
                                change.position.x > width - 54.dp.toPx() -> width - 54.dp.toPx()
                                else -> change.position.x
                            }
                            onVerticalScroll?.invoke((dragAmount.y))
                        }
                    )
                }
        ) {
            val xAxisSpace =
                (size.width - START_PADDING.dp.toPx() - (POINT_PADDING * 2).dp.toPx()) / (points.size - 1)
            val yAxisSpace = (size.height - BOTTOM_PADDING.dp.toPx()) / (ySize - 1)

            val valueToPx = (size.height - BOTTOM_PADDING.dp.toPx()) / yRange

            val xValueSpace = ceil(points.size.toFloat() / xSize).toInt()


            for (i in points.indices step xValueSpace) {
                val isToday =
                    SimpleDateFormat("MM-dd").format(System.currentTimeMillis()) == points[i].x
                drawContext.canvas.nativeCanvas.drawText(
                    if (isToday) "오늘" else points[i].x,
                    (xAxisSpace * i) + START_PADDING.dp.toPx() + POINT_PADDING.dp.toPx(),
                    size.height,
                    if (isToday) xTodayTextPaint else xTextPaint
                )
            }

            for (i in yValueList.indices) {
                drawContext.canvas.nativeCanvas.drawText(
                    yValueList[i].decimal(),
                    0f,
                    ((size.height - BOTTOM_PADDING.dp.toPx()) - yAxisSpace * (i)) - ((yTextPaint.descent() + yTextPaint.ascent()) / 2),
                    yTextPaint
                )

                drawLine(
                    start = Offset(
                        x = START_PADDING.dp.toPx(),
                        y = (size.height - BOTTOM_PADDING.dp.toPx()) - yAxisSpace * (i)
                    ),
                    end = Offset(
                        x = size.width,
                        y = (size.height - BOTTOM_PADDING.dp.toPx()) - yAxisSpace * (i)
                    ),
                    color = baseLineColor,
                    strokeWidth = 1.dp.toPx()
                )

                if (isEmptyList) break
            }

            if (isEmptyList) {
                drawContext.canvas.nativeCanvas.drawText(
                    "식사를 기록해 주세요.",
                    size.width / 2,
                    size.height - 105.dp.toPx(),
                    emptyTextPaint
                )
            } else {
                if (clickBarOffsetX != 0f) {
                    drawLine(
                        color = dragLineColor,
                        start = Offset(
                            clickBarOffsetX,
                            0f
                        ),
                        end = Offset(
                            clickBarOffsetX,
                            size.height - BOTTOM_PADDING.dp.toPx()
                        ),
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(
                                2.dp.toPx(),
                                2.dp.toPx()
                            ), phase = 2.dp.toPx()
                        ),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                val pointList = mutableListOf<SelectPoint>()

                Path().run {
                    reset()
                    for (i in points.indices) {
                        points[i].y?.let { yValue ->
                            val x =
                                (xAxisSpace * points[i].index) + START_PADDING.dp.toPx() + POINT_PADDING.dp.toPx()
                            val y =
                                ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (yValue - minYValue)))

                            if (i == 0) moveTo(x, y)
                            else {
                                if (points[i - 1].y == null) {
                                    lineTo(x, y)
                                    drawDottedPath(this, lineColor)
                                    reset()
                                    moveTo(x, y)
                                } else {
                                    lineTo(x, y)
                                    if (i == points.lastIndex) {
                                        drawNormalPath(this, lineColor)
                                    }
                                }
                            }
                            pointList.add(
                                SelectPoint(
                                    x,
                                    y,
                                    yValue,
                                    points[i].x,
                                    i == 0 || i == points.lastIndex
                                )
                            )
                        } ?: run {
                            when (i) {
                                0 -> {
                                    points.first { it.y != null }.y?.let { firstYValue ->
                                        moveTo(
                                            (xAxisSpace * points[i].index) + START_PADDING.dp.toPx() + POINT_PADDING.dp.toPx(),
                                            ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (firstYValue - minYValue)))
                                        )
                                    }
                                }

                                points.lastIndex -> {
                                    points.last { it.y != null }.y?.let { lastYValue ->
                                        lineTo(
                                            (xAxisSpace * points[i].index) + START_PADDING.dp.toPx() + POINT_PADDING.dp.toPx(),
                                            ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (lastYValue - minYValue)))
                                        )

                                        drawDottedPath(this, lineColor)
                                        reset()
                                    }
                                }

                                else -> {
                                    points[i - 1].y?.let { preYValue ->
                                        val preX =
                                            (xAxisSpace * points[i - 1].index) + START_PADDING.dp.toPx() + POINT_PADDING.dp.toPx()
                                        val preY =
                                            ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (preYValue - minYValue)))

                                        drawNormalPath(this, lineColor)
                                        reset()
                                        moveTo(preX, preY)
                                    }
                                }
                            }
                        }
                    }
                }

                for (point in pointList) {
                    drawPoint(point, lineColor, 5.dp, 3.dp)
                }

                if (clickBarOffsetX != 0f) {
                    for (i in pointList.indices) {
                        if (clickBarOffsetX > pointList[i].x - (xAxisSpace / 2) && clickBarOffsetX < pointList[i].x + (xAxisSpace / 2)) {
                            selectPoint = pointList[i]
                            drawPoint(pointList[i], lineColor, 7.dp, 4.5.dp)
                            break
                        } else selectPoint = SelectPoint()
                    }
                }
            }
        }

        if (selectPoint.x != 0f) {
            SpeechBubble(
                selectPoint,
                this,
                context,
                isVisibleXClickLabel,
                clickBarOffsetX
            )
        }
    }
}

@Composable
fun SpeechBubble(
    selectPoint: SelectPoint,
    boxScope: BoxScope,
    context: Context,
    isVisibleXClickLabel: Boolean,
    clickBarOffsetX: Float
) {
    var layoutWidthOffset by remember { mutableStateOf(0f) }
    var layoutHeightOffset by remember { mutableStateOf(0f) }

    boxScope.run {
        DimensionSubComposeLayout(
            modifier = Modifier.align(TopStart),
            mainContent = {
                Column(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (selectPoint.x - layoutWidthOffset).roundToInt(),
                                (selectPoint.y - layoutHeightOffset + 28.dp.toPx()).roundToInt()
                            )
                        }
                ) {
                    TextBox(
                        if (selectPoint.isBothEnds) "${selectPoint.value.decimal()}\nkcal"
                        else "${selectPoint.value.decimal()}kcal"
                    )

                    Image(
                        painter = painterResource(id = R.drawable.polygon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(8.dp)
                            .height(5.dp)
                            .align(CenterHorizontally)
                            .padding(bottom = 1.dp)
                    )
                }
            }
        ) {
            layoutWidthOffset = it.width / 2
            layoutHeightOffset = it.height + 11.dpToPixels(context)
        }

        if (isVisibleXClickLabel) {
            var xLabelWidthOffset by remember { mutableStateOf(0f) }

            DimensionSubComposeLayout(
                modifier = Modifier.align(TopStart),
                mainContent = {
                    Column(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    (clickBarOffsetX - xLabelWidthOffset).roundToInt(),
                                    188.dp.toPx().roundToInt()
                                )
                            }
                    ) {
                        TextBox(selectPoint.xLabel)
                    }
                }
            ) {
                xLabelWidthOffset = it.width / 2
            }
        }
    }

}

@Composable
fun GaugeTextBox(text: String) {
    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(100.dp),
                color = Color(0xbf111111)
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 7.dp, vertical = 4.dp)
                .align(Center),
            style = TextStyle(
                fontSize = 9.dp.textSp,
                color = Color(0xffffffff),
                fontWeight = FontWeight.W700,
                fontFamily = Font.nanumSquareRoundFont
            ),
            textAlign = TextAlign.Center,
            lineHeight = 10.dp.textSp
        )
    }
}


@Composable
fun TextBox(text: String) {
    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF222222)
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 7.dp, vertical = 6.dp)
                .align(Center),
            style = TextStyle(
                fontSize = 12.dp.textSp,
                color = Color(0xffffffff),
                fontWeight = FontWeight.W700,
                fontFamily = Font.nanumSquareRoundFont
            ),
            textAlign = TextAlign.Center
        )
    }
}


data class GraphPoint(
    val index: Int,
    val x: String,
    val y: Int?
)

data class SelectPoint(
    val x: Float = 0f,
    val y: Float = 0f,
    val value: Int = 0,
    val xLabel: String = "",
    val isBothEnds: Boolean = false
) {
    fun getOffset() = Offset(x, y)
}

fun Int.getLength() = this.toString().length

fun Int.getCeil() = when (this.getLength()) {
    1 -> 10
    2 -> (ceil(this.toFloat() / 10) * 10).toInt()
    else -> {
        val calculated = (10.0).pow(this.getLength() - 2)
        (ceil(this.toFloat() / calculated) * calculated).toInt()
    }
}

fun Int.getFloor() = when (this.getLength()) {
    1 -> 0
    2 -> (floor(this.toFloat() / 10) * 10).toInt()
    else -> {
        val calculated = (10.0).pow(this.getLength() - 2)
        (floor(this.toFloat() / calculated) * calculated).toInt()
    }
}


val Dp.textSp: TextUnit
    @Composable get() = with(LocalDensity.current) {
        this@textSp.toSp()
    }

fun Int.dpToPixels(context: Context): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
)


fun DrawScope.drawNormalPath(path: Path, lineColor: Color) {
    drawPath(
        path = path,
        color = lineColor,
        style = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
    )
}

fun DrawScope.drawDottedPath(path: Path, lineColor: Color) {
    drawPath(
        path = path,
        color = lineColor,
        style = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(4.dp.toPx(), 4.dp.toPx()),
                phase = 4.dp.toPx()
            )
        )
    )
}

fun DrawScope.drawPoint(
    point: SelectPoint,
    lineColor: Color,
    outRadius: Dp,
    inRadius: Dp
) {
    drawCircle(
        color = lineColor,
        radius = outRadius.toPx(),
        center = point.getOffset()
    )

    drawCircle(
        color = Color.White,
        radius = inRadius.toPx(),
        center = point.getOffset()
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getMonSunDay(): Pair<Long, Long> {
    val zoneId = ZoneId.of("Asia/Seoul")

    val monday = this.atStartOfDay(zoneId).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    val mondayMilliseconds = monday.toInstant().toEpochMilli()

    val sunday = this.atStartOfDay(zoneId).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
    val lastMomentOfSunday =
        ZonedDateTime.of(sunday.toLocalDate(), LocalTime.of(23, 59, 59), zoneId)
    val sundayMilliseconds = lastMomentOfSunday.toInstant().toEpochMilli()

    return mondayMilliseconds to sundayMilliseconds
}

@SuppressLint("SimpleDateFormat")
fun Pair<Long, Long>.toDateString(): String {
    val monthSdf = SimpleDateFormat("MM")
    val daySdf = SimpleDateFormat("dd")
    val startMonth = monthSdf.format(this.first).toInt()
    val startDay = daySdf.format(this.first).toInt()
    val endMonth = monthSdf.format(this.second).toInt()
    val endDay = daySdf.format(this.second).toInt()
    return "${startMonth}월 ${startDay}일 - ${endMonth}월 ${endDay}일"
}

@RequiresApi(Build.VERSION_CODES.O)
fun Pair<Long, Long>.preRange(): Pair<Long, Long> {
    val instant = Instant.ofEpochMilli((this.first - 1))
    val localDate = instant.atZone(ZoneId.of("Asia/Seoul")).toLocalDate()
    return localDate.getMonSunDay()
}

@RequiresApi(Build.VERSION_CODES.O)
fun Pair<Long, Long>.nextRange(): Pair<Long, Long> {
    val instant = Instant.ofEpochMilli((this.second + 1000))
    val localDate = instant.atZone(ZoneId.of("Asia/Seoul")).toLocalDate()
    return localDate.getMonSunDay()
}

fun Int.decimal() = DecimalFormat("#,###").format(this) ?: ""