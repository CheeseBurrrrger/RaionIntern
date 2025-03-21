package com.example.raionthings.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import com.example.raionthings.presentation.navigation.editprofile
import com.example.raionthings.presentation.navigation.explore
import com.example.raionthings.presentation.navigation.readprofile
import com.example.raionthings.presentation.navigation.sellfirst

@Composable
fun ReadProfileScreen(
    userData: UserData?,
    navController: NavController
) {
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
        Toast.makeText(
            context,
            "Refresh success",
            Toast.LENGTH_SHORT
        ).show()
        username = userData?.username.toString()
        email =userData?.email.toString()
        address=userData?.address.toString()
        loadProfilePicture()
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

        Text(
            text = username,
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp,10.dp),

        )

        Text(
            email,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            address,
            modifier = Modifier.fillMaxWidth()
        )


        Button(onClick = { forceRefresh++ }) {
            Text("Refresh")
        }
        Button(onClick = {navController.navigate(editprofile)}) {
            Text("Edit Profile")
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = {navController.navigate(explore)}) {
                Text("Ber")
            }
            Button(onClick = {}) {
                Text("Akt")
            }
            Button(onClick = {navController.navigate(sellfirst)}) {
                Text("add")
            }
            Button(onClick = {}) {
                Text("pes")
            }
            Button(onClick = {navController.navigate(readprofile)}) {
                Text("prof")
            }
        }
    }
}