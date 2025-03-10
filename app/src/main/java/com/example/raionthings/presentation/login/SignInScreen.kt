package com.example.raionthings.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewModelScope
import com.example.raionthings.R
import kotlinx.coroutines.launch

//@Composable
//fun SignInScreen(
//    authViewModel: AuthViewModel,
//    state: SignInState,
//    onSignInClick: ()-> Unit
//   ) {
//    val emailFocusRequester = remember { FocusRequester() }
//    val passwordFocusRequester = remember { FocusRequester() }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var showToast by remember { mutableStateOf(false) }
//    var toastMessage by remember { mutableStateOf("") }
//    val sfApple = FontFamily(
//        Font(R.font.blackitalic, FontWeight.Black, FontStyle.Italic),
//        Font(R.font.bold, FontWeight.Bold),
//        Font(R.font.heavyitalic, FontWeight.ExtraBold, FontStyle.Italic),
//        Font(R.font.lightitalic, FontWeight.Light, FontStyle.Italic),
//        Font(R.font.medium, FontWeight.Medium),
//    )
//    val context = LocalContext.current
//
//    LaunchedEffect(key1 = state.signInError) {
//        state.signInError?.let { error ->
//            Toast.makeText(
//                context,
//                error,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//    Box(modifier = Modifier.fillMaxWidth()) {
//        Column(
//            modifier = Modifier
//                .padding(20.dp, 10.dp)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp, 60.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text("Login Page",
//                    fontSize = 22.sp,
//                    fontFamily = sfApple,
//                    fontWeight = FontWeight.SemiBold)
//            }
//
//            Spacer(modifier = Modifier.height(100.dp))
//            TextField(
//                value = email,
//                onValueChange = { email = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp, 25.dp)
//                    .focusRequester(emailFocusRequester)
//                    .onKeyEvent { event ->
//                        if (event.key == Key.Enter) {
//                            passwordFocusRequester.requestFocus()
//                            true
//                        } else {
//                            false
//                        }
//                    },
//                colors = TextFieldDefaults.colors(
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedContainerColor = Color.LightGray,
//                    focusedContainerColor = Color.Gray
//                ),
//                label = { Text("Email",
//                    fontFamily = sfApple,
//                    fontWeight = FontWeight.SemiBold) },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//                keyboardActions = KeyboardActions(
//                    onNext = { passwordFocusRequester.requestFocus() }
//                )
//            )
//            Spacer(modifier = Modifier.height(60.dp))
//            TextField(
//                value = password,
//                onValueChange = { password = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp, 25.dp)
//                    .focusRequester(passwordFocusRequester)
//                    .onKeyEvent { event ->
//                        if (event.key == Key.Enter) {
//                            if (email.isEmpty() || password.isEmpty()) {
//                                toastMessage = "Please fill in all fields"
//                                showToast = true
//                            } else {
//                                authViewModel.login(email, password)
//                            }
//                            true
//                        } else {
//                            false
//                        }
//                    },
//                colors = TextFieldDefaults.colors(
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedContainerColor = Color.LightGray,
//                    focusedContainerColor = Color.Gray
//                ),
//                label = { Text("Password",
//                    fontFamily = sfApple,
//                    fontWeight = FontWeight.SemiBold) },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//                keyboardActions = KeyboardActions(
//                    onDone = { authViewModel.login(email, password) }
//                )
//            )
//            Spacer(modifier = Modifier.height(60.dp))
//            Button(onClick = {
//                if (email.isEmpty() || password.isEmpty()) {
//                    toastMessage = "Seng genah ae"
//                    showToast = true
//                } else {
//                    authViewModel.login(email, password)
//                }
//            }) {
//                Text("Login",
//                    fontFamily = sfApple,
//                    fontWeight = FontWeight.SemiBold)
//            }
//            Spacer(modifier = Modifier.height(60.dp))
//            TextButton(onClick = {  }) {
//                Text("Don't have an account? Sign up here",
//                    fontFamily = sfApple,
//                    fontWeight = FontWeight.SemiBold)
//            }
//            Spacer(modifier = Modifier.height(2.dp))
//            Button(onClick = onSignInClick) {
//                Text("Sign In with google",
//                    fontFamily = sfApple,
//                    fontWeight = FontWeight.SemiBold)
//            }
//        }
//    }
//}

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    state: SignInState,
    Email: EmailAuthUIClient,
    onSignInClick: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sfApple = FontFamily(
        Font(R.font.blackitalic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.bold, FontWeight.Bold),
        Font(R.font.heavyitalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.medium, FontWeight.Medium),
    )


    // Show error messages
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.resetState()
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                                Toast.makeText(
                                    context,
                                    "Signed out",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                viewModel.viewModelScope.launch {
                                    viewModel.onGoogleSignInSuccess()
                                    val result = Email.login(email, password)
                                    viewModel.onSignInResult(result)
                                }
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
                    onDone = {
                        viewModel.viewModelScope.launch {
                            viewModel.onGoogleSignInSuccess()
                            val result = Email.login(email, password)
                            viewModel.onSignInResult(result)
                        }
                    }
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.viewModelScope.launch {
                        viewModel.onGoogleSignInSuccess()
                        val result = Email.login(email, password)
                        viewModel.onSignInResult(result)
                    }
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()                }
            }) { Text("Login") }

            Spacer(modifier = Modifier.height(60.dp))
            TextButton(onClick = onNavigateToRegister) {
                Text("Don't have an account? Sign up here",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(2.dp))
            Button(onClick = onSignInClick) {
                Text("Sign In with google",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
