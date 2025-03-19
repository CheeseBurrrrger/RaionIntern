package com.example.raionteam6.presentasion.TransactionScreen.buyScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.raionteam6.R
import com.example.raionteam6.presentasion.BottomNavbar.BottomNavBar
import com.example.raionteam6.presentasion.BottomNavbar.floatActionButton
import com.example.raionteam6.presentasion.theme.ui.SFProdisplayFontFamily

@Composable
fun UploadItemScreen(modifier: Modifier = Modifier, navController: NavController) {
    SellScreen()
}
@Composable
fun SellScreen() {
    var productName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var TanggalProduksi by remember { mutableStateOf("") }
    var Kadarluasa by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var productionDate by remember { mutableStateOf("") }
    var bestBeforeDate by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Jual",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,

        )
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .size(197.dp)
                .clip(shape = RoundedCornerShape(34.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Nama Produk",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = productName,
                onValueChange = {productName = it},
                placeholder = {Text(
                    text = "Masukan nama produk",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "Pencil Icon",
                        modifier = Modifier.size(14.8.dp),
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,

                shape = RoundedCornerShape(24.dp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Harga",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = price,
                onValueChange = {price = it},
                placeholder = {Text(
                    text = "Rp. xxxxx",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "pencil Icon",
                        modifier = Modifier.size(14.8.dp),
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,

                shape = RoundedCornerShape(24.dp)
            )
            Dropdownfield()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .offset(x = -52.dp),
                text = "Tanggal Produksi",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .offset(x = -8.dp),
                text = "Baik Sebelum",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = TanggalProduksi,
                onValueChange = {TanggalProduksi = it},
                placeholder = {Text(
                    text = "DD/MM",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "pencil Icon",
                        modifier = Modifier.size(14.8.dp),
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 177.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )
            OutlinedTextField(
                value = Kadarluasa,
                onValueChange = {Kadarluasa = it},
                placeholder = {Text(
                    text = "DD/MM",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cil_pencil),
                        contentDescription = "pencil Icon",
                        modifier = Modifier.size(14.8.dp),
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 177.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )
        }
        NumberStepper()
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .size(width = 372.dp, height = 56.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Jual",
                fontFamily = SFProdisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSellScreen() {
    UploadFirstItemScreen()
    //SuccessUploadItem()vvh
}
@Composable
fun Dropdownfield(){
    val categories = listOf(
        "Ayam", "Roti", "Ricebox", "Daging", "Kuah", "Sayur",
        "Seafood", "Fast Food", "Minuman", "Padang", "Gorengan", "Dessert"
    )
    var selectedCategory by remember { mutableStateOf("Pilih kategori produk") }
    var selectedIndex by remember { mutableStateOf(categories[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    var textFiledSize by remember { mutableStateOf(Size.Zero) }

    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Kategori",
            fontSize = 16.sp,
            fontFamily = SFProdisplayFontFamily
        )
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = {selectedCategory = it},
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.arrow_up_vector),
                    contentDescription = "Arrow Icon",
                    modifier = Modifier.size(14.8.dp)
                        .clickable { isExpanded = !isExpanded },
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .size(width = 372.dp, height = 56.dp)
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable { isExpanded = !isExpanded }
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
            shape = RoundedCornerShape(24.dp)
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = Color.White)
                .size(250.dp),
            offset = (DpOffset(x = 20.dp, y = 400.dp))

        ) {
            categories.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategory = label
                        isExpanded = false
                    },
                    text = {
                        Text(text = label)
                    },
                    modifier = Modifier.background(color = Color.White)
                )
            }
        }
//
    }
}

@Composable
fun NumberStepper(
    initialValue: Int = 0,
    onValueChange: (Int) -> Unit = {},
    minValue: Int = 0,
    maxValue: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf(initialValue.toString()) }

    Surface(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(28.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = Color(0xFFE57373) // Light red border color
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Minus button
            OutlinedButton(
                onClick = {
                    val currentValue = value.toIntOrNull() ?: 0
                    if (currentValue > minValue) {
                        val newValue = currentValue - 1
                        value = newValue.toString()
                        onValueChange(newValue)
                    }
                },
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFE57373)
                ),
                border = null,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    text = "−",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // Value text field
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    val intValue = newValue.toIntOrNull() ?: return@OutlinedTextField
                    if (intValue in minValue..maxValue) {
                        value = newValue
                        onValueChange(intValue)
                    }
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )

            // Plus button
            OutlinedButton(
                onClick = {
                    val currentValue = value.toIntOrNull() ?: 0
                    if (currentValue < maxValue) {
                        val newValue = currentValue + 1
                        value = newValue.toString()
                        onValueChange(newValue)
                    }
                },
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFE57373)
                ),
                border = null,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Composable
fun UploadFirstItemScreen(){
    Scaffold(
        bottomBar = { BottomNavBar() },
        floatingActionButton = {floatActionButton(navController = rememberNavController())}
    ) { paddingvalues ->
        Column (
            modifier = Modifier.fillMaxSize()
                .background(color = Color.White)
                .padding(paddingvalues)
                .padding(horizontal = 16.dp)
        ){
            Text(
                text = "Unggah Makanan",
                fontFamily = SFProdisplayFontFamily,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 25.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ){
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    text = "Siap untuk menjual",
                    fontFamily = SFProdisplayFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "produk kuliner terbaikmu",
                    fontFamily = SFProdisplayFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(39.dp))
                Image(
                    painter = painterResource(R.drawable.girl_eating_soup),
                    contentDescription = "girl eating soup",

                )
                Spacer(modifier = Modifier.height(39.dp))
                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .size(width = 372.dp, height = 56.dp)
                        .padding(horizontal = 12.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Text(
                        text = "Jual Sekarang",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }



        }
    }

}

@Composable
fun SuccessUploadItem(){
    Column (
        modifier = Modifier
            .background(color = Color(0xFFEDC0C0))
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 160.dp)

    ){
        Image(
            painter = painterResource(R.drawable.succes_guy_photo),
            contentDescription = "girl eating soup",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Berhasil Menggungah!",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Makanan berhasil diunggah! Sekarang produk",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "kulinermu siap dijual dan ditemukan oleh pembeli",
            fontFamily = SFProdisplayFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .size(width = 372.dp, height = 56.dp)
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text(
                text = "Lihat Pesanan",
                fontFamily = SFProdisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}