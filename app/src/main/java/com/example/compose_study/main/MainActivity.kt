package com.example.compose_study.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.*
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.compose_study.R
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt


const val START_PADDING = 46
const val BOTTOM_PADDING = 17

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
        GraphPoint(0, "09-01", -1),
        GraphPoint(1, "09-02", 234),
        GraphPoint(2, "09-03", 61),
        GraphPoint(3, "09-04", 24),
        GraphPoint(4, "09-05", -1),
        GraphPoint(5, "09-06", 123),
        GraphPoint(6, "09-07", 111)
    )

    val list30_2 = listOf(
        GraphPoint(0, "09-01", -1),
        GraphPoint(1, "09-02", 37),
        GraphPoint(2, "09-03", 22),
        GraphPoint(3, "09-04", -1),
        GraphPoint(4, "09-05", -1),
        GraphPoint(5, "09-06", -1),
        GraphPoint(6, "09-07", 25),
        GraphPoint(7, "09-08", -1),
        GraphPoint(8, "09-09", 50),
        GraphPoint(9, "09-10", 70),
        GraphPoint(10, "09-11", 25),
        GraphPoint(11, "09-12", 37),
        GraphPoint(12, "09-13", -1),
        GraphPoint(13, "09-14", 24),
        GraphPoint(14, "09-15", 50),
        GraphPoint(15, "09-16", 13),
        GraphPoint(16, "09-17", 25),
        GraphPoint(17, "09-18", 24),
        GraphPoint(18, "09-19", 50),
        GraphPoint(19, "09-20", 70),
        GraphPoint(20, "09-21", 25),
        GraphPoint(21, "09-22", 37),
        GraphPoint(22, "09-23", -1),
        GraphPoint(23, "09-24", 24),
        GraphPoint(24, "09-25", 50),
        GraphPoint(25, "09-26", 51),
        GraphPoint(26, "09-27", 25),
        GraphPoint(27, "09-28", 24),
        GraphPoint(28, "09-29", -1),
        GraphPoint(29, "09-30", 24)
    )

    val list7 = listOf(
        GraphPoint(0, "09-01", 30),
        GraphPoint(1, "09-02", 37),
        GraphPoint(2, "09-03", 120),
        GraphPoint(3, "09-04", 20),
        GraphPoint(4, "09-05", 50),
        GraphPoint(5, "09-06", 65),
        GraphPoint(6, "09-07", 25)
    )

    @OptIn(ExperimentalFoundationApi::class)
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val state = rememberPagerState()
            val scope = rememberCoroutineScope()
            val scrollState = rememberScrollState()

            var test by remember { mutableStateOf(false) }
            var test2 by remember { mutableStateOf(false) }


            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    HorizontalPager(
                        pageCount = 12,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xffbbbbbb)),
                        state = state
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "${it + 1}",
                                modifier = Modifier.align(Center),
                                style = TextStyle(fontSize = 20.dp.textSp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    BarGraph(
                        modifier = Modifier.fillMaxWidth(),
                        valueList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3),
                        selectIndex = state.targetPage,
                        monthlyCount = listOf(
                            MonthlyCount(7, 5),
                            MonthlyCount(8, 4),
                            MonthlyCount(9, 3)
                        )
                    ) {
                        scope.launch {
                            state.animateScrollToPage(it)
                        }
                    }

                    Spacer(modifier = Modifier.height(70.dp))

                    Graph(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        xSize = if (test) 5 else 7,
                        ySize = 5,
                        points = if (test) {
                            if (test2) list30 else list30_2
                        } else {
                            if (test2) list7 else list7_2
                        },
                        isVisibleXClickLabel = test
                    ){
                        scope.launch {
                            scrollState.scrollBy(-it)
                        }
                    }

                    Spacer(modifier = Modifier.height(50.dp))
                    Row(modifier = Modifier.height(50.dp)) {
                        Button(onClick = { test = !test }, modifier = Modifier.height(50.dp)) {
                            Text(text = "월/주")
                        }

                        Spacer(modifier = Modifier.width(50.dp))

                        Button(onClick = { test2 = !test2 }, modifier = Modifier.height(50.dp)) {
                            Text(text = "데이터 on/off")
                        }
                    }

                }
            }
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
                    }
                }
            }

            viewHeight = yTextBottom.toDp()
        }
    }
}

fun Int.barGraphMaxValue() = if (this > 7) 7 else this

@Composable
fun Graph(
    modifier: Modifier,
    xSize: Int,
    ySize: Int,
    points: List<GraphPoint>,
    baseLineColor: Color = Color(0xFFF1F1F1),
    lineColor: Color = Color(0xFFFF4857),
    dragLineColor: Color = Color(0xFFFF8B95),
    textColor: Int = android.graphics.Color.parseColor("#FFAAAAAA"),
    lineTextSize: Int = 11,
    enableVibrator: Boolean = true,
    isVisibleXClickLabel: Boolean = false,
    onVerticalScroll : ((Float) -> Unit)? = null
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dpToPixels(context)


    val xTextPaint = remember(density) {
        Paint().apply {
            color = textColor
            textAlign = Paint.Align.CENTER
            textSize = density.run { lineTextSize.dp.toPx() }
        }
    }

    val yTextPaint = remember(density) {
        Paint().apply {
            color = textColor
            textAlign = Paint.Align.LEFT
            textSize = density.run { lineTextSize.dp.toPx() }
        }
    }

    var clickBarOffsetX by remember { mutableStateOf(0f) }
    var selectPoint by remember {
        mutableStateOf(SelectPoint())
    }

    val maxValue = points.maxOfOrNull { it.y } ?: 0
    val minValue = points.filter { it.y != -1 }.minOfOrNull { it.y } ?: 0

    val maxYValue = maxValue.getCeil()
    val minYValue = minValue.getFloor()

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
            .background(Color.White)
            .padding(horizontal = 20.dp),
        contentAlignment = Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            clickBarOffsetX = when {
                                it.x < START_PADDING.dp.toPx() -> 0f
                                it.x > width - 58.dp.toPx() -> 0f
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
                                change.position.x < START_PADDING.dp.toPx() -> START_PADDING.dp.toPx()
                                change.position.x > width - 58.dp.toPx() -> width - 58.dp.toPx()
                                else -> change.position.x
                            }
                            onVerticalScroll?.invoke((dragAmount.y))
                        }
                    )
                }
        ) {
            val xAxisSpace =
                (size.width - START_PADDING.dp.toPx() - 18.dp.toPx()) / (points.size - 1)
            val yAxisSpace = (size.height - BOTTOM_PADDING.dp.toPx()) / (ySize - 1)

            val valueToPx = (size.height - BOTTOM_PADDING.dp.toPx()) / yRange

            val xValueSpace = ceil(points.size.toFloat() / xSize).toInt()


            for (i in points.indices step xValueSpace) {
                drawContext.canvas.nativeCanvas.drawText(
                    points[i].x,
                    (xAxisSpace * i) + START_PADDING.dp.toPx(),
                    size.height,
                    xTextPaint
                )

            }

            for (i in yValueList.indices) {
                drawContext.canvas.nativeCanvas.drawText(
                    yValueList[i].toString(),
                    0f,
                    ((size.height - BOTTOM_PADDING.dp.toPx()) - yAxisSpace * (i)) - ((yTextPaint.descent() + yTextPaint.ascent()) / 2),
                    yTextPaint
                )

                drawLine(
                    start = Offset(
                        x = START_PADDING.dp.toPx() - 11.dp.toPx(),
                        y = (size.height - BOTTOM_PADDING.dp.toPx()) - yAxisSpace * (i)
                    ),
                    end = Offset(
                        x = size.width,
                        y = (size.height - BOTTOM_PADDING.dp.toPx()) - yAxisSpace * (i)
                    ),
                    color = baseLineColor,
                    strokeWidth = 1.dp.toPx()
                )

            }

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
                    if (points[i].y != -1) {
                        val x = (xAxisSpace * points[i].index) + START_PADDING.dp.toPx()
                        val y =
                            ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (points[i].y - minYValue)))

                        if (i == 0) moveTo(x, y)
                        else {
                            if (points[i - 1].y == -1) {
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
                        pointList.add(SelectPoint(x, y, points[i].y, points[i].x))
                    } else {
                        when (i) {
                            0 -> {
                                moveTo(
                                    (xAxisSpace * points[i].index) + START_PADDING.dp.toPx(),
                                    ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (points.first { it.y != -1 }.y - minYValue)))
                                )
                            }

                            points.lastIndex -> {
                                lineTo(
                                    (xAxisSpace * points[i].index) + START_PADDING.dp.toPx(),
                                    ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (points.last { it.y != -1 }.y - minYValue)))
                                )

                                drawDottedPath(this, lineColor)
                                reset()
                            }

                            else -> {
                                if (points[i - 1].y != -1) {
                                    val preX =
                                        (xAxisSpace * points[i - 1].index) + START_PADDING.dp.toPx()
                                    val preY =
                                        ((size.height - BOTTOM_PADDING.dp.toPx()) - (valueToPx * (points[i - 1].y - minYValue)))

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

        if (selectPoint.x != 0f) {

            var layoutWidthOffset by remember { mutableStateOf(0f) }
            var layoutHeightOffset by remember { mutableStateOf(0f) }


            DimensionSubComposeLayout(
                modifier = Modifier.align(TopStart),
                mainContent = {
                    Column(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    (selectPoint.x - layoutWidthOffset).roundToInt(),
                                    (selectPoint.y - layoutHeightOffset).roundToInt()
                                )
                            }
                    ) {
                        TextBox(
                            if (selectPoint.value > 999) "${selectPoint.value.decimal()}\nkcal"
                            else "${selectPoint.value}kcal"
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
                .padding(horizontal = 6.dp, vertical = 5.dp)
                .align(Center),
            style = TextStyle(
                fontSize = 11.dp.textSp,
                color = Color(0xffffffff),
                fontWeight = FontWeight.W700
            ),
            textAlign = TextAlign.Center
        )
    }
}


data class GraphPoint(
    val index: Int,
    val x: String,
    val y: Int
)

data class SelectPoint(
    val x: Float = 0f,
    val y: Float = 0f,
    val value: Int = 0,
    val xLabel: String = ""
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

fun Int.decimal() = DecimalFormat("#,###").format(this) ?: ""


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