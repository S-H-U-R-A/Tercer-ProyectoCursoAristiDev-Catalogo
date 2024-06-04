package com.example.jetpackcomposecatalogoelementosui.ui.mensajes_al_usuario

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

/**
 * Snack bar visuals with error, es una clase que implementa la interfaz [SnackbarVisuals] para definir la parte
 * visual de un composable [Snackbar] de forma más personalizada.
 *
 * @property message variable que representa el mensaje que queremos mostrar.
 * @property isError propiedad propia para, por ejemplo, personalizar el elemento.
 */
class SnackBarVisualsWithError(
    override val message: String,
    val isError: Boolean,
) : SnackbarVisuals {
    override val actionLabel: String
        get() = if (isError) "Error" else "OK"//En este caso usamos nuestra variable para personalizar el texto del boton de confirmación
    override val withDismissAction: Boolean
        get() = false
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Indefinite
}


@Composable
fun MyCustomScaffoldForSnackBarExamples(){
    //CONTEXT
    val context = LocalContext.current
    //SNACKBAR
    val snackBarHostState = remember { SnackbarHostState() }
    //SCOPE
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState){ snackBarData: SnackbarData ->
                MyCustomSnackBarExample(snackBarData = snackBarData)
            }
        }
    ) { paddingValues: PaddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = Color.LightGray)
                .verticalScroll(rememberScrollState())
        ) {
            Button(
                onClick = {
                    coroutineScope.launch() {
                        //Esta es la forma de generar un SnackBar personalizado, pasandole la clase personalizada
                        val snackBarResult: SnackbarResult = snackBarHostState.showSnackbar(
                            SnackBarVisualsWithError(
                                message = "Te amo Darlyn",
                                isError = false
                            )
                        )
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


@Composable
fun MyCustomSnackBarExample(
    modifier: Modifier = Modifier,
    snackBarData: SnackbarData //Datos que son enviados al momento de la creación del SnackBar
){
    val isError: Boolean = (
        snackBarData.visuals//Esto es la forma de obtener el SnackBar a partir del objeto Data que retorna el host
                as?
                SnackBarVisualsWithError//Casteamos la barra hacia la clase que creamos
    )?.isError ?: false//Obtenemos la variable que creamos propia o ponemos false por defecto

    //Esto sería el ejemplo de como usar nuestra variable para dependiendo de ella configurar
    //en este caso el color del botón de acción del SnackBar
    val buttonColor: ButtonColors = if (isError) {
        ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.error
        )
    } else {
        ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    }

    //SnackBar que podemos personalizar 
    Snackbar(
        action = {
            TextButton(
                onClick = { if (isError) snackBarData.dismiss() else snackBarData.performAction() }, //Comportamiento configurado a partir del atributo propio que definimos en la clase [SnackBarVisualsWithError]
                colors = buttonColor
            ) {
                Text(snackBarData.visuals.actionLabel ?: "")
            }
        },
        dismissAction = {
            IconButton( onClick = { snackBarData.dismiss() }) {
                Icon(imageVector = Icons.Filled.AccessAlarm, contentDescription = "Cerrar")
            }
        },
        shape = CutCornerShape(size = 25.dp),
        containerColor = Color.Magenta,
        contentColor = Color.Green,
        actionContentColor = Color.White, //No se usa por ahora porque se sobreescribe cuando usamos la acción
        dismissActionContentColor = Color.Blue,
        modifier = Modifier
            .border(2.dp, MaterialTheme.colorScheme.secondary)
            .padding(12.dp),
    ) {
        Row {
            Text(
                text = snackBarData.visuals.message,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MySnackBarCustomExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyCustomScaffoldForSnackBarExamples()
        }
    }
}