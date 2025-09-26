package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.taman.silmebagcalculator.models.CottonSavedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.cottonDataStore by preferencesDataStore(name = "cotton_preferences")

class CottonDataStore(private val context: Context) {
    // Define keys for each individual value
    private val HANDLE_WIDTH_KEY = stringPreferencesKey("handle_width")

    //carton_cost
    private val CARTON_COST_KEY = stringPreferencesKey("carton_cost")

    // runner_price
    private val RUNNER_PRICE_KEY = stringPreferencesKey("runner_price")

    // zipper_price_per_inch
    private val ZIPPER_PRICE_PER_INCH_KEY = stringPreferencesKey("zipper_price_per_inch")

    // zipper_cm_charge
    private val ZIPPER_CM_CHARGE_KEY = stringPreferencesKey("zipper_cm_charge")

    // runner_zipper_cost
    private val WASTAGE_PERCENTAGE_KEY = stringPreferencesKey("wastage_percentage")

    // save functions for each type of value
    suspend fun saveData(value: CottonSavedData) {
        context.cottonDataStore.edit {
            it[HANDLE_WIDTH_KEY] = value.handleWidth
            it[CARTON_COST_KEY] = value.cartonCost
            it[RUNNER_PRICE_KEY] = value.runnerPrice
            it[ZIPPER_PRICE_PER_INCH_KEY] = value.zipperPricePerInch
            it[ZIPPER_CM_CHARGE_KEY] = value.zipperCmCharge
            it[WASTAGE_PERCENTAGE_KEY] = value.wastagePercentage
        }
    }

    // Function to get flows for individual values
    val handleWidthFlow: Flow<String> = context.cottonDataStore.data
        .map { it[HANDLE_WIDTH_KEY] ?: "3.25" }
    val cartonCostFlow: Flow<String> = context.cottonDataStore.data
        .map { it[CARTON_COST_KEY] ?: "5" }
    val runnerPriceFlow: Flow<String> = context.cottonDataStore.data
        .map { it[RUNNER_PRICE_KEY] ?: "6" }
    val zipperPricePerInchFlow: Flow<String> = context.cottonDataStore.data
        .map { it[ZIPPER_PRICE_PER_INCH_KEY] ?: "0.20" }
    val zipperCmChargeFlow: Flow<String> = context.cottonDataStore.data
        .map { it[ZIPPER_CM_CHARGE_KEY] ?: "5" }
    val wastagePercentageFlow: Flow<String> = context.cottonDataStore.data
        .map { it[WASTAGE_PERCENTAGE_KEY] ?: "5" }
}