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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.taman.silmebagcalculator.ui.screens.nonwoven.NonWovenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetNonWoven(viewModel: NonWovenViewModel) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val handleBagHemming = viewModel.handleBagHemming.collectAsState()
    val handleBagHandleFabric = viewModel.handleBagHandleFabric.collectAsState()
    val handleBagMakingType = viewModel.handleBagMakingType.collectAsState()

    val dCutBagHemming = viewModel.dCutBagHemming.collectAsState()
    val dCutBagMakingType = viewModel.dCutBagMakingType.collectAsState()

    val sewingBagHemming = viewModel.sewingBagHemming.collectAsState()
    val sewingBagHandleFabric = viewModel.sewingBagHandleFabric.collectAsState()
    val sewingBagRunner = viewModel.sewingBagRunner.collectAsState()
    val sewingBagPipingExtraAddition = viewModel.sewingBagPipingExtraAddition.collectAsState()
    val sewingBagMakingType = viewModel.sewingBagMakingType.collectAsState()

    val autoboxHandleBagHemming = viewModel.autoboxHandleBagHemming.collectAsState()
    val autoboxHandleBagHandleFabric = viewModel.autoboxHandleBagHandleFabric.collectAsState()
    val autoboxHandleBagMakingType = viewModel.autoboxHandleBagMakingType.collectAsState()

    val autoboxDCutBagHemming = viewModel.autoboxDCutBagHemming.collectAsState()
    val autoboxDCutBagMakingType = viewModel.autoboxDCutBagMakingType.collectAsState()


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
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(top = 10.dp, start = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Handle Bag",
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = "Hemming",
                    text = handleBagHemming.value,
                    onTextChange = {
                        scope.launch {
                            viewModel.updateHandleBagHemming(it)
                        }
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Handle Fabric",
                    text = handleBagHandleFabric.value,
                    onTextChange = {
                        viewModel.updateHandleBagHandleFabric(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Making Type",
                    text = handleBagMakingType.value,
                    onTextChange = {
                        viewModel.updateHandleBagMakingType(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "D Cut Bag",
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = "Hemming",
                    text = dCutBagHemming.value,
                    onTextChange = {
                        viewModel.updateDCutBagHemming(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Making Type",
                    text = dCutBagMakingType.value,
                    onTextChange = {
                        viewModel.updateDCutBagMakingType(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Sewing Bag",
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = "Hemming",
                    text = sewingBagHemming.value,
                    onTextChange = {
                        viewModel.updateSewingBagHemming(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Handle Fabric",
                    text = sewingBagHandleFabric.value,
                    onTextChange = {
                        viewModel.updateSewingBagHandleFabric(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Runner",
                    text = sewingBagRunner.value,
                    onTextChange = {
                        viewModel.updateSewingBagRunner(it)
                    },
                    Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = "Piping Extra Addition",
                    text = sewingBagPipingExtraAddition.value,
                    onTextChange = {
                        viewModel.updateSewingBagPipingExtraAddition(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Making Type",
                    text = sewingBagMakingType.value,
                    onTextChange = {
                        viewModel.updateSewingBagMakingType(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Autobox Handle Bag",
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = "Hemming",
                    text = autoboxHandleBagHemming.value,
                    onTextChange = {
                        viewModel.updateAutoboxHandleBagHemming(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Handle Fabric",
                    text = autoboxHandleBagHandleFabric.value,
                    onTextChange = {
                        viewModel.updateAutoboxHandleBagHandleFabric(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Making Type",
                    text = autoboxHandleBagMakingType.value,
                    onTextChange = {
                        viewModel.updateAutoboxHandleBagMakingType(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Autobox D Cut Bag",
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BagTextField(
                    label = "Hemming",
                    text = autoboxDCutBagHemming.value,
                    onTextChange = {
                        viewModel.updateAutoboxDCutBagHemming(it)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BagTextField(
                    label = "Making Type",
                    text = autoboxDCutBagMakingType.value,
                    onTextChange = {
                        viewModel.updateAutoboxDCutBagMakingType(it)
                    },
                    Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}