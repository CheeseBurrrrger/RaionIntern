package com.example.raionthings.presentation.login

data class EmailSignInState (
    val isAuthenticated: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
