package com.taman.silmebagcalculator.ui.screens.nonwoven

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.components.BackgroundDroplet
import com.taman.silmebagcalculator.ui.components.BagTextField
import com.taman.silmebagcalculator.ui.components.BottomSheetNonWoven
import com.taman.silmebagcalculator.ui.components.OptionsCard
import com.taman.silmebagcalculator.ui.components.DropDownMenuComponent
import com.taman.silmebagcalculator.ui.components.FancyCardView
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NonWovenScreen(
    viewModel: NonWovenViewModel = koinViewModel(), navController: NavHostController,
) {
    // Observing the state variables from the ViewModel
    val fabricPrice by viewModel.fabricPrice
    val height by viewModel.height
    val width by viewModel.width
    val gsm by viewModel.gsm
    val gusset by viewModel.gusset
    val quantity by viewModel.quantity
    val additionalCost by viewModel.additionalCost
    val profit by viewModel.profit

    val selectedBagType = viewModel.selectedBagType
    val selectedPrintColor = viewModel.selectedPrintColor

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.nonwoven)) },
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
                            viewModel.updateBottomSheetState(!viewModel.isBottomSheetOpen.value)
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
                if (viewModel.isBottomSheetOpen.value) {
                    BottomSheetNonWoven(viewModel)
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
                            list = viewModel.nonWovenBagTypeList,
                            selectedOption = selectedBagType.value,
                            onOptionSelected = { selectedOption ->
                                viewModel.updateSelectedBagType(selectedOption)
                                viewModel.updateSewingBagOptionsVisibility(selectedOption == "Sewing Bag")
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        DropDownMenuComponent(
                            label = stringResource(R.string.print_color),
                            list = viewModel.printColors,
                            selectedOption = selectedPrintColor.value,
                            onOptionSelected = { selectedOption ->
                                viewModel.updateSelectedPrintColor(selectedOption)
                            },
                            Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {

                        BagTextField(
                            label = stringResource(R.string.fabric_price),
                            text = fabricPrice,
                            onTextChange = {
                                viewModel.updateFabricPrice(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.additional_cost),
                            text = additionalCost,
                            onTextChange = {
                                viewModel.updateAdditionalCost(it)
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
                            label = stringResource(R.string.gsm),
                            text = gsm,
                            onTextChange = {
                                viewModel.updateGsm(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.gusset),
                            text = gusset,
                            onTextChange = {
                                viewModel.updateGusset(it)
                            },
                            Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.quantity),
                            text = quantity,
                            onTextChange = {
                                viewModel.updateQuantity(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.profit),
                            text = profit,
                            onTextChange = {
                                viewModel.updateProfit(it)
                            },
                            Modifier.weight(1f)
                        )

                    }

                    OptionsCard(viewModel)

                    FancyCardView(viewModel)

                }
            }
        }
    )


}


