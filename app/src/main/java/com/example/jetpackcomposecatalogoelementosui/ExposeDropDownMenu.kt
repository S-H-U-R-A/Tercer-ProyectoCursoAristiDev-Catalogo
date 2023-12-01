package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//ESTE EJEMPLO ES DE UN COMBOBOX-SELECT-SPINNER_DROPDOWN QUE PERMITE ESCRIBIR
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownMenuEditableExample(
    bankOptions: List<String>,
    optionSelected: String,
    onOptionChange: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        OutlinedTextField(
            value = optionSelected,
            onValueChange = { optionSelected: String ->
                onOptionChange(optionSelected)
                expanded = true
            },
            label = {
                Text("Seleccione su banco")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults
                    .TrailingIcon(
                        expanded = expanded
                    )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )

        // Se filtra la lista con base en lo que se escribe en el TextField
        val filteringOptions: List<String> = getListFilteredWithText(
            bankOptionsWithOutFilter = bankOptions,
            optionSelected = optionSelected
        )

        if ( filteringOptions.isNotEmpty() ) { //Si la lista de coincidencias no esta vacia se muestra el dropdown
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onOptionChange(selectionOption)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}


/**
 * Get list filtered with text
 *
 * @param bankOptionsWithOutFilter lista de opciones sin filtrar
 * @param optionSelected texto escrito en el TextField
 * @return lista de opciones filtradas
 */
fun getListFilteredWithText(
    bankOptionsWithOutFilter: List<String>,
    optionSelected: String
) : List<String>{
    return bankOptionsWithOutFilter.filter { item: String ->
        item.contains(optionSelected, ignoreCase = true)
    }
}


//ESTE EJEMPLO ES DE UN COMBOBOX-SELECT-SPINNER_DROPDOWN CUALQUIERA
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownMenuExample(
    options: List<String>,
    optionSelected: String,
    onOptionChange: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { stateExpanded: Boolean -> //La caja retorna el estado del DropdownMenu (Abierto o cerrado)
            expanded = stateExpanded
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(), //Indica que el DropwDownMenu debe engancharse a este TextField
            readOnly = true,
            value = optionSelected, //Opción selecionada
            onValueChange = {}, //Con esta propiedad podriamos hacer un autocomplete text view
            label = {
                Text("Tipo de teléfono") //Texto que aparece cuando no hay nada escrito
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults
                    .TrailingIcon(
                        expanded = expanded //El icono al final de la caja se actualiza solo dependiendo del estado
                    )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {

            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onOptionChange(selectionOption)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }

        }
    }
}