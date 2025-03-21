package com.example.raionthings.presentation.buy

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.raionthings.presentation.sell.UserProduk
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BuyViewModel:ViewModel() {
    private val db = Firebase.firestore
    private val profileUser = db.collection("Profile")

    fun addToCart(
        userProduk: UserProduk,
        context: Context
    ){

    }
}