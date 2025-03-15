package com.example.raionteam6.presentasion.ForgetPassword


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily
import com.example.raionteam6.presentasion.theme.ui.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPASS(navController: NavController) {
    var emailText by remember { mutableStateOf("") }

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
                onClick = {navController.navigate("Pin_Password") },
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


