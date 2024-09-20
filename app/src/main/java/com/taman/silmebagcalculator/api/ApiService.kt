package com.taman.silmebagcalculator.api

import com.taman.silmebagcalculator.models.LoginRequest
import com.taman.silmebagcalculator.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/validate")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}