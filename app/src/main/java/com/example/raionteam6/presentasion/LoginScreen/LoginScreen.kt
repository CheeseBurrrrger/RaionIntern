package com.example.raionteam6.presentasion.LoginScreen

import android.icu.text.ListFormatter.Width
import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.raionteam6.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun LoginScreen(navController: NavController) {
    var Email_Username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val PoppinsBold = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_black, FontWeight.Normal)
    )
    val Poppins = FontFamily(
        Font(R.font.poppins_medium, FontWeight.Bold),
    )
    val icon = if(passwordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)
    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
            .background(color = Color.White)
    ){
        Spacer(modifier = Modifier.padding(100.dp))
        Text(
            text = "Login",
            color = Color.Black,
            fontSize = 31.96.sp,
            fontFamily = PoppinsBold,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)

        )

        Column (
            modifier = Modifier.fillMaxWidth().fillMaxHeight()

        ){
            //Username container
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Email atau Username",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            OutlinedTextField(
                value = Email_Username,
                onValueChange = {Email_Username = it},
                placeholder = {Text(text = "Enter the email" , color = Color(0xFF0078D7) , fontFamily = Poppins)},

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,

                shape = RoundedCornerShape(24.dp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Password",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            //Password container
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                placeholder = {Text(text = "Enter the Password" , color = Color(0xFF0078D7), fontFamily = Poppins)},
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
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier.fillMaxWidth().padding( horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Sign In",
                        fontSize = 14.sp,
                        fontFamily = Poppins,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 12.dp)
                            .clickable {navController.navigate("Register_Screen") },
                        textAlign = TextAlign.Start

                    )
                    Text(
                        text = "Forget Password ?",
                        fontSize = 14.sp,
                        fontFamily = Poppins,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .clickable {navController.navigate("Forget_Pass") },
                        textAlign = TextAlign.End

                    )
                }
                Button(
                    onClick = {
                        navController.navigate("Register_Screen")
                    },
                    modifier = Modifier
                        .size(width = 360.dp, height = 90.dp)
                        .padding(vertical = 12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF0078D7)),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text(
                        text = "Login",
                        fontFamily = PoppinsBold,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.line_6),
                    contentDescription = "line",
                    modifier = Modifier.padding(8.dp),


                )

                Text(
                    text = "atau dengan",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.line_6),
                    contentDescription = "line",
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook_logo",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .clickable {  },
                    )
                Spacer(modifier = Modifier.padding(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google_logo",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .clickable {  },
                )
            }
        }
    }
}
