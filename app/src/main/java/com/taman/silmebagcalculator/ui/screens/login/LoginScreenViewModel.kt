package com.taman.silmebagcalculator.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taman.silmebagcalculator.datastore.AuthDataStore
import com.taman.silmebagcalculator.models.AuthSavedData
import com.taman.silmebagcalculator.models.LoginResponse
import com.taman.silmebagcalculator.remote.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val authRepository: AuthRepository,
    private val authDataStore: AuthDataStore? = null
) : ViewModel() {

    private val _loginEvents = MutableSharedFlow<LoginResponse>()
    val loginEvents = _loginEvents.asSharedFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError

    // Expose saved flows for UI
    val savedEmail: Flow<String> = authDataStore?.userNameFlow ?: flowOf("")
    val savedPassword: Flow<String> = authDataStore?.passwordFlow ?: flowOf("")
    val savedRememberMe: Flow<Boolean> = authDataStore?.rememberMeFlow ?: flowOf(false)

    fun login(email: String, password: String, rememberMe: Boolean) {
        _loading.value = true
        authRepository.login(email, password) { success, message ->
            viewModelScope.launch {
                if (success && rememberMe) {
                    authDataStore?.saveData(AuthSavedData(true, email, password))
                } else if (!rememberMe) {
                    authDataStore?.saveData(AuthSavedData(false, "", ""))
                }
                _loginEvents.emit(LoginResponse(success, message ?: "Unknown error"))
            }
            _loading.value = false
        }
    }

    fun onEmailChanged(email: String) {
        _emailError.value = !isValidEmail(email) && email.isNotEmpty()
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