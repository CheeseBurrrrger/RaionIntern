package com.example.raionthings.presentation.profile

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.raionthings.R
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.readprofile
import com.example.raionthings.utils.uriToByteArray


@Composable
fun EditProfileScreen(
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

    Log.d("Masuk pak", userData.toString())
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
    LaunchedEffect(userData) {
        email = userData?.email ?: ""
        username = userData?.username ?: ""
        address = userData?.address ?: ""
    }
    LaunchedEffect(forceRefresh) {
        Toast.makeText(
            context,
            "Refresh success",
            Toast.LENGTH_SHORT
        ).show()
        loadProfilePicture()
        if(email!=userData?.email){
            Toast.makeText(
                context,
                "Unfortunately for right now u cannot edit the email :sad",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (imageUrl.isNotEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
        }else {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentScale = ContentScale.Fit
            )
        }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        if (imageUri==null){
            Button(onClick = { launcher.launch("image/*") }) {
                Text("Change Profile Picture")
            }
        }
        if (imageUri != null) {
            Button(onClick = {
                val imageByteArray = imageUri?.uriToByteArray(context)
                imageByteArray?.let {
                    if (userData != null) {
                        ProfileViewModel().uploadProfilePicture(
                            userData,
                            imageByteArray,
                            context
                        )
                        forceRefresh++
                        imageUri=null
                    }
                }
            }) {
                Text("Upload Image")
            }
        }
        Button(onClick = { forceRefresh++ }) {
            Text("Refresh")
        }
        Button(onClick = {
            if (userData != null
                && email==userData.email) {
                ProfileViewModel().updateProfile(userData,email,username,address,context)
            }
            else {
                email = userData?.email.toString()
                 Toast.makeText(
                     context,
                     "Unfortunately we cannot change email for right now :sad",
                     Toast.LENGTH_SHORT
                 ).show()
                }
        }) {
            Text("update data")
        }
        Button(onClick = { navController.navigate(readprofile) }) {
            Text("Done")
        }
    }
}
