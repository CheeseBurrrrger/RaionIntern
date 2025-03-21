package com.example.raionthings.presentation.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.raionthings.R
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.successchangeprofile
import com.example.raionthings.presentation.theme.ui.SFProdisplayFontFamily
import com.example.raionthings.utils.uriToByteArray

@Composable
fun EditProfileScreenv1(
    userData: UserData?,
    navController: NavController
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
    var forceRefresh by remember { mutableStateOf(0) }
    var username by remember { mutableStateOf(userData?.username ?: "") }
    var email by remember { mutableStateOf(userData?.email ?: "") }
    var address by remember { mutableStateOf(userData?.address ?: "") }

    fun loadProfilePicture() {
        userData?.let {
            ProfileViewModel().readProfilePicture(it) { newUrl ->
                imageUrl = if (newUrl.isNotEmpty()) {
                    "$newUrl?timestamp=${System.currentTimeMillis()}"
                } else {
                    newUrl
                }
            }
        }
    }
    LaunchedEffect(forceRefresh) {
        loadProfilePicture()
    }

    LaunchedEffect(userData) {
        email = userData?.email ?: ""
        username = userData?.username ?: ""
        address = userData?.address ?: ""
        loadProfilePicture()
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

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
            AsyncImage(
                model = imageUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(99.dp)
                    .clip(CircleShape)
                    .align(CenterHorizontally)
                    .clickable {
                        forceRefresh++
                        launcher.launch("image/*")  },
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
                value = username,
                onValueChange = { username = it},
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
                konten = "maaf email belum dapat diubah",
                judul = "Email",
                value = "",
                lebar = 372.dp,
                Panjang = 56.dp
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Alamat",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                enabled = true,
                value = address,
                onValueChange = {address = it},
                placeholder = {Text(
                    text = "Masukkan alamat",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(372.dp, 134.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,

                shape = RoundedCornerShape(24.dp)
            )
            ButtonInput(
                konten = "Maaf ya backendnya masih pusing",
                judul = "URL Alamat",
                value = "",
                lebar = 372.dp,
                Panjang = 134.dp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (userData != null) {
                        ProfileViewModel().updateProfile(userData,email,username,address,context)
                        if (imageUri!=null) {
                            val imageByteArray = imageUri?.uriToByteArray(context)
                            imageByteArray?.let {
                                if (userData != null) {
                                    ProfileViewModel().uploadProfilePicture(
                                        userData,
                                        imageByteArray,
                                        context
                                    )
                                    forceRefresh++
                                    imageUri = null
                                }
                            }
                        }
                        navController.navigate(successchangeprofile)
                    }
                },
                modifier = Modifier
                    .size(width = 372.dp, height = 56.dp)
                    .align(alignment = CenterHorizontally)
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

@Composable
fun ButtonInput(
    konten: String,
    judul: String,
    value: String,
    lebar: Dp,
    Panjang: Dp,
){
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = judul,
        fontSize = 16.sp,
        fontFamily = SFProdisplayFontFamily
    )

        OutlinedTextField(
            enabled = true,
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