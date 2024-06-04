package com.example.jetpackcomposecatalogoelementosui.ui.mensajes_al_usuario

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@Composable
fun MySimpleScaffoldForSnackBarExamples(){
    //CONTEXT
    val context = LocalContext.current
    //SNACKBAR
    val snackBarHostState = remember { SnackbarHostState() }
    //SCOPE
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { paddingValues: PaddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = Color.LightGray)
                .verticalScroll(state = rememberScrollState())
        ) {
            Button(
                onClick = {
                    coroutineScope.launch() {
                        //Esta es la forma de generar un SnackBar con la configuración por defecto que nos ofrece el SnackBarHost
                        val snackBarResult: SnackbarResult = snackBarHostState.showSnackbar(
                            message = "Texto de SnackBar",
                            actionLabel = "Ir",
                            withDismissAction = false,
                            duration = SnackbarDuration.Short
                        )
                        //Esta es la forma de capturar los eventos en este SnackBarSimple
                        when(snackBarResult){
                            SnackbarResult.Dismissed -> {
                                Toast.makeText(
                                    context,
                                    "Se oculto el SnackBar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            SnackbarResult.ActionPerformed -> {
                                Toast.makeText(
                                    context,
                                    "Se confirmo el action en el SnackBar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            ) {
                Text(text = "Mostrar SnackBar")
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MySimpleExampleForSnackBarPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MySimpleScaffoldForSnackBarExamples()
        }
    }
}