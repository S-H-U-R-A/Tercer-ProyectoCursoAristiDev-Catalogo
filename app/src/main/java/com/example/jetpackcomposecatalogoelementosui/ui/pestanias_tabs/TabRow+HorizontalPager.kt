package com.example.jetpackcomposecatalogoelementosui.ui.pestanias_tabs

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabRowPlusHorizontalPagerExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }//POSICIÓN EN LA LISTA DEL TAB SELECCIONADO EN LA LISTA DE TABS

    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3 con un poco más de Texto")//LISTADO DE TITULOS E ICONO A PINTAR COMO TABS
    val icons: List<ImageVector> = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    val pagerState = rememberPagerState { titles.size }//ESTADO PARA EL HORIZONTAL PAGER, ACÁ SE INDICA CUANTAS PÁGINAS VA A TENER, EN ESTE CASO LA MISMA CANTIDAD DE TABS

    //LAUNCHED EFFECT ES UN TRIGGER A UN ESTADO
    LaunchedEffect(key1 = stateTab){//ESTO SE ACTIVARÁ CADA VEZ QUE CAMBIE LA POSICION DEL TAB SELECCIONADO
        pagerState.animateScrollToPage(stateTab)//CUANDO CAMBIE LA POSICIÓN DEL TAB SELECCIONADO MOVEMOS EL PAGER A ESTA MISMA VENTANA
    }

    //LAUNCHED EFFECT ES UN TRIGGER A UN ESTADO, EN ESTE CASO AL CAMBIO DE LA PAGINA ACTUAL DEL HORIZONTAL PAGER
    //EN ESTE CASO SI ALGUNO DE LOS DOS ESTADOS CAMBIA EJECUTA EL CÓDIGO
    LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress){//PUEDE ESTAR ESCUCHANDO VARIOS ESTADOS PARA HACER LA ACCIÓN
        //SI CAMBIA LA PAGINA ACTUAL Y SE ESTA HACIENDO SCROLL NO SE EJECUTARÁ EL CÓDIGO
        if(!pagerState.isScrollInProgress){//PREGUNTAMOS SI YA FINALIZO EL DESPLAZAMIENTO
            stateTab = pagerState.currentPage
        }
    }//SE DEBE BUSCAR UNA FORMA MÁS EFIFCIENTE DE ACTUALIZAR EL TAB SELECCIONADO AL DESPLAZAR EL HORIZONTAL PAGER

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
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTabRowPlusHorizontalPagerExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyTabRowPlusHorizontalPagerExampleBasic()
        }
    }
}