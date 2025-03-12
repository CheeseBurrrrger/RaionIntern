package com.example.raionthings.presentation.profile


data class ProfileData(
    val userId: String,
    val email: String,
    val username: String = "",
    val address: String = "",
    val profilePictureUrl: String?)
{

}