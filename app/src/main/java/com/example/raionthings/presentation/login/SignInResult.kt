package com.example.raionthings.presentation.login

data class SignInResult(
    val data:UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val email: String?,
    val username: String?,
    val profilePictureUrl: String?
)
