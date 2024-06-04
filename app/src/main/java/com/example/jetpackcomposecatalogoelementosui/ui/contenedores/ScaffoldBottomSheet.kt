package com.example.jetpackcomposecatalogoelementosui.ui.contenedores

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.barra_superior_inferior.MyTopAppBarBasic
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldBottomSheetExample(
    modifier: Modifier = Modifier
){
    //PERMITE DEFINIR EL COMPORTAMIENTO DE LA BARRA CUANDO LA PANTALLA SE PUEDE DESPLAZAR, POR EJEMPLO CUANDO USAMOS LAS LISTAS
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val coroutineScope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA PARA EJECUTAR FUNCIONES QUE SON SUSPENDIDAS

    val scaffoldBottomSheetState = rememberBottomSheetScaffoldState(//PARA ESTE COMPOSABLE ANALIZADO, ASI SE LE PASAN LOS ESTADOS DEL SHEET Y DE SNACKBAR
        bottomSheetState = SheetState(
            initialValue = SheetValue.PartiallyExpanded,//ESTADO INICIAL PARA LA HOJA INFERIOR
            skipPartiallyExpanded = false,//ACÁ INDICAMOS QUE NO SE PASE POR ALTO EL ESTADO MEDIO ABIERTO
            skipHiddenState = true,//CON ESTO EVITAMOS QUE LA HOJA INFERIOR SE PUEDA OCULTAR
            density = LocalDensity.current
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldBottomSheetState,//ESTADO PARA EL SCAFFOLD
        topBar = {
            MyTopAppBarBasic(
                scrollBehavior = scrollBehavior,
                onIconClick = { text: String -> },
                onMenuClick = {  }
            )
        },//TOP APP BAR PARA EL SCAFFOLD SHEET
        sheetShape = CutCornerShape(//FORMA DE LAS ESQUINAS DE LA HOJA INFERIOR
            topStartPercent = 10,
            topEndPercent = 10
        ),
        sheetPeekHeight = BottomSheetDefaults.SheetPeekHeight,//ALTURA MINIMA DE LA HOLA INFERIOR CUANDO SE INDICA QUE SE COLAPSE. ESTA SERÁ LA ALTURA DE PARTIAL EXPAND
        sheetContent = {//CONTENIDO DE LA HOJA INFERIOR
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Swipe para expandir la hoja inferior")
                Text("Sheet content")
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            //COLAPSA LA HOJA INFERIOR, HASTA LA ALTURA CONFIGURADA EN sheetPeekHeight
                            scaffoldBottomSheetState.bottomSheetState.partialExpand()
                        }
                    }
                ) {
                    Text("Click to collapse sheet")
                }
            }
        },
        sheetContainerColor = Color.Red,//COLOR DEL CONTENEDOR
        sheetContentColor = Color.White,//COLOR DE LOS TEXTOS DEL CONTENIDO
        sheetShadowElevation = 32.dp, //ELEVACIÓN O SOMBRA DE LA HOJA INFERIOR
    ) { innerPadding: PaddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scaffoldBottomSheetState.bottomSheetState.expand()//ACÁ INDICAMOS QUE SE EXPANDA LA HOJA INFERIOR
                    }
                }
            ) {
                Text("Contenido del BottomSheet Scaffold")
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyScaffoldBottomSheetExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyScaffoldBottomSheetExample()
        }
    }
}