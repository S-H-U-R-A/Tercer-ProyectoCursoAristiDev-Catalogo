package com.example.jetpackcomposecatalogoelementosui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeCatalogoElementosUiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        /*EJEMPLO DE ELEVACIÓN DE ESTADO

                        var myText: String by remember {
                            mutableStateOf("")
                        }

                        MyTextField(
                            name = myText,
                            onNameChange = { textoEscrito ->
                                myText = textoEscrito
                            }
                        )*/


                        //EJEMPLO DE MULTIPLE CHECKBOX
                        //La idea no es cambiar los valores de la lista, sino la lista en si misma
                        /*var opcionesSeleccionadas by rememberSaveable {
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
                        }*/

                        //EJEMPLO DE ELEVACIÓN DE ESTADO DE RADIO BUTTON
                        /*var optionSelected by rememberSaveable {
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
                        }*/

                        /*Ejemplo de elevación de estado de un ExposedDropDownMenu

                        val options = listOf("Fijo", "Celular", "Casa", "Hogar", "Trabajo", "Oficina")

                        var optionSelected by remember { mutableStateOf(options[0]) }

                        MyExposedDropDownMenuExample(
                            options = options,
                            optionSelected = optionSelected,
                            onOptionChange = {
                                optionSelected = it
                            }
                        )*/

                        /*Ejemplo de elevación de estado de un AutoComplete ExposedDropDownMenu

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
                        )*/

                        //EJEMPLO DE ELEVACIÓN DE ESTADO DE UNA ALERTA DIALOGO
                        /*var stateDialog: Boolean by  remember {
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
                        )*/


                        //EJEMPLO DE UN DIALOGO SIMPLE PERSONALIZADO Y ELEVACIÓN DE ESTADO

                        /*var stateDialog: Boolean by  remember {
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
                        )*/

                        var stateDialog: Boolean by  remember {
                            mutableStateOf(false)
                        }

                        ButtonDisplayAlert(
                            onClick = {
                                stateDialog = it
                            }
                        )

                        /*MyCustomDialogExample(
                            stateDialog = stateDialog,
                            onDismiss = {
                                stateDialog = false
                            }
                        )*/

                        MyConfirmationDialogExample(
                            stateDialog = stateDialog,
                            onDismiss = {
                                stateDialog = false
                            }
                        )

                    }
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
            //MySimpleCustomDialog()
        }
    }
}













