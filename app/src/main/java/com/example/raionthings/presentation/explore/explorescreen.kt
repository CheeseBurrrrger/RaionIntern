package com.example.raionthings.presentation.explore

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.readprofile
import com.example.raionthings.presentation.navigation.sell
import com.example.raionthings.presentation.sell.sellViewModel

@Composable
fun ExploreScreen(
    navController: NavController,
    userData: UserData,
    onSignOut: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
Column (modifier = Modifier
    .fillMaxSize(),
    verticalArrangement = Arrangement.Bottom){
    TextButton(onClick = {
        sellViewModel().getAllDagangByUser(
            userData,
            onSuccess = { dagangList ->
                Log.d("Firestore", "User's products: $dagangList")
            },
            onFailure = { e ->
                Log.e("Firestore", "Error getting products", e)
            })
    }) {
        Text("buat log all product by user")
    }
    TextButton(onClick = {
        sellViewModel().getSpecificDagangItemsByNameExceptMe(
            userData,
            onSuccess = { dagangList ->
                Log.d("Firestore", "User's products: $dagangList")
            },
            onFailure = { e ->
                Log.e("Firestore", "Error getting products", e)
            },
            productName = productName
        )
    }) {
        Text("buat log all product except me pake nama produk")
    }
    TextField(
        value = productName,
        onValueChange = {productName=it}
    )
    TextButton(onClick = {
        sellViewModel().getDagangItem(
            userData,
            onSuccess = { dagangList ->
                Log.d("Firestore", "User's products: $dagangList")
            },
            onFailure = { e ->
                Log.e("Firestore", "Error getting products", e)
            },
            productName = productName
            )
    }) {
        Text("buat log single product by nama produk")
    }
    TextButton(onClick = {
        sellViewModel().getAllDagangExceptSeller(
            userData,
            onSuccess = { dagangList ->
                Log.d("Firestore", "User's products: $dagangList")
            },
            onFailure = { e ->
                Log.e("Firestore", "Error getting products", e)
            }
        )
    }) {
        Text("buat log all product except yg jual")
    }
    Button(onClick = onSignOut) {
        Text("Sign Out")
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            ,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = {}) {
            Text("Ber")
        }
        Button(onClick = {}) {
            Text("Akt")
        }
        Button(onClick = {navController.navigate(sell)}) {
            Text("add")
        }
        Button(onClick = {}) {
            Text("pes")
        }
        Button(onClick = {navController.navigate(readprofile)}) {
            Text("prof")
        }
    }
}



}

