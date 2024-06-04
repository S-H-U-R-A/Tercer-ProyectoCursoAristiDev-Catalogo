package com.example.jetpackcomposecatalogoelementosui.ui.formularios.opciones_de_seleccion

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun MyRadioButtonExampleBasic(
    interactionSource: InteractionSource = remember { MutableInteractionSource() }
) {
    var stateRadio: Boolean by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = stateRadio,
            onClick = {
                stateRadio = !stateRadio
            },
            enabled = false,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Green,
                disabledUnselectedColor = Color.Green
            )
        )
        Text(text = "Opción 1")
    }
}


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

        //Acá se podría crear las opciones de una mejor forma usando una lista y objetos
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = (optionSelected == "Darlyn"),
                onClick = {
                    onOptionSelected("Darlyn")
                },
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
                selected = (optionSelected == "Sergio"),
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


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyRadioButtonMultipleExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            var optionSelected by rememberSaveable { mutableStateOf("Darlyn") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MyRadioButtonMultipleExample(
                    optionSelected = optionSelected,
                    onOptionSelected = { option:String ->
                        optionSelected = option
                    }
                )
            }
        }
    }
}