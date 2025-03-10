package com.example.raionteam6.presentasion.ForgetPassword


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPASS(navController: NavController) {
    var emailText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.padding(100.dp))//Spacer
            //Forget Password
            Text(
                text = "Forget Password",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Enter your email for verification. We will send a 4-digit code to your email",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 24.dp),
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
            )

            Text(
                text = "Email",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
            )

            //Email Verif Container
            OutlinedTextField(
                value = emailText,
                onValueChange = { emailText = it },
                placeholder = { Text("Enter the email", color = Color(0xFF0078D7)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.LightGray
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {navController.navigate("Pin_Password") },
                enabled = emailText.contains("@gmail.com"),
                modifier = Modifier

                    .fillMaxWidth()
                    .size(width = 371.53.dp, height = 57.74.dp),
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


