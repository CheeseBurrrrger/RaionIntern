package com.example.raionthings.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
object Login

@Serializable
object Signup
@Serializable
object HomePage

//@Composable
//fun NavigationController(
//    authViewModel: AuthViewModel,
//    userViewModel: UserViewModel
//){
//    val navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = Login
//    ){
////        composable<Login> {
////            LoginScreen(navController,authViewModel)
////        }
//////        composable<Signup> {
//////            RegisterPage(navController,authViewModel)
//////        }
////        composable<HomePage> {
////            HomePage(navController,authViewModel,userViewModel)
////        }
//
//    }
//}
