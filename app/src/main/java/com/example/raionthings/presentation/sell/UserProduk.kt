package com.example.raionthings.presentation.sell

import com.google.firebase.Timestamp


data class UserProduk(
    val produkId: String = "",
    val sellerId: String = "",
    val namaProduk: String = "",
    val hargaProduk: Int = 0,
    val stokProduk: Int = 0,
    val dateCreated: Timestamp = Timestamp.now(),
    val dateExpired: Timestamp? = null ,
    val productPictureUrl: String = "",

//    val isSold: Boolean = false
) {
//    fun getFormattedDateCreated(): String {
//        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        return sdf.format(dateCreated.toDate())
//    }
}
