package com.taman.silmebagcalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.screens.cotton.CottonViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCotton(viewModel: CottonViewModel) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val handleWidth by viewModel.handleWidth.collectAsState()
    val cartonCost by viewModel.cartonCost.collectAsState()
    val runnerPrice by viewModel.runnerPrice.collectAsState()
    val zipperPricePerInch by viewModel.zipperPricePerInch.collectAsState()
    val zipperCmCharge by viewModel.zipperCmCharge.collectAsState()
    val wastagePercentage by viewModel.wastagePercentage.collectAsState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            viewModel.updateBottomSheetState(!viewModel.isBottomSheetOpen.value)
        }
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp)
                .height(40.dp),
            onClick = onClick@{
                scope.launch {
                    viewModel.saveDataToDataStore()
                    viewModel.updateBottomSheetState(false)
                }

            },
            shape = RoundedCornerShape(size = 4.dp)
        ) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
        // Sheet content with full page image
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(top = 10.dp, start = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = stringResource(R.string.handle_width),
                    text = handleWidth,
                    onTextChange = {
                        scope.launch {
                            viewModel.updateHandleWidth(it)
                        }
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.carton_cost),
                    text = cartonCost,
                    onTextChange = {
                        viewModel.updateCartonCost(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = stringResource(R.string.zipper_price_per_inch),
                    text = zipperPricePerInch,
                    onTextChange = {
                        viewModel.updateZipperPricePerInch(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.zipper_cm_charge),
                    text = zipperCmCharge,
                    onTextChange = {
                        viewModel.updateZipperCmCharge(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = stringResource(R.string.wastage_percentage),
                    text = wastagePercentage,
                    onTextChange = {
                        viewModel.updateWastagePercentage(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.runner_price),
                    text = runnerPrice,
                    onTextChange = {
                        viewModel.updateRunnerPrice(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}