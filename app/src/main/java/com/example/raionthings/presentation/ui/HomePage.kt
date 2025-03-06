package com.example.raionthings.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.raionthings.R
import com.example.raionthings.presentation.viewmodel.AuthState
import com.example.raionthings.presentation.viewmodel.AuthViewModel
import com.example.raionthings.presentation.viewmodel.UserViewModel

@Composable
fun HomePage(
    modifier: Modifier=Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val authState = authViewModel.auth_State.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value){
            is AuthState.Overload -> navController.navigate(Login)
            is AuthState.Unuthenticated -> navController.navigate(Login)
            else -> Unit
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.e39),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop
        )
        TextButton(onClick = { authViewModel.signout() },
            modifier = Modifier
                .background(Color.White)
                .offset()
                .align(Alignment.Center)) {
            Text("Signout", color = Color.Blue)
        }
        Column (modifier = Modifier
            .align(Alignment.TopCenter)){
            Button(onClick = {
            }) {
                Text("Get User Data")
            }

            TextField(
                value = firstName,
                onValueChange = {firstName = it},
                label = { Text("first name") }
                )
            TextField(value = lastName,
                onValueChange = {lastName = it},
                label = { Text("last name") }
            )
            Button(onClick = {
                if (!firstName.isEmpty() || !lastName.isEmpty()){
                    userViewModel.addUser(firstName, lastName)
                }
            }) {
                Text("Add info")
            }
            Button(onClick = {
                userViewModel.getUser()
                }) {
                Text("get user")
            }
        }
    }
}