package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private val Context.juteDataStore by preferencesDataStore(name = "jute_preferences")

class JuteDataStore(private val context: Context) {

}