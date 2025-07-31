package com.taman.silmebagcalculator.ui.screens.login

import androidx.lifecycle.ViewModel
import com.taman.silmebagcalculator.models.LoginResponse
import com.taman.silmebagcalculator.remote.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginScreenViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResponse?>(null)
    val loginResult: StateFlow<LoginResponse?> = _loginResult

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun login(email: String, password: String) {
        _loading.value = true
        authRepository.login(email, password) { success, message ->
            _loginResult.value = LoginResponse(success, message ?: "Unknown error")
            _loading.value = false
        }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

class FakeAuthRepository : AuthRepository {
    override fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        onResult(true, "Preview login success")
    }
}