package com.example.raionthings.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ResetPasswordScreen(
    onNavigateToLogin: () -> Unit
) {
    var email by remember { mutableStateOf( "") }
    val context = LocalContext.current
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("ur Email address for reset password") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            if(email.isNotEmpty()){
                EmailAuthUIClient().resetPassword(email,context)
            }
            else{
                Toast.makeText(
                    context,
                    "why u let the email field blank huh?",
                    Toast.LENGTH_SHORT
                ).show()
            }
            onNavigateToLogin()
        }) {
            Text("Reset password")
        }
    }
}