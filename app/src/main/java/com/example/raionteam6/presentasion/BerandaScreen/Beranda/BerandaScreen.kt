package com.example.raionteam6.presentasion.BerandaScreen.Beranda

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.Datalocal.DataSource
import com.example.raionteam6.Datalocal.TokoMakanan
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily
import com.example.raionteam6.presentasion.theme.ui.poppinsFontFamily
import java.util.Vector
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.zIndex
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.raionteam6.Datalocal.BottomNavItems

@Composable
fun BerandaScreen(navController: NavController, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banner_makan),
                    contentDescription = "Bannermakan",
                    modifier = Modifier
                        .size(width = 412.dp, height = 169.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kategori Makanan",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = SFProdisplayFontFamily
                )

                Text(
                    text = "Lihat Semua",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { navController.navigate("Kategori_Screen") }
                )
            }

            // Categories row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FoodCategoryItem("Ayam", R.drawable.ayam_icon)
                FoodCategoryItem("Roti", R.drawable.roti_icon)
                FoodCategoryItem("Sayur", R.drawable.sayur_icon)
                FoodCategoryItem("Ricebox", R.drawable.ricebox_icon)
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Paling Banyak Dibeli",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = SFProdisplayFontFamily,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Mencari makanan favorit ?",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = SFProdisplayFontFamily,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Lihat daftar makanan \n" +
                                "yang paling banyak dibeli! ?",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontFamily = SFProdisplayFontFamily,
                        fontSize = 13.sp,
                        modifier = Modifier.offset(y = 30.dp)
                    )

                    Button(
                        onClick = { navController.navigate("MostBuy_Screen") },
                        colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F)),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .width(137.dp)
                            .height(45.dp)
                            .offset(y = 80.dp)
                    ) {
                        Text(
                            text = "Cari",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = SFProdisplayFontFamily
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.cewe_ramen),
                        contentDescription = "Illustration",
                        modifier = Modifier.size(width = 200.dp, height = 130.dp)
                            .align(Alignment.Center)
                            .offset(x = 185.dp, y = 0.dp)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Restoran Terbaik",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = SFProdisplayFontFamily,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Itemlist(DataSource().loadToko())
        }

        FloatingSearchBar()
    }
}



@Composable
fun FoodCategoryItem(name: String, iconRes: Int) {
    Column (
       modifier = Modifier
           .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape
                )
                .background(White, CircleShape)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = name,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = name,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontFamily = SFProdisplayFontFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .size(width = 372.dp, height = 56.dp),
        colors = ButtonDefaults.buttonColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "Mau makan apa hari ini?",
                fontFamily = SFProdisplayFontFamily,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
@Composable
fun Itemlist(tokoMakanan: List<TokoMakanan>){
    LazyVerticalGrid (
        columns = GridCells.Fixed(1)
    ){
        items(tokoMakanan){
            ItemCard(it, Modifier.fillMaxHeight().fillMaxWidth())
        }
    }
}
@Composable
fun ItemCard(tokoMakanan: TokoMakanan, modifier: Modifier = Modifier){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .shadow(shape = RoundedCornerShape(24.dp),
                elevation = 4.dp)
            .clickable {  },
        colors = CardDefaults.cardColors(Color.White),
        shape = (RoundedCornerShape(24.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Image Section
            Image(
                painter = painterResource(tokoMakanan.gambar_toko),
                contentDescription = "toko Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(90.dp)

            )

            Spacer(modifier = Modifier.width(8.dp))

            // Text and Icon Section
            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Text(
                    text = tokoMakanan.nama_toko,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = SFProdisplayFontFamily
                )

                Text(
                    text = tokoMakanan.nama_jalan,
                    fontSize = 12.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = SFProdisplayFontFamily
                )

                Spacer(modifier = Modifier.height(21.dp))

                // Like and Time Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.ThumbUp,
                        contentDescription = "Likes",
                        tint = Color(0xFFC63433),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "40", fontSize = 12.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.time_icon),
                        contentDescription = "Time",
                        tint = Color(0xFFC63433),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "00.00-23.59", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}
@Composable
fun FloatingSearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = (140).dp) // Mengatur posisi search bar agar melayang
            .zIndex(1f) // Menempatkan di atas elemen lain
    ) {
        SearchBar()
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
}


