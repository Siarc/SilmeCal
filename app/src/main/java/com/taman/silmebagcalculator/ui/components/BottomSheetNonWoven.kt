package com.taman.silmebagcalculator.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.screens.nonwoven.NonWovenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetNonWoven(viewModel: NonWovenViewModel) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            viewModel.updateBottomSheetState(!viewModel.isBottomSheetOpen.value)
        }
    ) {
        // Sheet content with full page image
        Image(
            painter = painterResource(id = R.drawable.cotton_bag),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }

}