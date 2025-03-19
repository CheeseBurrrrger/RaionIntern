package com.example.raionteam6.presentasion.BottomNavbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.rememberNavController
import com.example.raionteam6.Datalocal.BottomNavItems
import com.example.raionteam6.R
import com.example.raionteam6.R.drawable.profile_icon
import com.example.raionteam6.presentasion.BerandaScreen.Beranda.FloatingSearchBar

@Composable
fun BottomNavBar() {
    NavigationBar (
        modifier = Modifier
            .shadow(
                elevation = 12.dp
            )
            .background(Color.White),
        containerColor = Color.White
    ){
        val bottomNavigation = listOf(
            BottomNavItems(
                label = "Beranda",
                icon = R.drawable.home_icon,
            ),
            BottomNavItems(
                label = "Aktivitas",
                icon = R.drawable.activitas_icon,
            ),
            BottomNavItems(
                label = "Pesanan",
                icon = R.drawable.pesanan_icon,
            ),
            BottomNavItems(
                label = "Profile",
                icon = profile_icon
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = it.label == bottomNavigation[0].label,
                onClick = { },
                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = it.label) },
                label = {Text(text = it.label)}
            )

        }
    }
}

@Composable
fun floatActionButton(modifier: Modifier = Modifier, navController: NavController = rememberNavController()) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        FloatingActionButton(
            onClick = {},
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
                tint = Color.White,
            )
        }
    }
}
@Preview(showSystemUi = false, showBackground = true)
@Composable
fun Preview(){
    BottomNavBar()
}