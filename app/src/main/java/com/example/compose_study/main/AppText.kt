package com.example.compose_study.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    val size = if(fontSize == TextUnit.Unspecified){
        if(style.fontSize == TextUnit.Unspecified){
            16f
        }else style.fontSize.value
    }else fontSize.value

    val height = if(lineHeight == TextUnit.Unspecified){
        if(style.lineHeight == TextUnit.Unspecified){
            size
        }else style.lineHeight.value
    }else lineHeight.value

    val baselineModifier = Modifier.padding(
        vertical = ((height.toDp - size.toDp)/2).dp
    )

    Text(
        text = text,
        modifier = modifier.then(baselineModifier),
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

@Composable
fun AppText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    val size = if(fontSize == TextUnit.Unspecified){
        if(style.fontSize == TextUnit.Unspecified){
            16f
        }else style.fontSize.value
    }else fontSize.value

    val height = if(lineHeight == TextUnit.Unspecified){
        if(style.lineHeight == TextUnit.Unspecified){
            size
        }else style.lineHeight.value
    }else lineHeight.value

    val baselineModifier = Modifier.padding(
        vertical = ((height - size)/2).dp
    )

    Text(
        text = text,
        modifier = modifier.then(baselineModifier),
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

val Float.toDp : Int
    @Composable get() = with(LocalContext.current){
        val scaledDensity = this.resources.displayMetrics.scaledDensity
        val density = this.resources.displayMetrics.density
        ((this@toDp * scaledDensity)/density).toInt()
    }

@Composable
fun CenterText(text : String, textStyle: TextStyle, modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        androidx.compose.material.Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            style = textStyle
        )
    }
}

@Composable
fun CenterText(text : AnnotatedString, textStyle: TextStyle, modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        androidx.compose.material.Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            style = textStyle
        )
    }
}

enum class ColorTextType{
    Single,Multi
}

@Composable
fun ColoredText(
    modifier: Modifier = Modifier,
    text: String,
    targetText : String,
    targetType : ColorTextType = ColorTextType.Single,
    targetColor : Color = Color(0xffff4857),
    targetWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    val annotatedString = buildAnnotatedString {
        append(text)

        if(targetType == ColorTextType.Single){
            val startIndex = text.lowercase().indexOf(targetText.lowercase())
            if (startIndex != -1) {
                val endIndex = startIndex + targetText.length
                addStyle(
                    style = SpanStyle(
                        color = targetColor,
                        fontWeight = targetWeight
                    ),
                    start = startIndex,
                    end = endIndex
                )
            }
        }else{
            findAllIndices(text.lowercase(),targetText.lowercase()).run {
                if(this.isNotEmpty()){
                    for(i in this){
                        val endIndex = i + targetText.length
                        addStyle(
                            style = SpanStyle(
                                color = targetColor
                            ),
                            start = i,
                            end = endIndex
                        )
                    }
                }
            }
        }
    }

    AppText(
        text = annotatedString,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

fun findAllIndices(mainString: String, subString: String): List<Int> {
    var currentIndex = 0
    val indices = mutableListOf<Int>()

    while (currentIndex < mainString.length) {
        val index = mainString.indexOf(subString, currentIndex)
        if (index == -1) {
            break
        }
        indices.add(index)
        currentIndex = index + 1
    }

    return indices
}