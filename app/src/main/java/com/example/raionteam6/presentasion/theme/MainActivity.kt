package com.example.raionteam6.presentasion.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raionteam6.presentasion.ForgetPassword.ForgetPASS
import com.example.raionteam6.presentasion.ForgetPassword.ForgetPASS
import com.example.raionteam6.presentasion.LoginScreen.LoginScreen
import com.example.raionteam6.presentasion.Pinpassword.Pinpassword
import com.example.raionteam6.presentasion.Register.RegisterScreen
import com.example.raionteam6.presentasion.theme.ui.RAIONTeam6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Login_Screen", builder = {
                composable("Login_Screen") {
                    LoginScreen(navController)
                }
                composable("Register_Screen") {
                    RegisterScreen()
                }
                composable ("Forget_Pass"){
                    ForgetPASS(navController)
                }
                composable ("Pin_Password"){
                    Pinpassword()
                }
            })
        }
    }
}


