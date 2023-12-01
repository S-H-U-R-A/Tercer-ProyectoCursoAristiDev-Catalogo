package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp


data class CustomOption(
    val isChecked: Boolean = false,
    val text: String
)

@Composable
fun MyCheckBoxExamplePhilipp() {

    //CREAMOS LA LISTA DE OPCIONES A MOSTRAR SIN EMBARGO NO SOBREVIVE LOS CAMBIOS DE CONFIGURACIÓN
    val checkboxes: SnapshotStateList<CustomOption> = remember {
        mutableStateListOf(
            CustomOption(
                text = "Photos"
            ),
            CustomOption(
                text = "Videos"
            ),
            CustomOption(
                text = "Audio"
            )
        )
    }

    //CREAMOS EL ESTADO INICIAL DEL CHECKBOX PADRE
    var triState by rememberSaveable {
        mutableStateOf(ToggleableState.Indeterminate)
    }

    //CREAMOS EL CHECKBOX PADRE
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        TriStateCheckbox(
            state = triState,
            onClick = { //LAMBDA PARA EL EVENTO ONCLICK DEL CHECKBOX PADRE

                triState =
                    when (triState) { // Acá validamos el estado y como cambiarlo cuando se pulsa el botón
                        ToggleableState.On -> ToggleableState.Off
                        ToggleableState.Off -> ToggleableState.On
                        ToggleableState.Indeterminate -> ToggleableState.On
                    }

                //Recorremos la lista de opciones y si el estado del padre es ON los activamos todos
                checkboxes.indices.forEach { index ->
                    checkboxes[index] = checkboxes[index].copy(
                        isChecked = (triState == ToggleableState.On) //false or true, dependiendo de la condición
                    )
                }
            }
        )
        Text(text = "File Types")
    }


    //CREAMOS LOS CHECKBOX HIJOS
    checkboxes.forEachIndexed { index, customOption ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 16.dp)
                .clickable {
                    checkboxes[index] = customOption.copy(
                        isChecked = !customOption.isChecked
                    )
                }
        ) {
            Checkbox(
                checked = customOption.isChecked,
                onCheckedChange = { isChecked ->
                    checkboxes[index] = customOption.copy(
                        isChecked = isChecked
                    )
                }
            )
            Text(
                text = customOption.text
            )
        }
    }

}

@Composable
fun MyTriStatusCheckboxExample() {

    var status: ToggleableState by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }

    TriStateCheckbox(
        state = status,
        onClick = {
            status = when (status) {
                ToggleableState.On -> {
                    ToggleableState.Off
                }

                ToggleableState.Off -> {
                    ToggleableState.Indeterminate
                }

                ToggleableState.Indeterminate -> {
                    ToggleableState.On
                }
            }
        }
    )

}

@Composable
fun MyCheckBoxMultipleExample(
    opcionesSeleccionadas: List<String>,
    opciones: List<String>,
    onCheckedChange: (Boolean, String) -> Unit
) {
    Column {
        opciones.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = opcionesSeleccionadas.contains(opcion),
                    onCheckedChange = { onCheckedChange(it, opcion) }
                )
                Text(
                    text = opcion
                )
            }

        }
    }
}

@Composable
fun MyCheckBoxWithText() {
    var state: Boolean by rememberSaveable {
        mutableStateOf(true)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = {
                state = !state
            }
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(
            text = "Ejemplo 1"
        )
    }
}

@Composable
fun MyCheckboxExample() {
    var state: Boolean by rememberSaveable {
        mutableStateOf(true)
    }
    Checkbox(
        checked = state,
        onCheckedChange = {
            state = !state
        },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Green
        )
    )
}