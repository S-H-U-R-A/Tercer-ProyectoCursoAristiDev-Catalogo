package com.example.jetpackcomposecatalogoelementosui.ui.contenedores

import android.content.res.Configuration
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MySurfaceExampleOneBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
) {
    Surface(
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        contentColor = Color.Cyan,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = stringResource(id = R.string.app_name))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Magenta
                )
            ) {
                Text(text = "Click me")
            }
        }
    }
}

@Composable
fun MySurfaceExampleTwoBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
) {
    var counterState: Int by remember{ mutableIntStateOf(0) }

    Surface(
        onClick = {
            counterState++
        },
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        contentColor = Color.Cyan,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Cantidad de pulsaciones $counterState")
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Magenta
                )
            ) {
                Text(text = "Click me")
            }
        }
    }
}

@Composable
fun MySurfaceExampleThreeBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
) {
    var stateChecked: Boolean by remember { mutableStateOf(false) }

    Surface(
        checked = stateChecked,
        onCheckedChange = {
            stateChecked = it
        },
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        contentColor = Color.Cyan,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Surface esta seleccionado: $stateChecked")
        }
    }
}

@Composable
fun MySurfaceExampleFourBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
) {
    var stateSelected: Boolean by remember { mutableStateOf(value = false) }

    Surface(
        selected = stateSelected,
        onClick = { stateSelected = !stateSelected },
        color = if(stateSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
        contentColor = Color.Cyan,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}


@Preview(
    name = "Surface",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MySurfaceExamplesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        MySurfaceExampleThreeBasic()
    }
}