package com.taman.silmebagcalculator.api

import android.util.Log
import com.taman.silmebagcalculator.models.LoginRequest
import com.taman.silmebagcalculator.models.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AuthRepository(private val apiService: ApiService) {
    fun login(email: String, password: String): Flow<LoginResponse> = flow {
        try {
            Log.d("Rony2", "AuthRepository: $email")
            val response = apiService.login(LoginRequest(email, password))
            emit(response)
        } catch (e: HttpException) {
            Log.d("Rony2", "HttpException: $e")
            emit(LoginResponse(success = false, message = "Network error: ${e.message}"))
        } catch (e: Exception) {
            Log.d("Rony2", "Exception: $e")
            emit(LoginResponse(success = false, message = "Unexpected error: ${e.message}"))
        }
    }
}