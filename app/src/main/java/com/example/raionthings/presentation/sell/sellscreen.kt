package com.example.raionthings.presentation.sell

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import java.util.Calendar

//@Composable
//fun SellScreen(
////    userData: UserData?,
////    navController: NavController
//    ) {
//    var productName by remember { mutableStateOf("") }
//    var productPrice by remember { mutableStateOf(0) }
//    var productStock by remember { mutableStateOf(0) }
//    var dateCreated by remember { mutableStateOf<Timestamp?>(null) }
//    var dateExpired by remember { mutableStateOf<Timestamp?>(null) }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var imageUrl by remember { mutableStateOf("") }
//    var forceRefresh by remember { mutableStateOf(0) }
//    var userProduk by remember { mutableStateOf<UserProduk?>(null) }
//
//    val context = LocalContext.current
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        imageUri = uri
//    }
//    fun loadProfilePicture() {
////    if (userData != null) {
////        sellViewModel().readProductPicture(userData,productName){ newUrl ->
////            imageUrl = if (newUrl.isNotEmpty()) {
////                "$newUrl?timestamp=${System.currentTimeMillis()}"
////            } else {
////                newUrl
////            }
////
////            }
////        }
////    }
//    LaunchedEffect(forceRefresh) {
//        Toast.makeText(
//            context,
//            "Refresh success",
//            Toast.LENGTH_SHORT
//        ).show()
//        loadProfilePicture()
////        if (userData != null) {
////            if (userData.username?.isNotEmpty() == true &&
////                userData.address?.isNotEmpty() == true &&
////                userData.profilePictureUrl?.isNotEmpty() == true){
////                navController.navigate(explore) {
////                    popUpTo(profile) { inclusive = true }
////                }
////            }
////        }
//    }
//
//            Box {
//                Column(modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//                    .padding(16.dp)) {
//
//                    if(imageUrl.isNotEmpty()) {
//                        AsyncImage(
//                            model = imageUrl,
//                            contentDescription = "Profile picture",
//                            modifier = Modifier
//                                .size(150.dp)
//                                .clip(CircleShape),
//                            contentScale = ContentScale.Fit
//                        )
//}
//
//                    ProductInputFields(
//                        productName = productName,
//                        onProductNameChange = { productName = it },
//                        productPrice = productPrice,
//                        onProductPriceChange = { productPrice = it },
//                        productStock = productStock,
//                        onProductStockChange = { productStock = it }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
////                    DateThing(
////                        onDateCreatedChange = { dateCreated = it },
////                        onDateExpiredChange = { dateExpired = it })
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    Button(onClick = {
//                        if (userData != null && imageUri==null) {
//                            dateCreated?.let {
//                                dateExpired?.let { it1 ->
//                                    sellViewModel().addDagang(
//                                        namaProduk = productName,
//                                        stokProduk = productStock,
//                                        hargaProduk = productPrice,
//                                        userData = userData,
//                                        dateCreated = it,
//                                        dateExpired = it1,
//                                        context = context,
//                                        productPictureUrl = imageUrl
//                                    )
//                                }
//                            }
//                        }
//                        val imageByteArray = imageUri?.uriToByteArray(context)
//                        if (userData != null) {
//                            if (imageByteArray != null) {
//                                sellViewModel().singleProductPicture(userData, productName,imageByteArray,context)
//                                imageUri=null
//                            }
//                        }
//                        Log.d("ProductInput", "Nama: $productName, \nHarga: $productPrice, \nStok: $productStock" +
//                                "\ntanggal produksi: ${dateCreated?.toDate()} \ntanggal expired: ${dateExpired?.toDate()}")
//                        navController.navigate(explore)
//                    }) {
//                        Text(text = "Simpan Produk")
//                    }
//                    if (imageUri==null){
//                        Button(onClick = { launcher.launch("image/*") }) {
//                            Text("Change Profile Picture")
//                        }
//                    }
//                    if (imageUri != null) {
//                        Button(onClick = {
//                            val imageByteArray = imageUri?.uriToByteArray(context)
//                            if (userData != null) {
//                                if (imageByteArray != null) {
//                                    sellViewModel().singleProductPicture(userData, productName,imageByteArray,context)
//                                    imageUri=null
//                                }
//                            }
//                        }) {
//                            Text("Upload Image")
//                        }
//                    }
//                    Button(onClick = { forceRefresh++ }) {
//                        Text("Refresh")
//                    }
//                }
//            }
//
//        }

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
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Stok Produk") },
                modifier = Modifier,
                singleLine = true
            )

        }
    }

}

@Composable
fun DatePickerDialog(
    showDialog: Boolean, // Control visibility of the dialog
    onDateSelected: (Timestamp) -> Unit, // Callback for selected date
    onDismiss: () -> Unit // Callback to dismiss the dialog
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Show the dialog when showDialog is true
    if (showDialog) {
        LaunchedEffect(Unit) {
            DatePickerDialog(
                context,
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    val date = calendar.time
                    onDateSelected(Timestamp(date.time / 1000, 0)) // Convert to Firestore Timestamp
                    onDismiss()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}

//
//@Composable
//fun DateThing(
//    onDateSelected: (day: Int, month: Int, year: Int) -> Unit,
//    onDismiss: () -> Unit
//) {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//
//    DatePickerDialog(
//        context,
//        { _, year, month, day ->
//            onDateSelected(day, month + 1, year) // Months are 0-based in Calendar
//            onDismiss()
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    ).show()

//    onDateCreatedChange: (Timestamp) -> Unit,
//    onDateExpiredChange: (Timestamp) -> Unit,
////    onDismiss: ()->Unit
//){
//       Column(modifier = Modifier.wrapContentSize().padding(16.dp)) {
//        DatePickerExample { selectedDate -> onDateCreatedChange(selectedDate) }
//        Spacer(Modifier.height(10.dp))
//        DatePickerExample { selectedDate -> onDateExpiredChange(selectedDate) }
//


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