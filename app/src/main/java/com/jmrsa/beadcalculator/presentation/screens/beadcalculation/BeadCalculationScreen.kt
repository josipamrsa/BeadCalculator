package com.jmrsa.beadcalculator.presentation.screens.beadcalculation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BeadCalculationScreen() {
    val viewModel = hiltViewModel<BeadCalculationViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Calculate amount of beads needed to build a beaded bag. Please provide your " +
                    "desired measurements in millimeters, and the size of the bead in millimeters.",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 20.dp,)
        )

        OutlinedTextField(
            value = state.value.mainPanelWidth,
            onValueChange = { onEvent(BeadCalculationEvent.OnWidthInputChanged(it)) },
            label = {
                Text(text = "Main panel width (in mm)")
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = state.value.mainPanelHeight,
            onValueChange = { onEvent(BeadCalculationEvent.OnHeightInputChanged(it)) },
            label = {
                Text(text = "Main panel height (in mm)")
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = state.value.sidePanelWidth,
            onValueChange = { onEvent(BeadCalculationEvent.OnSideWidthInputChanged(it)) },
            label = {
                Text(text = "Side panel width (in mm)")
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = state.value.beadSize,
            onValueChange = { onEvent(BeadCalculationEvent.OnBeadSizeInputChanged(it)) },
            label = {
                Text(text = "Bead size (in mm)")
            },
            supportingText = {
                Text(text = "Please input in w x h format, w being width in mm and h being height in mm")
            },
            modifier = Modifier
                .padding(
                    vertical = 20.dp,
                    horizontal = 10.dp
                )
                .fillMaxWidth()
        )

        Button(
            onClick = { onEvent(BeadCalculationEvent.OnCalculateBeadAmount) },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            Text("Calculate bead amount")
        }

        // TODO - other stitches dropdown
        Text(
            text = "Type of stitch: Right angle weave (RAW)",
            fontSize = 11.sp,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(top = 20.dp)
        )

        OutlinedTextField(
            value = "Approx. ${state.value.beadAmount} beads",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                    horizontal = 10.dp
                )
                .fillMaxWidth()
        )
    }
}

@Composable
@Preview(name = "BeadCalculation")
private fun BeadCalculationScreenPreview() {
    BeadCalculationScreen()
}
