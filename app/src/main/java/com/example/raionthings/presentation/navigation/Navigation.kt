package com.example.raionthings.presentation.navigation


import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raionthings.presentation.explore.ExploreScreen
import com.example.raionthings.presentation.login.EmailAuthUIClient
import com.example.raionthings.presentation.login.GoogleAuthUICLient
import com.example.raionthings.presentation.login.ProfileScreen
import com.example.raionthings.presentation.login.RegisterPage
import com.example.raionthings.presentation.login.ResetPasswordScreen
import com.example.raionthings.presentation.login.SignInScreen
import com.example.raionthings.presentation.login.SignInViewModel
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.profile.ProfileViewModel
import com.example.raionthings.presentation.sell.SellScreen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.Serializable

@Serializable
object sign_in
@Serializable
object register
@Serializable
object resetpassword
@Serializable
object profile
@Serializable
object explore
@Serializable
object sell

@Composable
fun AppNavigation(
    googleAuthUiClient: GoogleAuthUICLient,
    email: EmailAuthUIClient
) {
    val navController = rememberNavController()
    val context = LocalContext.current // Get context properly
    val coroutineScope = rememberCoroutineScope() // Use coroutineScope instead of lifecycleScope





    NavHost(navController = navController, startDestination = sign_in) {
        composable<sign_in> {
            val signInViewModel = viewModel<SignInViewModel>()
            val signInState by signInViewModel.state.collectAsStateWithLifecycle()
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        coroutineScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            signInViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(signInState.isSignInSuccessful) {
                if (signInState.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
                    navController.navigate(profile)
                    signInViewModel.resetState()
                }
            }

            SignInScreen(
                viewModel = signInViewModel,
                state = signInState,
                Email = email,
                onSignInClick = {
                    coroutineScope.launch {
                        signInViewModel.onGoogleSignInSuccess()
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build()
                        )
                    }
                },
                onNavigateToRegister = { navController.navigate(register) },
                onNavigateToReset = { navController.navigate(resetpassword) }
            )
        }

        composable<profile> {
            val signInViewModel = viewModel<SignInViewModel>()
            val googleUser = googleAuthUiClient.getSignedInUser()?.userId
            val emailUser = email.getCurrentUser()?.userId
            var currentUserId by remember { mutableStateOf(googleUser ?: emailUser) }
            var userData by remember { mutableStateOf<UserData?>(null) }

            LaunchedEffect(Unit) {
                val id = googleAuthUiClient.getSignedInUser()?.userId ?: email.getCurrentUser()?.userId
                currentUserId = id

                currentUserId?.let {
                    try {
                        val document = Firebase.firestore.collection("Profile").document(it).get().await()
                        if (document.exists()) {
                            userData = document.toObject(UserData::class.java)
                        } else {
                            userData = email.getCurrentUser()
                            userData?.let { ProfileViewModel().addNewProfile(it) }
                        }
                    } catch (e: Exception) {
                        Log.e("FirestoreError", "Error fetching user", e)
                    }
                }
            }
            if (userData?.username?.isNotEmpty() == true &&
                userData?.address?.isNotEmpty() == true &&
                userData?.profilePictureUrl?.isNotEmpty() == true){
                LaunchedEffect(Unit) {
                    navController.navigate(explore) {
                        popUpTo(profile) { inclusive = true }
                    }
                }
            }
            ProfileScreen(
                userData = userData,
                onSignOut = {
                    coroutineScope.launch {
                        googleAuthUiClient.signOut()
                        email.signout()
                        signInViewModel.resetState()
                        Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                    }
                },
                navController
            )
        }

        composable<register> {
            val signInViewModel = viewModel<SignInViewModel>()
            val signInState by signInViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(signInState.isSignedUp) {
                if (signInState.isSignedUp == true) {
                    navController.navigate(sign_in)
                    signInViewModel.resetState()
                }
            }

            RegisterPage(
                state = signInState,
                Email = email,
                viewModel = signInViewModel,
                onNavigateToLogin = { navController.navigate(sign_in) }
            )
        }

        composable<resetpassword> {
            ResetPasswordScreen(
                onNavigateToLogin = { navController.navigate(sign_in) }
            )
        }
        composable<explore> {
            val signInViewModel = viewModel<SignInViewModel>()
            val currentUserId = remember { googleAuthUiClient.getSignedInUser()?.userId ?: email.getCurrentUser()?.userId }
            var userData by remember { mutableStateOf<UserData?>(null) }
            LaunchedEffect(currentUserId){
                currentUserId?.let {
                    try {
                        val document = Firebase.firestore.collection("Profile").document(it).get().await()
                        if (document.exists()) {
                            userData = document.toObject(UserData::class.java)
                        } else {
                            userData = email.getCurrentUser()
                            userData?.let { ProfileViewModel().addNewProfile(it) }
                        }
                    } catch (e: Exception) {
                        Log.e("FirestoreError", "Error fetching user", e)
                    }
                }
            }
//            userData?.let { it1 -> ExploreScreen(it1) }
            userData?.let { it1 -> ExploreScreen(navController, it1,
                onSignOut = {
                coroutineScope.launch {
                    googleAuthUiClient.signOut()
                    email.signout()
                    signInViewModel.resetState()
                    Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                }
            }) }
        }
        composable<sell>{
            val googleUser = googleAuthUiClient.getSignedInUser()?.userId
            val emailUser = email.getCurrentUser()?.userId
            var currentUserId by remember { mutableStateOf(googleUser ?: emailUser) }
            var userData by remember { mutableStateOf<UserData?>(null) }
            LaunchedEffect(Unit) {
                val id = googleAuthUiClient.getSignedInUser()?.userId ?: email.getCurrentUser()?.userId
                currentUserId = id

                currentUserId?.let {
                    try {
                        val document = Firebase.firestore.collection("Profile").document(it).get().await()
                        if (document.exists()) {
                            userData = document.toObject(UserData::class.java)
                        } else {
//                            userData = email.getCurrentUser()
//                            userData?.let { ProfileViewModel().addNewProfile(it) }
                        }
                    } catch (e: Exception) {
                        Log.e("FirestoreError", "Error fetching user", e)
                    }
                }
            }
            SellScreen(userData, navController)
        }
    }
}

