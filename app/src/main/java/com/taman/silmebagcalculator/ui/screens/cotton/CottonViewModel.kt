package com.taman.silmebagcalculator.ui.screens.cotton

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taman.silmebagcalculator.datastore.CottonDataStore
import com.taman.silmebagcalculator.models.CottonSavedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale

class CottonViewModel(private val cottonDataStore: CottonDataStore) : ViewModel() {

    // Flows to observe data from DataStore
    private val _handleWidth = MutableStateFlow("3.25")
    val handleWidth: StateFlow<String> = _handleWidth.asStateFlow()
    private val _cartonCost = MutableStateFlow("5")
    val cartonCost: StateFlow<String> = _cartonCost.asStateFlow()
    private val _runnerPrice = MutableStateFlow("6")
    val runnerPrice: StateFlow<String> = _runnerPrice.asStateFlow()
    private val _zipperPricePerInch = MutableStateFlow("0.20")
    val zipperPricePerInch: StateFlow<String> = _zipperPricePerInch.asStateFlow()
    private val _zipperCmCharge = MutableStateFlow("5")
    val zipperCmCharge: StateFlow<String> = _zipperCmCharge.asStateFlow()
    private val _wastagePercentage = MutableStateFlow("5.0")
    val wastagePercentage: StateFlow<String> = _wastagePercentage.asStateFlow()

    // Declaring all flows that will be used to observe data from DataStore
    init {
        observeData()
    }

    // MutableStates for visibility
    var isBottomSheetOpen = mutableStateOf(false)
        private set

    // MutableStates for selection
    var selectedBagType = mutableStateOf("Flat Tote Bag")
        private set

    // Lists for dropdown
    val cottonBagTypeList = listOf(
        "Flat Tote Bag",
        "Gusseted Tote Bag"
    )

    // MutableStates for fields that affect price
    var fabricCuttableWidth = mutableStateOf("")
        private set
    var fabricPrice = mutableStateOf("")
        private set
    var printCost = mutableStateOf("")
        private set
    var height = mutableStateOf("")
        private set
    var width = mutableStateOf("")
        private set
    var gusset = mutableStateOf("")
        private set
    var handleLength = mutableStateOf("")
        private set
    var making = mutableStateOf("")
        private set
    var accessories = mutableStateOf("")
        private set
    var quantity = mutableStateOf("")
        private set
    var cnt = mutableStateOf("")
        private set
    var buyingCommission = mutableStateOf("")
        private set
    var zipperFabricWidth = mutableStateOf("")
        private set
    var profit = mutableStateOf("")
        private set

    // Unit price that will be updated dynamically
    var unitPrice = mutableStateOf("")
        private set
    var fabricPerBag = mutableStateOf("")
        private set
    var fabricPerDozen = mutableStateOf("")
        private set
    var totalFabricNeeded = mutableStateOf("")
        private set

    // Function to observe data from DataStore
    private fun observeData() {
        cottonDataStore.handleWidthFlow
            .onEach { it.let { _handleWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        cottonDataStore.cartonCostFlow
            .onEach { it.let { _cartonCost.value = it } }
            .catch { e -> Log.e("cartonCostFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        cottonDataStore.runnerPriceFlow
            .onEach { it.let { _runnerPrice.value = it } }
            .catch { e -> Log.e("runnerPriceFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        cottonDataStore.zipperPricePerInchFlow
            .onEach { it.let { _zipperPricePerInch.value = it } }
            .catch { e -> Log.e("zipperPricePerInchFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        cottonDataStore.zipperCmChargeFlow
            .onEach { it.let { _zipperCmCharge.value = it } }
            .catch { e -> Log.e("zipperCmChargeFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        cottonDataStore.wastagePercentageFlow
            .onEach { it.let { _wastagePercentage.value = it } }
            .catch { e -> Log.e("wastagePercentageFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

    }


    // Function to update bottom sheet visibility
    fun updateBottomSheetState(value: Boolean) {
        isBottomSheetOpen.value = value
    }

    // Functions to update each value and recalculate unit price
    fun updateFabricCuttableWidth(value: String) {
        fabricCuttableWidth.value = value
        calculateAllPrice()
    }

    fun updateProfit(value: String) {
        profit.value = value
        calculateAllPrice()
    }

    fun updateFabricPrice(value: String) {
        fabricPrice.value = value
        calculateAllPrice()
    }

    fun updatePrintCost(value: String) {
        printCost.value = value
        calculateAllPrice()
    }

    fun updateHeight(value: String) {
        height.value = value
        calculateAllPrice()
    }

    fun updateWidth(value: String) {
        width.value = value
        calculateAllPrice()
    }

    fun updateGusset(value: String) {
        gusset.value = value
        calculateAllPrice()
    }

    fun updateHandleLength(value: String) {
        handleLength.value = value
        calculateAllPrice()
    }

    fun updateMaking(value: String) {
        making.value = value
        calculateAllPrice()
    }

    fun updateAccessories(value: String) {
        accessories.value = value
        calculateAllPrice()
    }

    fun updateQuantity(value: String) {
        quantity.value = value
        calculateAllPrice()
    }

    fun updateCnt(value: String) {
        cnt.value = value
        calculateAllPrice()
    }

    fun updateBuyingCommission(value: String) {
        buyingCommission.value = value
        calculateAllPrice()
    }

    fun updateZipperFabricWidth(value: String) {
        zipperFabricWidth.value = value
        calculateAllPrice()
    }

    fun updateSelectedBagType(value: String) {
        selectedBagType.value = value
        calculateAllPrice()
    }

    fun updateHandleWidth(value: String) {
        _handleWidth.value = value
    }

    fun updateCartonCost(value: String) {
        _cartonCost.value = value
    }

    fun updateRunnerPrice(value: String) {
        _runnerPrice.value = value
    }

    fun updateZipperPricePerInch(value: String) {
        _zipperPricePerInch.value = value
    }

    fun updateZipperCmCharge(value: String) {
        _zipperCmCharge.value = value
    }

    fun updateWastagePercentage(value: String) {
        _wastagePercentage.value = value
    }

    private fun calculateAllPrice() {

        val fabricCuttableWidth = fabricCuttableWidth.value.toDoubleOrNull() ?: 0.0
        val fabricPrice = fabricPrice.value.toDoubleOrNull() ?: 0.0
        val profit = profit.value.toDoubleOrNull() ?: 0.0
        val printCost = printCost.value.toDoubleOrNull() ?: 0.0
        val height = height.value.toDoubleOrNull() ?: 0.0
        val width = width.value.toDoubleOrNull() ?: 0.0
        val gusset = gusset.value.toDoubleOrNull() ?: 0.0
        val handleLength = handleLength.value.toDoubleOrNull() ?: 0.0
        val making = making.value.toDoubleOrNull() ?: 0.0
        val accessories = accessories.value.toDoubleOrNull() ?: 0.0
        val quantity = quantity.value.toIntOrNull() ?: 0
        val cnt = cnt.value.toDoubleOrNull() ?: 0.0
        val buyingCommission = buyingCommission.value.toDoubleOrNull() ?: 0.0
        val zipperFabricWidth = zipperFabricWidth.value.toDoubleOrNull() ?: 0.0

        val handleWidth = handleWidth.value.toDoubleOrNull() ?: 0.0
        val zipperPricePerInch = zipperPricePerInch.value.toDoubleOrNull() ?: 0.0
        val runnerPrice = runnerPrice.value.toDoubleOrNull() ?: 0.0
        val zipperCmCharge = zipperCmCharge.value.toDoubleOrNull() ?: 0.0
        val cartonCost = cartonCost.value.toDoubleOrNull() ?: 0.0
        val wastagePercentage = wastagePercentage.value.toDoubleOrNull() ?: 0.0

        val bagConsumption = if (gusset == 0.0) {
            ((height + 2) * (width + 1) * 2) + (handleLength * handleWidth * 2) + (width * zipperFabricWidth)
        } else {
            (((height + 2) * (width) * 2)) + (handleLength * handleWidth * 2) + ((((height + 2) * 2) + width) * (gusset + 2)) + (width * (gusset + 2))
        }

        val bagConsumptionWithWastage =
            bagConsumption + (bagConsumption * wastagePercentage / 100.0)

        val totalSqInchPerYard =
            fabricCuttableWidth * 36 // A yard is a unit of length equal to 3 feet, or 36 inches.

        val fabricPricePerSqInch = fabricPrice / totalSqInchPerYard

        val fabricPricePerBag = fabricPricePerSqInch * bagConsumptionWithWastage

        var runnerZipperCost = 0.0

        if (zipperFabricWidth > 0) {
            val zipperSize = width + 3
            runnerZipperCost = (zipperSize * zipperPricePerInch) + runnerPrice + zipperCmCharge
        }
        val totalUnitPrice =
            fabricPricePerBag + making + printCost + cnt + cartonCost + runnerZipperCost + accessories + buyingCommission + profit

        if (totalUnitPrice < 0 || totalUnitPrice.isNaN() || totalUnitPrice.isInfinite()) {
            return
        }

        val formattedTotalUnitPrice = if (totalUnitPrice % 1 == 0.0) {
            totalUnitPrice.toInt().toString() // No decimal points if it's a whole number
        } else {
            String.format(Locale.ENGLISH, "%.4f", totalUnitPrice).trimEnd('0')
                .trimEnd('.') // Up to 4 decimal places, remove trailing zeros and decimal point if not needed
        }

        unitPrice.value = formattedTotalUnitPrice

        val totalFabricNeedForOneBag = bagConsumptionWithWastage / totalSqInchPerYard
        val totalConsumptionPerDozen = totalFabricNeedForOneBag * 12
        val overallFabricNeeded = totalFabricNeedForOneBag * quantity

        val formattedFabricPerBag = if (totalFabricNeedForOneBag % 1 == 0.0) {
            totalFabricNeedForOneBag.toInt().toString() // No decimal points if it's a whole number
        } else {
            String.format(Locale.ENGLISH, "%.4f", totalFabricNeedForOneBag).trimEnd('0')
                .trimEnd('.') // Up to 4 decimal places, remove trailing zeros and decimal point if not needed
        }

        val formattedFabricPerDozen = if (totalConsumptionPerDozen % 1 == 0.0) {
            totalConsumptionPerDozen.toInt().toString() // No decimal points if it's a whole number
        } else {
            String.format(Locale.ENGLISH, "%.4f", totalConsumptionPerDozen).trimEnd('0')
                .trimEnd('.') // Up to 4 decimal places, remove trailing zeros and decimal point if not needed
        }

        val formattedTotalFabricNeeded = if (overallFabricNeeded % 1 == 0.0) {
            overallFabricNeeded.toInt().toString() // No decimal points if it's a whole number
        } else {
            String.format(Locale.ENGLISH, "%.4f", overallFabricNeeded).trimEnd('0')
                .trimEnd('.') // Up to 4 decimal places, remove trailing zeros and decimal point if not needed
        }

        fabricPerBag.value = formattedFabricPerBag
        fabricPerDozen.value = formattedFabricPerDozen
        totalFabricNeeded.value = formattedTotalFabricNeeded

    }

    suspend fun saveDataToDataStore() {

        val cottonSavedData = CottonSavedData(
            handleWidth = handleWidth.value,
            cartonCost = cartonCost.value,
            runnerPrice = runnerPrice.value,
            zipperPricePerInch = zipperPricePerInch.value,
            zipperCmCharge = zipperCmCharge.value,
            wastagePercentage = wastagePercentage.value
        )

        cottonDataStore.saveData(cottonSavedData)

        calculateAllPrice()
    }

}