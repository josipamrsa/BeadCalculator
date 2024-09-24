package com.jmrsa.beadcalculator.presentation.screens.beadcalculation

data class BeadCalculationState(
    val mainPanelWidth: String = "",
    val mainPanelHeight: String = "",
    val sidePanelWidth: String = "",
    val beadSize: String = "",
    val beadAmount: Int? = 0
) {
    val formattedMainWidth: Double
        get() = mainPanelWidth.toDouble()

    val formattedMainHeight: Double
        get() = mainPanelHeight.toDouble()

    val formattedSideWidth: Double
        get() = sidePanelWidth.toDouble()

    val formattedBeadWidth: Double
        get() = beadSize.split("x").first().toDouble()

    val formattedBeadHeight: Double
        get() = beadSize.split("x").last().toDouble()
}

sealed class BeadCalculationEvent {
    data class OnWidthInputChanged(val mainWidth: String) : BeadCalculationEvent()
    data class OnHeightInputChanged(val mainHeight: String) : BeadCalculationEvent()
    data class OnSideWidthInputChanged(val sideWidth: String) : BeadCalculationEvent()
    data class OnBeadSizeInputChanged(val beadSize: String) : BeadCalculationEvent()
    data object OnCalculateBeadAmount : BeadCalculationEvent()
}



