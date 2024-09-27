package com.taman.silmebagcalculator.ui.screens.nonwoven

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taman.silmebagcalculator.datastore.NonWovenDataStore
import com.taman.silmebagcalculator.models.NonwovenUnitLocals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NonWovenViewModel @Inject constructor(
    private val nonWovenDataStore: NonWovenDataStore
) : ViewModel() {

    init {
        observeData()
    }

    // Flows to observe data from DataStore
    private val _handleBagHemming = MutableStateFlow(0.0)
    val handleBagHemming: StateFlow<Double> = _handleBagHemming.asStateFlow()

    private val _handleBagHandleFabric = MutableStateFlow(0.0)
    val handleBagHandleFabric: StateFlow<Double> = _handleBagHandleFabric.asStateFlow()

    private val _handleBagMakingType = MutableStateFlow(0.0)
    val handleBagMakingType: StateFlow<Double> = _handleBagMakingType.asStateFlow()

    private val _dCutBagHemming = MutableStateFlow(0.0)
    val dCutBagHemming: StateFlow<Double> = _dCutBagHemming.asStateFlow()

    private val _dCutBagMakingType = MutableStateFlow(0.0)
    val dCutBagMakingType: StateFlow<Double> = _dCutBagMakingType.asStateFlow()

    private val _sewingBagHemming = MutableStateFlow(0.0)
    val sewingBagHemming: StateFlow<Double> = _sewingBagHemming.asStateFlow()

    private val _sewingBagHandleFabric = MutableStateFlow(0.0)
    val sewingBagHandleFabric: StateFlow<Double> = _sewingBagHandleFabric.asStateFlow()

    private val _sewingBagRunner = MutableStateFlow(0.0)
    val sewingBagRunner: StateFlow<Double> = _sewingBagRunner.asStateFlow()

    private val _sewingBagPipingExtraAddition = MutableStateFlow(0.0)
    val sewingBagPipingExtraAddition: StateFlow<Double> = _sewingBagPipingExtraAddition.asStateFlow()

    private val _sewingBagMakingType = MutableStateFlow(0.0)
    val sewingBagMakingType: StateFlow<Double> = _sewingBagMakingType.asStateFlow()

    private val _autoboxHandleBagHemming = MutableStateFlow(0.0)
    val autoboxHandleBagHemming: StateFlow<Double> = _autoboxHandleBagHemming.asStateFlow()

    private val _autoboxHandleBagHandleFabric = MutableStateFlow(0.0)
    val autoboxHandleBagHandleFabric: StateFlow<Double> = _autoboxHandleBagHandleFabric.asStateFlow()

    private val _autoboxHandleBagMakingType = MutableStateFlow(0.0)
    val autoboxHandleBagMakingType: StateFlow<Double> = _autoboxHandleBagMakingType.asStateFlow()

    private val _autoboxDCutBagHemming = MutableStateFlow(0.0)
    val autoboxDCutBagHemming: StateFlow<Double> = _autoboxDCutBagHemming.asStateFlow()

    private val _autoboxDCutBagMakingType = MutableStateFlow(0.0)
    val autoboxDCutBagMakingType: StateFlow<Double> = _autoboxDCutBagMakingType.asStateFlow()

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
            .onEach { _handleBagHemming.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.handleBagHandleFabricFlow
            .onEach { _handleBagHandleFabric.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.handleBagMakingTypeFlow
            .onEach { _handleBagMakingType.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.dCutBagHemmingFlow
            .onEach { _dCutBagHemming.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.dCutBagMakingTypeFlow
            .onEach { _dCutBagMakingType.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagHemmingFlow
            .onEach { _sewingBagHemming.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagHandleFabricFlow
            .onEach { _sewingBagHandleFabric.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagRunnerFlow
            .onEach { _sewingBagRunner.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagPipingExtraAdditionFlow
            .onEach { _sewingBagPipingExtraAddition.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.sewingBagMakingTypeFlow
            .onEach { _sewingBagMakingType.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxHandleBagHemmingFlow
            .onEach { _autoboxHandleBagHemming.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxHandleBagHandleFabricFlow
            .onEach { _autoboxHandleBagHandleFabric.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxHandleBagMakingTypeFlow
            .onEach { _autoboxHandleBagMakingType.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxDCutBagHemmingFlow
            .onEach { _autoboxDCutBagHemming.value = it }
            .launchIn(viewModelScope)

        nonWovenDataStore.autoboxDCutBagMakingTypeFlow
            .onEach { _autoboxDCutBagMakingType.value = it }
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
            String.format("%.4f", totalCost).trimEnd('0').trimEnd('.') // Up to 4 decimal places, remove trailing zeros and decimal point if not needed
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
                val heming = handleBagHemming.value
                val handleFabric = handleBagHandleFabric.value
                unitLocals.makingType = handleBagMakingType.value
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) + (gusset * width) + handleFabric
            }
            "D Cut Bag" -> {
                val heming = dCutBagHemming.value
                unitLocals.makingType = dCutBagMakingType.value
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2.0) + (gusset * width)
            }
            "Sewing Bag" -> {
                val heming = sewingBagHemming.value
                val handleFabric = sewingBagHandleFabric.value
                val runner = sewingBagRunner.value
                val piping = (height * 4.0) + (width * 2.0) + sewingBagPipingExtraAddition.value
                unitLocals.makingType = sewingBagMakingType.value
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
                val heming = autoboxHandleBagHemming.value
                val handleFabric = autoboxHandleBagHandleFabric.value
                unitLocals.makingType = autoboxHandleBagMakingType.value
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) +
                        ((gusset + 0.75) * ((height + heming) * 2 + width)) +
                        handleFabric
            }
            "Autobox D Cut Bag" -> {
                val heming = autoboxDCutBagHemming.value
                unitLocals.makingType = autoboxDCutBagMakingType.value
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

}