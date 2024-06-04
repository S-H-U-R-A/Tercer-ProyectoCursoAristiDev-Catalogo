package com.example.jetpackcomposecatalogoelementosui.ui.elementos_basicos

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTooltipBoxExampleWithPlainTooltip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    val stateTooltip = rememberTooltipState()//ESTADO PARA CONFIGURAR Y OBTENER INFORMACIÓN DEL TOOLTIP

    val scope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA NECESARIO PARA MOSTRAR MANUALMENTE EL TOOLTIP

    Box(
        contentAlignment  = Alignment.Center
    ) {

        TooltipBox(
            state = stateTooltip,
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider() ,
            tooltip = {//CONTENIDO DEL TOOLTIP
                PlainTooltip(
                    containerColor = Color.Cyan,
                    contentColor = Color.Red,
                    shape = CutCornerShape(4.dp),
                    shadowElevation = 15.dp
                ){
                    Text(
                        text = "Texto del PlainTooltip",
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            enableUserInput = true,//ESTE ARGUMENTO INDICA SI AL PULSAR LARGO MOSTRAMOS EL TOOLTIP
            focusable = false//PUEDE OBTENER EL FOCO O NO
        ) {
            IconButton(//COMPOSABLE SOBRE EL CUAL APARECERÁ EL TOOLTIP
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Description"
                )
            }
        }

        OutlinedButton(
            onClick = { scope.launch { stateTooltip.show() } },
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
        ) {
            Text("Mostrar el Tooltip")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTooltipBoxExampleWithRichTooltip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    val stateTooltip = rememberTooltipState(//ESTADO PARA CONFIGURAR Y OBTENER INFORMACIÓN DEL TOOLTIP
        isPersistent = true,//CONFIGURACIÓN PREVIA QUE INDICA QUE UNA VEZ SE MUESTRE EL TOOLTIP NO SE ELIMINE
        initialIsVisible = true//CONFIGURACIÓN PREVIA QUE INDICA SI AL CARGAR EL COMPOSABLE SE DEBE MOSTRAR EN AUTOMÁTICO EL TOOLTIP
    )
    val scope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA NECESARIO PARA MOSTRAR MANUALMENTE EL TOOLTIP

    Box(
        contentAlignment  = Alignment.Center
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider() ,
            tooltip = {
                RichTooltip(//TOOLTIP ENRIQUECIDO ES DECIR SE 
                    title = { Text(text = "RichTooltip") },
                    action = {
                        Button(
                            onClick = { scope.launch { stateTooltip.dismiss() } },
                            shape = CutCornerShape(size = 8.dp)
                        ) {
                            Text(text = "Ocultar")
                        }
                    },
                    colors = TooltipDefaults.richTooltipColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Red,
                        titleContentColor = Color.Yellow,
                        actionContentColor = Color.Blue
                    ),
                    shape = CutCornerShape(size= 8.dp)
                ) {
                    Text(text = "Test RichTooltip")
                }
            },
            state = stateTooltip,
            enableUserInput = false
        ) {
            IconButton(//COMPOSABLE SOBRE EL QUE SE MOSTRARÁ EL TOLLTIP
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Description"
                )
            }
        }
        OutlinedButton(
            onClick = { scope.launch { stateTooltip.show() } },
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
        ) {
            Text("Display tooltip")
        }
    }
}




@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTooltipsExample(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ){
            MyTooltipBoxExampleWithRichTooltip()
        }
    }
}