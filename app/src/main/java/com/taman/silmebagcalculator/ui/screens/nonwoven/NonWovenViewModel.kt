package com.taman.silmebagcalculator.ui.screens.nonwoven

import android.util.Log
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

        Log.d("Rony3", "selectedBagType: ${selectedBagType.value}")

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

        Log.d("Rony2", "calculateUnitPrice heming: ${unitLocals.heming}")
        Log.d("Rony2", "calculateUnitPrice handleFabric: ${unitLocals.handleFabric}")
        Log.d("Rony2", "calculateUnitPrice runner: ${unitLocals.runner}")
        Log.d("Rony2", "calculateUnitPrice gussetPrint: ${unitLocals.gussetPrint}")
        Log.d("Rony2", "calculateUnitPrice piping: ${unitLocals.piping}")
        Log.d("Rony2", "calculateUnitPrice zipper: ${unitLocals.zipper}")
        Log.d("Rony2", "fabricSqInch: ${unitLocals.fabricSqInch}")
        Log.d("Rony2", "printColorValue: $printColorValue")
        Log.d("Rony2", "nonwovenBagFabricPricePerUnit: $nonwovenBagFabricPricePerUnit")
        Log.d("Rony2", "twoPercent: $twoPercent")
        Log.d("Rony2", "wastage: $wastage")
        Log.d("Rony2", "blockCost: $blockCost")
        Log.d("Rony2", "gussetCost: $gussetCost")
        Log.d("Rony2", "zipperCost: $zipperCost")
        Log.d("Rony2", "deliveryCost: $deliveryCost")
        Log.d("Rony2", "totalCost: $totalCost")
        Log.d("Rony2", "formattedTotalCost: $formattedTotalCost")

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
                Log.d("Rony3", "working with : Select Bag Type ")
                return unitLocals
            }
            "Handle Bag" -> {
                Log.d("Rony3", "working with : Handle Bag ")
                val heming = 1.5
                val handleFabric = 70.0
                unitLocals.makingType = 0.80
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) + (gusset * width) + handleFabric
            }
            "D Cut Bag" -> {
                Log.d("Rony3", "working with : D Cut Bag ")
                val heming = 2.5
                unitLocals.makingType = 0.50
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2.0) + (gusset * width)
            }
            "Sewing Bag" -> {
                Log.d("Rony3", "working with : Sewing Bag ")
                val heming = 1.5
                val handleFabric = 70.0
                val runner = 1.0
                val piping = (height * 4.0) + (width * 2.0) + 10.0
                unitLocals.makingType = 4.0
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
                Log.d("Rony3", "working with : Autobox Handle Bag ")
                val heming = 1.5
                val handleFabric = 70.0
                unitLocals.makingType = 2.0
                unitLocals.heming = heming
                unitLocals.handleFabric = handleFabric
                unitLocals.fabricSqInch = ((height + heming) * width * 2) +
                        ((gusset + 0.75) * ((height + heming) * 2 + width)) +
                        handleFabric
            }
            "Autobox D Cut Bag" -> {
                Log.d("Rony3", "working with : Autobox D Cut Bag ")
                val heming = 2.5
                unitLocals.makingType = 1.7
                unitLocals.heming = heming
                unitLocals.fabricSqInch = ((height + heming) * width * 2.0) +
                        ((gusset + 0.75) * ((height + heming) * 2.0 + width))
            }
            else -> {
                Log.d("Rony3", "working with : else ")
                return unitLocals
            }
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