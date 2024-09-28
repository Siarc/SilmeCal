package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.taman.silmebagcalculator.models.NonWovenSavedData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Extension property to create the DataStore instance
private val Context.nonWovenDataStore by preferencesDataStore(name = "non_woven_preferences")

@Singleton
class NonWovenDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    // Define keys for each individual value
    private val HANDLE_BAG_HEMMING_KEY = stringPreferencesKey("handle_bag_hemming")
    private val HANDLE_BAG_HANDLE_FABRIC_KEY = stringPreferencesKey("handle_bag_handle_fabric")
    private val HANDLE_BAG_MAKING_TYPE_KEY = stringPreferencesKey("handle_bag_making_type")

    private val D_CUT_BAG_HEMMING_KEY = stringPreferencesKey("d_cut_bag_hemming")
    private val D_CUT_BAG_MAKING_TYPE_KEY = stringPreferencesKey("d_cut_bag_making_type")

    private val SEWING_BAG_HEMMING_KEY = stringPreferencesKey("sewing_bag_hemming")
    private val SEWING_BAG_HANDLE_FABRIC_KEY = stringPreferencesKey("sewing_bag_handle_fabric")
    private val SEWING_BAG_RUNNER_KEY = stringPreferencesKey("sewing_bag_runner")
    private val SEWING_BAG_PIPING_EXTRA_ADDITION_KEY = stringPreferencesKey("sewing_bag_piping_extra_addition")
    private val SEWING_BAG_MAKING_TYPE_KEY = stringPreferencesKey("sewing_bag_making_type")

    private val AUTOBOX_HANDLE_BAG_HEMMING_KEY = stringPreferencesKey("autobox_handle_bag_hemming")
    private val AUTOBOX_HANDLE_BAG_HANDLE_FABRIC_KEY = stringPreferencesKey("autobox_handle_bag_handle_fabric")
    private val AUTOBOX_HANDLE_BAG_MAKING_TYPE_KEY = stringPreferencesKey("autobox_handle_bag_making_type")

    private val AUTOBOX_D_CUT_BAG_HEMMING_KEY = stringPreferencesKey("autobox_d_cut_bag_hemming")
    private val AUTOBOX_D_CUT_BAG_MAKING_TYPE_KEY = stringPreferencesKey("autobox_d_cut_bag_making_type")

    // save functions for each type of value
    suspend fun saveData(value: NonWovenSavedData) {
        context.nonWovenDataStore.edit {
            it[HANDLE_BAG_HEMMING_KEY] = value.handleBagHemming
            it[HANDLE_BAG_HANDLE_FABRIC_KEY] = value.handleBagHandleFabric
            it[HANDLE_BAG_MAKING_TYPE_KEY] = value.handleBagMakingType
            it[D_CUT_BAG_HEMMING_KEY] = value.dCutBagHemming
            it[D_CUT_BAG_MAKING_TYPE_KEY] = value.dCutBagMakingType
            it[SEWING_BAG_HEMMING_KEY] = value.sewingBagHemming
            it[SEWING_BAG_HANDLE_FABRIC_KEY] = value.sewingBagHandleFabric
            it[SEWING_BAG_RUNNER_KEY] = value.sewingBagRunner
            it[SEWING_BAG_PIPING_EXTRA_ADDITION_KEY] = value.sewingBagPipingExtraAddition
            it[SEWING_BAG_MAKING_TYPE_KEY] = value.sewingBagMakingType
            it[AUTOBOX_HANDLE_BAG_HEMMING_KEY] = value.autoboxHandleBagHemming
            it[AUTOBOX_HANDLE_BAG_HANDLE_FABRIC_KEY] = value.autoboxHandleBagHandleFabric
            it[AUTOBOX_HANDLE_BAG_MAKING_TYPE_KEY] = value.autoboxHandleBagMakingType
            it[AUTOBOX_D_CUT_BAG_HEMMING_KEY] = value.autoboxDCutBagHemming
            it[AUTOBOX_D_CUT_BAG_MAKING_TYPE_KEY] = value.autoboxDCutBagMakingType
        }
    }

    // Function to get flows for individual values
    val handleBagHemmingFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[HANDLE_BAG_HEMMING_KEY] ?: "1.5" }

    val handleBagHandleFabricFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[HANDLE_BAG_HANDLE_FABRIC_KEY] ?: "70.0" }

    val handleBagMakingTypeFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[HANDLE_BAG_MAKING_TYPE_KEY] ?: "0.80" }

    val dCutBagHemmingFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[D_CUT_BAG_HEMMING_KEY] ?: "2.5" }

    val dCutBagMakingTypeFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[D_CUT_BAG_MAKING_TYPE_KEY] ?: "0.50" }

    val sewingBagHemmingFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_HEMMING_KEY] ?: "1.5" }

    val sewingBagHandleFabricFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_HANDLE_FABRIC_KEY] ?: "70.0" }

    val sewingBagRunnerFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_RUNNER_KEY] ?: "1.0" }

    val sewingBagPipingExtraAdditionFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_PIPING_EXTRA_ADDITION_KEY] ?: "10.0" }

    val sewingBagMakingTypeFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_MAKING_TYPE_KEY] ?: "4.0" }

    val autoboxHandleBagHemmingFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_HANDLE_BAG_HEMMING_KEY] ?: "1.5" }

    val autoboxHandleBagHandleFabricFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_HANDLE_BAG_HANDLE_FABRIC_KEY] ?: "70.0" }

    val autoboxHandleBagMakingTypeFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_HANDLE_BAG_MAKING_TYPE_KEY] ?: "2.0" }

    val autoboxDCutBagHemmingFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_D_CUT_BAG_HEMMING_KEY] ?: "2.5" }

    val autoboxDCutBagMakingTypeFlow: Flow<String> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_D_CUT_BAG_MAKING_TYPE_KEY] ?: "1.7" }

}