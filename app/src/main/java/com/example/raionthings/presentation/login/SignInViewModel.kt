package com.example.raionthings.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()
    private val _isGoogleSignIn = MutableStateFlow(false)
    val isGoogleSignIn = _isGoogleSignIn.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { state ->
            state.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun onGoogleSignInSuccess() {
        _isGoogleSignIn.value = true
    }

    fun onEmailSignInSuccess() {
        _isGoogleSignIn.value = false
    }
    fun onSignInResult(result: EmailSignInResult) {
        _state.update { state ->
            state.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun onSignUpResult(result: SignInResult) {
        _state.update { state ->
            state.copy(
                isSignedUp = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}