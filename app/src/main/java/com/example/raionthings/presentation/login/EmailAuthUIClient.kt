package com.example.raionthings.presentation.login

import android.content.Context
import android.widget.Toast
import com.example.raionthings.presentation.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class EmailAuthUIClient {
    private val auth :FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): EmailSignInResult {
        return try {
            val Otenti = auth.signInWithEmailAndPassword(email,password).await()
            val Human = Otenti.user
            EmailSignInResult(
                data = Otenti?.run {
                    UserData(
                        userId = Human!!.uid,
                        email = Human.email.toString(),
                        username = Human.displayName,
                        profilePictureUrl = Human.photoUrl.toString(),
                        address = null
                    )
                },
                errorMessage = null
            )
        }catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            EmailSignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signup(email: String, password: String): SignInResult {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                SignInResult(
                    data = UserData(
                        userId = user.uid,
                        email = user.email,
                        username = user.displayName,
                        profilePictureUrl = null,
                        address = null
                    ),
                    errorMessage = null
                )
            } else {
                SignInResult(data = null, errorMessage = "Sign up failed")
            }
        } catch (e: Exception) {
            SignInResult(data = null, errorMessage = e.message)
        }
    }
    fun resetPassword(emailAddress:String,context: Context){
        auth.sendPasswordResetEmail(emailAddress)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Email sent!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

//    fun getCurrentUser(): UserData? {
//        val user = auth.currentUser
//        return user?.let {
//            UserData(
//                userId = it.uid,
//                email = it.email.toString(),
//                username = it.displayName,
//                profilePictureUrl = it.photoUrl.toString(),
//                address = null
//            )
//        }
//    }

    fun getCurrentUser(): UserData? {
        val id = auth.currentUser?.uid
        if (id != null) {
            ProfileViewModel().getUserData(id)
        }
        val user = auth.currentUser
        return user?.let {
            UserData(
                userId = it.uid,
                email = it.email.toString(),
                username = it.displayName,
                profilePictureUrl = it.photoUrl?.toString(),
                address = null
            )
        }
    }

//    fun resetPassword(){
//        auth.sendPasswordResetEmail()
//    }
    fun signout(){
        Firebase.auth.signOut()

    }
}