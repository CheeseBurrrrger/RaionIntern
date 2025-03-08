package com.example.raionthings.presentation.login

data class EmailSignInResult(
    val data: EmailUserData?,
    val errorMessage: String?
)

data class EmailUserData(
    val userId: String,
    val email: String,
)