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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.raionthings.presentation.profile.ProfileViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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
                        var userData by remember { mutableStateOf<UserData?>(null) }
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
                            if (signInState.isSignInSuccessful) {
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
                        val googleUser = googleAuthUiClient.getSignedInUser()?.userId
                        val emailUser = email.getCurrentUser()?.userId
                        var currentUserId by remember { mutableStateOf(googleUser ?: emailUser) }
                        var userData by remember { mutableStateOf<UserData?>(null) }
                        Log.d("karenuser", currentUserId.toString())
                        Log.d("ProfileScreen", "Google User: $googleUser, Email User: $emailUser")
                        LaunchedEffect(Unit) {
                            val id = googleAuthUiClient.getSignedInUser()?.userId ?: email.getCurrentUser()?.userId
                            currentUserId = id
                            Log.d("LaunchedEffect", "Triggered with ID: $currentUserId")
                            currentUserId?.let {
                                try {
                                    Log.d("TryBlock", "Fetching document for $currentUserId")
                                    val document = Firebase.firestore
                                        .collection("Profile")
                                        .document(currentUserId!!)
                                        .get()
                                        .await()

                                    Log.d("DocumentData", "Document: $document")
                                    if (document.exists()) {
                                        userData = document.toObject(UserData::class.java)
                                        Log.d("UserExist", "Existing user: $userData")
                                    } else {
                                        Log.e("NewUser", "Adding new profile")
                                        userData = EmailAuthUIClient().getCurrentUser()
                                        userData?.let { ProfileViewModel().addNewProfile(it) }
                                    }
                                } catch (e: Exception) {
                                    Log.e("FirestoreError", "Error fetching user", e)
                                } finally {
                                    Log.e("FinallyBlock", "Finally block executed")
                                }
                            }
                        }


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
                                    Log.d("Logins",emailUser.toString())
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
