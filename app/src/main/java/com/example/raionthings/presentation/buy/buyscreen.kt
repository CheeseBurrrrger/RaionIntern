@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.raionthings.presentation.buy

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.raionthings.R
import com.example.raionthings.presentation.explore.ImageCard
import com.example.raionthings.presentation.explore.convertTimestampToDate
import com.example.raionthings.presentation.explore.menuPopUp
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.sell.UserProduk
import com.example.raionthings.presentation.sell.sellViewModel
import com.example.raionthings.presentation.theme.ui.SFProdisplayFontFamily

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BuyScreen(
    userData: UserData,
    navController: NavController,
    viewModel: sellViewModel
) {
    val context = LocalContext.current
    var products by remember { mutableStateOf<List<UserProduk>>(emptyList()) }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var selectedProduct by rememberSaveable { mutableStateOf<UserProduk?>(null) }
    val openBottomSheet = { product: UserProduk ->
        selectedProduct = product
        isSheetOpen = true
        Log.d("produkk","produkkk: $product, is sheet open? $isSheetOpen")
    }
    var timestamp by remember { mutableStateOf(System.currentTimeMillis()) }

    LaunchedEffect(Unit) {
        sellViewModel().getAllDagangByUser(
            userData,
            onSuccess = { pedagangs ->
                products = pedagangs
                Log.d("DEBUG", "Products list: $products")
            },
            onFailure = {}
        )
    }
    LaunchedEffect(userData.profilePictureUrl) {
        timestamp = System.currentTimeMillis()
    }

//    getAllDagangByUser()
    Log.d("cek pass value", "seller: $userData")
    Log.d("cek pass value", sellViewModel().getAllDagangByUser(userData,onSuccess = { dagang ->
        products = dagang
        Log.d("DEBUGss", "Sellers list: $products")
    }, onFailure = { e ->
        Log.e("Firestore", "Error getting products", e)
    }).toString())

    LazyColumn (modifier = Modifier
        .background(Color(0xFFF5F5F5))){
        item {
            TopNav(navController)
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .offset(0.dp,-30.dp),
                model = "${userData.profilePictureUrl}?timestamp=$timestamp",
                contentDescription = "banner resto",
                contentScale = ContentScale.Crop
            )
            AboutResto(userData)
            Spacer(Modifier.height(80.dp))
            Text("Paling Banyak Dibeli",style =  MaterialTheme.typography.titleMedium, modifier = Modifier.padding(22.dp,0.dp) )
            Spacer(Modifier.height(10.dp))
        }
        items(products.size) { index ->
            val product = products[index]
            product.namaProduk?.let { name ->
                ImageCard(
                    seller = "${product.productPictureUrl}?timestamp=$timestamp",
                    contentDescription = "Product",
                    title = name,
                    harga = product.hargaProduk,
                    expired = product.dateExpired?.let { convertTimestampToDate(it) } ?: "Tidak ada kadaluarsa",
                    onClick = { openBottomSheet(product) })
                }
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {

        }
    }
    if (isSheetOpen) {
        selectedProduct?.let {
            menuPopUp(
                isSheetOpen = isSheetOpen,
                onDismissRequest = {
                    isSheetOpen = false
                    selectedProduct = null
                                   },
                product = it
            )
        }
    }
}

@Composable
fun TopNav(
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(89.dp)
        .zIndex(1f)){
        Image(
            painter = painterResource(R.drawable.arrow_back),
            contentDescription = "go back",
            modifier = Modifier
                .offset(22.dp,22.dp)
                .size(20.dp)
                .clickable {
                    try {
                        navController.popBackStack()
                    } catch (e: Exception) {
                        Log.e("Navigation", "Error navigating back", e)
                    }
                }

        )
        Image(
            painter = painterResource(R.drawable.info),
            contentDescription = "info about this resto",
            modifier = Modifier
                .offset(320.dp,20.dp)
                .size(23.dp)
        )
    }
}
@Composable
fun AboutResto(
    userData: UserData
){
    Box (modifier = Modifier
            .padding(20.dp,0.dp)
            .offset(0.dp,-10.dp)
            .height(30.dp))
    {
        userData.username?.let { Text(
            it.replace("+"," "),style =  MaterialTheme.typography.titleLarge, color = Color.Black
            ) }
        Image(
            painter = painterResource(R.drawable.mapgroup),
            contentScale = ContentScale.Fit,
            contentDescription = "location vector",
            modifier = Modifier
                .size(16.dp)
                .offset(0.dp,70.dp)
        )
        Image(
            painter = painterResource(R.drawable.time_icon),
            contentScale = ContentScale.Fit,
            contentDescription = "location vector",
            modifier = Modifier
                .size(15.dp)
                .offset(0.dp,97.dp)
        )
        Image(
            painter = painterResource(R.drawable.thumbdown),
            contentScale = ContentScale.Fit,
            contentDescription = "location vector",
            modifier = Modifier
                .size(14.dp)
                .offset(230.dp,97.dp)
        )
        Image(
            painter = painterResource(R.drawable.thumbup),
            contentScale = ContentScale.Fit,
            contentDescription = "location vector",
            modifier = Modifier
                .size(14.dp)
                .offset(274.dp,97.dp)
        )
        Text("40",style =  MaterialTheme.typography.labelLarge, modifier = Modifier.offset(248.dp,97.dp))
        Text("190",style =  MaterialTheme.typography.labelLarge, modifier = Modifier.offset(294.dp,97.dp))
        Text("Lihat lokasi", fontFamily = SFProdisplayFontFamily, textDecoration = TextDecoration.Underline, color = Color.Red, fontSize = 13.sp, modifier = Modifier.offset(22.dp,70.dp).clickable {  })
        Text(userData.address.toString().replace("+"," "), modifier = Modifier
            .offset(0.dp,35.dp))
        Text("00.00-23.59", modifier = Modifier.offset(22.dp,97.dp))
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun imageCardPopUp(
    product: UserProduk?
){
    var qty by remember { mutableStateOf(0) }
    Column (modifier = Modifier
        .padding(14.dp,0.dp)){
        Card (
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ){
            Box(modifier=Modifier.height(200.dp)){
                if (product != null) {
                    AsyncImage(
                        model = product.productPictureUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Box(modifier = Modifier.fillMaxSize()
                    ){
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp,24.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            if (product != null) {
                val harga = product.hargaProduk.toString()
                Text(product.namaProduk, fontSize = 20.sp, )
                Text("Rp$harga", fontSize = 20.sp)
            }
        }
        Box(modifier = Modifier
            .padding(10.dp,0.dp)){
            Column (){
                Text("Tanggal produksi", fontSize = 15.sp)
                if (product != null) {
                    val expiredText = product.dateExpired?.let { convertTimestampToDate(it) } ?: "No expiry"
                    Text(convertTimestampToDate(product.dateCreated), fontSize = 15.sp)
                    Spacer(modifier = Modifier .height(16.dp))
                    Text("Baik sebelum", fontSize = 15.sp)
                    Text(expiredText, fontSize = 15.sp)
                }
                Spacer(Modifier.height(20.dp))
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(width = 1.dp,Color.Red, RoundedCornerShape(25.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = {
                        if(qty>0){
                            qty--
                        }
                    },
                        modifier = Modifier
                            .padding(2.dp,0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black)) {
                        Image(painterResource(R.drawable.minus), contentDescription = null,Modifier.width(15.dp).fillMaxHeight(), contentScale = ContentScale.FillWidth)
                    }
                    Box (
                        modifier = Modifier
                            .width(130.dp)
                            .height(30.dp)
                            .border(width = 0.7.dp, color = Color.Red,shape = RoundedCornerShape(5.dp))
                        , contentAlignment = Alignment.Center
                    ){
                        Text(
                            qty.toString(),
                            textAlign = TextAlign.Center)
                    }
                    Button(onClick = {qty++},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black),
                        modifier = Modifier
                            .padding(2.dp,0.dp)) {
                        Image(painterResource(R.drawable.plus), contentDescription = null,Modifier.width(15.dp).fillMaxHeight(), contentScale = ContentScale.FillWidth)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonColors(Color.Red, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.Red)
                ) {
                    Text("Tambahkan ke keranjang", fontSize = 20.sp,style =  MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}






