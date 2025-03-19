package com.example.raionteam6.presentasion.ProfileScreen

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier,) {

    var Username by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Alamat by remember { mutableStateOf("") }
    var URL_Alamat by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 12.dp)
            .padding(vertical = 45.dp)
            .verticalScroll(rememberScrollState()),
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                painter = painterResource(id = R.drawable.arrow_asset),
                contentDescription = "back arrow",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.align(CenterVertically),
                text = "Edit Profile",
                fontFamily = SFProdisplayFontFamily,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Column (
            modifier = Modifier.align(CenterHorizontally),
        ){
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
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Username",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = Username,
                onValueChange = { Username = it},
                placeholder = {Text(
                    text = "Masukan Username",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
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

                shape = RoundedCornerShape(24.dp)
            )
            ButtonInput(
                konten = "Isi Email",
                judul = "Email",
                value = Email,
                lebar = 372.dp,
                Panjang = 56.dp
            )
            ButtonInput(
                konten = "Masukan alamat",
                judul = "Alamat",
                value = Alamat,
                lebar = 372.dp,
                Panjang = 134.dp
            )
            ButtonInput(
                konten = "Masukan URL alamat",
                judul = "URL Alamat",
                value = URL_Alamat,
                lebar = 372.dp,
                Panjang = 134.dp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                },
                modifier = Modifier
                    .size(width = 372.dp, height = 56.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(horizontal = 12.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
                shape = RoundedCornerShape(24.dp)
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

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}

@Composable
fun ButtonInput(
    konten: String,
    judul: String,
    value: String,
    lebar: Dp,
    Panjang: Dp
){
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = judul,
        fontSize = 16.sp,
        fontFamily = SFProdisplayFontFamily
    )
    OutlinedTextField(
        value = value,
        onValueChange = {},
        placeholder = {Text(
            text = konten,
            color = Color(0xFF757575),
            fontFamily = SFProdisplayFontFamily)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .size(lebar, Panjang)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(24.dp)
            ),
        singleLine = true,

        shape = RoundedCornerShape(24.dp)
    )
}