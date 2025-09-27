package com.taman.silmebagcalculator.ui.screens.jute

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.datastore.JuteDataStore
import com.taman.silmebagcalculator.ui.components.BackgroundDroplet
import com.taman.silmebagcalculator.ui.components.BagTextField
import com.taman.silmebagcalculator.ui.components.BottomSheetJute
import com.taman.silmebagcalculator.ui.components.DropDownMenuComponent
import com.taman.silmebagcalculator.ui.components.FancyJutePriceView
import com.taman.silmebagcalculator.ui.components.LaminationOptionsCard
import org.koin.compose.viewmodel.koinViewModel


@Preview
@Composable
fun JuteScreenPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()

    // Provide a fake or default ViewModel instance for preview
    val previewViewModel = remember {
        JuteViewModel(JuteDataStore(context))
    }

    JuteScreen(
        viewModel = previewViewModel,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuteScreen(viewModel: JuteViewModel = koinViewModel(), navController: NavHostController) {

    // Observing the state variables from the ViewModel
    val isBottomSheetOpen by viewModel.isBottomSheetOpen

    val height by viewModel.height
    val width by viewModel.width
    val gussetWidth by viewModel.gussetWidth
    val handleCost by viewModel.handleCost
    val printCost by viewModel.printCost
    val accessories by viewModel.accessories
    val profit by viewModel.profit
    val quantity by viewModel.quantity
    val makingCost by viewModel.makingCost
    val deliveryCost by viewModel.deliveryCost

    val selectedBagType = viewModel.selectedBagType
    val selectedFabricType = viewModel.selectedFabricType

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.jute)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.updateBottomSheetState(!isBottomSheetOpen)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier.padding(padding)
            ) {
                if (isBottomSheetOpen) {
                    BottomSheetJute(viewModel)
                }
                BackgroundDroplet()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .imePadding()
                        .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        DropDownMenuComponent(
                            label = stringResource(R.string.bag_type),
                            list = viewModel.cottonBagTypeList,
                            selectedOption = selectedBagType.value,
                            onOptionSelected = { selectedOption ->
                                viewModel.updateSelectedBagType(selectedOption)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        DropDownMenuComponent(
                            label = stringResource(R.string.fabric_type),
                            list = viewModel.fabricTypeList,
                            selectedOption = selectedFabricType.value,
                            onOptionSelected = { selectedOption ->
                                viewModel.updateFabricType(selectedOption)
                            },
                            Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {

                        BagTextField(
                            label = stringResource(R.string.height),
                            text = height,
                            onTextChange = {
                                viewModel.updateHeight(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.width),
                            text = width,
                            onTextChange = {
                                viewModel.updateWidth(it)
                            },
                            Modifier.weight(1f)
                        )
                    }



                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.gusset_width),
                            text = gussetWidth,
                            onTextChange = {
                                viewModel.updateGussetWidth(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.handle_cost),
                            text = handleCost,
                            onTextChange = {
                                viewModel.updateHandleCost(it)
                            },
                            Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.print_cost),
                            text = printCost,
                            onTextChange = {
                                viewModel.updatePrintCost(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.accessories),
                            text = accessories,
                            onTextChange = {
                                viewModel.updateAccessories(it)
                            },
                            Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.profit),
                            text = profit,
                            onTextChange = {
                                viewModel.updateProfit(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.quantity),
                            text = quantity,
                            onTextChange = {
                                viewModel.updateQuantity(it)
                            },
                            Modifier.weight(1f)
                        )

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.making_cost),
                            text = makingCost,
                            onTextChange = {
                                viewModel.updateMakingCost(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.delivery_fee),
                            text = deliveryCost,
                            onTextChange = {
                                viewModel.updateDeliveryCost(it)
                            },
                            Modifier.weight(1f)
                        )

                    }
                    LaminationOptionsCard(viewModel)

                    FancyJutePriceView(viewModel)

                }
            }
        }
    )


}