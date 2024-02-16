package com.example.compose_study.main

import android.annotation.SuppressLint
import android.view.Gravity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose_study.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

fun customSnackBarHost(
    gravity: Int
): @Composable (SnackbarHostState) -> Unit =
    { snackBarHostState: SnackbarHostState ->
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = when (gravity) {
                Gravity.CENTER -> Modifier.fillMaxSize()
                Gravity.BOTTOM -> Modifier
                else -> Modifier.fillMaxSize()
            }
        ) { snackBarData ->

            Box(
                modifier = when (gravity) {
                    Gravity.CENTER -> Modifier.fillMaxSize()
                    Gravity.BOTTOM -> Modifier
                    else -> Modifier.fillMaxSize()
                }
            ) {
                Card(
                    shape = RoundedCornerShape(100.dp),
                    backgroundColor = Color(0xbf000000),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(
                            bottom = when (gravity) {
                                Gravity.CENTER -> 0.dp
                                Gravity.BOTTOM -> 72.dp
                                else -> 0.dp
                            }
                        )
                        .align(
                            when (gravity) {
                                Gravity.CENTER -> Alignment.Center
                                Gravity.BOTTOM -> Alignment.BottomCenter
                                else -> Alignment.Center
                            }
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = snackBarData.message,
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 13.dp.textSp,
                                color = Color(0xffffffff),
                                fontWeight = FontWeight.W700,
                                fontFamily = FontFamily(
                                    Font(R.font.nanum_square_round)
                                ),
                                lineHeight = 20.dp.textSp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            LaunchedEffect(snackBarData) {
                delay(
                    when (gravity) {
                        Gravity.CENTER -> 1000
                        Gravity.BOTTOM -> 2000
                        else -> 1000
                    }
                )
                snackBarData.dismiss()
            }
        }
    }

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun snackBarScaffold(
    gravity: Int = Gravity.CENTER,
    content : @Composable ()-> Unit
) : Channel<String> {
    val snackBarHostState = remember { SnackbarHostState() }
    val channel = remember { Channel<String>(Channel.CONFLATED) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(channel){
        channel.receiveAsFlow().collect{
            snackBarHostState.currentSnackbarData?.dismiss()

            scope.launch {
                snackBarHostState.showSnackbar(
                    message = it,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Scaffold(
            scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState),
            snackbarHost = customSnackBarHost(gravity)
        ) {
            content()
        }
    }

    return channel
}

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}

