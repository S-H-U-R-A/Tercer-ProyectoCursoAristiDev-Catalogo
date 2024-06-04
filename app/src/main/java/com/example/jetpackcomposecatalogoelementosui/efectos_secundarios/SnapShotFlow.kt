package com.example.jetpackcomposecatalogoelementosui.efectos_secundarios

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.listas.ItemHero
import com.example.jetpackcomposecatalogoelementosui.ui.listas.getSuperHeroes
import com.example.jetpackcomposecatalogoelementosui.model.SuperHero
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.flow.filter

@Composable
fun MySnapShotFlowExample(
    modifier: Modifier = Modifier
){
    val rvState: LazyListState = rememberLazyListState()//ESTADO PARA LA LISTA QUE PERMITE CONFIGURAR Y OBTENER INFORMACIÓN.

    //EJEMPLO DE USAR ANALITICAS EN UNA LISTA DE ELEMENTOS
    LaunchedEffect(key1 = rvState){
        snapshotFlow { rvState.firstVisibleItemIndex }//ACÁ INDICAMOS QUÉ A PARTIR DEL ESTADO CREAMOS UN FLUJO
            .filter {index: Int -> index > 0 }
            .collect{
                //ENVIAR UNA ANÁLITICA VIEWMODEL Y QUE ESTE LA ENVIE A UN REPOSITORIO Y AL SERVIDOR
                //viewModel.sendAnalithics(getSuperHeroes()[it])
                Log.d("SnapShotFlow", "Se envio el item ${getSuperHeroes()[it]} al servidor")
            }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(items = getSuperHeroes()) { superhero: SuperHero ->
                ItemHero(
                    superHero = superhero,
                    onItemSelected = { },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MySnapShotFlowExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MySnapShotFlowExample()
        }
    }
}