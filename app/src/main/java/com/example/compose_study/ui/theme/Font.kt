package com.example.compose_study.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.compose_study.R

object Font {

    val nanumSquareRoundFont = FontFamily(
        Font(
            R.font.nanum_square_round,
            FontWeight.Normal,
            FontStyle.Normal
        ),
        Font(
            R.font.nanum_square_round_b,
            FontWeight.Bold,
            FontStyle.Normal
        ),
        Font(R.font.nanum_square_round_e_b, FontWeight.ExtraBold, FontStyle.Normal)
    )
}