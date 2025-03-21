package com.example.raionthings.utils

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.raionthings.R

val sfApple = FontFamily(
    Font(R.font.blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.heavyitalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.medium, FontWeight.Medium),
)
val CustomTypography = Typography(

    bodyLarge = TextStyle(
        fontFamily = sfApple,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sfApple,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 25.sp
    ),
    labelLarge = TextStyle(
        fontFamily = sfApple,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = sfApple,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = sfApple,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = sfApple,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = sfApple,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
)
