package com.example.lab_3

data class Contact(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val phoneNumber: String,
    val email: String = ""
)
