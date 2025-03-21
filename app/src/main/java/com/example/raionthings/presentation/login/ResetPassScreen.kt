package com.example.raionthings.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionthings.R
import com.example.raionthings.presentation.navigation.newpassscreen
import com.example.raionthings.presentation.navigation.successchangepasssign
import com.example.raionthings.presentation.theme.ui.SFProdisplayFontFamily
import com.example.raionthings.presentation.theme.ui.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    navController: NavController
) {
    var emailText by remember { mutableStateOf("") }
    val context = LocalContext.current
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
        Spacer(modifier = Modifier.padding(30.dp)) //Spacer
        //Forget Password
        Text(
            text = "Lupa Kata Sandi",
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 20.dp),
            fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "Masukkan email Anda untuk proses verifikasi, kami akan mengirimkan kode 4 digit ke email Anda",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(horizontal = 20.dp),
            fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "Email",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 20.dp),
            fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
        )

        //Email Verif Container
        OutlinedTextField(
            value = emailText,
            onValueChange = { emailText = it },
            placeholder = { Text("E-mail", color = Color(0xFF757575)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.quill_mail),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(width = 22.dp, height = 22.dp)
                )
            },
            shape = RoundedCornerShape(24.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.LightGray
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {
                if (emailText.isNotEmpty()){
                    EmailAuthUIClient().resetPassword(emailText,context)
                    navController.navigate(newpassscreen)

                }
                else{
                Toast.makeText(
                    context,
                    "why u let the email field blank huh?",
                    Toast.LENGTH_SHORT
                ).show()
            }
                      },
            enabled = emailText.contains("@gmail.com"),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth()
                .size(width = 371.53.dp, height = 57.74.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC63433)
            ),
            shape = RoundedCornerShape(24.dp)
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

    val checkPassword = password.isNotBlank() && newPassword.isNotBlank()
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
            onClick = {navController.navigate(successchangepasssign) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .size(width = 372.19.dp, height = 57.74.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC63433)
            ),
            shape = RoundedCornerShape(24.dp),
            enabled = password == newPassword && checkPassword
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