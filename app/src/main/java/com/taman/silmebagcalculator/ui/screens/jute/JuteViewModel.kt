package com.taman.silmebagcalculator.ui.screens.jute

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taman.silmebagcalculator.datastore.JuteDataStore
import com.taman.silmebagcalculator.models.JuteSavedData
import com.taman.silmebagcalculator.models.JuteUnitLocal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale

class JuteViewModel(private val juteDataStore: JuteDataStore) : ViewModel() {

    // Flows to observe data from DataStore
    private val _natural15x15JuteWidth = MutableStateFlow("54")
    val natural15x15JuteWidth = _natural15x15JuteWidth.asStateFlow()
    private val _color15x15JuteWidth = MutableStateFlow("49")
    val color15x15JuteWidth = _color15x15JuteWidth.asStateFlow()
    private val _natural13x13JuteWidth = MutableStateFlow("54")
    val natural13x13JuteWidth = _natural13x13JuteWidth.asStateFlow()
    private val _color13x13JuteWidth = MutableStateFlow("49")
    val color13x13JuteWidth = _color13x13JuteWidth.asStateFlow()
    private val _juteCottonSinglyPlyNaturalWidth = MutableStateFlow("58")
    val juteCottonSinglyPlyNaturalWidth = _juteCottonSinglyPlyNaturalWidth.asStateFlow()
    private val _juteCottonSinglyPlyColorWidth = MutableStateFlow("58")
    val juteCottonSinglyPlyColorWidth = _juteCottonSinglyPlyColorWidth.asStateFlow()
    private val _juteCottonDoublyPlyNaturalWidth = MutableStateFlow("58")
    val juteCottonDoublyPlyNaturalWidth = _juteCottonDoublyPlyNaturalWidth.asStateFlow()
    private val _juteCottonDoublyPlyColorWidth = MutableStateFlow("58")
    val juteCottonDoublyPlyColorWidth = _juteCottonDoublyPlyColorWidth.asStateFlow()
    private val _natural15x15JuteYard = MutableStateFlow("123")
    val natural15x15JuteYard = _natural15x15JuteYard.asStateFlow()
    private val _color15x15JuteYard = MutableStateFlow("173")
    val color15x15JuteYard = _color15x15JuteYard.asStateFlow()
    private val _natural13x13JuteYard = MutableStateFlow("118")
    val natural13x13JuteYard = _natural13x13JuteYard.asStateFlow()
    private val _color13x13JuteYard = MutableStateFlow("168")
    val color13x13JuteYard = _color13x13JuteYard.asStateFlow()
    private val _juteCottonSinglyPlyNaturalYard = MutableStateFlow("140")
    val juteCottonSinglyPlyNaturalYard = _juteCottonSinglyPlyNaturalYard.asStateFlow()
    private val _juteCottonSinglyPlyColorYard = MutableStateFlow("170")
    val juteCottonSinglyPlyColorYard = _juteCottonSinglyPlyColorYard.asStateFlow()
    private val _juteCottonDoublyPlyNaturalYard = MutableStateFlow("150")
    val juteCottonDoublyPlyNaturalYard = _juteCottonDoublyPlyNaturalYard.asStateFlow()
    private val _juteCottonDoublyPlyColorYard = MutableStateFlow("180")
    val juteCottonDoublyPlyColorYard = _juteCottonDoublyPlyColorYard.asStateFlow()
    private val _zipperPricePerInch = MutableStateFlow("0.15")
    val zipperPricePerInch = _zipperPricePerInch.asStateFlow()
    private val _runnerPrice = MutableStateFlow("2.2")
    val runnerPrice = _runnerPrice.asStateFlow()
    private val _sampleCost = MutableStateFlow("1")
    val sampleCost = _sampleCost.asStateFlow()
    private val _wastagePercentage = MutableStateFlow("5")
    val wastagePercentage = _wastagePercentage.asStateFlow()

    // Declaring all flows that will be used to observe data from DataStore
    init {
        observeData()
    }

    // MutableStates for visibility
    var isBottomSheetOpen = mutableStateOf(false)
        private set

    // Lamination yes or no
    var isLaminationSelected = mutableStateOf(false)
        private set

    // MutableStates for selection
    var selectedBagType = mutableStateOf("Shopping bag Common")
        private set

    // Lists for dropdown
    val cottonBagTypeList = listOf(
        "Shopping bag Common",
        "Handle bag",
        "Drawstring Bag",
        "Wine bag",
        "Ladies bag",
        "Messenger bag",
        "Jute File Folder",
    )

    var selectedFabricType = mutableStateOf("Natural 15x15")
        private set

    // Lists for dropdown
    val fabricTypeList = listOf(
        "Natural 15x15",
        "Color 15x15",
        "Natural 13x13",
        "Color 13x13",
        "Jute Cotton Single ply Natural",
        "Jute Cotton Single ply Color",
        "Jute Cotton Double ply Natural",
        "Jute Cotton Double ply Color",
    )

    // MutableStates for fields that affect price
    var height = mutableStateOf("")
        private set
    var width = mutableStateOf("")
        private set
    var gussetWidth = mutableStateOf("")
        private set
    var handleCost = mutableStateOf("")
        private set
    var printCost = mutableStateOf("")
        private set
    var accessories = mutableStateOf("")
        private set
    var profit = mutableStateOf("")
        private set
    var quantity = mutableStateOf("")
        private set
    var makingCost = mutableStateOf("")
        private set
    var deliveryCost = mutableStateOf("")
        private set

    // Unit price that will be updated dynamically
    var unitPrice = mutableStateOf("")
        private set

    // Function to observe data from DataStore
    private fun observeData() {
        juteDataStore.natural15x15JuteWidthFlow
            .onEach { it.let { _natural15x15JuteWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.color15x15JuteWidthFlow
            .onEach { it.let { _color15x15JuteWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.natural13x13JuteWidthFlow
            .onEach { it.let { _natural13x13JuteWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.color13x13JuteWidthFlow
            .onEach { it.let { _color13x13JuteWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonSinglyPlyNaturalWidthFlow
            .onEach { it.let { _juteCottonSinglyPlyNaturalWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonSinglyPlyColorWidthFlow
            .onEach { it.let { _juteCottonSinglyPlyColorWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonDoublyPlyNaturalWidthFlow
            .onEach { it.let { _juteCottonDoublyPlyNaturalWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonDoublyPlyColorWidthFlow
            .onEach { it.let { _juteCottonDoublyPlyColorWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.natural15x15JuteYardFlow
            .onEach { it.let { _natural15x15JuteYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.color15x15JuteYardFlow
            .onEach { it.let { _color15x15JuteYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.natural13x13JuteYardFlow
            .onEach { it.let { _natural13x13JuteYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.color13x13JuteYardFlow
            .onEach { it.let { _color13x13JuteYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonSinglyPlyNaturalYardFlow
            .onEach { it.let { _juteCottonSinglyPlyNaturalYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonSinglyPlyColorYardFlow
            .onEach { it.let { _juteCottonSinglyPlyColorWidth.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonDoublyPlyNaturalYardFlow
            .onEach { it.let { _juteCottonDoublyPlyNaturalYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.juteCottonDoublyPlyColorYardFlow
            .onEach { it.let { _juteCottonDoublyPlyColorYard.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.zipperPricePerInchFlow
            .onEach { it.let { _zipperPricePerInch.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.runnerPriceFlow
            .onEach { it.let { _runnerPrice.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.sampleCostFlow
            .onEach { it.let { _sampleCost.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
        juteDataStore.wastagePercentageFlow
            .onEach { it.let { _wastagePercentage.value = it } }
            .catch { e -> Log.e("handleWidthFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
    }

    // Function to update bottom sheet visibility
    fun updateBottomSheetState(value: Boolean) {
        isBottomSheetOpen.value = value
    }

    // Functions to update each value and recalculate unit price
    fun updateFabricType(value: String) {
        selectedFabricType.value = value
        calculateAllPrice()
    }

    fun updateSelectedBagType(value: String) {
        selectedBagType.value = value
        calculateAllPrice()
    }

    fun updateLamination(value: Boolean) {
        isLaminationSelected.value = value
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

    fun updateGussetWidth(value: String) {
        gussetWidth.value = value
        calculateAllPrice()
    }

    fun updateHandleCost(value: String) {
        handleCost.value = value
        calculateAllPrice()
    }

    fun updatePrintCost(value: String) {
        printCost.value = value
        calculateAllPrice()
    }

    fun updateAccessories(value: String) {
        accessories.value = value
        calculateAllPrice()
    }

    fun updateProfit(value: String) {
        profit.value = value
        calculateAllPrice()
    }

    fun updateQuantity(value: String) {
        quantity.value = value
        calculateAllPrice()
    }

    fun updateMakingCost(value: String) {
        makingCost.value = value
        calculateAllPrice()
    }

    fun updateDeliveryCost(value: String) {
        deliveryCost.value = value
        calculateAllPrice()
    }

    fun updateNatural15x15JuteWidth(value: String) {
        _natural15x15JuteWidth.value = value
    }

    fun updateColor15x15JuteWidth(value: String) {
        _color15x15JuteWidth.value = value
    }

    fun updateNatural13x13JuteWidth(value: String) {
        _natural13x13JuteWidth.value = value
    }

    fun updateColor13x13JuteWidth(value: String) {
        _color13x13JuteWidth.value = value
    }

    fun updateJuteCottonSinglyPlyNaturalWidth(value: String) {
        _juteCottonSinglyPlyNaturalWidth.value = value
    }

    fun updateJuteCottonSinglyPlyColorWidth(value: String) {
        _juteCottonSinglyPlyColorWidth.value = value
    }

    fun updateJuteCottonDoublyPlyNaturalWidth(value: String) {
        _juteCottonDoublyPlyNaturalWidth.value = value
    }

    fun updateJuteCottonDoublyPlyColorWidth(value: String) {
        _juteCottonDoublyPlyColorWidth.value = value
    }

    fun updateNatural15x15JuteYard(value: String) {
        _natural15x15JuteYard.value = value
    }

    fun updateColor15x15JuteYard(value: String) {
        _color15x15JuteYard.value = value
    }

    fun updateNatural13x13JuteYard(value: String) {
        _natural13x13JuteYard.value = value
    }

    fun updateColor13x13JuteYard(value: String) {
        _color13x13JuteYard.value = value
    }

    fun updateJuteCottonSinglyPlyNaturalYard(value: String) {
        _juteCottonSinglyPlyNaturalYard.value = value
    }

    fun updateJuteCottonSinglyPlyColorYard(value: String) {
        _juteCottonSinglyPlyColorYard.value = value
    }

    fun updateJuteCottonDoublyPlyNaturalYard(value: String) {
        _juteCottonDoublyPlyNaturalYard.value = value
    }

    fun updateJuteCottonDoublyPlyColorYard(value: String) {
        _juteCottonDoublyPlyColorYard.value = value
    }

    fun updateZipperPricePerInch(value: String) {
        _zipperPricePerInch.value = value
    }

    fun updateRunnerPrice(value: String) {
        _runnerPrice.value = value
    }

    fun updateSampleCost(value: String) {
        _sampleCost.value = value
    }

    fun updateWastagePercentage(value: String) {
        _wastagePercentage.value = value
    }

    private fun getJuteUnitLocal(value: String): JuteUnitLocal {

        val juteUnitLocal = JuteUnitLocal()

        when (value) {
            "Select Bag Type" -> {
                return juteUnitLocal
            }

            "Natural 15x15" -> {
                juteUnitLocal.fabricTypeWidth = natural15x15JuteWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard = natural15x15JuteYard.value.toDoubleOrNull() ?: 0.0
            }

            "Color 15x15" -> {
                juteUnitLocal.fabricTypeWidth = color15x15JuteWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard = color15x15JuteYard.value.toDoubleOrNull() ?: 0.0
            }

            "Natural 13x13" -> {
                juteUnitLocal.fabricTypeWidth = natural13x13JuteWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard = natural13x13JuteYard.value.toDoubleOrNull() ?: 0.0
            }

            "Color 13x13" -> {
                juteUnitLocal.fabricTypeWidth = color13x13JuteWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard = color13x13JuteYard.value.toDoubleOrNull() ?: 0.0
            }

            "Jute Cotton Singly Ply Natural" -> {
                juteUnitLocal.fabricTypeWidth =
                    juteCottonSinglyPlyNaturalWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard =
                    juteCottonSinglyPlyNaturalYard.value.toDoubleOrNull() ?: 0.0
            }

            "Jute Cotton Singly Ply Color" -> {
                juteUnitLocal.fabricTypeWidth =
                    juteCottonSinglyPlyColorWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard =
                    juteCottonSinglyPlyColorYard.value.toDoubleOrNull() ?: 0.0
            }

            "Jute Cotton Doubly Ply Natural" -> {
                juteUnitLocal.fabricTypeWidth =
                    juteCottonDoublyPlyNaturalWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard =
                    juteCottonDoublyPlyNaturalYard.value.toDoubleOrNull() ?: 0.0
            }

            "Jute Cotton Doubly Ply Color" -> {
                juteUnitLocal.fabricTypeWidth =
                    juteCottonDoublyPlyColorWidth.value.toDoubleOrNull() ?: 0.0
                juteUnitLocal.fabricTypeYard =
                    juteCottonDoublyPlyColorYard.value.toDoubleOrNull() ?: 0.0
            }

            else -> {
                return juteUnitLocal
            }

        }

        return juteUnitLocal
    }

    fun calculateAllPrice() {

        val height = height.value.toDoubleOrNull() ?: 0.0
        val width = width.value.toDoubleOrNull() ?: 0.0
        val gussetWidth = gussetWidth.value.toDoubleOrNull() ?: 0.0
        val handleCost = handleCost.value.toDoubleOrNull() ?: 0.0
        val printCost = printCost.value.toDoubleOrNull() ?: 0.0
        val accessories = accessories.value.toDoubleOrNull() ?: 0.0
        val profit = profit.value.toDoubleOrNull() ?: 0.0
        //val quantity = quantity.value.toDoubleOrNull() ?: 0.0
        val makingCost = makingCost.value.toDoubleOrNull() ?: 0.0
        val deliveryCost = deliveryCost.value.toDoubleOrNull() ?: 0.0

        val zipperPricePerInch = zipperPricePerInch.value.toDoubleOrNull() ?: 0.0
        val runnerPrice = runnerPrice.value.toDoubleOrNull() ?: 0.0
        val sampleCost = sampleCost.value.toDoubleOrNull() ?: 0.0
        val wastagePercentage = wastagePercentage.value.toDoubleOrNull() ?: 0.0

        val juteUnitLocal = getJuteUnitLocal(selectedFabricType.value)

        if (isLaminationSelected.value && juteUnitLocal.fabricTypeWidth > 0.0 && juteUnitLocal.fabricTypeYard > 0.0) {
            juteUnitLocal.fabricTypeWidth = juteUnitLocal.fabricTypeWidth - 25
            juteUnitLocal.fabricTypeYard = juteUnitLocal.fabricTypeYard - 25
        }

        val fabricPricePerSqInch =
            juteUnitLocal.fabricTypeYard / (juteUnitLocal.fabricTypeWidth * 36)

        val bodyFabricPricePerYard = fabricPricePerSqInch
        val gussetFabricPricePerYard = fabricPricePerSqInch

        val bodyConsumption = (height + 1.5) * width * 2
        val gussetConsumption = ((height + 1.5) * 2 + width) * (gussetWidth + 2)
        val zipperFabricConsumption = width * (gussetWidth + 2)
        val zipperSize = width + 3
        val runnerZipperCost = (zipperSize * zipperPricePerInch) + runnerPrice
        val bagFabricCost =
            ((bodyConsumption + zipperFabricConsumption) * bodyFabricPricePerYard) + (gussetConsumption * gussetFabricPricePerYard)
        val wastage = bagFabricCost * (wastagePercentage / 100)
        val totalUnitPrice =
            bagFabricCost + handleCost + makingCost + runnerZipperCost + sampleCost + printCost + accessories + wastage + profit + deliveryCost

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

    }

    suspend fun saveDataToDataStore() {

        val juteSavedData = JuteSavedData(
            natural15x15JuteWidth = natural15x15JuteWidth.value,
            color15x15JuteWidth = color15x15JuteWidth.value,
            natural13x13JuteWidth = natural13x13JuteWidth.value,
            color13x13JuteWidth = color13x13JuteWidth.value,
            juteCottonSinglyPlyNaturalWidth = juteCottonSinglyPlyNaturalWidth.value,
            juteCottonSinglyPlyColorWidth = juteCottonSinglyPlyColorWidth.value,
            juteCottonDoublyPlyNaturalWidth = juteCottonDoublyPlyNaturalWidth.value,
            juteCottonDoublyPlyColorWidth = juteCottonDoublyPlyColorWidth.value,
            natural15x15JuteYard = natural15x15JuteYard.value,
            color15x15JuteYard = color15x15JuteYard.value,
            natural13x13JuteYard = natural13x13JuteYard.value,
            color13x13JuteYard = color13x13JuteYard.value,
            juteCottonSinglyPlyNaturalYard = juteCottonSinglyPlyNaturalYard.value,
            juteCottonSinglyPlyColorYard = juteCottonSinglyPlyColorYard.value,
            juteCottonDoublyPlyNaturalYard = juteCottonDoublyPlyNaturalYard.value,
            juteCottonDoublyPlyColorYard = juteCottonDoublyPlyColorYard.value,
            zipperPricePerInch = zipperPricePerInch.value,
            runnerPrice = runnerPrice.value,
            sampleCost = sampleCost.value,
            wastagePercentage = wastagePercentage.value,
        )

        juteDataStore.saveData(juteSavedData)

        calculateAllPrice()
    }

}