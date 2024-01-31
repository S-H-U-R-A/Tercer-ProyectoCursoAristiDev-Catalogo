package com.example.jetpackcomposecatalogoelementosui

import android.content.ClipData
import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LocalPinnableContainer
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.jetpackcomposecatalogoelementosui.composition_local.ElevationLocal
import com.example.jetpackcomposecatalogoelementosui.composition_local.LocalCustomElevation
import com.example.jetpackcomposecatalogoelementosui.composition_local.LocalSpacing
import com.example.jetpackcomposecatalogoelementosui.composition_local.Spacing
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun MyTestModifiersExamples(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()},
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit
){
    val context: Context = LocalContext.current

    var stateTextF: String by remember { mutableStateOf("") }

    LaunchedEffect(true){
        Toast.makeText(context, "Recomposición", Toast.LENGTH_SHORT).show()
    }

    Column {
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


@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun Saludar() {

    val uriHandler = LocalUriHandler.current

    var txtSaludar by remember { mutableStateOf("") }

    var conteoRecomposiciones by remember { mutableIntStateOf(0) }

    SideEffect {
        conteoRecomposiciones++
        //Toast.makeText(context, "Recomposición $conteoRecomposiciones", Toast.LENGTH_LONG).show()
    }

    CompositionLocalProvider(
        values = arrayOf(
            LocalSpacing provides Spacing(), //USANDO LA NOTACIÓN INFIX
            LocalCustomElevation.provides(ElevationLocal())
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = LocalSpacing.current.large)
        ) {
            TextField(
                value = txtSaludar,
                onValueChange = {
                    txtSaludar = it
                }
            )
            Text(text = txtSaludar)
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = LocalCustomElevation.current.card)
            ) {
                Text(text = "Hola desde la tarjeta")
            }
            Button(onClick = { uriHandler.openUri("https://www.example.com") }) {
                Text(text = "Ir")
            }

            Text(
                text = "Esto es un texto de ejemplo",
            )

        }
    }

}




@Composable
fun Counter()  {
    // Creamos un flujo de datos asincrónico
    val countFlow: Flow<Int> = flow {
        for (i in 1..5) {
            delay(2000) // Simulamos un retraso de 1 segundo entre cada emisión
            emit(i)
        }
    }

    // Utilizamos produceState para convertir el flujo en un estado de Compose
    val countState by produceState(initialValue = 0) {
        // Collect es una función de extensión de Flow que consume el flujo
        countFlow.collect { value ->
            // Actualizamos el estado, lo que provoca una recomposición
            this.value = value
        }
    }

    // Ahora countState se puede utilizar en el código del composable
    Text("Contador: $countState")
}



@Preview(
    name = "Example tipes of modifiers",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTestModifiersExamplesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Saludar()
        }
    }
}