package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.taman.silmebagcalculator.models.AuthSavedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.authDataStore by preferencesDataStore(name = "auth_preferences")

class AuthDataStore(private val context: Context) {
    // Define keys for each individual value
    private val REMEMBER_ME_KEY = booleanPreferencesKey("remember_me")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val PASSWORD_KEY = stringPreferencesKey("password")

    // save functions for each type of value
    suspend fun saveData(value: AuthSavedData) {
        context.authDataStore.edit {
            it[REMEMBER_ME_KEY] = value.rememberMe
            it[EMAIL_KEY] = value.email
            it[PASSWORD_KEY] = value.password
        }
    }

    // Function to get flows for individual values
    val rememberMeFlow: Flow<Boolean> = context.authDataStore.data
        .map { it[REMEMBER_ME_KEY] ?: false }
    val userNameFlow: Flow<String> = context.authDataStore.data
        .map { it[EMAIL_KEY] ?: "" }
    val passwordFlow: Flow<String> = context.authDataStore.data
        .map { it[PASSWORD_KEY] ?: "" }

}