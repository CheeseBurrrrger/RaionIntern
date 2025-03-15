package com.example.raionteam6.presentasion.SuccessNewPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily

@Composable
fun SuccesNewPassword(navController: NavController) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(R.drawable.rafiki),
            contentDescription = "Background astronout",
            modifier = Modifier.size(width = 373.dp, height = 294.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Kata Sandi Berhasil Diubah",
            fontSize = 25.sp,
            fontFamily = SFProdisplayFontFamily,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(19.dp))

        Text(
            text = "Kata sandi Anda telah berhasil diperbarui",
            fontSize = 16.sp,
            fontFamily = SFProdisplayFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF5B5B5B)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {navController.navigate("Login_Screen")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .size(width = 372.19.dp, height = 57.74.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC63433)
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Kembali Ke Login",
                color = Color.White,
                fontSize = 20.62.sp,
                fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Bold
            )
        }
    }
}