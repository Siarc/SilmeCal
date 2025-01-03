package com.taman.silmebagcalculator.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.taman.silmebagcalculator.models.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel(){

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _loginResult = MutableStateFlow<LoginResponse?>(null)
    val loginResult: StateFlow<LoginResponse?> = _loginResult

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true // Set loading to true before the API call

            firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginResult.value = LoginResponse(success = true, message = "Login successful")
                    } else {
                        _loginResult.value = LoginResponse(success = false, message = "Login failed: ${task.exception?.message}")
                    }
                    _loading.value = false // Set loading to false after receiving the response
                }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}