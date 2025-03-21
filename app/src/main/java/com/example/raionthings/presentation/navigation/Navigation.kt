package com.example.raionthings.presentation.navigation


import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.raionthings.presentation.buy.BuyScreen
import com.example.raionthings.presentation.explore.beranda.BerandaScreen
import com.example.raionthings.presentation.login.EmailAuthUIClient
import com.example.raionthings.presentation.login.GoogleAuthUICLient
import com.example.raionthings.presentation.login.LoginScreen
import com.example.raionthings.presentation.login.NewPasswordScreen
import com.example.raionthings.presentation.login.RegisterScreen
import com.example.raionthings.presentation.login.ResetPasswordScreen
import com.example.raionthings.presentation.login.SignInViewModel
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.profile.ChangePass
import com.example.raionthings.presentation.profile.EditProfileScreenv1
import com.example.raionthings.presentation.profile.ProfileViewModel
import com.example.raionthings.presentation.profile.SuccesChangePassScreen
import com.example.raionthings.presentation.profile.SuccesChangePasssign
import com.example.raionthings.presentation.profile.SuccesChangeProfileScreen
import com.example.raionthings.presentation.profile.UpdateProfileScreen
import com.example.raionthings.presentation.sell.SellScreen
import com.example.raionthings.presentation.sell.SuccessUploadItem
import com.example.raionthings.presentation.sell.UploadFirstItemScreen
import com.example.raionthings.presentation.sell.sellViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
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
object newpassscreen
@Serializable
object profile
@Serializable
object explore{
    const val route = "explore"
}
@Serializable
object successchangepasssign
@Serializable
object sellfirst
@Serializable
object readprofile{
    const val route = "readprofile"

}
@Serializable
object editprofile
@Serializable
object aktivitas{
    const val route = "aktivitas"
}
@Serializable
object pesanan{
    const val route = "pesanan"
}
@Serializable
object changepass
@Serializable
object successchangeprofile
@Serializable
object uploadproduk
@Serializable
object successsell
@Serializable
object successchangepass
// change password beda dengan reset password!
//yang dipake read profile, profile tidak dipake


@RequiresApi(Build.VERSION_CODES.O)
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
            LaunchedEffect(signInState.isSignInSuccessful) {
                if (signInState.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
                    navController.navigate(profile)
                    signInViewModel.resetState()
                }
            }
//            if (userData?.username?.isNotEmpty() == true &&
//                userData?.address?.isNotEmpty() == true &&
//                userData?.profilePictureUrl?.isNotEmpty() == true){
//                LaunchedEffect(Unit) {
//                    navController.navigate(explore) {
//                        popUpTo(profile) { inclusive = true }
//                    }
//                }
//            }

            LoginScreen(
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
                navController
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
            EditProfileScreenv1(
                userData,
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
            LaunchedEffect(signInState.isSignInSuccessful) {
                if (signInState.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
                    navController.navigate(profile)
                    signInViewModel.resetState()
                }
            }
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
            RegisterScreen(
                state = signInState,
                Email = email,
                viewModel = signInViewModel,
                navController,
                onSignInClick = {
                    coroutineScope.launch {
                        signInViewModel.onGoogleSignInSuccess()
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build()
                        )
                    }
                }

            )
        }

        composable<resetpassword> {
            ResetPasswordScreen(
                navController
            )
        }
        composable<explore> {
            val sellViewModel = viewModel<sellViewModel>()
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
//            userData?.let { it1 -> ExploreScreen(navController, it1,
//                onSignOut = {
//                coroutineScope.launch {
//                    googleAuthUiClient.signOut()
//                    email.signout()
//                    signInViewModel.resetState()
//                    Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()
//                    navController.navigate(sign_in)
//                }
//            }) }
            userData?.let { it1 -> BerandaScreen(navController, it1,sellViewModel) }
        }
        composable<sellfirst>{
            Log.d("sellfirst","cek info sellfirst")
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
            //ini halaman awal
            UploadFirstItemScreen(navController)
//            SellScreen(userData, navController)
        }
        composable<uploadproduk> {
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
            SellScreen(userData,navController)
        }
        composable<successsell> {
            SuccessUploadItem(navController)
        }
        composable<readprofile>{
            val googleUser = googleAuthUiClient.getSignedInUser()?.userId
            val emailUser = email.getCurrentUser()?.userId
            var currentUserId by remember { mutableStateOf(googleUser ?: emailUser) }
            var userData by remember { mutableStateOf<UserData?>(null) }
            val signInViewModel = viewModel<SignInViewModel>()

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

            userData?.let { it1 ->
                UpdateProfileScreen(
                    userData = it1,
                    onSignOut = {
                        coroutineScope.launch {
                            googleAuthUiClient.signOut()
                            email.signout()
                            signInViewModel.resetState()
                            Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        }
                    },
                    navController = navController
                )
            }
        }
        composable<editprofile>{
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
            EditProfileScreenv1(
                userData,
                navController
            )
        }
        composable<successchangeprofile> {
            SuccesChangeProfileScreen(navController)
        }
        composable(
            route = "buy/{seller}",
            arguments = listOf(navArgument("seller") { type = NavType.StringType })
        ) { backStackEntry ->
            val gson = Gson()
            val sellerJson = backStackEntry.arguments?.getString("seller") ?: ""
            val seller = gson.fromJson(sellerJson, UserData::class.java)
            Log.d("intoBuy","gson: ${gson.toString()} \nsellerJson: $sellerJson \nseller: $seller")
            val sellViewModel = viewModel<sellViewModel>()
//            BuyScreen(seller,navController,sellViewModel)
            if (seller != null) {
                BuyScreen(
                    userData = seller,
                    navController = navController,
                    sellViewModel
                )
            } else {
                // Handle invalid seller data
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("Invalid seller data", modifier = Modifier.align(Alignment.Center))
                    LaunchedEffect(Unit) {
                        navController.popBackStack()
                    }
                }
            }
        }
        composable<changepass> {
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
                        }
                    } catch (e: Exception) {
                        Log.e("FirestoreError", "Error fetching user", e)
                    }
                }
            }
            userData?.let { user -> ChangePass(user, navController = navController) }
        }
        composable<successchangepass> {

            SuccesChangePassScreen(navController)
        }
        composable<newpassscreen> {
            NewPasswordScreen(navController)
        }
        composable<successchangepasssign> {
            SuccesChangePasssign(navController)
        }
    }
}

