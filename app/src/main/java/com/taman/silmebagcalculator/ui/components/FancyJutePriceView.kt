package com.taman.silmebagcalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.screens.jute.JuteViewModel

@Preview(showBackground = true)
@Composable
fun PreviewFancyJutePriceView() {
    val viewModel: JuteViewModel = viewModel()
    FancyJutePriceView(viewModel)
}

@Composable
fun FancyJutePriceView(viewModel: JuteViewModel) {

    val unitPrice by viewModel.unitPrice

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ), colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ), shape = RoundedCornerShape(12.dp) // Rounded corners
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Padding inside the card
            horizontalArrangement = Arrangement.SpaceBetween, // Distribute space between label and price
            verticalAlignment = Alignment.CenterVertically
        ) {
            // "Unit Price" Label
            Text(
                text = stringResource(R.string.unit_price),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold, color = Color.Black
                )
            )

            // Price Text
            Text(
                text = unitPrice,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold, color = Color.Black
                )
            )
        }
    }
}

