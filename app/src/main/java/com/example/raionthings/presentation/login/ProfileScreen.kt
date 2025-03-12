package com.example.raionthings.presentation.login

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.raionthings.presentation.profile.ProfileUIClient
import com.example.raionthings.utils.uriToByteArray


@Composable
    fun ProfileScreen(
        userData: UserData?,
        onSignOut: ()-> Unit
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            var imageUrl by remember { mutableStateOf("") }
            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(
                contract =
                    ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                imageUri = uri
            }

            Button(onClick = {launcher.launch("image/*")}) {
                Text("choose image")
            }
            if (imageUri!=null){
                Button(onClick = {
                    val imageByteArray = imageUri?.uriToByteArray(context)
                    imageByteArray?.let {
                        if (userData != null) {
                            ProfileUIClient().uploadProfilePicture(userData,imageByteArray)
                        }
                    }
                    Log.d("test upload", imageByteArray.toString())
                }) {
                    Text("Upload Image")
                }
            }

            if(userData?.profilePictureUrl!=null){
                profilePic(userData.profilePictureUrl)
            }else if (imageUrl!=null){
                profilePic(imageUrl)
            }

            Log.d("ProfileScreen",userData.toString())
            if (userData != null) {
                ProfileUIClient().addProfile(userData)
                Log.d("CekProfil",userData.toString())
            }

            if (userData != null) {
                Text("Welcome, ${userData.email ?: "User"}!")
            } else {
                Text("No user data found")
            }

            if (userData?.username !=null){
                Text(
                    text = userData.username,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Button(onClick ={
            if (userData != null) {
                ProfileUIClient().readProfilePicture(userData,){
                    imageUrl = it
                }
                Log.d("getImage", imageUrl)
            }
        } ) {
        Text("Refresh")
        }
            Button(onClick = onSignOut) {
                Text("Sign Out")
            }
        }
    }
@Composable
fun profilePic(
    imageUrl:String
){
    AsyncImage(
        model = imageUrl,
        contentDescription = "Profile picture",
        modifier = Modifier
            .size(300.dp),
//                        .clip(CircleShape),
        contentScale = ContentScale.Fit
    )
}


//if (userData?.profilePictureUrl!=null){
//    AsyncImage(
//        model = userData.profilePictureUrl,
//        contentDescription = "Profile picture",
//        modifier = Modifier
//            .size(150.dp)
//            .clip(CircleShape),
//        contentScale = ContentScale.Crop
//    )
//
//    Spacer(modifier = Modifier.height(16.dp))
//}
//else if (imageUrl.isNotEmpty()){
//    AsyncImage(
//        model = imageUrl,
//        contentDescription = "Profile picture",
//        modifier = Modifier
//            .size(300.dp),
////                        .clip(CircleShape),
//        contentScale = ContentScale.Fit
//    )
//    Log.d("getImage", imageUrl)
//}


//--get image
//Button(onClick ={
//    if (userData != null) {
//        ProfileUIClient().readProfilePicture(userData,){
//            imageUrl = it
//        }
//
//        Log.d("getImage", imageUrl)
//    }
//} ) {
//
//}