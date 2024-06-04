package com.example.jetpackcomposecatalogoelementosui.ui.interacciones_y_eventos

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.interaction.DragInteraction
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyInteractionSourceCustomExampleAdvance(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }//FLOW DE INTERACCIONES
){
    val context: Context = LocalContext.current//CONTEXTO
    //ESTA LISTA LA USAMOS PARA IR ALMACENANDO EL ORDEN EN EL QUE SE DISPARAN LOS EVENTOS
    val interactions = remember { mutableStateListOf<Interaction>() }

    //CUANDO CAMBIA EL EVENTO DISPARADO SE ACTIVA ESTE EFECTO SECUNDARIO
    LaunchedEffect(key1 = mutableInteractionSource){
        //COLLECTAMOS EL FLOW QUE TRAE LOS EVENTOS
        mutableInteractionSource.interactions.collect{ interaction: Interaction ->
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

    val lastInteraction: String = when ( interactions.lastOrNull() ){//ESTA ES LA FORMA DE SABER CUAL ES LA ÚLTIMA INTERACCION O EVENTO
        is DragInteraction.Start -> "Arrastrado"
        is PressInteraction.Release -> "Presionado"
        is PressInteraction.Cancel -> "Cancelado"
        else -> "No hay un evento valido"
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {},
            interactionSource = mutableInteractionSource,
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        ) {
            Text(text = "Click me")
        }
        Text(
            text = "Último evento detectado $lastInteraction",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
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
            MyInteractionSourceCustomExampleAdvance()
        }
    }
}