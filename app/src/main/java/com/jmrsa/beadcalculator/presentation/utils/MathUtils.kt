package com.jmrsa.beadcalculator.presentation.utils

fun calculateAmount(x: Double, b1: Double, b2: Double) : Double {
    return 1 + ((x - b1 + b2) / b1)
}