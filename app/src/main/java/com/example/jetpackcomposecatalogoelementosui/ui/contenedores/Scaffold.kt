package com.example.jetpackcomposecatalogoelementosui.ui.contenedores

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.formularios.botones.MyFABBasic
import com.example.jetpackcomposecatalogoelementosui.ui.contenedores_navegacion.MyNavigationBarExample
import com.example.jetpackcomposecatalogoelementosui.ui.barra_superior_inferior.MyTopAppBarBasic
import com.example.jetpackcomposecatalogoelementosui.ui.modales.MyBottomSheetExampleBasic
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldExample(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
){
    val context: Context = LocalContext.current//CONTEXTO DE EN DONDE ESTOY

    val coroutineScope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA PARA EJECUTAR FUNCIONES DE SUSPENCIÓN

    //MODAL BOTTOM SHEET
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showBottomSheet by remember { mutableStateOf(value = false) }//CON ESTO VALIDAMOS SI SE DEBE MOSTRAR LA HOJA MODAL INFERIOR

    val snackBarHostState = remember { SnackbarHostState() }//ESTADO DEL SNACKBARHOST

    //PERMITE DEFINIR EL COMPORTAMIENTO DE LA BARRA SUPERIOR CUANDO LA PANTALLA SE PUEDE DESPLAZAR, POR EJEMPLO CUANDO USAMOS LAS LISTAS
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            MyTopAppBarBasic(
                scrollBehavior = scrollBehavior,//COMPORTAMIENTO DE LA BARRA SUPEIOR CUANDO TENEMOS UNA LISTA DE ELEMENTOS
                onIconClick = { text: String ->
                    coroutineScope.launch {
                        //CREAMOS EL SNACKBAR QUE SE MOSTRARÁ Y OBTNEMOS EL ESTADO RESULTADO
                        val snackBarResult: SnackbarResult = snackBarHostState.showSnackbar(
                            message = text,
                            actionLabel = "Ir",
                            duration = SnackbarDuration.Long
                        )
                        //VALIDAMOS EL ESTADO RESULTADO DEL SNACKBAR PARA IDENTIFICAR SI CONFIRMARÓN O DESCARTARÓN EL MENSAJE
                        when (snackBarResult) {
                            SnackbarResult.ActionPerformed -> {//ESTADO DE CONFIRMACIÓN
                                Toast.makeText(
                                    context,
                                    "Se confirmo el SnackBar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            SnackbarResult.Dismissed -> {//ESTADO DE DESCARTAR EL MENSAJE
                                Toast.makeText(
                                    context,
                                    "Se oculto el SnackBar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                },
                onMenuClick = onMenuClick
            )
        },
        bottomBar = {
            MyNavigationBarExample()
            //MyCustomBottomAppBar() //EJEMPLO DE USO DE UN BOTTOM APP BAR
        },
        floatingActionButton = {
            MyFABBasic(onFABClick = { showBottomSheet = true })//BOTTOM SHEET CUSTOM QUE MUESTRA LA HOJA INFERIOR
        },
        floatingActionButtonPosition = FabPosition.End,//POSICIÓN EN DONDE SE UBICARÁ SEGUN EL SCAFFOLD EL FAB
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection)//COORDINAMOS EL TRABAJO ENTRE LA TOP APP BAR Y EL CONTENIDO DEL SCAFFOLD
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues = it)
                .height(2000.dp)
                .fillMaxWidth()
                .background(color = Color.Gray)
                .verticalScroll( rememberScrollState() )//ACÁ AÑADIMOS UN SCROLL PARA HACER PRUEBAS SOBRE LA COORDINACIÓN CON LA TOP APP BAR
        ) {
            Text(
                color = Color.Blue,
                text = "Hola solo existo para pruebas"
            )
        }

        if(showBottomSheet){//SE VALIDA SI SE DEBE MOSTRAL EL MODAL DE LA HOJA INFERIOR
            MyBottomSheetExampleBasic(
                sheetState = sheetState,
                coroutineScope = coroutineScope,
                onCloseSheet = { isShowSheet: Boolean ->
                    showBottomSheet = isShowSheet
                }
            )
        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MyScaffoldPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyScaffoldExample(onMenuClick = {})
        }
    }
}