package com.taman.silmebagcalculator.datastore

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
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
    private val HANDLE_BAG_HEMMING_KEY = doublePreferencesKey("handle_bag_hemming")
    private val HANDLE_BAG_HANDLE_FABRIC_KEY = doublePreferencesKey("handle_bag_handle_fabric")
    private val HANDLE_BAG_MAKING_TYPE_KEY = doublePreferencesKey("handle_bag_making_type")

    private val D_CUT_BAG_HEMMING_KEY = doublePreferencesKey("d_cut_bag_hemming")
    private val D_CUT_BAG_MAKING_TYPE_KEY = doublePreferencesKey("d_cut_bag_making_type")

    private val SEWING_BAG_HEMMING_KEY = doublePreferencesKey("sewing_bag_hemming")
    private val SEWING_BAG_HANDLE_FABRIC_KEY = doublePreferencesKey("sewing_bag_handle_fabric")
    private val SEWING_BAG_RUNNER_KEY = doublePreferencesKey("sewing_bag_runner")
    private val SEWING_BAG_PIPING_EXTRA_ADDITION_KEY = doublePreferencesKey("sewing_bag_piping_extra_addition")
    private val SEWING_BAG_MAKING_TYPE_KEY = doublePreferencesKey("sewing_bag_making_type")

    private val AUTOBOX_HANDLE_BAG_HEMMING_KEY = doublePreferencesKey("autobox_handle_bag_hemming")
    private val AUTOBOX_HANDLE_BAG_HANDLE_FABRIC_KEY = doublePreferencesKey("autobox_handle_bag_handle_fabric")
    private val AUTOBOX_HANDLE_BAG_MAKING_TYPE_KEY = doublePreferencesKey("autobox_handle_bag_making_type")

    private val AUTOBOX_D_CUT_BAG_HEMMING_KEY = doublePreferencesKey("autobox_d_cut_bag_hemming")
    private val AUTOBOX_D_CUT_BAG_MAKING_TYPE_KEY = doublePreferencesKey("autobox_d_cut_bag_making_type")

    // save functions for each type of value
    suspend fun saveHandleBagHemming(value: Double) {
        context.nonWovenDataStore.edit {
            it[HANDLE_BAG_HEMMING_KEY] = value
        }
    }

    suspend fun saveHandleBagHandleFabric(value: Double) {
        context.nonWovenDataStore.edit {
            it[HANDLE_BAG_HANDLE_FABRIC_KEY] = value
        }
    }

    suspend fun saveHandleBagMakingType(value: Double) {
        context.nonWovenDataStore.edit {
            it[HANDLE_BAG_MAKING_TYPE_KEY] = value
        }
    }

    suspend fun saveDCutBagHemming(value: Double) {
        context.nonWovenDataStore.edit {
            it[D_CUT_BAG_HEMMING_KEY] = value
        }
    }

    suspend fun saveDCutBagMakingType(value: Double) {
        context.nonWovenDataStore.edit {
            it[D_CUT_BAG_MAKING_TYPE_KEY] = value
        }
    }

    suspend fun saveSewingBagHemming(value: Double) {
        context.nonWovenDataStore.edit {
            it[SEWING_BAG_HEMMING_KEY] = value
        }
    }

    suspend fun saveSewingBagHandleFabric(value: Double) {
        context.nonWovenDataStore.edit {
            it[SEWING_BAG_HANDLE_FABRIC_KEY] = value
        }
    }

    suspend fun saveSewingBagRunner(value: Double) {
        context.nonWovenDataStore.edit {
            it[SEWING_BAG_RUNNER_KEY] = value
        }
    }

    suspend fun saveSewingBagPipingExtraAddition(value: Double) {
        context.nonWovenDataStore.edit {
            it[SEWING_BAG_PIPING_EXTRA_ADDITION_KEY] = value
        }
    }

    suspend fun saveSewingBagMakingType(value: Double) {
        context.nonWovenDataStore.edit {
            it[SEWING_BAG_MAKING_TYPE_KEY] = value
        }
    }

    suspend fun saveAutoboxHandleBagHemming(value: Double) {
        context.nonWovenDataStore.edit {
            it[AUTOBOX_HANDLE_BAG_HEMMING_KEY] = value
        }
    }

    suspend fun saveAutoboxHandleBagHandleFabric(value: Double) {
        context.nonWovenDataStore.edit {
            it[AUTOBOX_HANDLE_BAG_HANDLE_FABRIC_KEY] = value
        }
    }

    suspend fun saveAutoboxHandleBagMakingType(value: Double) {
        context.nonWovenDataStore.edit {
            it[AUTOBOX_HANDLE_BAG_MAKING_TYPE_KEY] = value
        }
    }

    suspend fun saveAutoboxDCutBagHemming(value: Double) {
        context.nonWovenDataStore.edit {
            it[AUTOBOX_D_CUT_BAG_HEMMING_KEY] = value
        }
    }

    suspend fun saveAutoboxDCutBagMakingType(value: Double) {
        context.nonWovenDataStore.edit {
            it[AUTOBOX_D_CUT_BAG_MAKING_TYPE_KEY] = value
        }
    }

    // Function to get flows for individual values
    val handleBagHemmingFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[HANDLE_BAG_HEMMING_KEY] ?: 1.5 }

    val handleBagHandleFabricFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[HANDLE_BAG_HANDLE_FABRIC_KEY] ?: 70.0 }

    val handleBagMakingTypeFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[HANDLE_BAG_MAKING_TYPE_KEY] ?: 0.80 }

    val dCutBagHemmingFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[D_CUT_BAG_HEMMING_KEY] ?: 2.5 }

    val dCutBagMakingTypeFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[D_CUT_BAG_MAKING_TYPE_KEY] ?: 0.50 }

    val sewingBagHemmingFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_HEMMING_KEY] ?: 1.5 }

    val sewingBagHandleFabricFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_HANDLE_FABRIC_KEY] ?: 70.0 }

    val sewingBagRunnerFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_RUNNER_KEY] ?: 1.0 }

    val sewingBagPipingExtraAdditionFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_PIPING_EXTRA_ADDITION_KEY] ?: 10.0 }

    val sewingBagMakingTypeFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[SEWING_BAG_MAKING_TYPE_KEY] ?: 4.0 }

    val autoboxHandleBagHemmingFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_HANDLE_BAG_HEMMING_KEY] ?: 1.5 }

    val autoboxHandleBagHandleFabricFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_HANDLE_BAG_HANDLE_FABRIC_KEY] ?: 70.0 }

    val autoboxHandleBagMakingTypeFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_HANDLE_BAG_MAKING_TYPE_KEY] ?: 2.0 }

    val autoboxDCutBagHemmingFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_D_CUT_BAG_HEMMING_KEY] ?: 2.5 }

    val autoboxDCutBagMakingTypeFlow: Flow<Double> = context.nonWovenDataStore.data
        .map { it[AUTOBOX_D_CUT_BAG_MAKING_TYPE_KEY] ?: 1.7 }

}