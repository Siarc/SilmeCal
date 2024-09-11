package com.taman.silmebagcalculator.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel(){
    private val _backPressedOnce = MutableStateFlow(false)
    val backPressedOnce: StateFlow<Boolean> = _backPressedOnce

    fun onBackPressed() {
        if (_backPressedOnce.value) {
            // Perform exit action
        } else {
            _backPressedOnce.value = true
            viewModelScope.launch {
                delay(2000) // Delay for 2 seconds
                _backPressedOnce.value = false
            }
        }
    }
}