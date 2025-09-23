package com.taman.silmebagcalculator.ui.screens.cotton

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
import com.taman.silmebagcalculator.datastore.CottonDataStore
import com.taman.silmebagcalculator.ui.components.BackgroundDroplet
import com.taman.silmebagcalculator.ui.components.BagTextField
import com.taman.silmebagcalculator.ui.components.DropDownMenuComponent
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun CottonScreenPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()

    // Provide a fake or default ViewModel instance for preview
    val previewViewModel = remember {
        CottonViewModel(CottonDataStore(context))
    }

    CottonScreen(
        viewModel = previewViewModel,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CottonScreen(
    viewModel: CottonViewModel = koinViewModel(), navController: NavHostController,
) {
    // Observing the state variables from the ViewModel
    val isBottomSheetOpen by viewModel.isBottomSheetOpen

    val fabricCuttableWidth by viewModel.fabricCuttableWidth
    val fabricPrice by viewModel.fabricPrice
    val printCost by viewModel.printCost
    val height by viewModel.height
    val width by viewModel.width
    val gusset by viewModel.gusset
    val handleLength by viewModel.handleLength
    val making by viewModel.making
    val accessories by viewModel.accessories
    val quantity by viewModel.quantity
    val cnt by viewModel.cnt
    val buyingCommission by viewModel.buyingCommission
    val zipperFabricWidth by viewModel.zipperFabricWidth

    val selectedBagType = viewModel.selectedBagType

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.cotton)) },
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
                    //BottomSheetCotton(viewModel)
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
                        BagTextField(
                            label = stringResource(R.string.fabric_cuttable_width),
                            text = fabricCuttableWidth,
                            onTextChange = {
                                viewModel.updateFabricCuttableWidth(it)
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
                            label = stringResource(R.string.print_cost),
                            text = printCost,
                            onTextChange = {
                                viewModel.updatePrintCost(it)
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
                            label = stringResource(R.string.gusset),
                            text = gusset,
                            onTextChange = {
                                viewModel.updateGusset(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.handle_length),
                            text = handleLength,
                            onTextChange = {
                                viewModel.updateHandleLength(it)
                            },
                            Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.making),
                            text = making,
                            onTextChange = {
                                viewModel.updateMaking(it)
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
                            label = stringResource(R.string.quantity),
                            text = quantity,
                            onTextChange = {
                                viewModel.updateQuantity(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.commercial_transport),
                            text = cnt,
                            onTextChange = {
                                viewModel.updateCnt(it)
                            },
                            Modifier.weight(1f)
                        )

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        BagTextField(
                            label = stringResource(R.string.buying_commission),
                            text = buyingCommission,
                            onTextChange = {
                                viewModel.updateBuyingCommission(it)
                            },
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        BagTextField(
                            label = stringResource(R.string.zipper_fabric_width),
                            text = zipperFabricWidth,
                            onTextChange = {
                                viewModel.updateZipperFabricWidth(it)
                            },
                            Modifier.weight(1f)
                        )

                    }


                    //FancyCardView(viewModel)

                }
            }
        }
    )


}