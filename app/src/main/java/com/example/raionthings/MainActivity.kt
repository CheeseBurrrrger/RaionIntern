package com.example.raionthings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.raionthings.presentation.login.EmailAuthUIClient
import com.example.raionthings.presentation.login.GoogleAuthUICLient
import com.example.raionthings.presentation.navigation.AppNavigation
import com.example.raionthings.utils.CustomTypography
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUICLient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private val email by mutableStateOf(EmailAuthUIClient())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme (
                typography = CustomTypography
            ){             AppNavigation(googleAuthUiClient, email)
            }
        }
    }
}