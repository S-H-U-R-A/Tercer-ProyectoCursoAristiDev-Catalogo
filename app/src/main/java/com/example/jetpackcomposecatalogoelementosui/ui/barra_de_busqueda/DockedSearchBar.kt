package com.example.jetpackcomposecatalogoelementosui.ui.barra_de_busqueda

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDockedSearchBarExampleBasic(
    modifier: Modifier = Modifier
){
    //ESTADO PARA EL TEXTO QUE SE MUESTRA, QUE EL USUARIO ESCRIBE
    var text:String by rememberSaveable { mutableStateOf(value = "") }

    //ESTADO PARA SABER SI EL CUADRO DE BUSQUEDA ESTA ACTIVO ES DECIR SI ESTA EXPANDIDO O CERRADO
    var active by rememberSaveable { mutableStateOf(value = false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DockedSearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp),
            active = active, //ACÁ SE CONFIGURA SI SE DEBE EXPANDER O CONTRAEREL CONTENT DE LA BARRA DE BUSQUEDA
            query = text, //ESTE ES EL TEXTO QUE SE MUESTRA EN EL INPUT
            onQueryChange = { caracterIngresado: String ->
                text = caracterIngresado //ACÁ SE PODRÍAN HACER MÁS COSAS ANTES DE ASIGNAR EL VALOR
            },
            onSearch = { textoABuscar: String ->//EVENTO ENVIADO DESDE EL BOTÓN DE BUSCAR DEL TECLADO,
                active = false //CONTRAEMOS EL CONTENT DE LA BARRA DE BUSQUEDA, ADEMÄS PODRIAMOS HACER MÁS COSAS
            },
            onActiveChange = { searchBarActive: Boolean ->//ESTE EVENTO SE DISPARA CADA VEZ QUE SE INDICA QUE SE DEBE CONTRAER O EXPANDIR LA BARRA DE BUSQUEDA
                active = searchBarActive
            },
            placeholder = { Text("Texto PlaceHolder") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
        ) {
            //CONTENT DE SUGERENCIAS DE LA BARRA DE BUSQUEDA
            repeat(4) { idx ->
                val resultText = "Suggestion $idx"
                ListItem(
                    headlineContent = { Text(resultText) },
                    supportingContent = { Text("Additional info") },
                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                    modifier = Modifier
                        .clickable {
                            text = resultText//ASIGNAMOS LA SELECCIÓN AL TEXTO DEL TEXTFIELD DE LA BARRA DE BUSQUEDA
                            active = false//CUANDO SE PULSA EN UN ITEM CONTRAEMOS EL CUDRO DE SUGERENCIAS
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val list = List(100) { "Text $it" }
            items(count = list.size) {
                Text(
                    text = list[it],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MySimpleDockedSearchBarExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyDockedSearchBarExampleBasic()
        }
    }
}