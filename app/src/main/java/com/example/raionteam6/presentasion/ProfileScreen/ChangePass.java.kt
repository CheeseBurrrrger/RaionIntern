package com.example.raionteam6.presentasion.ProfileScreen

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily

@Composable
fun ChangePass(modifier: Modifier = Modifier) {

    var Email by remember { mutableStateOf("") }
    var Kata_sandi by remember { mutableStateOf("") }
    var Kata_sandi_baru by remember { mutableStateOf("") }

    var checkPass = Kata_sandi == Kata_sandi_baru
    Column (
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 12.dp)
            .padding(vertical = 45.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_asset),
                contentDescription = "back arrow",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.align(CenterVertically),
                text = "Ganti Password",
                fontFamily = SFProdisplayFontFamily,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Column (
            modifier = Modifier.align(CenterHorizontally),
        ) {
            Image(
                painter = painterResource(id = R.drawable.dhiyaulhaq_1),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(99.dp)
                    .clip(CircleShape)
                    .align(CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(40.dp))
            
            //E-Mail Container
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "E-Mail",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = Email,
                onValueChange = { Email = it },
                placeholder = {
                    Text(
                        text = "Masukan email",
                        color = Color(0xFF757575),
                        fontFamily = SFProdisplayFontFamily
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(width = 16.dp, height = 16.dp),
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

                shape = RoundedCornerShape(24.dp),
            )

            //Password Container
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Kata Sandi Sekarang",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = Kata_sandi,
                onValueChange = { Kata_sandi = it },
                placeholder = {
                    Text(
                        text = "Masukan kata sandi sekarang",
                        color = Color(0xFF757575),
                        fontFamily = SFProdisplayFontFamily
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(width = 16.dp, height = 16.dp),
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

                shape = RoundedCornerShape(24.dp),
            )

            //New Password Container
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Kata Sandi Baru",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = Kata_sandi_baru,
                onValueChange = { Kata_sandi_baru = it },
                placeholder = {
                    Text(
                        text = "Minimal 8 Karakter",
                        color = Color(0xFF757575),
                        fontFamily = SFProdisplayFontFamily
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(width = 16.dp, height = 16.dp),
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

                shape = RoundedCornerShape(24.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Button
            Button(
                onClick = {
                },
                modifier = Modifier
                    .size(width = 372.dp, height = 56.dp)
                    .padding(horizontal = 12.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
                shape = RoundedCornerShape(24.dp),
                enabled = checkPass && Kata_sandi_baru.length == 8
            ) {
                Text(
                    text = "Simpan Perubahan",
                    fontFamily = SFProdisplayFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePassPreview(){
    SuccesChangePassScreen()
}

@Composable
fun SuccesChangeProfileScreen(){
    Column (
        modifier = Modifier
            .background(color = Color(0xFFEDC0C0))
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 130.dp)

    ){
        Image(
            painter = painterResource(R.drawable.succes_change_pw_image),
            contentDescription = "girl succes change profile",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 372.dp, 394.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Profil Berhasil Diubah!",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Profil telah berhasil diperbarui",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .size(width = 372.dp, height = 56.dp)
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(
                text = "Kembali ke profil",
                fontFamily = SFProdisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}
@Composable
fun SuccesChangePassScreen(){
    Column (
        modifier = Modifier
            .background(color = Color(0xFFEDC0C0))
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 130.dp)

    ){
        Image(
            painter = painterResource(R.drawable.changepass_girl_image),
            contentDescription = "girl succes change pass",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 370.dp, 365.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Kata Sandi Berhasil Diubah",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Kata sandi telah berhasil diperbarui",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .size(width = 372.dp, height = 56.dp)
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(
                text = "Kembali ke profil",
                fontFamily = SFProdisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}