package com.example.raionthings

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raionthings.presentation.login.EmailAuthUIClient
import com.example.raionthings.presentation.login.GoogleAuthUICLient
import com.example.raionthings.presentation.login.ProfileScreen
import com.example.raionthings.presentation.login.RegisterPage
import com.example.raionthings.presentation.login.SignInScreen
import com.example.raionthings.presentation.login.SignInViewModel
import com.example.raionthings.presentation.login.UserData
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

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
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ){
                val navController= rememberNavController()
                NavHost(navController = navController, startDestination = "sign_in"){

                    composable("sign_in") {
                        val signInViewModel = viewModel<SignInViewModel>()
                        val signInState by signInViewModel.state.collectAsStateWithLifecycle()

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        signInViewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )
                        LaunchedEffect(key1 = signInState.isSignInSuccessful) {
                            if (signInState.isSignInSuccessful){
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate("profile")
                                signInViewModel.resetState()
                            }
                        }

                        SignInScreen(
                            viewModel = signInViewModel,
                            state = signInState,
                            Email = email,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    signInViewModel.onGoogleSignInSuccess()
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            },
                            onNavigateToRegister = { navController.navigate("register") },
                        )
                    }
                    composable("profile") {
                        val signInViewModel = viewModel<SignInViewModel>()

                        val googleUser = googleAuthUiClient.getSignedInUser()?.let { googleUser ->
                            UserData(
                                userId = googleUser.userId,
                                email = googleUser.email,
                                username = googleUser.username,
                                profilePictureUrl = googleUser.profilePictureUrl,
                                address = null
                            )
                        }

                        val emailUser = email.getCurrentUser()?.let { emailUser ->
                            UserData(
                                userId = emailUser.userId,
                                email = emailUser.email,
                                username = emailUser.username,
                                profilePictureUrl = emailUser.profilePictureUrl,
                                address = null
                            )

                        }
                        Log.d("Tessting", googleUser.toString())

                        Log.d("Tessting", emailUser.toString())

                        val userData = googleUser ?: emailUser
                        ProfileScreen (
                            userData = userData,
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    email.signout()
                                    signInViewModel.resetState()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed out",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    Log.d("Login",googleUser.toString())
                                    Log.d("Login",emailUser.toString())
                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                    composable ("register"){
                        val signInViewModel = viewModel<SignInViewModel>()
                        val signInState by signInViewModel.state.collectAsStateWithLifecycle()
                        LaunchedEffect(key1 = signInState.isSignedUp) {
                            if (signInState.isSignedUp == true) {
                                navController.navigate("sign_in")
                                signInViewModel.resetState()
                            }
                        }
                        RegisterPage(
                            state = signInState,
                            Email = email,
                            viewModel = signInViewModel,
                            onNavigateToLogin = {navController.navigate("sign_in")}
                        )
                    }
                }
            }
        }
    }
}





