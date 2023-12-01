package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MyRadioButtonMultipleExample(
    optionSelected: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = (optionSelected == "Darlyn"),
                onClick = { onOptionSelected("Darlyn") },
            )
            Text(
                text = "Darlyn",
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = ( optionSelected == "Sergio" ),
                onClick = { onOptionSelected("Sergio") },
            )
            Text(
                text = "Sergio",
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = (optionSelected == "Maleja"),
                onClick = { onOptionSelected("Maleja") },
            )
            Text(
                text = "Maleja",
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = (optionSelected == "Bebé Barrigas"),
                onClick = { onOptionSelected("Bebé Barrigas") },
            )
            Text(
                text = "Bebé Barrigas",
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = (optionSelected == "Emma & Matias"),
                onClick = { onOptionSelected("Emma & Matias") },
            )
            Text(
                text = "Emma & Matias",
                color = Color.Black
            )
        }
    }
}

@Composable
fun MyRadioButtonExampleBasic() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = false,
            onClick = { },
            enabled = false,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Green
            )
        )
        Text(text = "Opción 1")
    }
}