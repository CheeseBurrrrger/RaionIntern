package com.example.raionthings.presentation.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

data class SignInResult(
    val data:UserData?,
    val errorMessage: String?
)

@Parcelize
data class UserData(
    val userId: String = "",
    val email: String? = null,
    val username: String? = null,
    val profilePictureUrl: String?=null,
    var address: String? = null,
): Parcelable

data class UserProduk(
//    val productId: String, // ID unik produk auto generate
    val sellerId: String,  // ID user penjual
    val namaProduk: String,
    val hargaProduk: Int,
    val stokProduk: Int,
    val onGoing: Int,
    val dateCreated: LocalDate,
    val dateExpired: LocalDate,
    val productPictureUrl: String,
    val isSold:Boolean
)
data class UserKeranjang(
//    val cartItemId: String, // ID unik item di keranjang auto generate(makanya di comment)
    val productId: String,  // Referensi ke produk di subcollection dagangan
    val sellerId: String,   // Referensi ke user penjual
    val userId: String,
    val quantity: Int,       // Jumlah item alamat dan alamat dlm bentuk url
    val isCheckout: Boolean
)
data class UserHistory(
    val userId: String,
    val productId: String,  // Referensi ke produk di subcollection dagangan
    val sellerId: String,   // Referensi ke user penjual
    val quantity: Int,       // Jumlah item
    val dateCheckout: LocalDate
)


//on goiing sama rate, otp