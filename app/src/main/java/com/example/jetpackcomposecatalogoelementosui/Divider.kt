package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyDividerExample() {

    Divider( //Divisor horizontal
        thickness = 4.dp,
        color = Color.Red,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    )

    Row {
        Text(
            text = "Ejemplo 1"
        )
        Divider( //Divisor vertical
            thickness = 4.dp,
            color = Color.Red,
            modifier = Modifier
                .fillMaxHeight() //El divisor se extiende a lo alto del contenedor
                .padding(start = 32.dp)
        )
        Text(
            text = "Ejemplo 2"
        )

    }
}