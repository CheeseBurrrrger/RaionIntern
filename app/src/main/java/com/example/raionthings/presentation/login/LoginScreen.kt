package com.example.raionthings.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionthings.R
import com.example.raionthings.presentation.navigation.HomePage
import com.example.raionthings.presentation.navigation.Signup
import com.example.raionthings.presentation.ux.CustomToast


@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = authViewModel.auth_State.observeAsState()
    val context = LocalContext.current
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    val sfApple = FontFamily(
        Font(R.font.blackitalic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.bold, FontWeight.Bold),
        Font(R.font.heavyitalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.medium, FontWeight.Medium),
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Login Page",
                    fontSize = 22.sp,
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(100.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 25.dp)
                    .focusRequester(emailFocusRequester)
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter) {
                            passwordFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.Gray
                ),
                label = { Text("Email",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 25.dp)
                    .focusRequester(passwordFocusRequester)
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter) {
                            if (email.isEmpty() || password.isEmpty()) {
                                toastMessage = "Please fill in all fields"
                                showToast = true
                            } else {
                                authViewModel.login(email, password)
                            }
                            true
                        } else {
                            false
                        }
                    },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.Gray
                ),
                label = { Text("Password",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { authViewModel.login(email, password) }
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    toastMessage = "Seng genah ae"
                    showToast = true
                } else {
                    authViewModel.login(email, password)
                }
            }) {
                Text("Login",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(60.dp))
            TextButton(onClick = { navController.navigate(Signup) }) {
                Text("Don't have an account? Sign up here",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }
        }

        // CustomToast inside the Box
        if (showToast) {
            CustomToast(
                message = toastMessage,
                onDismiss = { showToast = false },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(HomePage)
            is AuthState.Error -> {
                toastMessage = "Seng genah ae"
                showToast = true
            }
            else -> Unit
        }
    }
}