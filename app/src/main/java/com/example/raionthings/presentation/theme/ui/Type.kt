package com.example.raionthings.presentation.theme.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.raionthings.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_light, FontWeight.Light)
)
val SFProdisplayFontFamily = FontFamily(
    Font(R.font.sfprodisplaybold, FontWeight.Bold),
    Font(R.font.sfprodisplaymedium, FontWeight.Medium),
    Font(R.font.sfprodisplayregular, FontWeight.Light)
)
