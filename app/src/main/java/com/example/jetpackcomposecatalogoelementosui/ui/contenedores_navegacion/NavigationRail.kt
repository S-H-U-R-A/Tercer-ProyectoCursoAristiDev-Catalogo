package com.example.jetpackcomposecatalogoelementosui.ui.contenedores_navegacion

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

//USADO PARA TABLETS Y ESCRITORIO
@Composable
fun MyNavigationRailExampleBasic(
    modifier: Modifier = Modifier
){
    //ESTADO PARA MARCAR CUAL ELEMENTO DEBE ESTAR SELECCIONADO, EN UN APP REAL LOS ELEMENTOS PODRÍAN VENIR DE UNA LISTA DE OBJETOS
    var selectedItem by rememberSaveable { mutableIntStateOf(value = 0) }

    //ESTO SERÍA MEJOR CON UN LISTADO DE OBJETOS
    val items = listOf("Home", "Search", "Settings")//LISTADO DE TITULOS
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)//LISTADO DE ICONOS

    Scaffold {
        NavigationRail(//ESTE COMPOSABLE SE SUELE USAR MÁS EN TABLETS Y EN ESCRITORIO, PARA MOVILES SE SUGIERE USAR MODAL NAVIGATION DRAWE
            header = {//CABECERO RECOMENDADO PARA ESTE NAVIGATION RAIL
                FloatingActionButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                }
            },
            containerColor = Color.White,
            contentColor = Color.Blue,
            modifier = modifier
                .padding(paddingValues = it)
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = (selectedItem == index),//SI EL INDICE DEL ITEM ES IGUAL AL VALOR DEL ESTADO DE SELECCIONADO, ENTONCES ESTE ELEMENTO DEBERÍA ESTAR SELECCIONADO
                    onClick = { selectedItem = index },//MARCAMOS COMO SELECCIONADO EL ITEM QUE SE PULSO
                    icon = { Icon(imageVector = icons[index] , contentDescription = item) },
                    label = { Text(text = item) },
                    alwaysShowLabel = true,//INDICA SI SE DEBE MOSTRAR SOLO EL ICONO O SI DEBE IR EL ICONO Y EL TEXTO, CUANDO ESTA EL ITEM SELECCIONADO SI SE MUESTRAN LOS DOS OBLIGATORIAMENTE
                    colors = NavigationRailItemDefaults.colors(
                        selectedTextColor = Color.Red
                    )
                )
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Modo Oscuro"
)
@Composable
fun MyNavigationRailExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyNavigationRailExampleBasic()
        }
    }
}