package com.example.jetpackcomposecatalogoelementosui.composition_local

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ElevationLocal(
    val default: Dp = 0.dp,
    val card: Dp = 32.dp,
    val button: Dp = 8.dp
)

val LocalCustomElevation: ProvidableCompositionLocal<ElevationLocal> = compositionLocalOf { ElevationLocal() }