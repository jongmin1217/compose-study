package com.example.compose_study.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.os.*
import android.util.Log
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Font
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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


    @OptIn(ExperimentalFoundationApi::class)
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val state = rememberPagerState()
            val scope = rememberCoroutineScope()
            val scrollState = rememberScrollState()

            var test by remember { mutableStateOf(false) }
            var type by remember { mutableStateOf(0) }


            Surface(modifier = Modifier.fillMaxSize()) {
                //ScrollTest()
                PagerTest()
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .verticalScroll(scrollState)
//                ) {
//                    HorizontalPager(
//                        pageCount = 12,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp)
//                            .background(Color(0xffbbbbbb)),
//                        state = state
//                    ) {
//                        Box(
//                            modifier = Modifier.fillMaxSize()
//                        ) {
//                            Text(
//                                text = "${it + 1}",
//                                modifier = Modifier.align(Center),
//                                style = TextStyle(fontSize = 20.dp.textSp)
//                            )
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(30.dp))
//
//                    BarGraph(
//                        modifier = Modifier.fillMaxWidth(),
//                        valueList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3),
//                        selectIndex = state.currentPage,
//                        monthlyCount = listOf(
//                            MonthlyCount(7, 5),
//                            MonthlyCount(8, 4),
//                            MonthlyCount(9, 3)
//                        )
//                    ) {
//                        scope.launch {
//                            state.scrollToPage(it)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(70.dp))
//
//
//                    Graph(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        xSize = if (test) 5 else 7,
//                        ySize = 3,
//                        points = if (test) {
//                            when(type){
//                                0 -> list30
//                                1 -> list30_2
//                                else -> list30_3
//                            }
//                        } else {
//                            when(type){
//                                0 -> list7
//                                1 -> list7_2
//                                else -> list7_3
//                            }
//                        },
//                        isVisibleXClickLabel = test
//                    ) {
//                        scope.launch {
//                            scrollState.scrollBy(-it)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(50.dp))
//                    Row(modifier = Modifier.height(50.dp)) {
//                        Button(onClick = { test = !test }, modifier = Modifier.height(50.dp)) {
//                            Text(text = "월/주")
//                        }
//
//                        Spacer(modifier = Modifier.width(30.dp))
//
//                        Button(onClick = { type-- }, modifier = Modifier.height(50.dp)) {
//                            Text(text = "-")
//                        }
//
//                        Button(onClick = { type++ }, modifier = Modifier.height(50.dp)) {
//                            Text(text = "+")
//                        }
//                    }
//
//                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PagerTest(){
    val scope = rememberCoroutineScope()
    val state = rememberPagerState(initialPage = 500)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            pageCount = 1000,
            state = state,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val page = it % 7
            val image = painterResource(
                id = when(page){
                    0 -> R.drawable.image_1
                    1 -> R.drawable.image_2
                    2 -> R.drawable.image_3
                    3 -> R.drawable.image_4
                    4 -> R.drawable.image_5
                    5 -> R.drawable.image_6
                    else -> R.drawable.image_7
                }
            )

            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .graphicsLayer {
                        val pageOffset = state.offsetForPage(it)
                        translationX = size.width * pageOffset

                        val endOffset = state.endOffsetForPage(it)

                        shape = RectPath(progress = 1f - endOffset.absoluteValue)
                        clip = true

                    }
                ,
                contentScale = ContentScale.Companion.Crop
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Button(onClick = {
                scope.launch {
                    state.animateScrollToPage(state.currentPage - 1)
                }
            }) {
                Text(text = "-")
            }

            Button(onClick = {
                scope.launch {
                    state.animateScrollToPage(state.currentPage + 1)
                }
            }) {
                Text(text = "+")
            }
        }
    }

}

class RectPath(private val progress: Float) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(Path().apply {
            addRect(
                Rect(
                    size.width - (size.width * progress),0f,size.width,size.height
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

    var isVisibleBtn by remember{ mutableStateOf(true) }

    LaunchedEffect(progress){
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