package com.example.raionthings.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raionthings.presentation.viewmodel.AuthViewModel
import com.example.raionthings.presentation.viewmodel.UserViewModel
import kotlinx.serialization.Serializable


@Serializable
object Login

@Serializable
object Signup
@Serializable
object HomePage

@Composable
fun NavigationController(
    modifier: Modifier=Modifier,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =Login
    ){
        composable<Login> {
            LoginPage(modifier,navController,authViewModel)
        }
        composable<Signup> {
            RegisterPage(modifier,navController,authViewModel)
        }
        composable<HomePage> {
            HomePage(modifier,navController,authViewModel,userViewModel)
        }
    }
}
