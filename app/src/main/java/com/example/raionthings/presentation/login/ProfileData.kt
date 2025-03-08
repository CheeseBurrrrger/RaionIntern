package com.example.raionthings.presentation.login


data class ProfileData(
    val userId: String,
    val email: String,
    val username: String = "",
    val lastName: String = "",
    val address: String = "",
    val phoneNumber: String = ""
){
    fun isComplete(): Boolean {
        return username.isNotBlank() && lastName.isNotBlank() && phoneNumber.isNotBlank()
                &&address.isNotBlank()&&phoneNumber.isNotBlank()
    }
}