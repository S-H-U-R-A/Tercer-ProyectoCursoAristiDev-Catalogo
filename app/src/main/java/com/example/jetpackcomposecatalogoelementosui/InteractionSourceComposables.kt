package com.example.jetpackcomposecatalogoelementosui

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyInteractionSourceHighLevelExampleBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
){
    val context: Context = LocalContext.current
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val isDragged: Boolean by interactionSource.collectIsDraggedAsState()
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()
    val isHovered: Boolean by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {},
            interactionSource = interactionSource,
            modifier = Modifier
                .hoverable(interactionSource = interactionSource)
                .focusable(interactionSource = interactionSource)
        ) {
            Text(text = "Click me")
        }
    }

    if(isPressed) Toast.makeText(context, "El botón fue presionado", Toast.LENGTH_SHORT).show()

}


@Composable
fun MyInteractionSourceCustomExampleBasic(
    modifier: Modifier = Modifier
){
    val context: Context = LocalContext.current
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = interactionSource,){
        interactionSource.interactions.collect(){
            when (it) {
                is PressInteraction.Release -> { Toast.makeText(context, "Click Aceptado", Toast.LENGTH_SHORT).show() }
                is PressInteraction.Cancel -> { Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show() }
                is HoverInteraction.Enter -> { Toast.makeText(context, "Sobre el Botón", Toast.LENGTH_SHORT).show() }
            }
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {},
            interactionSource = interactionSource,
        ) {
            Text(text = "Click me")
        }
    }
}

@Composable
fun MyInteractionSourceCustomExampleAdvance(
    modifier: Modifier = Modifier
){
    /**
     * Este ejemplo muestra como obtener los eventos que puede tener un composable a través de InteractionSource, con el fin de
     * tener el orden en el cual son llamados por eso creamos una lista para ir guardando cada evento que sucede.
     */

    val context: Context = LocalContext.current
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val interactions = remember { mutableStateListOf<Interaction>() } //Usado para ir almacenando el orden de los eventos que ocurren

    LaunchedEffect(key1 = interactionSource){
        interactionSource.interactions.collect{ interaction: Interaction ->
            when (interaction) {
                is PressInteraction.Release -> {
                    interactions.add(interaction)
                    Toast.makeText(context, "Click Aceptado", Toast.LENGTH_SHORT).show()
                }
                is PressInteraction.Cancel -> {
                    interactions.add(interaction)
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                }
                is DragInteraction.Start -> {
                    interactions.add(interaction)
                }
            }
        }
    }

    val lastInteraction = when (interactions.lastOrNull()){ //Esta sería la forma de saber el último evento ocurrido
        is DragInteraction.Start -> "Arrastrado"
        is PressInteraction.Press -> "Presionado"
        else -> "No hay estado"
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {},
            interactionSource = interactionSource,
            modifier = Modifier
                .clickable(
                    onClick = {},
                    interactionSource = interactionSource,
                    indication = rememberRipple() // Esto hará que retorne el último evento detectado en el interactionSource
                )
        ) {
            Text(text = "Click me")
        }
    }
}








@Preview(
    name = "Mode Light",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyInteractionSourceExamplesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyInteractionSourceCustomExampleBasic()
        }
    }
}