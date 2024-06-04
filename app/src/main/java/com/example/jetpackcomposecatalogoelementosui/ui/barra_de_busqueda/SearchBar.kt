package com.example.jetpackcomposecatalogoelementosui.ui.barra_de_busqueda

import android.content.res.Configuration
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBarExampleBasic(
    modifier: Modifier = Modifier
){
    var text by rememberSaveable { mutableStateOf(value = "") }//ESTADO PARA EL TEXTO QUE SE MUESTRA Y QUE EL USUARIO ESCRIBE

    var active by rememberSaveable { mutableStateOf(value = false) }//ESTADO PARA SABER SI EL CUADRO DE BUSQUEDA ESTA ACTIVO ES DECIR SI ESTA ABIERTO O CERRADO

    val listaOriginalDeSugerencias: List<String> = listOf("Darlyn", "Sergio", "Alcira", "Dalgi", "Ivan", "Maleja", "Pelados")
    var listaFiltrada by rememberSaveable { mutableStateOf( listaOriginalDeSugerencias ) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics {
                this.isTraversalGroup = true
            },
        contentAlignment = Alignment.TopCenter
    ) {
        SearchBar(
            query = text,//TEXTO A MOSTRAR EN EL CUADRO
            onQueryChange = { textoIngresado: String ->//TRIGGER DEL TEXTO INGRESADO POR EL USUARIO
                text = textoIngresado
                listaFiltrada = listaOriginalDeSugerencias.filter { element: String -> element.contains(textoIngresado, ignoreCase = true) }
            },
            onSearch = { textoIngresado: String ->//EVENTO ENVIADO DESDE EL BOTÓN DE BUSCAR DEL TECLADO,
                //ESTA LAMDA ES MUY PARECIDA A ONQUERYCHANGE PORQUE PODEMOS OBTENER LO QUE ESCRIBIÓ EL USUARIO AL MOMENTO DE PULSAR EL BOTÓN DEL TECLADO
                active = false//AL INDICAR FALSE SE OCULTA LA BARRA DE ESTADO
            },
            active = active,//ESTO INDICA SI SE DEBE EXPANDIR O NO EL CUADRO DE BUSQUEDA
            onActiveChange = { activeChange: Boolean ->//ESTE EVENTO SE DISPARA CADA VEZ QUE SE CONTRAE O EXPANDE LA BARRA DE BUSQUEDA
                active = activeChange
            },
            placeholder = { Text(text = "TEXTO PLACEHOLDER") },//TEXTO POR SUGERIDO PARA LA BARRA  DE BUSQUEDA
            leadingIcon = { Icon(imageVector =  Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) },
            shape = CutCornerShape(size = 16.dp),
            colors = SearchBarDefaults.colors(
                containerColor = Color.Gray,
                dividerColor = Color.Red,
                inputFieldColors = TextFieldDefaults.colors(
                    cursorColor = Color.Red,
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Magenta
                )
            )
        ) { //LO QUE VA DENTRO DE ESTE COMPOSABLE ES EL CONTENIDO DE SUGERENCIAS PARA LA BUSQUEDA
            listaFiltrada.forEach{ elemento: String ->
                ListItem(
                    headlineContent = { Text(text = elemento) },
                    supportingContent = { Text("Additional info") },
                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                    modifier = Modifier
                        .clickable {
                            text = elemento //Cuando selecionan algún elemento de la lista, se indica que el nuevo texto es el seleccionado
                            active = false // Cómo ya pulso y selecciono se entiende que se debe ocultar el SearchBar
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MySimpleSearchBarExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MySearchBarExampleBasic()
        }
    }
}