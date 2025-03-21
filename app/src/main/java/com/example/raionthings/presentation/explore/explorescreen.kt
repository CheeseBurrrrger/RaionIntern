@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.raionthings.presentation.explore

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.raionthings.R
import com.example.raionthings.presentation.buy.imageCardPopUp
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.readprofile
import com.example.raionthings.presentation.navigation.sellfirst
import com.example.raionthings.presentation.sell.UserProduk
import com.example.raionthings.presentation.sell.sellViewModel
import com.google.firebase.Timestamp
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ExploreScreen(
    navController: NavController,
    userData: UserData,
    onSignOut: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var sellers by remember { mutableStateOf<List<UserData>>(emptyList()) }

    LaunchedEffect(Unit) {
        sellViewModel().getAllSeller(
            userData,
            onSuccess = { pedagangs ->
                sellers = pedagangs
                Log.d("DEBUG", "Sellers list: $sellers")
            },
            onFailure = {}
        )
    }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))){
        LazyColumn (modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ){

            item {  TextButton(onClick = {
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

                TextButton(onClick = {
                    sellViewModel().getAllSeller(
                        userData,
                        onSuccess = {pedagangs ->
                            sellers = pedagangs
                            Log.d("DEBUG", "Sellers list: $sellers")
                        },
                        onFailure = {}
                    )
                }) {
                    Text("get all pedagangs")
                }
            }
            item() {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp,100.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                    ){
                    TextField(
                        value = productName,
                        onValueChange = {productName=it},
                    )
                }
            }
            items(sellers.size) { seller ->
                        sellers[seller].let {
                            sellers[seller].username?.let { it1 ->
                                ImageCard(
                                    seller = it,
                                    contentDescription = "Seller Profile",
                                    title = it1,
                                    navController
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
            item { Button(onClick = onSignOut) {
                Text("Sign Out")
            } }
            item{
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = {}) {
                        Text("Ber")
                    }
                    Button(onClick = {}) {
                        Text("Akt")
                    }
                    Button(onClick = {navController.navigate(sellfirst)}) {
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
    }
}




@Composable
fun ImageCard(
    seller: UserData,
    contentDescription: String,
    title: String,
    navController: NavController
){
    val gson = Gson()
    val sellerJson = URLEncoder.encode(gson.toJson(seller), StandardCharsets.UTF_8.toString())
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        onClick = {navController.navigate("buy/$sellerJson")}
    ){
        Box(modifier=Modifier
            .height(100.dp)
            .background(Color.White)){
            Box (modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(0.dp,0.dp)){
                AsyncImage(
                    model = "${seller.profilePictureUrl}?timestamp=${System.currentTimeMillis()}",
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                    ,
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier=Modifier
                    .fillMaxSize()
                    .padding(0.dp,12.dp)
                    .offset(120.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopStart){
                Text(title, style =  MaterialTheme.typography.titleMedium)
                Text(seller.address.toString(), style =  MaterialTheme.typography.titleSmall, modifier = Modifier
                    .offset(0.dp,25.dp))
                Image(
                    painter = painterResource(R.drawable.like),
                    contentDescription = "like",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .offset(0.dp,62.dp)
                        .size(14.dp)
                        .background(Color.Transparent)
                )
                Image(
                    painter = painterResource(R.drawable.time_icon),
                    contentDescription = "like",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .offset(54.dp,62.dp)
                        .size(14.dp)
                        .background(Color.Transparent),
                )
                Text("00.00-23.59", modifier = Modifier .offset(74.dp,62.dp),style =  MaterialTheme.typography.bodySmall)
                Text("100", modifier = Modifier.offset(18.dp,62.dp),style =  MaterialTheme.typography.bodySmall)
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun menuPopUp(
    isSheetOpen: Boolean,
    onDismissRequest: () -> Unit,
    product: UserProduk
){
    val sheetState = rememberModalBottomSheetState()
    var qty by remember { mutableStateOf(0) }

    if(isSheetOpen && product!=null){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            tonalElevation = 13.dp,
            shape = RoundedCornerShape(14.dp),
            containerColor = Color(0xfffefefe)
        ) {
            Box( modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp,-10.dp)
                .fillMaxHeight(0.65f)){
                imageCardPopUp(product)
            }
        }
    }
}

@Composable
fun ImageCard(
    seller: String,
    contentDescription: String,
    title: String,
    harga:Int,
    expired:String,
    onClick: ()->Unit
){
    val gson = Gson()
    val sellerJson = URLEncoder.encode(gson.toJson(seller), StandardCharsets.UTF_8.toString())
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        onClick = onClick
    ){
        Box(modifier=Modifier
            .height(100.dp)
            .background(Color.White)){
            Box (modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(0.dp,0.dp)){
                if(seller.isBlank()){
                    Image(
                        painter = painterResource(R.drawable.sajigobw),
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                        ,
                        contentScale = ContentScale.Fit
                    )
                }else{
                    AsyncImage(
                        model = seller,
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                        ,
                        contentScale = ContentScale.Crop
                    )
                }

            }
            Box(
                modifier=Modifier
                    .fillMaxSize()
                    .padding(10.dp,8.dp)
                    .offset(120.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopStart){
                Text(title, style =  MaterialTheme.typography.titleSmall, fontSize = 17.sp)
                Text("Baik sebelum: $expired", modifier = Modifier
                    .offset(0.dp,20.dp),style =  MaterialTheme.typography.bodySmall)
                Text("Rp$harga", modifier = Modifier
                    .offset(0.dp,60.dp),
                    style =  MaterialTheme.typography.titleSmall)
            }
            Button(onClick = {},
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(-8.dp,6.dp)
                    .padding(5.dp)
                    .size(20.dp),
                colors = ButtonColors(Color.Red, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.Red),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("+", color = Color.White, textAlign = TextAlign.Center)
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun convertTimestampToDate(timestamp: Timestamp): String {
    val instant = timestamp.toDate().toInstant()
    val formatter = DateTimeFormatter.ofPattern("dd/MM").withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}