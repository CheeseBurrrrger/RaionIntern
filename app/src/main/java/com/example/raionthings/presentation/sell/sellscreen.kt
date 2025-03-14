package com.example.raionthings.presentation.sell

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.raionthings.R
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.navigation.explore
import com.google.firebase.Timestamp
import java.util.Calendar

@Composable
fun SellScreen(
    userData: UserData?,
    navController: NavController
    ) {
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf(0) }
    var productStock by remember { mutableStateOf(0) }
    var dateCreated by remember { mutableStateOf<Timestamp?>(null) }
    var dateExpired by remember { mutableStateOf<Timestamp?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box {
        Column(modifier = Modifier.padding(16.dp)) {
            ProductInputFields(
                productName = productName,
                onProductNameChange = { productName = it },
                productPrice = productPrice,
                onProductPriceChange = { productPrice = it },
                productStock = productStock,
                onProductStockChange = { productStock = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DateThing(dateCreated = dateCreated,
                dateExpired = dateExpired,
                onDateCreatedChange = { dateCreated = it },
                onDateExpiredChange = { dateExpired = it })
            Spacer(modifier = Modifier.height(16.dp))
            if (imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            }else {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.Black),
                    contentScale = ContentScale.Fit
                )
            }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                imageUri = uri
            }
            if (imageUri==null){
                Button(onClick = { launcher.launch("image/*") }) {
                    Text("Change Profile Picture")
                }
            }
//            if (imageUri != null) {
//                Button(onClick = {
//                    val imageByteArray = imageUri?.uriToByteArray(context)
//                    imageByteArray?.let {
//                        if (userData != null) {
//                            ProfileViewModel().uploadProfilePicture(
//                                userData,
//                                imageByteArray,
//                                context
//                            )
//                            forceRefresh++
//                            imageUri=null
//                        }
//                    }
//                }) {
//                    Text("Upload Image")
//                }
//            }
            Button(onClick = {
                if (userData != null) {
                    dateCreated?.let {
                        dateExpired?.let { it1 ->
                            sellViewModel().addDagang(
                                namaProduk = productName,
                                stokProduk = productStock,
                                hargaProduk = productPrice,
                                userData = userData,
                                dateCreated = it,
                                dateExpired = it1,
                                context = context
                            )
                        }
                    }
                }
                Log.d("ProductInput", "Nama: $productName, \nHarga: $productPrice, \nStok: $productStock" +
                        "\ntanggal produksi: ${dateCreated?.toDate()} \ntanggal expired: ${dateExpired?.toDate()}")
                navController.navigate(explore)
            }) {
                Text(text = "Simpan Produk")
            }
        }
    }

}
@Composable
fun ProductInputFields(
    productName: String,
    onProductNameChange: (String) -> Unit,
    productPrice: Int,
    onProductPriceChange: (Int) -> Unit,
    productStock: Int,
    onProductStockChange: (Int) -> Unit
) {
    Box {
        Column(modifier = Modifier.padding(16.dp)
            .wrapContentSize()) {
            // Nama Produk
            OutlinedTextField(
                value = productName,
                onValueChange = { onProductNameChange(it) },
                label = { Text("Nama Produk") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            // Harga Produk
            OutlinedTextField(
                value = productPrice.toString(),
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        onProductPriceChange(it.toIntOrNull() ?: 0) // Convert safely
                    }
                },
                label = { Text("Harga Produk") },
                modifier = Modifier,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            // Stok Produk
            OutlinedTextField(
                value = productStock.toString(),
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        onProductStockChange(it.toIntOrNull() ?: 0) // Convert safely
                                                            }
                                },
                label = { Text("Stok Produk") },
                modifier = Modifier,
                singleLine = true
            )

        }
    }

}

@Composable
fun DateThing(
    dateCreated: Timestamp?,
    dateExpired: Timestamp?,
    onDateCreatedChange: (Timestamp) -> Unit,
    onDateExpiredChange: (Timestamp) -> Unit
){
       Column(modifier = Modifier.wrapContentSize().padding(16.dp)) {
        Text(text = "Pilih Tanggal Dibuat")
        DatePickerExample { selectedDate -> onDateCreatedChange(selectedDate) }
        Spacer(Modifier.height(10.dp))
        Text(text = "Pilih Tanggal Kedaluwarsa")
        DatePickerExample { selectedDate -> onDateExpiredChange(selectedDate) }

    }
}

@Composable
fun DatePickerExample(
    onDateSelected: (Timestamp) -> Unit
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }
    var timestamp by remember { mutableStateOf<Timestamp?>(null) }
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            // Format tanggal
            val formattedDate = "$selectedDay-${selectedMonth + 1}-$selectedYear"
            selectedDate = formattedDate

            // Konversi ke Timestamp Firestore
            val calendarSelected = Calendar.getInstance().apply {
                set(selectedYear, selectedMonth, selectedDay)
                Log.d("cekValue", "$selectedYear $selectedDay $selectedMonth")
            }
            timestamp = Timestamp(calendarSelected.time)
            timestamp?.let { onDateSelected(it) } // Kirim hasil ke parent composable
        },
        year, month, day
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Selected Date: $selectedDate")
        Button(onClick = { datePickerDialog.show() }) {
            Text(text = "Pick a Date")
            }
       }
}
//OutlinedTextField(
//value = productStock,
//onValueChange = {
//    if (it.all { char -> char.isDigit() }) onProductStockChange(it)
//},
//label = { Text("Stok Produk") },
//modifier = Modifier,
//singleLine = true
//)