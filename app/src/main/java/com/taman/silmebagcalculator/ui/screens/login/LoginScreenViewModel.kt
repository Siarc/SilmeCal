package com.taman.silmebagcalculator.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taman.silmebagcalculator.api.AuthRepository
import com.taman.silmebagcalculator.api.RetrofitInstance
import com.taman.silmebagcalculator.models.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel(){
    private val repository = AuthRepository(RetrofitInstance.api)
    private val _loginResult = MutableStateFlow<LoginResponse?>(null)
    val loginResult: StateFlow<LoginResponse?> = _loginResult

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true // Set loading to true before the API call
            repository.login(email, password).collect { response ->
                _loginResult.value = response
                _loading.value = false // Set loading to false after receiving the response
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}