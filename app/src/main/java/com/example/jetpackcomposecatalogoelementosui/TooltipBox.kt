package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.PlainTooltip
//import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
//import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
//import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTooltipBoxExampleWithPlainTooltip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    val stateTooltip = rememberTooltipState()
    val scope = rememberCoroutineScope()

    Box(
        contentAlignment  = Alignment.Center
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider() ,
            tooltip = {
                PlainTooltip(
                    containerColor = Color.Cyan,
                    contentColor = Color.Red,
                    shape = CutCornerShape(50.dp),
                    shadowElevation = 15.dp
                ){
                    Text(text = "Test PlainTooltip")
                }
            },
            state = stateTooltip,
            enableUserInput = false, // Con esto indicamos si queremos la pulsación larga para mostrar el PlainTooltip
            focusable = false
        ) {
            IconButton(
                onClick = { *//* Icon button's click event *//* }
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
}*/
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTooltipBoxExampleWithRichTooltip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    val stateTooltip = rememberTooltipState(
        isPersistent = true, //Se indica que una vez se haya mostrado no se va a ocultar
        initialIsVisible = true
    )
    val scope = rememberCoroutineScope()

    Box(
        contentAlignment  = Alignment.Center
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider() ,
            tooltip = {
                RichTooltip(
                    title = { Text(text = "RichTooltip") },
                    action = {
                        TextButton(
                            onClick = { scope.launch { stateTooltip.dismiss() } },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Blue
                            )
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
            IconButton(
                onClick = { *//* Icon button's click event *//* }
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
}*/

@Preview(
    name = "Examples of Tooltips",
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
            //MyTooltipBoxExampleWithRichTooltip()
        }
    }
}