package com.taman.silmebagcalculator.models

data class AuthSavedData(
    val rememberMe: Boolean,
    val email: String,
    val password: String,
)