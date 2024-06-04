package com.example.jetpackcomposecatalogoelementosui.ui.contenedores_navegacion

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyNavigationBarExample(
    modifier: Modifier = Modifier,
){
    var index: Int by remember { mutableIntStateOf(value = 0) }//ESTADO PARA MARCAR CUAL ELEMENTO DEBE ESTAR SELECCIONADO, EN UN APP REAL LOS ELEMENTOS PODRÍAN VENIR DE UNA LISTA DE OBJETOS

    NavigationBar(
        containerColor = Color.Red,
        contentColor = Color.White,
    ) {
        //DEFINIMOS LOS COLORES QUE SERAN USADOS PARA EL INDICADOR Y EL ICONO PARA CUANDO DEL ELEMENTO QUE ESTE SELECCIONADO
        val colorsActive: NavigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Gray,
            selectedIconColor = Color.Blue
        )

        NavigationBarItem(//CADA UNO DE ESTOS COMPOSABLES DEFINEN UN ITEM EN BARRA DE NAVEGACIÓN
            selected = (index == 0),//SI EL INDICE ES IGUAL A CERO, ENTONCES ESTE ELEMENTO DEBERÍA ESTAR SELECCIONADO
            onClick = { index = 0 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                )
            },
            label = { Text(text = "Home") },
            colors = colorsActive,
        )
        NavigationBarItem(//CADA UNO DE ESTOS COMPOSABLES DEFINEN UN ITEM EN BARRA DE NAVEGACIÓN
            selected = (index == 1),//SI EL INDICE ES IGUAL A UNO, ENTONCES ESTE ELEMENTO DEBERÍA ESTAR SELECCIONADO
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                )
            },
            label = { Text(text = "Favorite") },
            colors = colorsActive
        )
        NavigationBarItem(//CADA UNO DE ESTOS COMPOSABLES DEFINEN UN ITEM EN BARRA DE NAVEGACIÓN
            selected = (index == 2),//SI EL INDICE ES IGUAL A DOS, ENTONCES ESTE ELEMENTO DEBERÍA ESTAR SELECCIONADO
            onClick = { index = 2 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person"
                )
            },
            label = { Text(text = "Person") },
            colors = colorsActive
        )
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MyNavigationBarExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface{
            Scaffold(
                bottomBar = {
                    MyNavigationBarExample()
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().padding(paddingValues = it)
                ) {
                    Text(text = "Texto de ejemplo para la caja")
                }
            }
        }
    }
}
