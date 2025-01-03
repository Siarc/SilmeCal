package com.taman.silmebagcalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.theme.LightGreen
import com.taman.silmebagcalculator.ui.theme.SilmeBagCalculatorTheme
import com.taman.silmebagcalculator.ui.screens.nonwoven.NonWovenViewModel

@Preview(showBackground = true)
@Composable
fun DeliveryOptionCardPreview() {
    SilmeBagCalculatorTheme {
        val viewModel: NonWovenViewModel = viewModel()
        OptionsCard(viewModel)
    }
}


@Composable
fun OptionsCard(viewModel: NonWovenViewModel) {

    val deliveryFee by viewModel.deliveryFee

    val showSewingBagOptions = viewModel.showSewingBagOptions.value
    val showDeliveryOptions = viewModel.showDeliveryOptions.value
    val isGussetSelected = viewModel.isGussetSelected.value
    val isZipperSelected = viewModel.isZipperSelected.value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ), colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            if(showSewingBagOptions){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ){
                    OptionsButton(
                        option = stringResource(R.string.gusset_print),
                        isSelected = isGussetSelected,
                        onOptionSelected = {
                            viewModel.updateGussetSelected()
                        },
                        Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Pressable Button for "Home Delivery"
                    OptionsButton(
                        option = stringResource(R.string.zipper),
                        isSelected = isZipperSelected,
                        onOptionSelected = {
                            viewModel.updateZipperSelected()
                        },
                        Modifier.weight(1f)
                    )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                OptionsButton(
                    option = stringResource(R.string.no_delivery),
                    isSelected = !showDeliveryOptions,
                    onOptionSelected = {
                        viewModel.updateDeliveryOptionsVisibility(false)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Pressable Button for "Home Delivery"
                OptionsButton(
                    option = stringResource(R.string.home_delivery),
                    isSelected = showDeliveryOptions,
                    onOptionSelected = {
                        viewModel.updateDeliveryOptionsVisibility(true)
                    },
                    Modifier.weight(1f)
                )
            }


            // Conditional TextView for Home Delivery Fee
            if (showDeliveryOptions) {
                Text(
                    text = stringResource(R.string.please_enter_the_delivery_fee),
                    modifier = Modifier.padding(top = 16.dp)
                )
                BagTextField(
                    label = stringResource(R.string.delivery_fee),
                    text = deliveryFee,
                    onTextChange = {
                        viewModel.updateDeliveryFee(it)
                    }
                )
            }
        }
    }
}

@Composable
fun OptionsButton(
    option: String,
    isSelected: Boolean,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onOptionSelected(option) }
            .background(
                color = if (isSelected) LightGreen else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 0.5.dp, // Set border width
                color = Color.LightGray, // Set border color
                shape = RoundedCornerShape(8.dp) // Match shape with background
            )
            .padding(16.dp)) {
        Text(
            text = option,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
}