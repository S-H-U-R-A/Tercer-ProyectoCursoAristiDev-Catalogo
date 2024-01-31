package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyLabelExample(
    modifier: Modifier = Modifier
){
    var sliderPosition by remember { mutableStateOf(0f) }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val testList = remember { mutableListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center
    ) {



/*        Slider(
            value = sliderPosition,
            onValueChange = {it: Float -> sliderPosition = it},
            valueRange = 0f..10f,
            interactionSource = interactionSource,
            onValueChangeFinished = { },//Cuando se termina de arrastrar el puntero.
            steps = 9,
            thumb = {
                Label(
                    label = {
                        PlainTooltip(
                            modifier = Modifier
                                .requiredSize(45.dp, 25.dp)
                                .wrapContentWidth()
                        ) {
                            val roundedEnd =
                                (sliderPosition * 100.0).roundToInt() / 100.0
                            Text(text = roundedEnd.toString())
                        }
                    },
                    isPersistent = true
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = Color.Red
                    )
                }
            }
        )*/

/*        Label( //Simplemente es un contenedor para poner un composable encima del contenido, siendo el más adecuado un Tooltip
            label = {
    *//*        Text(
                    color = Color.Blue,
                    text = "De Sergio",
                    modifier = Modifier
                        .background(color = Color.Green)
                )*//*
                    Icon(
                        imageVector = Icons.Default.KeyboardAlt,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                    )
            },
            isPersistent = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(color = Color.Magenta)
        ) {

        }*/
    }
}






@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Mode Light"
)
@Composable
fun MyLabelPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyLabelExample()
        }
    }
}