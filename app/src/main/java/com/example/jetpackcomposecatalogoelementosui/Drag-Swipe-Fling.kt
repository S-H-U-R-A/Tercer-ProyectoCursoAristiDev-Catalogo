package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.AnchoredDraggableState
//import androidx.compose.foundation.gestures.DraggableAnchors
//import androidx.compose.foundation.gestures.Orientation
//import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlin.math.roundToInt


enum class DragAnchors{ Start, Center, End }

@Composable
fun MyDraggableExamples(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
/*    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.safeGestures)
    ){

        var offSetInY: Float by remember { mutableFloatStateOf(0f) }

        val pixelDraggableState = rememberDraggableState { delta: Float -> //CANTIDAD DE PIXELES QUE SE DESPLAZA AL ARRASTRAR
            offSetInY += delta
        }

        Text(
            text = "Arrastrame",
            modifier = Modifier
                .offset {
                    IntOffset(
                        x= 0,
                        y= offSetInY.roundToInt()
                    )
                } //Empieza en 0 en X y 0 en Y, lo que va cambiando es X
                .draggable(
                    orientation = Orientation.Vertical,
                    state = pixelDraggableState,
                    enabled = true,//Acá podriamos hacer validaciones para indicar cuando se puede arrastrar
                    startDragImmediately = true, //Con esto damos prioridad al evento de arrastre de nuestro composable
                    onDragStarted = { offset:Offset -> },
                    onDragStopped = { pixelOrientation:Float -> },
                    reverseDirection = false, //Si arrastramos hacia la izquierda lo mueve hacia la derecha,
                    interactionSource = interactionSource
                )
        )
    }*/

    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeGestures)
    ) {

        var offsetInX by remember { mutableFloatStateOf(0f) }
        var offsetInY by remember { mutableFloatStateOf(0f) }

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetInX.roundToInt(), offsetInY.roundToInt()) }
                .background(color = Color.Blue)
                .size(50.dp)
                .pointerInput(key1 = Unit) {
                    this.detectDragGestures {
                            change: PointerInputChange,
                            dragAmount: Offset //Valores de desplazamiento en X y en Y
                        ->
                        change.consume() //Acá indicamos que nosotros queremos toda la información del gesto
                        offsetInX += dragAmount.x //Actualizamos la posición del composable
                        offsetInY += dragAmount.y
                    }
                }
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
/*@Composable
fun MyAnchoredDraggableExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    val density: Density = LocalDensity.current

    //ESTADO QUE INDICA COMO VA FUNCIONAR EL ARRASTRE
    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            anchors = DraggableAnchors<DragAnchors> {
                    DragAnchors.Start at with(density){-100.dp.toPx() }
                    DragAnchors.Center at 0f
                    DragAnchors.End at with(density){100.dp.toPx() }
            },
            initialValue = DragAnchors.Start,
            positionalThreshold = { distancia: Float ->//Distancia entre anclas para activar el tiron hacia una u otra
                distancia *0.5f
            },
            velocityThreshold = {
                with(density) {
                    50.dp.toPx() //Velocidad que indica que debe pasar de un ancla a otra independientemente de la distancia indicada de positionalThresHold
                }
            },
            animationSpec = spring(),
            confirmValueChange = {
                true
            }
        )*//*.apply {
            updateAnchors(
                newAnchors = DraggableAnchors(){ // Map<String, Float-Píxeles >
                    DragAnchors.Start at 0f
                    DragAnchors.Center at 450f
                    DragAnchors.End at 900f
                }
            )
        }*//*
    }

    //SE AÑADE LA CAPACIDAD DE SER ARRASTRABLE A UN COMPOSABLE
    Box(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.flash),
            modifier = Modifier
                .size(size= 80.dp)
                .offset {
                    IntOffset(
                        x = anchoredDraggableState.requireOffset().roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(
                    anchoredDraggableState,
                    Orientation.Horizontal
                ),
            contentDescription = null,
        )
    }
}*/



@Preview(
    name = "Examples of Draggable, Swiping and Fling",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyEventsExamplesPrevie(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyDraggableExamples()
        }
    }
}