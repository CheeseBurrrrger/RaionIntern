package com.example.raionteam6.presentasion.BerandaScreen.MostBuyScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.raionteam6.Datalocal.DataSource
import com.example.raionteam6.Datalocal.TokoMakanan
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.BerandaScreen.Beranda.ItemCard
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily

@Composable
fun MostBuyScreen(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 16.dp)

                .fillMaxWidth()
                .background(Color.White),
        ){

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(width = 32.dp, height = 32.dp)
                    .clickable { navController.navigate("Beranda_Screen") }
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Paling Banyak Dibeli",
                fontFamily = SFProdisplayFontFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Itemlist(DataSource().loadToko())
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