package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.taman.silmebagcalculator.models.JuteSavedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.juteDataStore by preferencesDataStore(name = "jute_preferences")

class JuteDataStore(private val context: Context) {
    // Define keys for each individual value

    private val NATURAL_15_x_15_JUTE_WIDTH_KEY = stringPreferencesKey("natural_15_x_15_jute_width")
    private val COLOR_15_x_15_JUTE_WIDTH_KEY = stringPreferencesKey("color_15_x_15_jute_width")
    private val NATURAL_13_x_13_JUTE_WIDTH_KEY = stringPreferencesKey("natural_13_x_13_jute_width")
    private val COLOR_13_x_13_JUTE_WIDTH_KEY = stringPreferencesKey("color_13_x_13_jute_width")
    private val JUTE_COTTON_SINGLY_PLY_NATURAL_WIDTH_KEY = stringPreferencesKey("jute_cotton_singly_ply_natural_width")
    private val JUTE_COTTON_SINGLY_PLY_COLOR_WIDTH_KEY = stringPreferencesKey("jute_cotton_singly_ply_color_width")
    private val JUTE_COTTON_DOUBLY_PLY_NATURAL_WIDTH_KEY = stringPreferencesKey("jute_cotton_doubly_ply_natural_width")
    private val JUTE_COTTON_DOUBLY_PLY_COLOR_WIDTH_KEY = stringPreferencesKey("jute_cotton_doubly_ply_color_width")
    private val NATURAL_15_x_15_JUTE_YARD_KEY = stringPreferencesKey("natural_15_x_15_jute_yard")
    private val COLOR_15_x_15_JUTE_YARD_KEY = stringPreferencesKey("color_15_x_15_jute_yard")
    private val NATURAL_13_x_13_JUTE_YARD_KEY = stringPreferencesKey("natural_13_x_13_jute_yard")
    private val COLOR_13_x_13_JUTE_YARD_KEY = stringPreferencesKey("color_13_x_13_jute_yard")
    private val JUTE_COTTON_SINGLY_PLY_NATURAL_YARD_KEY = stringPreferencesKey("jute_cotton_singly_ply_natural_yard")
    private val JUTE_COTTON_SINGLY_PLY_COLOR_YARD_KEY = stringPreferencesKey("jute_cotton_singly_ply_color_yard")
    private val JUTE_COTTON_DOUBLY_PLY_NATURAL_YARD_KEY = stringPreferencesKey("jute_cotton_doubly_ply_natural_yard")
    private val JUTE_COTTON_DOUBLY_PLY_COLOR_YARD_KEY = stringPreferencesKey("jute_cotton_doubly_ply_color_yard")
    private val ZIPPER_PRICE_PER_INCH_KEY = stringPreferencesKey("zipper_price_per_inch")
    private val RUNNER_PRICE_KEY = stringPreferencesKey("runner_price")
    private val SAMPLE_COST_KEY = stringPreferencesKey("sample_cost")
    private val WASTAGE_PERCENTAGE_KEY = stringPreferencesKey("wastage_percentage")

    // save functions for each type of value
    suspend fun saveData(value: JuteSavedData){
        context.juteDataStore.edit {
            it[NATURAL_15_x_15_JUTE_WIDTH_KEY] = value.natural15x15JuteWidth
            it[COLOR_15_x_15_JUTE_WIDTH_KEY] = value.color15x15JuteWidth
            it[NATURAL_13_x_13_JUTE_WIDTH_KEY] = value.natural13x13JuteWidth
            it[COLOR_13_x_13_JUTE_WIDTH_KEY] = value.color13x13JuteWidth
            it[JUTE_COTTON_SINGLY_PLY_NATURAL_WIDTH_KEY] = value.juteCottonSinglyPlyNaturalWidth
            it[JUTE_COTTON_SINGLY_PLY_COLOR_WIDTH_KEY] = value.juteCottonSinglyPlyColorWidth
            it[JUTE_COTTON_DOUBLY_PLY_NATURAL_WIDTH_KEY] = value.juteCottonDoublyPlyNaturalWidth
            it[JUTE_COTTON_DOUBLY_PLY_COLOR_WIDTH_KEY] = value.juteCottonDoublyPlyColorWidth
            it[NATURAL_15_x_15_JUTE_YARD_KEY] = value.natural15x15JuteYard
            it[COLOR_15_x_15_JUTE_YARD_KEY] = value.color15x15JuteYard
            it[NATURAL_13_x_13_JUTE_YARD_KEY] = value.natural13x13JuteYard
            it[COLOR_13_x_13_JUTE_YARD_KEY] = value.color13x13JuteYard
            it[JUTE_COTTON_SINGLY_PLY_NATURAL_YARD_KEY] = value.juteCottonSinglyPlyNaturalYard
            it[JUTE_COTTON_SINGLY_PLY_COLOR_YARD_KEY] = value.juteCottonSinglyPlyColorYard
            it[JUTE_COTTON_DOUBLY_PLY_NATURAL_YARD_KEY] = value.juteCottonDoublyPlyNaturalYard
            it[JUTE_COTTON_DOUBLY_PLY_COLOR_YARD_KEY] = value.juteCottonDoublyPlyColorYard
            it[ZIPPER_PRICE_PER_INCH_KEY] = value.zipperPricePerInch
            it[RUNNER_PRICE_KEY] = value.runnerPrice
            it[SAMPLE_COST_KEY] = value.sampleCost
            it[WASTAGE_PERCENTAGE_KEY] = value.wastagePercentage
        }
    }

    // Function to get flows for individual values

    val natural15x15JuteWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[NATURAL_15_x_15_JUTE_WIDTH_KEY] ?: "54" }
    val color15x15JuteWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[COLOR_15_x_15_JUTE_WIDTH_KEY] ?: "49" }
    val natural13x13JuteWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[NATURAL_13_x_13_JUTE_WIDTH_KEY] ?: "54" }
    val color13x13JuteWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[COLOR_13_x_13_JUTE_WIDTH_KEY] ?: "49" }
    val juteCottonSinglyPlyNaturalWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_SINGLY_PLY_NATURAL_WIDTH_KEY] ?: "58" }
    val juteCottonSinglyPlyColorWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_SINGLY_PLY_COLOR_WIDTH_KEY] ?: "58" }
    val juteCottonDoublyPlyNaturalWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_DOUBLY_PLY_NATURAL_WIDTH_KEY] ?: "58" }
    val juteCottonDoublyPlyColorWidthFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_DOUBLY_PLY_COLOR_WIDTH_KEY] ?: "58" }
    val natural15x15JuteYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[NATURAL_15_x_15_JUTE_YARD_KEY] ?: "123" }
    val color15x15JuteYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[COLOR_15_x_15_JUTE_YARD_KEY] ?: "173" }
    val natural13x13JuteYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[NATURAL_13_x_13_JUTE_YARD_KEY] ?: "118" }
    val color13x13JuteYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[COLOR_13_x_13_JUTE_YARD_KEY] ?: "168" }
    val juteCottonSinglyPlyNaturalYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_SINGLY_PLY_NATURAL_YARD_KEY] ?: "140" }
    val juteCottonSinglyPlyColorYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_SINGLY_PLY_COLOR_YARD_KEY] ?: "170" }
    val juteCottonDoublyPlyNaturalYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_DOUBLY_PLY_NATURAL_YARD_KEY] ?: "150" }
    val juteCottonDoublyPlyColorYardFlow: Flow<String> = context.juteDataStore.data
        .map { it[JUTE_COTTON_DOUBLY_PLY_COLOR_YARD_KEY] ?: "180" }
    val zipperPricePerInchFlow: Flow<String> = context.juteDataStore.data
        .map { it[ZIPPER_PRICE_PER_INCH_KEY] ?: "0.15" }
    val runnerPriceFlow: Flow<String> = context.juteDataStore.data
        .map { it[RUNNER_PRICE_KEY] ?: "2.2" }
    val sampleCostFlow: Flow<String> = context.juteDataStore.data
        .map { it[SAMPLE_COST_KEY] ?: "1" }
    val wastagePercentageFlow: Flow<String> = context.juteDataStore.data
        .map { it[WASTAGE_PERCENTAGE_KEY] ?: "5" }

}