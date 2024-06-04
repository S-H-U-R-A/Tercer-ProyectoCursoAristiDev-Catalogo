package com.example.jetpackcomposecatalogoelementosui.ui.interacciones_y_eventos

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlin.math.roundToInt

@Composable
fun MyEventDraggableExamplesBasic(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .windowInsetsPadding(insets = WindowInsets.safeGestures)
    ){
        //INDICAMOS QUE LA POSICIÓN EN "Y" SERÁ DE 0 DESDE LA POSICIÓN INICIAL EN LA QUÉ SE ENCUENTRE UBIUCADO EL COMPOSABLE EN LA QUE SE APLIQUE
        var offSetInY: Float by remember { mutableFloatStateOf(value = 0f) }

        val pixelDraggableState = rememberDraggableState { delta: Float ->
            offSetInY += delta//DELTA ES LA CANTIDAD DE PIXELES QUE SE DESPLAZA EN Y EL GESTO AL ARRASTRAR
        }

        Text(
            text = "Arrastrame ${offSetInY.roundToInt()}",
            modifier = Modifier
                .offset {
                    IntOffset(//POSICION EN X e Y CON LA QUE ARRANCA EL COMPOSABLE
                        x= 0,
                        y= offSetInY.roundToInt()
                    )
                }
                .border(width = 1.dp, color = Color.Red)//PARA ESTE EJEMPLO USAMOS ESTE BORDE PARA VER MEJOR EL DESPLAZAMIENTO
                .draggable(
                    orientation = Orientation.Vertical,//ORIENTACIÓN EN LA QUE SE VA A ARRASTRAR EL COMPOSABLE
                    state = pixelDraggableState,//ESTADO QUE NOS IRA INDICANDO LA CANTIDAD DE PIXELES QUÉ EL GESTO RECORRIO
                    enabled = true,//CON ESTE ARGUMENTO PODRÍAMOS HACER VALIDACIONES PARA SABER CUANDO ACTIVAMOS QUE ESTE COMPOSABLE SE PUEDE ARRASTRAR
                    startDragImmediately = true, //CON ESTE ARGUMENTO LE DAMOS PRIORIDAD AL EVENTO DE ARRASTRE DE ESTE COMPOSABLE SOBRE OTROS GESTOS
                    onDragStarted = { offset: Offset -> },//VA DEVOLVIENDO LA CANTIDAD DE PIXELES QUE RECORRE EL GESTO
                    onDragStopped = { pixelOrientation:Float -> },//SE DISPARA CUANDO SE TERMINA DE HACER EL GESTO Y DEVUELVE LA CANTIDAD DE PIXELES QUE RECORRIO
                    reverseDirection = false,//SI EL USUARIO HACE EN ESTE CASO EL ARRASTRE HACIA ABAJO ESTO LE INDICA QUE HAGA LO CONTRARIO, ES DECIR, LO MUEVA HACIA ARRIBA
                    interactionSource = interactionSource//OBJETO QUE CAPTURA LAS INTERACCIONES DEL USUARIO CON EL COMPOSABLE
                )
        )
    }
}



//EJEMPLO AVANZADO DE DETECCIÓN DE GESTO DE ARRASTRE USANDO POINTERINPUT QUE NOS PERMITE OBTENER LA UBICACIÓN DEL RECORRIDO DEL ARRASTRE EN X e Y AL MISMO TIEMPO
@Composable
fun MyEventDraggableExampleAdvance(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(insets = WindowInsets.safeGestures)
    ) {

        var offsetInX by remember { mutableFloatStateOf(0f) }//INDICAMOS QUE LA POSICIÓN EN X SERÁ 0 COMO EN UN PLANO CARTESIANO
        var offsetInY by remember { mutableFloatStateOf(0f) }//INDICAMOS QUE LA POSICIÓN EN Y SERÁ 0 DESDE LA PARTE SUPERIOR IZQUIERDA

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(//POSICION EN X e Y CON LA QUE ARRANCA EL COMPOSABLE
                        x = offsetInX.roundToInt(),
                        y = offsetInY.roundToInt()
                    )
                }
                .background(color = Color.Blue)
                .size(50.dp)
                .pointerInput(key1 = Unit) {//MODIFICADOR QUE PERMITE CAPTURAR DIFERENTES GESTOS QUE EL USUARIO REALIZA EN UN COMPOSABLE
                    //CAPTURAMOS EL EVENTO DE ARRASTRE, ESTE NOS RETORNA
                    this.detectDragGestures { change: PointerInputChange, dragAmount: Offset -> //DRAGAMOUNT CONTIENE LOS VALORES DE DESPLAZAMIENTO DE X e Y

                        change.consume()//ACÁ INDICAMOS QUE QUEREMOS TODA INFORMACIÓN DEL ARRASTRE

                        offsetInX += dragAmount.x//ESTO SERÍA COMO EL DELTA EN EJEMPLO BÁSICO, CON EL CUAL OBTENEMOS LOS PIXELES RECORRIDOS POR EL ARRASTRE

                        offsetInY += dragAmount.y//ESTO SERÍA COMO EL DELTA EN EJEMPLO BÁSICO, CON EL CUAL OBTENEMOS LOS PIXELES RECORRIDOS POR EL ARRASTRE
                    }
                }
        )
    }
}


@Preview(
    name = "Examples of Draggable, Swiping and Fling",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyEventDraggableExamplesBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyEventDraggableExampleAdvance()
        }
    }
}