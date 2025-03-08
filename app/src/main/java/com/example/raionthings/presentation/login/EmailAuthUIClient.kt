package com.example.raionthings.presentation.login

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class EmailAuthUIClient (
    private val context: Context,
){
    private val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): EmailSignInResult {
        if (email.isEmpty() || password.isEmpty()) {
            return EmailSignInResult(
                data = null,
                errorMessage = "Kindly fill your email and password"
            )
        }

        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user

            if (user != null) {
                EmailSignInResult(
                    data = user?.run {
                        EmailUserData(
                            userId = uid,
                            email = email
                        )
                    },
                    errorMessage = null
                )
            } else {
                EmailSignInResult(
                    data = null,
                    errorMessage = "User not found"
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            EmailSignInResult(
                data = null,
                errorMessage = e.message ?: "Authentication failed"
            )
        }
    }

    suspend fun signOut(){
        Firebase.auth.signOut()
    }

    suspend fun getEmailSignedInUser(){
        try {

        }catch (e: Exception){
            throw e
        }
    }

}