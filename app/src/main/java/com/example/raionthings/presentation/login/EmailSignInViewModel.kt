package com.example.raionthings.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmailSignInViewModel:ViewModel(){
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun blankField(result: SignInResult){
        _state.update { it.copy(
            isSignedUp = false,
            signInError = "blank identity field",
            isSignInSuccessful = false
            )
        }
    }


}