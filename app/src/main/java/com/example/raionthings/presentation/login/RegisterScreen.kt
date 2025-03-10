package com.example.raionthings.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@Composable
fun RegisterPage(
    state: SignInState,
    Email: EmailAuthUIClient,
    viewModel: SignInViewModel,
    onNavigateToLogin: () -> Unit
    ){
    val emailFocusRequester = remember { FocusRequester() }
    val RepasswordFocus = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var Repassword by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = state.isSignedUp) {
        if (state.isSignedUp == true) {
            onNavigateToLogin()
            viewModel.resetState()
        }
    }

    // Show error messages
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.resetState()
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Register Page")
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
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            )
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 25.dp)
                .focusRequester(passwordFocusRequester)
                .onKeyEvent { event ->
                    if (event.key == Key.Enter) ({
                        if (email.isEmpty() || password.isEmpty() )
                        {passwordFocusRequester.requestFocus()}
                        else {
                            RepasswordFocus.requestFocus()
                            true
                        }
                    }) as Boolean else {
                        false
                    }
                },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.Gray
            ),
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { RepasswordFocus.requestFocus() }
            )
        )
        TextField(
            value = Repassword,
            onValueChange = { Repassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 25.dp)
                .focusRequester(RepasswordFocus)
                .onKeyEvent { event ->
                    if (event.key == Key.Enter ) ({
                        if (email.isEmpty() || password.isEmpty() ) {Toast.makeText(context, "Please make sure u fill all fields", Toast.LENGTH_SHORT).show()}
                        else if (Repassword!=password){Toast.makeText(context, "kindly check ur password", Toast.LENGTH_SHORT).show()}
                        else {
                            if (email.isNotEmpty() && password.isNotEmpty() && Repassword == password) {
                                viewModel.viewModelScope.launch {
                                    val result = Email.signup(email, password)
                                    viewModel.onSignUpResult(result)
                                }
                            } else {
                                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()            }
                            true
                        }
                    }) as Boolean else {
                        false
                    }
                },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.Gray
            ),
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (email.isNotEmpty() && password.isNotEmpty() && Repassword == password) {
                        viewModel.viewModelScope.launch {
                            val result = Email.signup(email, password)
                            viewModel.onSignUpResult(result)
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()            }
                }
            )
        )
        Button(onClick = {
            if (email.isNotEmpty() && password.isNotEmpty() && Repassword == password) {
                viewModel.viewModelScope.launch {
                    val result = Email.signup(email, password)
                    viewModel.onSignUpResult(result)
                }
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()            }
        }) { Text("Sign Up") }
        TextButton(onClick = onNavigateToLogin){
            Text("Already have an account?, Sign In here")
        }
    }
}

