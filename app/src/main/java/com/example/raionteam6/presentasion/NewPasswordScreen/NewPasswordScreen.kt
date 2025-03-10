package com.example.raionteam6.presentasion.NewPasswordScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily
import com.example.raionteam6.presentasion.theme.ui.poppinsFontFamily

@Composable
fun NewPasswordScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    var newPasswordVisibility by rememberSaveable { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)

    val icon2 = if (newPasswordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                .background(color = Color.White)

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
            Spacer(modifier = Modifier.padding(30.dp)) //enter
            Text(
                text = "Atur Ulang Kata Sandi",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 20.dp),
                fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Tetapkan kata sandi baru untuk akun Anda, sehingga Anda dapat masuk dan mengakses semua fitur",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 20.dp),
                fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Kata Sandi",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 20.dp),
                fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Kata Sandi",
                        color = Color(0xFF757575),
                        fontFamily = SFProdisplayFontFamily
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.simple_line_icons_lock),
                        contentDescription = "Icon lock",
                        modifier = Modifier.size(width = 22.dp, height = 22.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon",
                            modifier = Modifier.size(width = 24.dp, height = 24.dp)
                        )
                    }
                },

                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),

                shape = RoundedCornerShape(24.dp)
            )
            Text(
                text = "Konfirmasi Kata Sandi",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 20.dp),
                fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = {
                    Text(
                        text = "Kata sandi harus sama",
                        color = Color(0xFF757575),
                        fontFamily = SFProdisplayFontFamily
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.simple_line_icons_lock),
                        contentDescription = "Icon lock",
                        modifier = Modifier.size(width = 22.dp, height = 22.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        newPasswordVisibility = !newPasswordVisibility
                    }) {
                        Icon(
                            painter = icon2,
                            contentDescription = "Visibility Icon",
                            modifier = Modifier.size(width = 24.dp, height = 24.dp)
                        )
                    }
                },


                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),

                visualTransformation = if (newPasswordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                onClick = {navController.navigate("Success_NewPassword") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.19.dp, height = 57.74.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF632713)
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = password == newPassword
            ) {
                Text(
                    text = "Lanjut",
                    color = Color.White,
                    fontSize = 20.62.sp,
                    fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Bold
                )
            }
        }
    }
