package com.example.raionthings.presentation.navigation


import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.raionthings.R
import com.example.raionthings.R.drawable.profile_icon

@Composable
fun BottomNavBarv(navController: NavController) {
    val bottomNavigation = listOf(
        BottomNavItems(
            label = "Beranda",
            icon = R.drawable.home_icon,
            route = explore // Use the @Serializable object directly
        ),
        BottomNavItems(
            label = "Aktivitas",
            icon = R.drawable.activitas_icon,
            route = "" // Use the @Serializable object directly
        ),
        BottomNavItems(
            label = "Pesanan",
            icon = R.drawable.pesanan_icon,
            route = pesanan // Use the @Serializable object directly
        ),
        BottomNavItems(
            label = "Profile",
            icon = profile_icon,
            route = readprofile // Use the @Serializable object directly
        )
    )

    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Log.d("BottomNavBar", "Current Route: $currentRoute")
    NavigationBar(
        modifier = Modifier
            .shadow(elevation = 12.dp)
            .background(Color.White),
        containerColor = Color.Transparent
    ) {
        bottomNavigation.forEach { item ->
            Log.d("BottomNavBar", "Current Route: $currentRoute Item route: ${item.route}")

            NavigationBarItem(
                selected = item.route.toString().contains(currentRoute.toString()),
                onClick = {
                    // Navigate to the selected screen
                    navController.navigate(item.route) {
                        // Pop up to the start destination to avoid back stack buildup
                        popUpTo(navController.graph.startDestinationId)
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        tint = if (item.route.toString().contains(currentRoute.toString())) Color.Red else Color(0xFEB3B3B3)
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Red,
                    unselectedIconColor = Color(0xFEB3B3B3),
                    selectedTextColor = Color.Red,
                    unselectedTextColor = Color(0xFEB3B3B3),
                    selectedIndicatorColor = Color.Transparent,
                    disabledIconColor = Color(0xFEB3B3B3),
                    disabledTextColor = Color(0xFEB3B3B3)
                ),
                label = {
                    Text(text = item.label,
                    color = if (item.route.toString().contains(currentRoute.toString())) Color.Red else Color(0xFEB3B3B3)) }
            )
        }
    }
}

@Composable
fun floatActionButton(
    navController: NavController ) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        FloatingActionButton(
            onClick = {
                Log.d("FloatActionButton", "Navigating to sellfirst")
                try {
                    navController.navigate(sellfirst) // Use string directly for debugging
                } catch (e: Exception) {
                    Log.e("FloatActionButton", "Navigation failed: ${e.message}")
                }
            },
            containerColor = Color(0xFFB3B3B3),
            shape = CircleShape,
            modifier = Modifier
                .zIndex(1f)
                .offset(y = 45.dp, x = 15.dp)
                .align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}
@Preview(showSystemUi = false, showBackground = true)
@Composable
fun Preview(){
//    BottomNavBar()
}
data class BottomNavItems (
    val label: String,
    @DrawableRes val icon: Int,
    val route: Any
)



//dump


//@Composable
//fun BottomNavBar(
//    navController: NavController
//) {
//    NavigationBar (
//        modifier = Modifier
//            .shadow(
//                elevation = 12.dp
//            )
//            .background(Color.White)
//            ,
//
//        containerColor = Color.Transparent,
//    ){
//        val bottomNavigation = listOf(
//            BottomNavItems(
//                label = "Beranda",
//                icon = R.drawable.home_icon,
//                route = explore
//            ),
//            BottomNavItems(
//                label = "Aktivitas",
//                icon = R.drawable.activitas_icon,
//                route = aktivitas
//            ),
//            BottomNavItems(
//                label = "Pesanan",
//                icon = R.drawable.pesanan_icon,
//                route = pesanan
//            ),
//            BottomNavItems(
//                label = "Profile",
//                icon = profile_icon,
//                route = profile
//            )
//        )
//        val currentRoute = navController.currentBackStackEntry?.destination?.route
//        bottomNavigation.map {
//            NavigationBarItem(
//                selected = it.label == bottomNavigation[0].label,
//                onClick = {
//
//                },
//                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = it.label) },
//                colors = NavigationBarItemColors(selectedIconColor = Color.Red,Color.Red,Color.White,Color(0xFEB3B3B3),Color(0xFEB3B3B3),Color(0xFEB3B3B3),Color(0xFEB3B3B3)),
//                label = {Text(text = it.label)}
//            )
//
//        }
//    }
//}
