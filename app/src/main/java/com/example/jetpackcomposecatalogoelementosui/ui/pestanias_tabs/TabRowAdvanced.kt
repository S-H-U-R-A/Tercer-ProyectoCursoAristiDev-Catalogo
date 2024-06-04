package com.example.jetpackcomposecatalogoelementosui.ui.pestanias_tabs

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyTabRowCustomIndicatorExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }//POSICIÓN DEL TAB SELECCIONADO
    //OPCIONES DE TEXTO QUE VAMOS AUSAR PARA DIBUJAR LAS TABS
    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TabRow(
            selectedTabIndex = stateTab,
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
            divider = {
                HorizontalDivider(
                    thickness = 5.dp, //ANCHO Y COLOR DEL DIVISOR O CARRETERA DONDE SE PUEDE DESPLAZAR EL INDICADOR
                    color = Color.Green
                )
            }
        ) {
            titles.forEachIndexed { index, item ->
                Tab( //SOBRE CARGA DEL COMPOSABLE TAB QUE PERMITE PERSONALIZAR EL CONTENIDO
                    selected = (stateTab == index) ,
                    onClick = { stateTab = index }
                ) {
                    Column(
                        Modifier
                            .padding(10.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            Modifier
                                .size(20.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .background(
                                    color = if (stateTab == index) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.inverseOnSurface
                                )
                        )
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }

        /*
                SecondaryTabRow(selectedTabIndex = stateTab) {
                    titles.forEachIndexed { index, item ->
                        Tab( //SOBRE CARGA DEL COMPOSABLE TAB QUE PERMITE PERSONALIZAR EL CONTENIDO
                            selected = (stateTab == index) ,
                            onClick = { stateTab = index }
                        ) {
                            Column(
                                Modifier
                                    .padding(10.dp)
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    Modifier
                                        .size(20.dp)
                                        .align(alignment = Alignment.CenterHorizontally)
                                        .background(
                                            color = if (stateTab == index) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.background
                                        )
                                )
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }*/

        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            text = "Text tab ${stateTab + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPrimaryTabRowCustomIndicatorExampleBasic(){
    var stateTab: Int by remember { mutableIntStateOf(0) }//POSICIÓN DEL TAB SELECCIONADO
    //OPCIONES DE TEXTO QUE VAMOS AUSAR PARA DIBUJAR LAS TABS
    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        PrimaryTabRow(
            selectedTabIndex = stateTab,
            indicator = {
                Icon(
                    imageVector = Icons.Default.Accessibility,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .tabIndicatorOffset(
                            stateTab,
                            matchContentSize = true
                        )
                )
            },
            divider = {
                HorizontalDivider(
                    thickness = 4.dp,
                    color = Color.Blue
                )
            }
        ) {
            titles.forEachIndexed { index, item ->
                Tab(//SOBRE CARGA DEL COMPOSABLE TAB QUE PERMITE PERSONALIZAR EL CONTENIDO
                    selected = (stateTab == index) ,
                    onClick = { stateTab = index }
                ) {
                    Column(
                        Modifier
                            .padding(10.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            Modifier
                                .size(20.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .background(
                                    color = if (stateTab == index) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.inverseOnSurface
                                )
                        )
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            text = "Text tab ${stateTab + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTabRowCustomAdvanceExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyPrimaryTabRowCustomIndicatorExampleBasic()
        }
    }
}