package com.taman.silmebagcalculator.remote

interface AuthRepository {
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit)
}