package com.example.raionthings.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EmailSignInViewModel(
    private val authClient: EmailAuthUIClient) : ViewModel() {
    private val auth_State = MutableStateFlow(EmailSignInState())
    val authState = auth_State.asStateFlow()

    suspend fun login(email: String, password: String): EmailSignInResult {
        return try {
            // Firebase authentication
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user

            if (user != null) {
                EmailSignInResult(
                    data = EmailUserData(
                        userId = user.uid,
                        email = user.email.toString()
                    ),
                    errorMessage = null
                )
            } else {
                EmailSignInResult(
                    data = null,
                    errorMessage = "User not found"
                )
            }
        } catch (e: Exception) {
            EmailSignInResult(
                data = null,
                errorMessage = e.message ?: "Authentication failed"
            )
        }
    }


    fun onSignInResult(result: EmailSignInResult) {
        auth_State.update {
            it.copy(
                isAuthenticated = result.data != null,
                isLoading = false,
                error = result.errorMessage
            )
        }
    }

    fun signOut(){
        viewModelScope.launch{
            try {
                authClient.signOut()
                resetState()
            }catch(e:Exception){
                auth_State.update {
                    it.copy(error = "Sign out error and i don't know why, but here is the reason: ${e.message} ")
                }
            }
        }
    }

    fun resetState() {
        auth_State.update { EmailSignInState() }
    }
}