package com.example.jetpackcomposecatalogoelementosui.ui.pestanias_tabs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyTabRowExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(value = 0) }//POSICIÓN DEL TAB SELECCIONADO

    //OPCIONES DE TEXTO E ICONOS QUE VAMOS AUSAR PARA DIBUJAR LAS TABS
    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    val icons: List<ImageVector> = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = stateTab
        ) {
            titles.forEachIndexed { index:Int, itemLabel:String ->
                Tab(
                    selected = (stateTab == index),
                    onClick = { stateTab = index },
                    text = {
                        Text(
                            text = itemLabel,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = itemLabel
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(height = 32.dp))
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Text tab ${stateTab + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


//ACÁ USAMOS LA SOBRECARGA DEL COMPOSABLE TAB QUE NOS PERMITE PERSONALIZAR CADA TAB
@Composable
fun MyTabRowExampleBasicCustom(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }//POSICIÓN DEL TAB SELECCIONADO
    //OPCIONES DE TEXTO QUE VAMOS AUSAR PARA DIBUJAR LAS TABS
    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TabRow(
            selectedTabIndex = stateTab
        ) {
            titles.forEachIndexed { index, item ->
                Tab(//SOBRE CARGA DEL COMPOSABLE TAB QUE PERMITE PERSONALIZAR EL CONTENIDO
                    selected = (stateTab == index),//SE VALIDA SI EL ITEM DE LA LISTA ES LA POSICION SELECCIONADA PARA INDICAR SI ESTA SELECCIONADO
                    onClick = { stateTab = index }
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(50.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .background(//VALIDAMOS EL COLOR DE LA CAJA CON RESPECTO A SI ESTA SELECCIONADA O NO
                                    color = if (stateTab == index) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.inverseOnSurface
                                )
                        )
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
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
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTabRowBasicExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyTabRowExampleBasicCustom()
        }
    }
}