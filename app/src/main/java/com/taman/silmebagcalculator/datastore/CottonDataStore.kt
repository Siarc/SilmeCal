package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private val Context.cottonDataStore by preferencesDataStore(name = "cotton_preferences")

class CottonDataStore(private val context: Context) {
    // Define keys for each individual value

}