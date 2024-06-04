package com.example.jetpackcomposecatalogoelementosui.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


//PARA QUE ESTE COMPOSABLE SE EJECUTE CORRECTAMENTE DEBE COMPILARSE COMO UNA APP Y NO SOLO COMO UN COMPOSABLE
//DONDE SOLO VEMOS EL FUNCIONAMIENTO DEL COMPOSABLE
@Composable
fun MyWindowInsetsExampleGetInsets(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    val localDensity = LocalDensity.current//DENSIDAD DE PIXELES DEL DISPOSITIVO
    //LOS MÉTODOS OBTENER TOP, LEFT, RIGHT, BOTTOM QUE ESTAN DISPONIBLES EN DIFERENTES OBJETOS DEL OBJETO WINDOWINSETS

    val top: Int = WindowInsets.ime.getTop(localDensity)//RETORNA LA CANTIDAD DE PIXELES DESDE LA PARTE SUPERIOR DEL INSET HASTA EL MÁRGEN DEL SIGUIENTE INSET

    val left: Int = WindowInsets.ime.getLeft(localDensity, LayoutDirection.Ltr)

    val right: Int = WindowInsets.ime.getRight(localDensity, LayoutDirection.Ltr)

    val bottom: Int = WindowInsets.ime.getBottom(localDensity)

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {

        Text(
            text = "$top" ,
            color = Color.Black,
            fontSize = 32.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "$left",
                fontSize = 32.sp,
                color = Color.Black
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ime Keyboard of device",
                    fontSize = 16.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp
                )
                Spacer(modifier = Modifier.height(height = 16.dp))
                TextField(value = "", onValueChange = {})
            }


            Text(
                text = "$right",
                fontSize = 32.sp,
                color = Color.Black
            )
        }

        Text(
            text = "$bottom",
            color = Color.Black,
            fontSize = 32.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

    }
}


//PARA QUE ESTE COMPOSABLE SE EJECUTE CORRECTAMENTE DEBE COMPILARSE COMO UNA APP Y NO SOLO COMO UN COMPOSABLE
//DONDE SOLO VEMOS EL FUNCIONAMIENTO DEL COMPOSABLE
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyWindowInsetExampleImeAndList(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    LazyColumn(//LISTA DE EJEMPLO
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .safeDrawingPadding()//ACÁ INDICAMOS QUE LA LISTA SE DIBUJE DE FORMA SEGURA, RESPETANDO LOS INSETS/MÁRGENES DE LA UI DEL SISTEMA.
            .imePadding()//ACÁ SE INDICA QUE SE AÑADA A LA LISTA EL PADDING QUE CORRESPONDE AL IME DEL TECLADO CUANDO SE MUESTRE.
            .imeNestedScroll()//ACÁ VINCULAMOS EL SCROLL DE LA LISTA EN ESTE CASO CON EL TECLADO, PARA QUE CUANDO LA LISTA SE DESPLACE HACIA ABAJO EL TECLADO SE CIERRE
    ){
        items(20){
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                placeholder = { Text(text = "Escribe algo ${it+1}") },
                modifier = Modifier.fillMaxWidth(),
                interactionSource = mutableInteractionSource
            )
        }
        item {//SIEMPRE SE RECOMIENDA USAR UN SPACER() COMO SEPARADOR DEL CONTENIDO EN ESTE CASO LA LISTA CON RESPECTO A LA NAVIGATIONBAR DEL CELULAR
            Spacer(
                modifier = Modifier
                    .background(color = Color.Red)
                    //INDICAMOS QUE LA ALTURA DE ESTE SPACER SE DEFINE A PARTIR DE LAS MEDIDAS DEL OBJETO INSET NAVIGATIONBAR DEL CELULAR.
                    .windowInsetsBottomHeight(insets = WindowInsets.navigationBars)
            )
        }
    }

}

@Preview(
    name = "Example Window Inset use",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyWindowInsetPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyWindowInsetExampleImeAndList()
        }
    }
}