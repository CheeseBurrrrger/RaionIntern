package com.example.raionthings.domain.model

import com.google.firebase.firestore.DocumentId

data class User (
    @DocumentId val id: String = "",
    val uid: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = ""
)