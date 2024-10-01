package com.taman.silmebagcalculator.ui.screens.nonwoven

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taman.silmebagcalculator.datastore.NonWovenDataStore
import com.taman.silmebagcalculator.models.NonWovenSavedData
import com.taman.silmebagcalculator.models.NonwovenUnitLocals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NonWovenViewModel @Inject constructor(
    private val nonWovenDataStore: NonWovenDataStore
) : ViewModel() {

    // Flows to observe data from DataStore
    private val _handleBagHemming = MutableStateFlow("1.5")
    val handleBagHemming: StateFlow<String> = _handleBagHemming.asStateFlow()

    private val _handleBagHandleFabric = MutableStateFlow("70.0")
    val handleBagHandleFabric: StateFlow<String> = _handleBagHandleFabric.asStateFlow()

    private val _handleBagMakingType = MutableStateFlow("0.80")
    val handleBagMakingType: StateFlow<String> = _handleBagMakingType.asStateFlow()

    private val _dCutBagHemming = MutableStateFlow("2.5")
    val dCutBagHemming: StateFlow<String> = _dCutBagHemming.asStateFlow()

    private val _dCutBagMakingType = MutableStateFlow("0.50")
    val dCutBagMakingType: StateFlow<String> = _dCutBagMakingType.asStateFlow()

    private val _sewingBagHemming = MutableStateFlow("1.5")
    val sewingBagHemming: StateFlow<String> = _sewingBagHemming.asStateFlow()

    private val _sewingBagHandleFabric = MutableStateFlow("70.0")
    val sewingBagHandleFabric: StateFlow<String> = _sewingBagHandleFabric.asStateFlow()

    private val _sewingBagRunner = MutableStateFlow("1.0")
    val sewingBagRunner: StateFlow<String> = _sewingBagRunner.asStateFlow()

    private val _sewingBagPipingExtraAddition = MutableStateFlow("10.0")
    val sewingBagPipingExtraAddition: StateFlow<String> = _sewingBagPipingExtraAddition.asStateFlow()

    private val _sewingBagMakingType = MutableStateFlow("4.0")
    val sewingBagMakingType: StateFlow<String> = _sewingBagMakingType.asStateFlow()

    private val _autoboxHandleBagHemming = MutableStateFlow("1.5")
    val autoboxHandleBagHemming: StateFlow<String> = _autoboxHandleBagHemming.asStateFlow()

    private val _autoboxHandleBagHandleFabric = MutableStateFlow("70.0")
    val autoboxHandleBagHandleFabric: StateFlow<String> = _autoboxHandleBagHandleFabric.asStateFlow()

    private val _autoboxHandleBagMakingType = MutableStateFlow("2.0")
    val autoboxHandleBagMakingType: StateFlow<String> = _autoboxHandleBagMakingType.asStateFlow()

    private val _autoboxDCutBagHemming = MutableStateFlow("2.5")
    val autoboxDCutBagHemming: StateFlow<String> = _autoboxDCutBagHemming.asStateFlow()

    private val _autoboxDCutBagMakingType = MutableStateFlow("1.7")
    val autoboxDCutBagMakingType: StateFlow<String> = _autoboxDCutBagMakingType.asStateFlow()

    // Declaring all flows that will be used to observe data from DataStore
    init {
        observeData()
    }

    // MutableStates for visibility
    var isBottomSheetOpen = mutableStateOf(false)
        private set

    var showSewingBagOptions = mutableStateOf(false)
        private set

    var showDeliveryOptions = mutableStateOf(false)
        private set

    var isGussetSelected = mutableStateOf(false)
        private set

    var isZipperSelected = mutableStateOf(false)
        private set

    // MutableStates for selection
    var selectedBagType = mutableStateOf("Handle Bag")
        private set

    var selectedPrintColor = mutableStateOf("One Color")
        private set

    // Lists for dropdown
    val nonWovenBagTypeList = listOf("Handle Bag",
        "D Cut Bag",
        "Autobox Handle Bag",
        "Autobox D Cut Bag",
        "Sewing Bag")
    val printColors = listOf("One Color",
        "Two Color",
        "Three Color",
        "Four Color")

    // MutableStates for fields that affect price
    var fabricPrice = mutableStateOf("")
        private set

    var height = mutableStateOf("")
        private set

    var width = mutableStateOf("")
        private set

    var gsm = mutableStateOf("")
        private set

    var gusset = mutableStateOf("")
        private set

    var quantity = mutableStateOf("")
        private set

    var additionalCost = mutableStateOf("")
        private set

    var profit = mutableStateOf("")
        private set

    var deliveryFee = mutableStateOf("")
        private set

    // Unit price that will be updated dynamically
    var unitPrice = mutableStateOf("")
        private set

    // Function to observe data from DataStore
    private fun observeData() {

        nonWovenDataStore.handleBagHemmingFlow
            .onEach { it.let { _handleBagHemming.value = it } }
            .catch { e -> Log.e("handleBagHemmingFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.handleBagHandleFabricFlow
            .onEach { it.let { _handleBagHandleFabric.value = it } }
            .catch { e -> Log.e("handleBagHandleFabricFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.handleBagMakingTypeFlow
            .onEach { it.let { _handleBagMakingType.value = it } }
            .catch { e -> Log.e("handleBagMakingTypeFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.dCutBagHemmingFlow
            .onEach { it.let { _dCutBagHemming.value = it } }
            .catch { e -> Log.e("dCutBagHemmingFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.dCutBagMakingTypeFlow
            .onEach { it.let { _dCutBagMakingType.value = it } }
            .catch { e -> Log.e("dCutBagMakingTypeFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagHemmingFlow
            .onEach { it.let { _sewingBagHemming.value = it } }
            .catch { e -> Log.e("sewingBagHemmingFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagHandleFabricFlow
            .onEach { it.let { _sewingBagHandleFabric.value = it } }
            .catch { e -> Log.e("sewingBagHandleFabricFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagRunnerFlow
            .onEach { it.let { _sewingBagRunner.value = it } }
            .catch { e -> Log.e("sewingBagRunnerFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagPipingExtraAdditionFlow
            .onEach { it.let { _sewingBagPipingExtraAddition.value = it } }
            .catch { e -> Log.e("sewingBagPipingExtraAdditionFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagMakingTypeFlow
            .onEach { it.let { _sewingBagMakingType.value = it } }
            .catch { e -> Log.e("sewingBagMakingTypeFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxHandleBagHemmingFlow
            .onEach { it.let { _autoboxHandleBagHemming.value = it } }
            .catch { e -> Log.e("autoboxHandleBagHemmingFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxHandleBagHandleFabricFlow
            .onEach { it.let { _autoboxHandleBagHandleFabric.value = it } }
            .catch { e -> Log.e("autoboxHandleBagHandleFabricFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxHandleBagMakingTypeFlow
            .onEach { it.let { _autoboxHandleBagMakingType.value = it } }
            .catch { e -> Log.e("autoboxHandleBagMakingTypeFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxDCutBagHemmingFlow
            .onEach { it.let { _autoboxDCutBagHemming.value = it } }
            .catch { e -> Log.e("autoboxDCutBagHemmingFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxDCutBagMakingTypeFlow
            .onEach { it.let { _autoboxDCutBagMakingType.value = it } }
            .catch { e -> Log.e("autoboxDCutBagMakingTypeFlow", "Error: ${e.message}") }
            .launchIn(viewModelScope)
    }

    // Function to get the print color value
    private fun getColorValue(): Int {
        return when (selectedPrintColor.value) {
            "Print Color" -> 0
            "One Color" -> 1
            "Two Color" -> 2
            "Three Color" -> 3
            "Four Color" -> 4
            else -> 0
        }
    }

    // Function to calculate the unit price dynamically
    private fun calculateUnitPrice() {
        val heightValue = height.value.toDoubleOrNull() ?: 0.0
        val widthValue = width.value.toDoubleOrNull() ?: 0.0
        val gussetValue = gusset.value.toDoubleOrNull() ?: 0.0
        val quantityValue = quantity.value.toIntOrNull() ?: 1 // Avoid dividing by 0, default to 1
        val gsmValue = gsm.value.toDoubleOrNull() ?: 0.0
        val fabricPriceValue = fabricPrice.value.toDoubleOrNull() ?: 0.0
        val deliveryFeeValue = deliveryFee.value.toDoubleOrNull() ?: 0.0
        val additionalCostValue = additionalCost.value.toDoubleOrNull() ?: 0.0
        val profitValue = profit.value.toDoubleOrNull() ?: 0.0

        val unitLocals = getNonwovenUnitLocals(
            selectedBagType.value,
            heightValue,
            widthValue,
            gussetValue,
            quantityValue,
            gsmValue,
            fabricPriceValue,
            isGussetSelected.value,
            isZipperSelected.value
        )

        val printColorValue = getColorValue()

        val nonwovenBagFabricPricePerUnit = unitLocals.fabricSqInch * 0.00067 * (fabricPriceValue / 1000.0) * gsmValue
        val twoPercent = 2.0/100.0
        val wastage = nonwovenBagFabricPricePerUnit * twoPercent
        val blockCost = ((heightValue - 4.0) * (widthValue - 3.0) * 10.0) / quantityValue
        val gussetCost = unitLocals.gussetPrint
        val zipperCost = unitLocals.zipper
        val makingTypeCost = unitLocals.makingType

        val deliveryCost = if (deliveryFeeValue > 0) deliveryFeeValue / quantityValue else 0.0

        val totalCost = nonwovenBagFabricPricePerUnit + makingTypeCost + blockCost + printColorValue +gussetCost + zipperCost + additionalCostValue + profitValue + deliveryCost + wastage

        if (totalCost < 0 || totalCost.isNaN() || totalCost.isInfinite()) {
            return
        }

        val formattedTotalCost = if (totalCost % 1 == 0.0) {
            totalCost.toInt().toString() // No decimal points if it's a whole number
        } else {
            String.format(Locale.ENGLISH,"%.4f", totalCost).trimEnd('0').trimEnd('.') // Up to 4 decimal places, remove trailing zeros and decimal point if not needed
        }

        unitPrice.value = formattedTotalCost
    }

    private fun getNonwovenUnitLocals(
        nonwovenBagType: String,
        height: Double,
        width: Double,
        gusset: Double,
        quantity: Int,
        gsm: Double,
        fabricPrice: Double,
        allowGussetPrint: Boolean,
        allowZipper: Boolean
    ): NonwovenUnitLocals {
        val unitLocals = NonwovenUnitLocals()

        when (nonwovenBagType) {
            "Select Bag Type" -> {
                return unitLocals
            }
            "Handle Bag" -> {
                val heming = handleBagHemming.value.toDoubleOrNull() ?: 0.0
                val handleFabric = handleBagHandleFabric.value.toDoubleOrNull() ?: 0.0
                unitLocals.makingType = handleBagMakingType.value.toDoubleOrNull() ?: 0.0
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) + (gusset * width) + handleFabric
            }
            "D Cut Bag" -> {
                val heming = dCutBagHemming.value.toDoubleOrNull() ?: 0.0
                unitLocals.makingType = dCutBagMakingType.value.toDoubleOrNull() ?: 0.0
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2.0) + (gusset * width)
            }
            "Sewing Bag" -> {
                val heming = sewingBagHemming.value.toDoubleOrNull() ?: 0.0
                val handleFabric = sewingBagHandleFabric.value.toDoubleOrNull() ?: 0.0
                val runner = sewingBagRunner.value.toDoubleOrNull() ?: 0.0
                val piping = (height * 4.0) + (width * 2.0) + (sewingBagPipingExtraAddition.value.toDoubleOrNull() ?: 0.0)
                unitLocals.makingType = sewingBagMakingType.value.toDoubleOrNull() ?: 0.0
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.runner = runner
                unitLocals.piping = piping

                if (allowGussetPrint) {
                    unitLocals.gussetPrint = 1.0 + ((height - 3.0) * (gusset - 1.0) * 10.0) / quantity
                }
                if (allowZipper) {
                    unitLocals.zipper = runner +
                            ((width + 3.0) * 0.15) +
                            3.0 +
                            ((width + 1.0) * height) * 0.00067 * gsm * (fabricPrice / 1000.0)
                }
                unitLocals.fabricSqInch = ((height + heming) * width * 2.0) +
                        (gusset * ((height + heming) * 2.0 + width)) +
                        handleFabric +
                        piping
            }
            "Autobox Handle Bag" -> {
                val heming = autoboxHandleBagHemming.value.toDoubleOrNull() ?: 0.0
                val handleFabric = autoboxHandleBagHandleFabric.value.toDoubleOrNull() ?: 0.0
                unitLocals.makingType = autoboxHandleBagMakingType.value.toDoubleOrNull() ?: 0.0
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) +
                        ((gusset + 0.75) * ((height + heming) * 2 + width)) +
                        handleFabric
            }
            "Autobox D Cut Bag" -> {
                val heming = autoboxDCutBagHemming.value.toDoubleOrNull() ?: 0.0
                unitLocals.makingType = autoboxDCutBagMakingType.value.toDoubleOrNull() ?: 0.0
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2.0) +
                        ((gusset + 0.75) * ((height + heming) * 2.0 + width))
            }
            else -> {
                return unitLocals
            }
        }

        return unitLocals
    }

    // Function to update bottom sheet visibility
    fun updateBottomSheetState(value: Boolean){
        isBottomSheetOpen.value = value
    }

    // Functions to update each value and recalculate unit price
    fun updateFabricPrice(newPrice: String) {
        fabricPrice.value = newPrice
        calculateUnitPrice()  // Recalculate when fabric price is updated
    }

    fun updateHeight(newHeight: String) {
        height.value = newHeight
        calculateUnitPrice()  // Recalculate when height is updated
    }

    fun updateWidth(newWidth: String) {
        width.value = newWidth
        calculateUnitPrice()  // Recalculate when width is updated
    }

    fun updateGsm(newGsm: String) {
        gsm.value = newGsm
        calculateUnitPrice()  // Recalculate when GSM is updated
    }

    fun updateGusset(newGusset: String) {
        gusset.value = newGusset
        calculateUnitPrice()  // Recalculate when gusset is updated
    }

    fun updateQuantity(newQuantity: String) {
        quantity.value = newQuantity
        calculateUnitPrice()  // Recalculate when quantity is updated
    }

    fun updateAdditionalCost(newCost: String) {
        additionalCost.value = newCost
        calculateUnitPrice()  // Recalculate when additional cost is updated
    }

    fun updateProfit(newProfit: String) {
        profit.value = newProfit
        calculateUnitPrice()  // Recalculate when profit is updated
    }

    fun updateDeliveryFee(newFee: String) {
        deliveryFee.value = newFee
        calculateUnitPrice()  // Recalculate when delivery fee is updated
    }

    // Sewing Bag Option visibility updates
    fun updateSewingBagOptionsVisibility(value : Boolean) {
        showSewingBagOptions.value = value
    }

    // Update print color
    fun updateSelectedPrintColor(value : String) {
        selectedPrintColor.value = value
        calculateUnitPrice()
    }

    // Update bag type
    fun updateSelectedBagType(value : String) {
        selectedBagType.value = value
        calculateUnitPrice()
    }

    // Delivery Option visibility updates
    fun updateDeliveryOptionsVisibility(value : Boolean) {
        showDeliveryOptions.value = value
    }

    // Toggle Gusset options
    fun updateGussetSelected() {
        isGussetSelected.value = !isGussetSelected.value
        calculateUnitPrice()  // Recalculate when gusset is toggled
    }

    // Toggle Zipper options
    fun updateZipperSelected() {
        isZipperSelected.value = !isZipperSelected.value
        calculateUnitPrice()  // Recalculate when zipper is toggled
    }

    // Handle Bag Options
    fun updateHandleBagHemming(value : String){
        _handleBagHemming.value = value
    }
    fun updateHandleBagHandleFabric(value : String){
        _handleBagHandleFabric.value = value
    }
    fun updateHandleBagMakingType(value : String){
        _handleBagMakingType.value = value
    }

    // D Cut Bag Options
    fun updateDCutBagHemming(value : String){
        _dCutBagHemming.value = value
    }
    fun updateDCutBagMakingType(value : String) {
        _dCutBagMakingType.value = value
    }

    // Sewing Bag Options
    fun updateSewingBagHemming(value : String){
        _sewingBagHemming.value = value
    }
    fun updateSewingBagHandleFabric(value : String){
        _sewingBagHandleFabric.value = value
    }
    fun updateSewingBagRunner(value : String){
        _sewingBagRunner.value = value
    }
    fun updateSewingBagPipingExtraAddition(value : String){
        _sewingBagPipingExtraAddition.value = value
    }
    fun updateSewingBagMakingType(value : String){
        _sewingBagMakingType.value = value
    }

    // Autobox Handle Bag Options
    fun updateAutoboxHandleBagHemming(value : String){
        _autoboxHandleBagHemming.value = value
    }
    fun updateAutoboxHandleBagHandleFabric(value : String){
        _autoboxHandleBagHandleFabric.value = value
    }
    fun updateAutoboxHandleBagMakingType(value : String) {
        _autoboxHandleBagMakingType.value = value
    }

    // Autobox D Cut Bag Options
    fun updateAutoboxDCutBagHemming(value : String){
        _autoboxDCutBagHemming.value = value
    }
    fun updateAutoboxDCutBagMakingType(value : String) {
        _autoboxDCutBagMakingType.value = value
    }

    suspend fun saveDataToDataStore() {

        val nonWovenSavedData = NonWovenSavedData(
            handleBagHemming = handleBagHemming.value,
            handleBagHandleFabric = handleBagHandleFabric.value,
            handleBagMakingType = handleBagMakingType.value,
            dCutBagHemming = dCutBagHemming.value,
            dCutBagMakingType = dCutBagMakingType.value,
            sewingBagHemming = sewingBagHemming.value,
            sewingBagHandleFabric = sewingBagHandleFabric.value,
            sewingBagRunner = sewingBagRunner.value,
            sewingBagPipingExtraAddition = sewingBagPipingExtraAddition.value,
            sewingBagMakingType = sewingBagMakingType.value,
            autoboxHandleBagHemming = autoboxHandleBagHemming.value,
            autoboxHandleBagHandleFabric = autoboxHandleBagHandleFabric.value,
            autoboxHandleBagMakingType = autoboxHandleBagMakingType.value,
            autoboxDCutBagHemming = autoboxDCutBagHemming.value,
            autoboxDCutBagMakingType = autoboxDCutBagMakingType.value
        )

        calculateUnitPrice()

        nonWovenDataStore.saveData(nonWovenSavedData)
    }

}