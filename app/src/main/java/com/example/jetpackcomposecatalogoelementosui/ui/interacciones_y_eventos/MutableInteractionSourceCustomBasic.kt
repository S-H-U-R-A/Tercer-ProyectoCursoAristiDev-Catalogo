package com.example.jetpackcomposecatalogoelementosui.ui.interacciones_y_eventos

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyInteractionSourceCustomExampleBasic(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }//FLOW QUE SE ALIMENTA DE LAS INTERACCIONES DEL USUARIO CON LA APP
){
    val context: Context = LocalContext.current//CONTEXTO

    LaunchedEffect(key1 = mutableInteractionSource){//
        mutableInteractionSource.interactions.collect{it: Interaction ->
            when (it) {
                is PressInteraction.Release -> { Toast.makeText(context, "Click Aceptado", Toast.LENGTH_SHORT).show() }
                is PressInteraction.Cancel  -> { Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show() }
                is HoverInteraction.Enter   -> { Toast.makeText(context, "Sobre el Botón", Toast.LENGTH_SHORT).show() }
                is FocusInteraction.Unfocus -> { Toast.makeText(context, "Focus ", Toast.LENGTH_SHORT).show() }
                is FocusInteraction.Focus   -> { Toast.makeText(context, "UnFocus ", Toast.LENGTH_SHORT).show() }
                is DragInteraction.Stop     -> { Toast.makeText(context, "Final del arrastre ", Toast.LENGTH_SHORT).show()}
            }
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {},
            interactionSource = mutableInteractionSource,
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
fun MyInteractionSourceCustomExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyInteractionSourceCustomExampleBasic()
        }
    }
}