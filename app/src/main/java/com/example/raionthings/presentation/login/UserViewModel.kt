package com.example.raionthings.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.raionthings.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class UserViewModel() :  ViewModel() {
    private val userCollectionRef = Firebase.firestore.collection("Users")
//    private val authState = MutableLiveData<AuthState>()
//    val auth_State : LiveData<AuthState> = authState
    private val TAG = "FirestoreExample"

    val auth = FirebaseAuth.getInstance()

//    fun addUser(firstName:String, lastName:String){
//        val userr = Firebase.auth.currentUser
//        if (auth_State.value == (AuthState.Unuthenticated)) {
//            Log.d("dunno","error cak")
//            authState.value= AuthState.Unuthenticated
//        }else{
//            val user = userr?.let { User(it.uid,userr.uid,userr.email.toString(),firstName,lastName) }
//            Log.d("dunno",user.toString())
//            if (user != null) {
//                saveUser(user)
//            }
//        }
//    }

    fun saveUser(user: User) = CoroutineScope(Dispatchers.IO).launch{
        val userr = Firebase.auth.currentUser
        try {
        userr?.let { userCollectionRef.document(it.uid).set(user).await() }

    }
    catch (e: Exception){

    }
    }
    fun getUser (){
        val userr = Firebase.auth.currentUser
        val temp = userr?.let { userCollectionRef.document(it.uid) }
        temp?.get()?.addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data} ${userr?.email}")
            } else {
                Log.d(TAG, "No such document")
            }
        }
            ?.addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

    }
}
