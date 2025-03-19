package com.example.raionteam6.presentasion.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raionteam6.presentasion.BerandaScreen.Beranda.BerandaScreen
import com.example.raionteam6.presentasion.BerandaScreen.Kategori.KategoriScreen
import com.example.raionteam6.presentasion.BerandaScreen.MostBuyScreen.MostBuyScreen
import com.example.raionteam6.presentasion.BottomNavbar.BottomNavBar
import com.example.raionteam6.presentasion.ForgetPassword.ForgetPASS
import com.example.raionteam6.presentasion.ForgetPassword.ForgetPASS
import com.example.raionteam6.presentasion.LoginScreen.LoginScreen
import com.example.raionteam6.presentasion.NewPasswordScreen.NewPasswordScreen
import com.example.raionteam6.presentasion.Pinpassword.Pinpassword
import com.example.raionteam6.presentasion.ProfileScreen.ChangePass
import com.example.raionteam6.presentasion.ProfileScreen.ChangePassPreview
import com.example.raionteam6.presentasion.ProfileScreen.EditProfileScreen
import com.example.raionteam6.presentasion.ProfileScreen.SuccesChangePassScreen
import com.example.raionteam6.presentasion.ProfileScreen.UpdateProfileScreen
import com.example.raionteam6.presentasion.ProfileScreen.UpdateProfileScreenPreview
import com.example.raionteam6.presentasion.Register.RegisterScreen
import com.example.raionteam6.presentasion.SuccessNewPassword.SuccesNewPassword
import com.example.raionteam6.presentasion.TransactionScreen.buyScreen.SuccessUploadItem
import com.example.raionteam6.presentasion.TransactionScreen.buyScreen.UploadFirstItemScreen
import com.example.raionteam6.presentasion.TransactionScreen.buyScreen.UploadItemScreen
import com.example.raionteam6.presentasion.theme.ui.RAIONTeam6Theme
import com.example.raionteam6.presentasion.testing.testing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
//              val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "Login_Screen", builder = {
//                    composable("Login_Screen") {
//                        LoginScreen(navController)
//                    }
//                    composable("Register_Screen") {
//                        RegisterScreen(navController)
//                    }
//                    composable ("Forget_Pass"){
//                        ForgetPASS(navController)
//                    }
//                    composable ("Pin_Password"){
//                        Pinpassword(navController)
//                    }
//                    composable("New_Password") {
//                        NewPasswordScreen(navController)
//                    }
//                    composable("Success_NewPassword") {
//                        SuccesNewPassword(navController)
//                    }
//                    composable("Beranda_Screen") {
//                        BerandaScreen(navController)
//                    }
//                    composable("Kategori_Screen") {
//                        KategoriScreen(navController)
//                    }
//                    composable("MostBuy_Screen") {
//                        MostBuyScreen(navController)
//                    }
//                    composable("UploadItem_Screen") {
//                        UploadItemScreen(navController = navController)
//                    }
//                })
                UploadFirstItemScreen()
            }
        }
    }
}


