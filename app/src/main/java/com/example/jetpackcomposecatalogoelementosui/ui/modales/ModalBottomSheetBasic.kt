package com.example.jetpackcomposecatalogoelementosui.ui.modales

import android.content.res.Configuration
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySampleScaffoldForModalBottomSheetBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() }
){
    //ESTADO PARA CONOCER LOS EVENTOS DEL MODAL INFERIOR
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false, //ACÁ INDICAMOS QUE VAMOS A HACER USO DEL ESTADO INTERMEDIO O MEDIO ABIERTO
        confirmValueChange = { sheetValue: SheetValue ->
            //VALIDAR SI EL ESTADO OBTENIDO AL DESPLAZAR EL MODAL INFERIOR ES VALIDFO PARA NOSOTROS
            when(sheetValue){
                SheetValue.Hidden -> true// ACÁ INDICAMOS QUE EL ESTADO OCULTO ES ACEPTADO POR ESO DEVOLVEMOS VERDADERO
                SheetValue.Expanded -> true
                SheetValue.PartiallyExpanded -> true
            }
        }
    )

    //ESTADO PARA CONOCER LOS EVENTOS DEL MODAL INFERIOR MUY SIMILAR AL ANTERIOR
    val sheetStateStandard = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded,
        skipHiddenState = false,
        confirmValueChange = { sheetValue: SheetValue ->
            //BASICAMENTE CUANDO CAMBIE EL ESTADO PODEMOS HACER ALGO, CÓDIGO, ETC.
            //ESTO CAMBIA CUANDO PROGRAMATICAMENTE LLAMAMOS A .HIDE(), .SHOW(), ETC
            //EL BOOLEAN QUE PIDE ES SI QUE QUEREMOS MANEJAR ESTE EVENTO O NO
            when(sheetValue){
                SheetValue.Hidden -> false// ACÁ INDICAMOS QUE EL ESTADO OCULTO ES ACEPTADO POR ESO DEVOLVEMOS VERDADERO
                SheetValue.Expanded -> true
                SheetValue.PartiallyExpanded -> true
            }
        }
    )

    //ÁMBITO DE CORRUTINA PARA PODER EJECUTAR ACCIONES DEL MODAL INFERIOR QUE SON SUSPEND
    val scope: CoroutineScope = rememberCoroutineScope()

    //ESTADO PARA SABER SI DEBEMOS MOSTRAR O NO EL MODAL, ESTO ES DISTINTO A SABER EN QUE EVENTO ESTA EL MODAL
    var isShow: Boolean by rememberSaveable { mutableStateOf(value = false) }

    Scaffold(
       contentWindowInsets = WindowInsets.safeContent
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            TextButton(
                onClick = {
                    isShow = true
                }
            ) {
                Text(text = "Mostrar Modal inferior")
            }
        }

        //QUE EL MODAL ESTE PRESENTE SE HACE MEDIANTE UN CONDICIONAL
        if(isShow){
            MyBottomSheetExampleBasic(
                sheetState = sheetStateStandard,
                coroutineScope = scope,
                onCloseSheet = {
                    isShow = it
                },
            )
        }
    }
}



//MODAL INFERIOR EN LA PANTALLA
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetExampleBasic(
    sheetState: SheetState,
    coroutineScope: CoroutineScope,
    onCloseSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        scrimColor = Color.Transparent,
        onDismissRequest = {
            coroutineScope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if(!sheetState.isVisible) onCloseSheet(false)
            }
        },
        dragHandle = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if(!sheetState.isVisible) onCloseSheet(false)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Hide Sheet"
                )
            }
        },
    ) {
        //CONTENIDO DEL MODAL INFERIOR
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) onCloseSheet(false)
                    }
                }
            ) {
                Text(text = "Hide bottom sheet")
            }

            Spacer(modifier = Modifier.height(height = 16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(50) {
                    ListItem(
                        headlineContent = { Text("Item $it") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyModalBottomSheetExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MySampleScaffoldForModalBottomSheetBasic()
        }
    }
}