package com.example.raionteam6.presentasion.Pinpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily
import com.example.raionteam6.presentasion.theme.ui.poppinsFontFamily

@Composable
fun Pinpassword(
    navController: NavController
) {
    var verificationCode by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.sajigo),
                    contentDescription = "SajigoLogo",
                    modifier = Modifier.size(width = 209.dp, height = 120.dp)
                )
            }
            Spacer(modifier = Modifier.padding(30.dp))
            Text(
                text = "Masukan Kode 4 Digit",
                fontSize = 32.sp,
                fontFamily = SFProdisplayFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Masukkan kode 4 digit yang Anda terima di email Anda",
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            BasicTextField(
                value = TextFieldValue(
                    text = verificationCode,
                    selection = TextRange(verificationCode.length)
                ),
                onValueChange = {
                    if (it.text.length <= 4 && it.text.all { char -> char.isDigit() }) {
                        verificationCode = it.text
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                decorationBox = { innerTextField ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for (i in 0 until 4) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(50.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            ) {
                                Text(
                                    text = if (i < verificationCode.length) verificationCode[i].toString() else "",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    }

                    // Hidden actual text field
                    Box(modifier = Modifier.size(0.dp)) {
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {navController.navigate("New_Password")},
                enabled = verificationCode.length == 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 372.19.dp, height = 57.74.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC63433),
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Lanjut",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = poppinsFontFamily
                )
            }
        }
}

