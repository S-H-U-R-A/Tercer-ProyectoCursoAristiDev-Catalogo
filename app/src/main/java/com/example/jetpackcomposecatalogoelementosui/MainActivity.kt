package com.example.jetpackcomposecatalogoelementosui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        /*enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim =  Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim =  Color.Transparent.toArgb()
            )
        )*/
        super.onCreate(savedInstanceState)

        setContent {




            JetPackComposeCatalogoElementosUiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MyFirstExampleAdaptive()

/*                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        *//*EJEMPLO DE ELEVACIÓN DE ESTADO

                        var myText: String by remember {
                            mutableStateOf("")
                        }

                        MyTextField(
                            name = myText,
                            onNameChange = { textoEscrito ->
                                myText = textoEscrito
                            }
                        )*//*


                        //EJEMPLO DE MULTIPLE CHECKBOX
                        //La idea no es cambiar los valores de la lista, sino la lista en si misma
                        *//*var opcionesSeleccionadas by rememberSaveable {
                            mutableStateOf( listOf<String>() )
                        }

                        val opciones: List<String> = listOf("Sergio", "Darlyn", "Maleja", "Bebe Barrigas")

                        MyCheckBoxMultipleExample(
                            opcionesSeleccionadas = opcionesSeleccionadas,
                            opciones = opciones,
                            onCheckedChange = { selected, opcion ->
                                if(selected){
                                    opcionesSeleccionadas += opcion
                                }else{
                                    opcionesSeleccionadas -= opcion
                                }
                            }
                        )
                        Column {
                            opcionesSeleccionadas.forEach {
                                Text(text = it)
                            }
                        }*//*

                        //EJEMPLO DE ELEVACIÓN DE ESTADO DE RADIO BUTTON
                        *//*var optionSelected by rememberSaveable {
                            mutableStateOf("Darlyn")
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            MyRadioButtonMultipleExample(
                                optionSelected = optionSelected,
                                onOptionSelected = {
                                    optionSelected = it
                                }
                            )
                        }*//*

                        *//*Ejemplo de elevación de estado de un ExposedDropDownMenu

                        val options = listOf("Fijo", "Celular", "Casa", "Hogar", "Trabajo", "Oficina")

                        var optionSelected by remember { mutableStateOf(options[0]) }

                        MyExposedDropDownMenuExample(
                            options = options,
                            optionSelected = optionSelected,
                            onOptionChange = {
                                optionSelected = it
                            }
                        )*//*

                        *//*Ejemplo de elevación de estado de un AutoComplete ExposedDropDownMenu

                        val bankOptions: List<String> = listOf(
                            "Bancolombia",
                            "Davivienda",
                            "Banco de Bogotá",
                            "BBVA",
                            "Av Villas"
                        )

                        var optionSelected by remember { mutableStateOf( "" ) }

                        MyExposedDropDownMenuEditableExample(
                            bankOptions = bankOptions,
                            optionSelected = optionSelected,
                            onOptionChange = {
                                optionSelected = it
                            }
                        )*//*

                        //EJEMPLO DE ELEVACIÓN DE ESTADO DE UNA ALERTA DIALOGO
                        *//*var stateDialog: Boolean by  remember {
                            mutableStateOf(false)
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Button(
                                onClick = {
                                    stateDialog = true
                                }
                            ) {
                                Text(
                                    text = "Abrir Alerta"
                                )
                            }
                        }
                        MyAlertDialogBasicExample(
                            stateDialog = stateDialog,
                            onConfirmButtonClicked = {
                                stateDialog = false
                            },
                            onCancelButtonClicked = {
                                stateDialog = false
                            }
                        )*//*


                        //EJEMPLO DE UN DIALOGO SIMPLE PERSONALIZADO Y ELEVACIÓN DE ESTADO

                        *//*var stateDialog: Boolean by  remember {
                            mutableStateOf(false)
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Button(
                                onClick = {
                                    stateDialog = true
                                }
                            ) {
                                Text(
                                    text = "Abrir Alerta"
                                )
                            }
                        }

                        MySimpleCustomDialogExample(
                            stateDialog = stateDialog,
                            onCancelButtonClicked = { stateDialog = false }
                        )*//*

                        *//*var stateDialog: Boolean by  remember {
                            mutableStateOf(false)
                        }

                        ButtonDisplayAlert(
                            onClick = {
                                stateDialog = it
                            }
                        )*//*

                        *//*MyCustomDialogExample(
                            stateDialog = stateDialog,
                            onDismiss = {
                                stateDialog = false
                            }
                        )*//*

                        *//*MyConfirmationDialogExample(
                            stateDialog = stateDialog,
                            onDismiss = {
                                stateDialog = false
                            }
                        )*//*

                        SuperHeroStickyView()

                    }*/
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DefaultPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SuperHeroView()
        }
    }
}













