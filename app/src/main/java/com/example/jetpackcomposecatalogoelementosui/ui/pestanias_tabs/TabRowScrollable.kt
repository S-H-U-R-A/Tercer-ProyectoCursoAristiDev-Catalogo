package com.example.jetpackcomposecatalogoelementosui.ui.pestanias_tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyScrollTabRowExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(value = 0) }//POSICIÓN EN LA LISTA DEL TAB SELECCIONADO

    //LISTADO DE TITULOS E ICONO A PINTAR COMO TABS
    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6")
    val icons: List<ImageVector> = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings, Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ScrollableTabRow(
            selectedTabIndex = stateTab,//POSICIÓN QUE DEBE MARCARSE COMO SELECCIONADA POR EL INDICADOR
            indicator = { listTabPosition: List<TabPosition> ->//LISTADO DE TABPOSITION QUE SE CREAN POR CADA ITEM DE LA TABROW, ESTE CONTIENE LAS MEDIDAS DESDE LA IZQUIERDA Y EL ANCHO
                Box(
                    modifier
                        .tabIndicatorOffset(//PERMITE ANIMAR EL DESPLAZAMIENTO DEL INDICADOR, CON BASE EN EL TABPOSITION QUE TIENE MEDIDAS QUE PERMITEN CALCULAR DICHO DESPLAZAMIENTO
                            currentTabPosition = listTabPosition[stateTab]
                        )
                        .padding(4.dp)
                        .fillMaxSize()
                        .border(
                            BorderStroke(1.dp, Color.Red),
                            RoundedCornerShape(5.dp)
                        )
                )
            },
            edgePadding = 8.dp//ES UN RELLENO AL INICIO Y EL FINAL DE LAS TABS, CON EL FIN DE QUE EL USUARIO ENTIENDA QUE PUEDE DEZPLAZARSE
        ) {
            titles.forEachIndexed { index, itemLabel ->//LISTADO DE TABS A DIBUJAR
                Tab(
                    selected = (stateTab == index),
                    onClick = {
                        stateTab = index
                    },
                    text = {
                        Text(
                            text = itemLabel,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = icons[index],//INDICAMOS EL ICONO QUE DEBEMOS USAR EN FUNCION DE LA POSICIÓN DE LA LISTA DE TITULOS
                            contentDescription = itemLabel
                        )
                    }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text tab ${stateTab + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun MyScrollTabRowExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyScrollTabRowExampleBasic()
        }
    }
}