package com.example.jetpackcomposecatalogoelementosui.efectos_secundarios

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

//ESTE EJEMPLO TOMA UN ESTADO Y A PARTIR DE ESTE Y SUS PROPIEDADES Y MÉTODOS
//LOS USA PARA CALCULAR UN NUEVO VALOR, ENTONCES ES ALGO ASI COMO UN ESTADO
//QUE REACCIONA AL CAMBIO DE OTRO POR ESO SE LLAMA ESTADO DERIVADO

@Composable
fun MyDerivedStateOfExample(
    modifier: Modifier = Modifier
){
    val rvState: LazyListState = rememberLazyListState()//ESTADO PARA LA LISTA QUE PERMITE CONFIGURAR Y OBTENER INFORMACIÓN DE ESTE.

    val stateElement by remember {//ESTADO QUE REACCIONA A LA INFORMACIÓN DEL ESTADO DE LA LISTA PARA ESTE CASO.
        derivedStateOf {
            rvState.firstVisibleItemIndex > 0//ACÁ SE CALCULA UN VALOR BOOLEANO QUE INDICA SI YA NO TENEMOS VISIBLE EL INDICE CERO DE LA LISTA
        }
    }

}