package com.example.raionteam6.presentasion.BerandaScreen.Kategori

import androidx.navigation.NavController
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KategoriScreen(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
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
                text = "Kategori Makanan",
                fontFamily = SFProdisplayFontFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
        )
        {
            FoodCategoriesGrid()
        }
    }

}

@Composable
fun FoodCategoriesGrid(modifier: Modifier = Modifier) {
    // Define food categories
    val categories = listOf(
        FoodCategory("Ayam", ),
        FoodCategory("Sapi", ),
        FoodCategory("Bebek", ),
        FoodCategory("Desert", ),
        FoodCategory("Seafood", ),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(categories) {
            FoodCategoryItem(it)
        }
    }
}

@Composable
fun FoodCategoryItem(category: FoodCategory) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = CircleShape,
                )
                .background(Color.White, CircleShape)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sayur_icon),
                contentDescription = category.name,
                modifier = Modifier.fillMaxSize()
                    .size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.name,
            fontSize = 19.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontFamily = SFProdisplayFontFamily
        )
    }
}

// Data class for food categories
data class FoodCategory(
    val name: String
)
