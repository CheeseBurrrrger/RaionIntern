package com.example.raionteam6.presentasion.NewPasswordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.poppinsFontFamily

@Composable
fun NewPasswordScreen() {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start

        ) {
            Spacer(modifier = Modifier.padding(100.dp))//Spacer
            Text(
                text = "Reset Password",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Set a new password for your account so you can log in and access all features",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 24.dp),
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = "Current Password",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Enter the current password",
                        color = Color(0xFF0078D7),
                        fontFamily = poppinsFontFamily
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
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),

                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "New Password",
                fontSize = 14.sp,
                color = Color.DarkGray,
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = {
                    Text(
                        text = "Minimum 8 characters",
                        color = Color(0xFF0078D7),
                        fontFamily = poppinsFontFamily
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 372.19.dp, height = 57.74.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0078D7)
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 20.62.sp,
                    fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewNewPass(){
    NewPasswordScreen()
}