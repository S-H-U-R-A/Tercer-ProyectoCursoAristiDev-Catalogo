package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBarExampleBasic(
    modifier: Modifier = Modifier
){

    var text by rememberSaveable { mutableStateOf(value = "") } // Texto escrito por el usuario

    var active by rememberSaveable { mutableStateOf(false) } //Saber si el cuadro de busqueda esta activo

    val listaOriginal: List<String> =
        listOf("Darlyn", "Sergio", "Alcira", "Dalgi", "Ivan", "Maleja", "Pelados")

    var listaFiltrada by remember { mutableStateOf( listaOriginal ) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics {
                this.isTraversalGroup = true
            },
        contentAlignment = Alignment.TopCenter
    ) {

        SearchBar(
            query = text, //Texto a mostrar en el componente
            onQueryChange = { textoIngresado: String -> //Escucha y entrega el texto ingresado por el usuario
                text = textoIngresado
                listaFiltrada = listaOriginal.filter {
                    it.contains(textoIngresado, ignoreCase = true)
                }
            },
            onSearch = {// Evento enviado desde el botón de buscar del teclado, el cual indica que se debe ocultar el Search
                //Podriamos validar algo sobre la cadena de texto a buscar
                active = false
            },
            active = active,
            onActiveChange = { active = it },//Evento que se dispara cuando cambia el estado del Search
            placeholder = { Text(text = "Texto de busqueda sugerido")},
            leadingIcon = { Icon(imageVector =  Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
            shape = CutCornerShape( size = 16.dp),
            colors = SearchBarDefaults.colors(
                containerColor = Color.Cyan,
                dividerColor = Color.Red,
                inputFieldColors = TextFieldDefaults.colors(

                )
            )
        ) { //Esto sería como la vista para las sugerencias
            listaFiltrada.forEach{
                ListItem(
                    headlineContent = { Text(it) },
                    supportingContent = { Text("Additional info") },
                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                    modifier = Modifier
                        .clickable {
                            text =
                                it //Cuando selecionan algún elemento de la lista, se indica que el nuevo texto es el seleccionado
                            active =
                                false // Cómo ya pulso y selecciono se entiende que se debe ocultar el SearchBar
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}