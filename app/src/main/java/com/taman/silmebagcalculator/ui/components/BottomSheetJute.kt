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
import com.taman.silmebagcalculator.ui.screens.jute.JuteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetJute(viewModel: JuteViewModel) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val natural15x15JuteWidth by viewModel.natural15x15JuteWidth.collectAsState()
    val color15x15JuteWidth by viewModel.color15x15JuteWidth.collectAsState()
    val natural13x13JuteWidth by viewModel.natural13x13JuteWidth.collectAsState()
    val color13x13JuteWidth by viewModel.color13x13JuteWidth.collectAsState()

    val juteCottonSinglyPlyNaturalWidth by viewModel.juteCottonSinglyPlyNaturalWidth.collectAsState()
    val juteCottonSinglyPlyColorWidth by viewModel.juteCottonSinglyPlyColorWidth.collectAsState()
    val juteCottonDoublyPlyNaturalWidth by viewModel.juteCottonDoublyPlyNaturalWidth.collectAsState()
    val juteCottonDoublyPlyColorWidth by viewModel.juteCottonDoublyPlyColorWidth.collectAsState()

    val natural15x15JuteYard by viewModel.natural15x15JuteYard.collectAsState()
    val color15x15JuteYard by viewModel.color15x15JuteYard.collectAsState()
    val natural13x13JuteYard by viewModel.natural13x13JuteYard.collectAsState()
    val color13x13JuteYard by viewModel.color13x13JuteYard.collectAsState()

    val juteCottonSinglyPlyNaturalYard by viewModel.juteCottonSinglyPlyNaturalYard.collectAsState()
    val juteCottonSinglyPlyColorYard by viewModel.juteCottonSinglyPlyColorYard.collectAsState()
    val juteCottonDoublyPlyNaturalYard by viewModel.juteCottonDoublyPlyNaturalYard.collectAsState()
    val juteCottonDoublyPlyColorYard by viewModel.juteCottonDoublyPlyColorYard.collectAsState()

    val zipperPricePerInch by viewModel.zipperPricePerInch.collectAsState()
    val runnerPrice by viewModel.runnerPrice.collectAsState()
    val sampleCost by viewModel.sampleCost.collectAsState()
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
            Text(
                text = stringResource(R.string.fabric_cuttable_width),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.natural_15_x_15),
                    text = natural15x15JuteWidth,
                    onTextChange = {
                        scope.launch {
                            viewModel.updateNatural15x15JuteWidth(it)
                        }
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.color_15_x_15),
                    text = color15x15JuteWidth,
                    onTextChange = {
                        viewModel.updateColor15x15JuteWidth(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.natural_13_x_13),
                    text = natural13x13JuteWidth,
                    onTextChange = {
                        viewModel.updateNatural13x13JuteWidth(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.color_13_x_13),
                    text = color13x13JuteWidth,
                    onTextChange = {
                        viewModel.updateColor13x13JuteWidth(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.jute_cotton_single_ply_natural),
                    text = juteCottonSinglyPlyNaturalWidth,
                    onTextChange = {
                        viewModel.updateJuteCottonSinglyPlyNaturalWidth(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.jute_cotton_single_ply_color),
                    text = juteCottonSinglyPlyColorWidth,
                    onTextChange = {
                        viewModel.updateJuteCottonSinglyPlyColorWidth(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.jute_cotton_double_ply_natural),
                    text = juteCottonDoublyPlyNaturalWidth,
                    onTextChange = {
                        viewModel.updateJuteCottonDoublyPlyNaturalWidth(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.jute_cotton_double_ply_color),
                    text = juteCottonDoublyPlyColorWidth,
                    onTextChange = {
                        viewModel.updateJuteCottonDoublyPlyColorWidth(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.fabric_price_per_yard),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.natural_15_x_15),
                    text = natural15x15JuteYard,
                    onTextChange = {
                        scope.launch {
                            viewModel.updateNatural15x15JuteYard(it)
                        }
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.color_15_x_15),
                    text = color15x15JuteYard,
                    onTextChange = {
                        viewModel.updateColor15x15JuteYard(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.natural_13_x_13),
                    text = natural13x13JuteYard,
                    onTextChange = {
                        viewModel.updateNatural13x13JuteYard(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.color_13_x_13),
                    text = color13x13JuteYard,
                    onTextChange = {
                        viewModel.updateColor13x13JuteYard(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.jute_cotton_single_ply_natural),
                    text = juteCottonSinglyPlyNaturalYard,
                    onTextChange = {
                        viewModel.updateJuteCottonSinglyPlyNaturalYard(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.jute_cotton_single_ply_color),
                    text = juteCottonSinglyPlyColorYard,
                    onTextChange = {
                        viewModel.updateJuteCottonSinglyPlyColorYard(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.jute_cotton_double_ply_natural),
                    text = juteCottonDoublyPlyNaturalYard,
                    onTextChange = {
                        viewModel.updateJuteCottonDoublyPlyNaturalYard(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.jute_cotton_double_ply_color),
                    text = juteCottonDoublyPlyColorYard,
                    onTextChange = {
                        viewModel.updateJuteCottonDoublyPlyColorYard(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.other),
                modifier = Modifier.fillMaxWidth()
            )
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
                    label = stringResource(R.string.runner_price),
                    text = runnerPrice,
                    onTextChange = {
                        viewModel.updateRunnerPrice(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BagTextField(
                    label = stringResource(R.string.sample_cost),
                    text = sampleCost,
                    onTextChange = {
                        viewModel.updateSampleCost(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = stringResource(R.string.wastage_percentage),
                    text = wastagePercentage,
                    onTextChange = {
                        viewModel.updateWastagePercentage(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}