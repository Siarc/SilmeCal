package com.taman.silmebagcalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.taman.silmebagcalculator.ui.theme.LightGreen

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