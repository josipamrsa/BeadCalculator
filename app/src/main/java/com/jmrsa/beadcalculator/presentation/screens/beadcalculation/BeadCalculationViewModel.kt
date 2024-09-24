package com.jmrsa.beadcalculator.presentation.screens.beadcalculation

import androidx.lifecycle.ViewModel
import com.jmrsa.beadcalculator.presentation.utils.calculateAmount
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class BeadCalculationViewModel @Inject constructor() : ViewModel() {
    private val mutableState = MutableStateFlow(BeadCalculationState())
    val state: StateFlow<BeadCalculationState> = mutableState.asStateFlow()

    fun onEvent(event: BeadCalculationEvent) {
        when (event) {
            is BeadCalculationEvent.OnCalculateBeadAmount -> handleBeadCalculation()
            is BeadCalculationEvent.OnBeadSizeInputChanged -> handleBeadSizeChanged(event.beadSize)
            is BeadCalculationEvent.OnHeightInputChanged -> handleHeightChanged(event.mainHeight)
            is BeadCalculationEvent.OnSideWidthInputChanged -> handleSideWidthChanged(event.sideWidth)
            is BeadCalculationEvent.OnWidthInputChanged -> handleWidthChanged(event.mainWidth)
        }
    }

    private fun handleSideWidthChanged(sideWidth: String) {
        mutableState.update {
            it.copy(sidePanelWidth = sideWidth)
        }
    }

    private fun handleWidthChanged(mainWidth: String) {
        mutableState.update {
            it.copy(mainPanelWidth = mainWidth)
        }
    }

    private fun handleHeightChanged(mainHeight: String) {
        mutableState.update {
            it.copy(mainPanelHeight = mainHeight)
        }
    }

    private fun handleBeadSizeChanged(beadSize: String) {
        mutableState.update {
            it.copy(beadSize = beadSize)
        }
    }

    private fun calculateMainPanelAmount(
        panelWidth: Double,
        panelHeight: Double,
        beadWidth: Double,
        beadHeight: Double
    ): Double {
        val widthAmount = calculateAmount(panelWidth, beadWidth, beadHeight)
        val heightAmount = calculateAmount(panelHeight, beadHeight, beadWidth)

        return widthAmount * heightAmount
    }

    private fun calculateSidePanelAmount(
        sideWidth: Double,
        panelHeight: Double,
        beadWidth: Double,
        beadHeight: Double
    ) : Double {
        val halfPanelHeight = (panelHeight - sideWidth) / 2

        val widthAmount = calculateAmount(sideWidth, beadWidth, beadHeight)
        val heightAmount = calculateAmount(halfPanelHeight, beadHeight, beadWidth)

        return widthAmount * heightAmount
    }

    private fun handleBeadCalculation() {
        mutableState.update {
            val mainPanelAmount = calculateMainPanelAmount(
                it.formattedMainWidth,
                it.formattedMainHeight,
                it.formattedBeadWidth,
                it.formattedBeadHeight
            )

            val sidePanelAmount = calculateSidePanelAmount(
                it.formattedSideWidth,
                it.formattedMainHeight,
                it.formattedBeadWidth,
                it.formattedBeadHeight
            )

            it.copy(
                beadAmount = mainPanelAmount.toInt() + sidePanelAmount.toInt()
            )
        }
    }
}

