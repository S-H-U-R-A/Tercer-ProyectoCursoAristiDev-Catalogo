package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.PrimaryTabRow
//import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun MyTabRowExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }

    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    val icons: List<ImageVector> = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)
    
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = stateTab
        ) {
            titles.forEachIndexed { index, itemLabel ->
                Tab(
                    selected = (stateTab == index) ,
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
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text tab ${stateTab + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

//LA DIFERENCIA DE ESTE CON EL ANTERIOR ES QUE EN ESTE ESTAMOS USANDO LA SOBRECARGA QUE
//PERMITE ESPECIFICAR EL CONTENIDO PERSONALIZADO
@Composable
fun MyTabRowCustomTabExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }

    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TabRow(
            selectedTabIndex = stateTab
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
        }
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
fun MyTabRowCustomIndicatorExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }

    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TabRow(
            selectedTabIndex = stateTab,
            indicator = {
                Box(
                    modifier
                        .tabIndicatorOffset(currentTabPosition = it[stateTab])
                        .padding(5.dp)
                        .fillMaxSize()
                        .border(
                            BorderStroke(2.dp, Color.Red),
                            RoundedCornerShape(5.dp)
                        )
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
        }

/*        PrimaryTabRow(selectedTabIndex = stateTab) {
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
        }

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabRowDynamicContent(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) } //ESTO NOS INDICA EN QUE POSICIÓN DEL ARREGLO VAMOS A ESTAR

    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text") //LISTADO DE TITULOS E ICONO A PINTAR
    val icons: List<ImageVector> = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    val pagerState = rememberPagerState { titles.size } //ESTADO PARA EL PAGER, ACÁ SE INDICA CUANTAS PÁGINAS VA A TENER

    //ES COMO UN TRIGGER A UN ESTADO
    LaunchedEffect(key1 = stateTab){ //ESTO SE ACTIVARÁ CADA VEZ QUE CAMBIE EL ESTADO DE LA PANTALLA ACTUAL
        pagerState.animateScrollToPage(stateTab)
    }

    //ES COMO UN TRIGGER A UN ESTADO
    LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress){//PUEDE ESTAR ESCUCHANDO VARIOS ESTADOS PARA HACER LA ACCIÓN
        if(!pagerState.isScrollInProgress){
            stateTab = pagerState.currentPage
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = stateTab
        ) {
            titles.forEachIndexed { index, itemLabel ->
                Tab(
                    selected = (stateTab == index) ,
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

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {indice: Int ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = titles[indice])
            }
        }

    }
}



@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTabRowExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //MyTabRowExampleBasic()
            //MyTabRowCustomTabExampleBasic()
            //MyTabRowCustomIndicatorExampleBasic()
            //MyScrollTabRowExampleBasic()
            //MyTabRowDynamicContent()
            MyTabRowDynamicContent()
        }
    }
}