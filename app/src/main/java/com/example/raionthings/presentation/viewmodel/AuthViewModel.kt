package com.example.raionthings.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raionthings.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {
    private val auth :FirebaseAuth = FirebaseAuth.getInstance()
    private val authState = MutableLiveData<AuthState>()
    val auth_State : LiveData<AuthState> = authState
    fun login(email: String, password: String) {
        if (email.isEmpty()||password.isEmpty()){
            authState.value= AuthState.Error("Kindly fill ur email or password")
        }
        authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    authState.value = AuthState.Authenticated
                }else authState.value=
                    AuthState.Error(task.exception?.message ?: "Something went wrong")
            }
    }
    fun signup(email: String, password: String ,firstName:String, lastName:String){
        if (email.isEmpty()||password.isEmpty()){
            authState.value= AuthState.Error("Kindly fill ur email or password")
        }
        authState.value = AuthState.Loading
        val user = User()
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    authState.value = AuthState.SignedUp
                }else authState.value=
                    AuthState.Error(task.exception?.message ?: "Something went wrong")
            }
    }
    fun signout(){
        authState.value= AuthState.Unuthenticated
        Firebase.auth.signOut()
    }
    fun checkAuthStatus(){
        if (auth.currentUser==null){
            authState.value= AuthState.Unuthenticated
        }
        else authState.value= AuthState.Authenticated
    }
    init{
        checkAuthStatus()
    }




}
sealed class AuthState{
    object Authenticated : AuthState()
    object SignedUp : AuthState()
    object Unuthenticated : AuthState()
    object  Loading : AuthState()
    object Overload : AuthState()
    data class Error(val message : String) : AuthState()
 }