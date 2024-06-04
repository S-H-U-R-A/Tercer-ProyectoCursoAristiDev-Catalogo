package com.example.jetpackcomposecatalogoelementosui.ui.listas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.model.SuperHero
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun SuperHeroWithSpecialControlsView(
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current //Contexto de la aplicación

    val coroutineScope = rememberCoroutineScope() //Ámbito de Corrutina necesario para realizar acciones en la lista

    val rvState: LazyListState = rememberLazyListState() //Estado que nos permite recuperar los estados posibles de la lista y poder hacer operaciones con ellos.

    val stateElement by remember {//Estado llamado derivado porque trabaja en este caso a partir de algun dato del estado de la lista.
        derivedStateOf {
            rvState.firstVisibleItemIndex > 0//Acá se irá alamcenando el valor de si la primer imágen esta en pantalla
        }
    }

    //EJEMPLO DE USAR ANALITICAS EN UNA LISTA DE ELEMENTOS
    LaunchedEffect(key1 = rvState){
        snapshotFlow { rvState.firstVisibleItemIndex }//ACÁ INDICAMOS QUÉ A PARTIR DEL ESTADO CREAMOS UN FLUJO
            .filter {index: Int -> index > 0 }
            .distinctUntilChanged()
            .collect{
                //ENVIAR UNA ANÁLITICA
                getSuperHeroes()[it]
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
                    onItemSelected = {
                        Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (stateElement) {
            Button(
                onClick = {
                    coroutineScope.launch() {
                        rvState.scrollToItem(0)
                    }
                },
            ) {
                Text(text = "Ir al comienzo")
            }
        }
    }
}


@Preview()
@Composable
fun MyLazyColumnAdvanceControlExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            SuperHeroWithSpecialControlsView()
        }
    }
}