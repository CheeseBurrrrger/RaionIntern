package com.example.raionthings.presentation.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionthings.data.remote.dto.supabase
import com.example.raionthings.presentation.login.UserData
import com.example.raionthings.presentation.login.UserProduk
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModel() {
    private val db = Firebase.firestore
    private val profileUser = db.collection("Profile")


    fun addProfile(userData: UserData){
        val profileRef = profileUser.document(userData.userId)
        profileRef.set(userData, SetOptions.merge())
        Log.d("AddProfile", "Profile saved: $userData")
    }
    fun addNewProfile(userData: UserData){
        val profil = userData.run {
            UserData(
                userId = userData.userId,
                username = userData.username,
                email = userData.email.toString(),
                profilePictureUrl = profilePictureUrl,
                address = address
            )
        }
        profileUser.document(profil.userId).set(profil)
        Log.d("TesAddUser", profil.toString())
    }


    fun uploadProfilePicture(userData: UserData, byteArray: ByteArray, context: Context){
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
                profileUser.document(userData.userId).update("profilePictureUrl",
                    bucket.publicUrl("${userData.userId}profilepic.jpg")
                ).addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Profile picture uploaded!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

    fun getUserData(idUser: String){
        val docUser = profileUser.document(idUser)
        var currentUserData : UserData? = null
        val user = docUser.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("getUser", "DocumentSnapshot data: ${document.data}")
                    Log.d("getUser", currentUserData.toString())
                } else {
                    Log.d("getUser", "No such document")
                }
            }
        Log.d("getUser", user.toString())
    }

    fun updateProfile (userData: UserData, username:String, address: String, context: Context){
        val profileRef = profileUser.document(userData.userId)
        profileRef.update(mapOf(
            "username" to username,
            "address" to address
        ))
            .addOnSuccessListener {
                Toast.makeText(
                context,
                    "Data Updated!",
                    Toast.LENGTH_SHORT
                ).show()
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