package com.example.raionthings.presentation.ux

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CustomToast(
    message: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black.copy(alpha = 0.8f),
    textColor: Color = Color.White,
    duration: Long = 1500L,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isVisible) {
        if (isVisible) {
            delay(duration)
            isVisible = false
            onDismiss()
        }
    }

    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .then(modifier),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                color = backgroundColor,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(8.dp)
                    .wrapContentSize()
            ) {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ){
                    Icon(
                        painter = painterResource(com.example.raionthings.R.drawable.wrong),
                        contentDescription = "E39",
                        modifier = Modifier
                            .size(AssistChipDefaults.IconSize)
                            .align(Alignment.CenterVertically),
                        tint = Color.White
                    )
                    Text(
                        text = message,
                        color = textColor,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                }
        }
    }
}