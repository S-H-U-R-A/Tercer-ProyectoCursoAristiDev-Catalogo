package com.example.jetpackcomposecatalogoelementosui.efectos_secundarios

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyLaunchedEffectExampleBasic(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit ={},
    onStop: () -> Unit = {}
){
    val context: Context = LocalContext.current//CONTEXTO

    var stateTextF: String by remember { mutableStateOf("") }//ESTADO PARA EL TEXTFIELD
    //ESTO SOLO SE EJECUTARÁ UNA VEZ YA QUE EL VALOR TRUE ES CONSTANTE Y NO SE PODRÁ CAMBIAR, SI EL VALOR CAMBIARÁ POR EJEMPLO USANDO UN ESTADO
    //ENTONCES POR CADA CAMBIO SE DISPARARRÍA ESTE TRIGGUER
    LaunchedEffect(key1 = true){//
        Toast.makeText(context, "Recomposición", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = stateTextF,
            onValueChange = {
                stateTextF = it
            },
            placeholder = {
                Text(text = "Descripción del campo")
            }
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyLaunchedEffectExamples(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyLaunchedEffectExampleBasic()
        }
    }
}