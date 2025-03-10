package com.example.raionthings.presentation.login

data class EmailSignInResult(
    val data: UserData?,
    val errorMessage: String?
)

//data class UserData(
//    val userId: String?,
//    val email: String,
//    val username: String? = null,
//    val profilePictureUrl: String? = null
//)