package com.example.jetpackcomposecatalogoelementosui.ui.formularios.opciones_de_seleccion

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MySwitchExample() {

    var state: Boolean by rememberSaveable { mutableStateOf(true) }

    Switch(
        checked = state,
        onCheckedChange = {
            state = !state
        },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
        )
    )
}