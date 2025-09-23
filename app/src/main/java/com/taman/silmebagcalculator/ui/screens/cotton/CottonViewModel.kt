package com.taman.silmebagcalculator.ui.screens.cotton

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.taman.silmebagcalculator.datastore.CottonDataStore

class CottonViewModel(private val cottonDataStore: CottonDataStore) : ViewModel() {

    // Flows to observe data from DataStore

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

    // Update bag type
    fun updateSelectedBagType(value: String) {
        selectedBagType.value = value
        calculateAllPrice()
    }



    private fun calculateAllPrice() {

    }

}