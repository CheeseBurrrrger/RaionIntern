package com.example.raionthings.presentation.profile


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.raionthings.R
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.BottomNavBarv
import com.example.raionthings.presentation.navigation.changepass
import com.example.raionthings.presentation.navigation.editprofile
import com.example.raionthings.presentation.navigation.floatActionButton
import com.example.raionthings.presentation.theme.ui.SFProdisplayFontFamily


@Composable
fun UpdateProfileScreen(
    navController: NavController,
    onSignOut: () -> Unit,
    userData: UserData) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
    var username by remember { mutableStateOf(userData?.username ?: "") }
    var email by remember { mutableStateOf(userData?.email ?: "") }
    var address by remember { mutableStateOf(userData?.address ?: "") }
    var forceRefresh by remember { mutableStateOf(0) }

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
    }

    Scaffold (
        bottomBar = { BottomNavBarv(navController) },
        floatingActionButton = {floatActionButton(navController)}
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Profil",
                    fontFamily = SFProdisplayFontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
                    .size(width = 372.dp, height = 115.dp)
            ) {
                if (imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(115.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }else {
                    Image(
                        painter = painterResource(R.drawable.sajigobw),
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(115.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                // Name and Email
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = SFProdisplayFontFamily
                    )
                    Text(
                        text = email,
                        fontWeight = FontWeight.Normal,
                        fontFamily = SFProdisplayFontFamily,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 372.dp, height = 134.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Column (Modifier.clickable { navController.navigate(editprofile) }){
                        ProfileMenuItem(
                            icon = painterResource(R.drawable.setting_vector_icon),
                            title = "Edit Profil",
                            showDivider = true,
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Column (Modifier.clickable { navController.navigate(changepass) }){
                        ProfileMenuItem(
                            icon = painterResource(R.drawable.key_icon),
                            title = "Ganti Password",
                            showDivider = false,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 372.dp, height = 134.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    // Edit Profile Option
                    ProfileMenuItem(
                        icon = painterResource(R.drawable.faq_icon),
                        title = "FAQ",
                        showDivider = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Change Password Option
                    Column (Modifier.clickable { onSignOut() }){
                        ProfileMenuItem(
                            icon = painterResource(R.drawable.exit_icon_vector),
                            title = "Keluar",
                            textColor = Color.Red,
                            showDivider = false,
                        )
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpdateProfileScreenPreview(){
//    UpdateProfileScreen()
}

@Composable
fun ProfileMenuItem(
    icon: Painter,
    title: String,
    textColor: Color = Color.Black,
    showDivider: Boolean = true
) {
    Column (){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (title == "Keluar"){
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                }
                else {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontFamily = SFProdisplayFontFamily,
                    fontSize = 16.sp,
                    color = textColor
                )
            }
            Icon(
                painter = painterResource(R.drawable.arrow_right_simple_icon), // Using this as "chevron right" icon
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }

        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFFEEEEEE),
                thickness = 1.dp
            )
        }
    }
}