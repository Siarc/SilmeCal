package com.taman.silmebagcalculator.ui.screens.nonwoven

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.taman.silmebagcalculator.models.NonwovenUnitLocals


class NonWovenViewModel: ViewModel() {

    var showSewingBagOptions = mutableStateOf(false)
        private set

    var showDeliveryOptions = mutableStateOf(false)
        private set

    var isGussetSelected = mutableStateOf(false)
        private set

    var isZipperSelected = mutableStateOf(false)
        private set

    var selectedBagType = mutableStateOf("Handle Bag")
        private set

    var selectedPrintColor = mutableStateOf("One Color")
        private set

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

        val nonwovenBagFabricPricePerUnit = unitLocals.fabricSqInch * 0.00067 * (fabricPriceValue / 1000) * gsmValue
        val wastage = nonwovenBagFabricPricePerUnit * (2 / 100)
        val blockCost = ((heightValue - 4) * (widthValue - 3) * 10) / quantityValue
        val gussetCost = unitLocals.gussetPrint
        val zipperCost = unitLocals.zipper

        val deliveryCost = if (deliveryFeeValue > 0) deliveryFeeValue / quantityValue else 0.0

        val totalCost = nonwovenBagFabricPricePerUnit + blockCost + printColorValue +gussetCost + zipperCost + additionalCostValue + profitValue + deliveryCost + wastage

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
            "Select Bag Type" -> return unitLocals
            "Handle Bag" -> {
                val heming = 1.5
                val handleFabric = 70.0
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) + (gusset * width) + handleFabric
            }
            "D Cut Bag" -> {
                val heming = 2.5
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2) + (gusset * width)
            }
            "Sewing Bag" -> {
                val heming = 1.5
                val handleFabric = 70.0
                val runner = 1.0
                val piping = (height * 4) + (width * 2) + 10
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.runner = runner
                unitLocals.piping = piping

                if (allowGussetPrint) {
                    unitLocals.gussetPrint = 1 + ((height - 3) * (gusset - 1) * 10) / quantity
                }

                if (allowZipper) {
                    unitLocals.zipper = runner +
                            ((width + 3) * 0.15) +
                            3 +
                            ((width + 1) * height) * 0.00067 * gsm * (fabricPrice / 1000)
                }

                unitLocals.fabricSqInch = ((height + heming) * width * 2) +
                        (gusset * ((height + heming) * 2 + width)) +
                        handleFabric +
                        piping
            }
            "Autobox Handle Bag" -> {
                val heming = 1.5
                val handleFabric = 70.0
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) +
                        ((gusset + 0.75) * ((height + heming) * 2 + width)) +
                        handleFabric
            }
            "Autobox D Cut Bag" -> {
                val heming = 2.5
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2) +
                        ((gusset + 0.75) * ((height + heming) * 2 + width))
            }
            else -> return unitLocals
        }

        return unitLocals
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