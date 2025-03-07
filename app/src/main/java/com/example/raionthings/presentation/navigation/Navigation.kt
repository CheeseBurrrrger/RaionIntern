package com.example.raionthings.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raionthings.presentation.login.AuthViewModel
import com.example.raionthings.presentation.login.LoginScreen
import com.example.raionthings.presentation.login.RegisterPage
import com.example.raionthings.presentation.login.UserViewModel
import com.example.raionthings.presentation.ui.HomePage
import kotlinx.serialization.Serializable


@Serializable
object Login

@Serializable
object Signup
@Serializable
object HomePage

@Composable
fun NavigationController(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
    ){
        composable<Login> {
            LoginScreen(navController,authViewModel)
        }
        composable<Signup> {
            RegisterPage(navController,authViewModel)
        }
        composable<HomePage> {
            HomePage(navController,authViewModel,userViewModel)
        }

    }
}
