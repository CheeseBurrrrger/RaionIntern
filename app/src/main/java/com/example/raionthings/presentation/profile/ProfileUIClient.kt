package com.example.raionthings.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionthings.data.remote.dto.supabase
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.login.UserProduk
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch

class ProfileUIClient:ViewModel() {
    val db = Firebase.firestore
    val profileUser = db.collection("Profile")

    fun addProfile(userData: UserData, Url:String = ""){
        val profil = userData.run {
            UserData(
                userId = userData.userId,
                username = userData.username,
                email = userData.email.toString(),
                profilePictureUrl = Url,
                address = address
            )
        }
        profileUser.document(profil.userId).set(profil)
        Log.d("TesAddUser", profil.toString())
    }


    fun uploadProfilePicture(userData: UserData, byteArray: ByteArray){
        viewModelScope.launch {
            try {
                val bucket = supabase.client.storage["profile_thing"]
                bucket.upload("${userData.userId}profilepic.jpg",byteArray,true)
                UserData(
                    profilePictureUrl = bucket.publicUrl("${userData.userId}profilepic.jpg"),
                    userId = userData.userId,
                    address = userData.address,
                    email = userData.email,
                    username = userData.username
                )
                Log.d("UploadPic", "success i guess")
            }catch (e:Exception){
                Log.d("UploadPic", "fail: ${e.message}"
                )
            }
        }
    }

    fun readProfilePicture(
        userData: UserData,
        onImageUrlRetrieved: (url: String) -> Unit,){
        viewModelScope.launch {
            try {
                val bucket = supabase.client.storage["profile_thing"]
                val url = bucket.publicUrl("${userData.userId}profilepic.jpg")
                onImageUrlRetrieved(url)
            }catch (e:Exception){

            }
        }
    }
    fun addDagangan(userData: UserData, userProduk: UserProduk){
//        val dagang = userProduk.run {
////            UserProduk(
//
//            )
//        }
    }


}