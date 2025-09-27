package com.taman.silmebagcalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.screens.jute.JuteViewModel
import com.taman.silmebagcalculator.ui.theme.SilmeBagCalculatorTheme

@Preview(showBackground = true)
@Composable
fun LaminationOptionsCardPreview() {
    SilmeBagCalculatorTheme {
        val viewModel: JuteViewModel = viewModel()
        LaminationOptionsCard(viewModel)
    }
}


@Composable
fun LaminationOptionsCard(viewModel: JuteViewModel) {

    val isLaminationSelected by viewModel.isLaminationSelected


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

            Text(
                text = stringResource(R.string.lamination),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                OptionsButton(
                    option = stringResource(R.string.no),
                    isSelected = !isLaminationSelected,
                    onOptionSelected = {
                        viewModel.updateLamination(false)
                    },
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OptionsButton(
                    option = stringResource(R.string.yes),
                    isSelected = isLaminationSelected,
                    onOptionSelected = {
                        viewModel.updateLamination(true)
                    },
                    Modifier.weight(1f)
                )
            }


        }
    }
}