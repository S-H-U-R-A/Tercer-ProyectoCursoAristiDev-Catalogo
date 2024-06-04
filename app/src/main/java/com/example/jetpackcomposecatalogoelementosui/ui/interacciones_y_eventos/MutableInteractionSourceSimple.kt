package com.example.jetpackcomposecatalogoelementosui.ui.interacciones_y_eventos

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


//EN ESTE EJEMPLO USAMOS ALGUNOS DE LOS COLECTORES PREDEFINIDOS POR EL FLOW DE INTERACCIONES
@Composable
fun MyInteractionSourceHighLevelExampleBasic(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
){
    val context: Context = LocalContext.current//CONTEXTO

    //ASI PODEMOS DETECTAR SI EL BOTÓN FUE PRESIONADO
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()//COLLECTOR DE FLOW SOLO PARA EL EVENTO PRESIONADO

    //ASI PODEMOS DETECTAR SI EL BOTÓN FUE ARRASTRADO
    val isDragged: Boolean by interactionSource.collectIsDraggedAsState()//COLLECTOR DE FLOW SOLO PARA EL EVENTO DE ARRASTRE

    //ASI PODEMOS DETECTAR SI EL BOTÓN TIENE EL FOCO
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()//COLLECTOR DE FLOW SOLO PARA EL EVENTO FOCUSABLE

    //ASI PODEMOS DETECTAR SI EL MOUSE PASO POR ENCIMA DEL BOTÓN, ESTO ES MÁS PARA WEB O TABLETS
    val isHovered: Boolean by interactionSource.collectIsHoveredAsState()//COLLECTOR DE FLOW SOLO PARA EL EVENTO DE PASAR EL MOUSE POR ENCIMA DEL ELEMENTO

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {},
            interactionSource = interactionSource,//ACÁ PASAMOS EL OBJETO DE INTERACCIONES PARA PODER DETECTAR LOS EVENTOS QUE PUEDA TENER ESTE BOTÓN
            modifier = Modifier
                .hoverable(interactionSource = interactionSource)
                .focusable(interactionSource = interactionSource)
        ) {
            Text(text = "Click me")
        }
    }

    if(isPressed) Toast.makeText(context, "El botón fue presionado", Toast.LENGTH_SHORT).show()

}

@Preview(
    name = "Mode Light",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyInteractionSourceHighLevelExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyInteractionSourceHighLevelExampleBasic()
        }
    }
}