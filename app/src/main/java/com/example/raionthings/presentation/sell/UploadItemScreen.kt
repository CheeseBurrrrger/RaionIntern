package com.example.raionthings.presentation.sell


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.raionthings.R
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.BottomNavBarv
import com.example.raionthings.presentation.navigation.explore
import com.example.raionthings.presentation.navigation.floatActionButton
import com.example.raionthings.presentation.navigation.successsell
import com.example.raionthings.presentation.navigation.uploadproduk
import com.example.raionthings.presentation.theme.ui.SFProdisplayFontFamily
import com.example.raionthings.utils.uriToByteArray
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UploadItemScreen(modifier: Modifier = Modifier, navController: NavController) {
//    SellScreen()
}
@Composable
fun SellScreen(
    userData: UserData?,
    navController: NavController
) {

//    var category by remember { mutableStateOf("") }
//    var productName by remember { mutableStateOf("") }
//    var productPrice by remember { mutableStateOf(0) }
//    var productStock by remember { mutableStateOf(0) }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var imageUrl by remember { mutableStateOf("") }
//    var forceRefresh by remember { mutableStateOf(0) }
//    var userProduk by remember { mutableStateOf<UserProduk?>(null) }
//    val context = LocalContext.current
//    var showCreatedPicker by remember { mutableStateOf(false) }
//    var showExpiredPicker by remember { mutableStateOf(false) }
//    var dateCreated by remember { mutableStateOf<Timestamp?>(null) }
//    var dateExpired by remember { mutableStateOf<Timestamp?>(null) }
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        imageUri = uri
//    }
//    if (showCreatedPicker) {
//        LaunchedEffect(Unit) {
//            val calendar = Calendar.getInstance()
//            android.app.DatePickerDialog(
//                context,
//                { _, year, month, day ->
//                    calendar.set(year, month, day)
//                    dateCreated = Timestamp(calendar.timeInMillis / 1000, 0)
//                    showCreatedPicker = false
//                },
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }
//    }
//    if (showExpiredPicker) {
//        android.app.DatePickerDialog(
//            context,
//            { _, year, month, day ->
//                val calendar = Calendar.getInstance().apply {
//                    set(year, month, day)
//                }
//                dateExpired = Timestamp(calendar.timeInMillis / 1000, 0)
//                showExpiredPicker = false
//            },
//            Calendar.getInstance().get(Calendar.YEAR),
//            Calendar.getInstance().get(Calendar.MONTH),
//            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        ).show()
//    }
//    fun loadProfilePicture() {
//        if (userData != null) {
//            sellViewModel().readProductPicture(userData,productName){ newUrl ->
//                imageUrl = if (newUrl.isNotEmpty()) {
//                    "$newUrl?timestamp=${System.currentTimeMillis()}"
//                } else {
//                    newUrl
//                }
//
//            }
//        }
//    }
//    LaunchedEffect(forceRefresh) {
//        Toast.makeText(
//            context,
//            "Refresh success",
//            Toast.LENGTH_SHORT
//        ).show()
//        loadProfilePicture()
//    }
//
//    Column(
//        modifier = Modifier
//            .padding(vertical = 40.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Jual",
//            fontFamily = SFProdisplayFontFamily,
//            fontSize = 25.sp,
//            fontWeight = FontWeight.Bold,
//
//            )
//        Spacer(modifier = Modifier.height(30.dp))
//        Box(
//            modifier = Modifier
//                .size(197.dp)
//                .clip(shape = RoundedCornerShape(34.dp))
//                .background(Color.LightGray),
//            contentAlignment = Alignment.Center,
//        ) {
//            if(imageUrl.isNotEmpty()) {
//                AsyncImage(
//                    model = imageUrl,
//                    contentDescription = "Profile picture",
//                    modifier = Modifier
//                        .size(150.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Fit
//                )
//            }
//            else {
//                Icon(Icons.Default.Add, contentDescription = "Add Image", Modifier.clickable {
//                    if (imageUri == null) {
//                        launcher.launch("image/*")
//                    }
//                })
//            }
//        Spacer(modifier = Modifier.height(16.dp))
//        Column (
//            modifier = Modifier
//                .fillMaxWidth()
//        ){
//            Text(
//                modifier = Modifier.padding(horizontal = 20.dp),
//                text = "Nama Produk",
//                fontSize = 16.sp,
//                fontFamily = SFProdisplayFontFamily
//            )
//            OutlinedTextField(
//                value = productName,
//                onValueChange = {productName = it},
//                placeholder = {Text(
//                    text = "Masukan nama produk",
//                    color = Color(0xFF757575),
//                    fontFamily = SFProdisplayFontFamily)},
//                trailingIcon = {
//                    Icon(
//                        painter = painterResource(R.drawable.cil_pencil),
//                        contentDescription = "Pencil Icon",
//                        modifier = Modifier.size(14.8.dp),
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 12.dp, vertical = 10.dp)
//                    .size(width = 372.dp, height = 56.dp)
//                    .border(
//                        width = 2.dp,
//                        color = Color.LightGray,
//                        shape = RoundedCornerShape(24.dp)
//                    ),
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
//                shape = RoundedCornerShape(24.dp)
//            )
//            Text(
//                modifier = Modifier.padding(horizontal = 20.dp),
//                text = "Harga",
//                fontSize = 16.sp,
//                fontFamily = SFProdisplayFontFamily
//            )
//            OutlinedTextField(
//                value = "$productPrice",
//                onValueChange = {productPrice = it.toIntOrNull()!! },
//                placeholder = {Text(
//                    text = "Rp. xxxxx",
//                    color = Color(0xFF757575),
//                    fontFamily = SFProdisplayFontFamily)},
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                trailingIcon = {
//                    Icon(
//                        painter = painterResource(R.drawable.cil_pencil),
//                        contentDescription = "pencil Icon",
//                        modifier = Modifier.size(14.8.dp),
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 12.dp, vertical = 10.dp)
//                    .size(width = 372.dp, height = 56.dp)
//                    .border(
//                        width = 2.dp,
//                        color = Color.LightGray,
//                        shape = RoundedCornerShape(24.dp)
//                    ),
//                singleLine = true,
//                shape = RoundedCornerShape(24.dp)
//            )
//            Dropdownfield()
//        }
//        Row(
//            horizontalArrangement = Arrangement.SpaceEvenly,
//        ){
//            Text(
//                modifier = Modifier
//                    .padding(horizontal = 20.dp)
//                    .offset(x = -52.dp)
//                    .clickable {
//                        showCreatedPicker = true
//                    },
//                text = "Tanggal Produksi",
//                fontSize = 16.sp,
//                fontFamily = SFProdisplayFontFamily
//            )
//
//            Text(
//                modifier = Modifier
//                    .padding(horizontal = 20.dp)
//                    .offset(x = -8.dp)
//                    .clickable { showExpiredPicker = true },
//                text = "Baik Sebelum",
//                fontSize = 16.sp,
//                fontFamily = SFProdisplayFontFamily
//            )
//        }
//        Row(
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            OutlinedTextField(
//                value = "",
//                onValueChange = {it},
//                placeholder = {Text(
//                    text = "DD/MM",
//                    color = Color(0xFF757575),
//                    fontFamily = SFProdisplayFontFamily)},
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                trailingIcon = {
//                    Icon(
//                        painter = painterResource(R.drawable.cil_pencil),
//                        contentDescription = "pencil Icon",
//                        modifier = Modifier.size(14.8.dp),
//                    )
//                },
//                modifier = Modifier
//                    .padding(horizontal = 12.dp, vertical = 10.dp)
//                    .size(width = 177.dp, height = 56.dp)
//                    .border(
//                        width = 2.dp,
//                        color = Color.LightGray,
//                        shape = RoundedCornerShape(24.dp)
//                    ),
//                singleLine = true,
//                shape = RoundedCornerShape(24.dp)
//            )
//            OutlinedTextField(
//                value = dateExpired.toString(),
//                onValueChange = {},
//                placeholder = {Text(
//                    text = "DD/MM",
//                    color = Color(0xFF757575),
//                    fontFamily = SFProdisplayFontFamily)},
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                trailingIcon = {
//                    Icon(
//                        painter = painterResource(R.drawable.cil_pencil),
//                        contentDescription = "pencil Icon",
//                        modifier = Modifier.size(14.8.dp),
//                    )
//                },
//                modifier = Modifier
//                    .padding(horizontal = 12.dp, vertical = 10.dp)
//                    .size(width = 177.dp, height = 56.dp)
//                    .border(
//                        width = 2.dp,
//                        color = Color.LightGray,
//                        shape = RoundedCornerShape(24.dp)
//                    ),
//                singleLine = true,
//                shape = RoundedCornerShape(24.dp)
//            )
//        }
//        NumberStepper()
//        Spacer(modifier = Modifier.height(6.dp))
//        Button(
//            onClick = { navController.navigate(successsell)
//            },
//            modifier = Modifier
//                .size(width = 372.dp, height = 56.dp),
//            colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
//            shape = RoundedCornerShape(24.dp)
//        ) {
//            Text(
//                text = "Jual",
//                fontFamily = SFProdisplayFontFamily,
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp
//            )
//        }
//    }
//}
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf(0) }
    var productStock by remember { mutableStateOf(0) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }
    var TanggalProduksi by remember { mutableStateOf("") }
    val context = LocalContext.current
    var showCreatedPicker by remember { mutableStateOf(false) }
    var showExpiredPicker by remember { mutableStateOf(false) }
    var dateCreated by remember { mutableStateOf<Timestamp?>(null) }
    var dateExpired by remember { mutableStateOf<Timestamp?>(null) }
    var forceRefresh by remember { mutableStateOf(0) }
    var userProduk by remember { mutableStateOf<UserProduk?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    if (showCreatedPicker) {
        DatePickerDialog(
            showDialog = true,
            onDateSelected = { timestamp ->
                dateCreated = timestamp
                showCreatedPicker = false
            },
            onDismiss = { showCreatedPicker = false }
        )
    }

    if (showExpiredPicker) {
        DatePickerDialog(
            showDialog = true,
            onDateSelected = { timestamp ->
                dateExpired = timestamp
                showExpiredPicker = false
            },
            onDismiss = { showExpiredPicker = false }
        )
    }
    fun loadProfilePicture() {
        if (userData != null) {
            sellViewModel().readProductPicture(userData,productName){ newUrl ->
                imageUrl = if (newUrl.isNotEmpty()) {
                    "$newUrl?timestamp=${System.currentTimeMillis()}"
                } else {
                    newUrl
                }

            }
        }
    }
    if (imageUrl==null){
        val imageByteArray = imageUri?.uriToByteArray(context)
        imageByteArray?.let {
            if (userData != null) {

            }
        }
    }
    LaunchedEffect(forceRefresh) {
        loadProfilePicture()
    }
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                .background(Color.LightGray)
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center,
        ) {
            if(imageUri.toString()!=null){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = imageUri
                    ),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            else{
                Icon(Icons.Default.Add, contentDescription = "Add Image")
            }
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
                value = "$productPrice",
                onValueChange = {productPrice = it.toIntOrNull()!!},
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
            Log.d("LogDate", "Date created: $dateCreated Date Expired: $dateExpired")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.clickable {
                showCreatedPicker = true
            }){
                OutlinedTextField(
                    value = dateCreated?.toDate()?.formatAsDate() ?: "",
                    onValueChange = {TanggalProduksi = it},
                    enabled = false,
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
            Box(Modifier.clickable {
                showExpiredPicker = true
            }){
                OutlinedTextField(
                    value = dateExpired?.toDate()?.formatAsDate() ?: "",
                    onValueChange = {},
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
                    enabled = false,
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp)
                )
            }
        }
        NumberStepper(
            initialValue = productStock,
            onValueChange = {newValue ->
                productStock = newValue
            },
            minValue = 0,
            maxValue = 20
        )
        Log.d("CekStok", productStock.toString())
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                val imageByteArray = imageUri?.uriToByteArray(context)
                if (userData != null) {
                    if (imageByteArray != null) {
                        sellViewModel().singleProductPicture(userData, productName,imageByteArray,context)
                        imageUri=null
                    }
                    loadProfilePicture()
                }

                if (userData != null && imageUri==null) {
                            dateCreated?.let {
                                dateExpired?.let { it1 ->
                                    sellViewModel().addDagang(
                                        namaProduk = productName,
                                        stokProduk = productStock,
                                        hargaProduk = productPrice,
                                        userData = userData,
                                        dateCreated = it,
                                        dateExpired = it1,
                                        context = context,
                                        productPictureUrl = imageUrl
                                    )
                                }
                            }
                        }

                        Log.d("ProductInput", "Nama: $productName, \nHarga: $productPrice, \nStok: $productStock" +
                                "\ntanggal produksi: ${dateCreated?.toDate()} \ntanggal expired: ${dateExpired?.toDate()}")
                        navController.navigate(successsell)
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
fun Date.formatAsDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSellScreen() {
//    var navController = rememberNavController()
//    UploadFirstItemScreen(navController)
//    SuccessUploadItem()
//    SellScreen()
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
                    modifier = Modifier
                        .size(14.8.dp)
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
    initialValue: Int ,
    onValueChange: (Int) -> Unit = {},
    minValue: Int,
    maxValue: Int
) {
    var value by remember { mutableStateOf(initialValue.toString()) }

    Surface(
        modifier = Modifier .padding(horizontal = 16.dp, vertical = 8.dp),
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
fun UploadFirstItemScreen(
    navController: NavController
){
    Scaffold(
        bottomBar = { BottomNavBarv(navController) },
        floatingActionButton = {floatActionButton(navController)}
    ) { paddingvalues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
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
                    onClick = {navController.navigate(uploadproduk)
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
fun SuccessUploadItem(
    navController: NavController
){
    Column (
        modifier = Modifier
            .background(color = Color(0xFFEDC0C0))
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = 160.dp)

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
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {navController.navigate(explore)
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