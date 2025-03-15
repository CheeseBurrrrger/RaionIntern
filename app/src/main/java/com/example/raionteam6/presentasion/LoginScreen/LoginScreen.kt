package com.example.raionteam6.presentasion.LoginScreen

import androidx.compose.foundation.*
import android.icu.text.ListFormatter.Width
import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily


@Composable
fun LoginScreen(navController: NavController) {
    var Email_Username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    val icon = if(passwordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)
    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
            .background(color = Color.White)
    ){
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
            text = "Login",
            color = Color.Black,
            fontSize = 31.96.sp,
            fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)

        )

        Column (
            modifier = Modifier.fillMaxWidth().fillMaxHeight()

        ){
            //Username container
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Email",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = Email_Username,
                onValueChange = {Email_Username = it},
                placeholder = {Text(
                    text = "E-Mail",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.quill_mail),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(width = 22.dp, height = 22.dp),
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.dp, height = 56.dp)
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
                text = "Kata Sandi",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            //Password container
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                placeholder = {Text(
                    text = "Kata Sandi",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
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
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.simple_line_icons_lock),
                        contentDescription = "Lock Icon",
                        modifier = Modifier.size(width = 22.dp, height = 22.dp),
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),

                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier.fillMaxWidth().padding( horizontal = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = "Lupa Kata Sandi ?",
                        fontSize = 14.sp,
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF632713),
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 12.dp)
                            .clickable {navController.navigate("Forget_Pass") },
                        textAlign = TextAlign.End

                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        navController.navigate("Beranda_Screen")
                    },
                    modifier = Modifier
                        .size(width = 372.dp, height = 56.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Login",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Belum mempunyai akun? ",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "Daftar",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color(255 ,165, 0),
                        modifier = Modifier.clickable {
                            navController.navigate("Register_Screen")
                        }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = R.drawable.line_6),
                    contentDescription = "line",
                    modifier = Modifier.padding(8.dp),

                )

                Text(
                    text = "Atau dengan",
                    fontFamily = SFProdisplayFontFamily,
                    fontSize = 14.sp
                )

                Image(
                    painter = painterResource(id = R.drawable.line_6),
                    contentDescription = "line",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {},
                    modifier = Modifier
                        .size(width = 372.dp, height = 56.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFE3E3E3)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_google_nobg),
                            contentDescription = "Logo Google",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sign Up with Google",
                            fontFamily = SFProdisplayFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
