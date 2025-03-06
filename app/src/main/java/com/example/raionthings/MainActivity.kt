package com.example.raionthings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.raionthings.presentation.ui.NavigationController
import com.example.raionthings.presentation.viewmodel.AuthViewModel
import com.example.raionthings.presentation.viewmodel.UserViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
    val authViewModel: AuthViewModel by viewModels()
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            auth = FirebaseAuth.getInstance()
            NavigationController(modifier = Modifier , authViewModel = AuthViewModel(), userViewModel = UserViewModel())
        }
    }
}





