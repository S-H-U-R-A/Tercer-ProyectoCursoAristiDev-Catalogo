package com.example.jetpackcomposecatalogoelementosui.ui.formularios.campos_de_texto

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyTextFieldOutlined() {

    var myText: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = myText,
            onValueChange = { myText = it }, //Cada vez que cambie el texto se ejecuta esta función
            label = {
                Text(
                    text = "Introduce tu nombre" //Texto que aparece cuando no hay nada escrito
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.BrokenImage, contentDescription = null)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Red, //Color del texto
                unfocusedTextColor = Color.Red,
                focusedBorderColor = Color.Magenta, //Color del borde cuando está enfocado
                unfocusedBorderColor = Color.Blue //Color del borde cuando no está enfocado
            ),
            modifier = Modifier
                .padding(24.dp)
        )
    }
}

@Composable
fun MyTextFieldAdvance() {
    var myText: String by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = myText,
            onValueChange = {//Cada vez que cambie el texto se ejecuta esta función
                //Esto es una muestra de que podemos ejecutar lógica dentro de la función lambda
                myText = if (it.contains("a")) {
                    it.replace("a", "")
                } else {
                    it
                }
            },
            label = {
                Text(
                    text = "Introduce tu nombre" //Texto que aparece cuando no hay nada escrito
                )
            },
            placeholder = {}
        )
    }
}

@Composable
fun MyTextField(
    name: String,
    onNameChange: (String) -> Unit
) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) }, //Cada vez que cambie el texto se ejecuta esta función
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MiEjemploPerro() {

    LocalTextSelectionColors.current

    var campo: TextFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(""))  }
    var stateError by remember { mutableStateOf(false) }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    var range by remember { mutableIntStateOf(0) }

    val keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        TextField(
            value = campo,
            onValueChange = {
                campo = it
                range = it.selection.length
                stateError = it.selection.start > 10
            },
            label = { Text(text = range.toString()) },
            isError = stateError,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation(mask = 'a') else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Search),
            trailingIcon = {
                IconButton( onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon = if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    //TEXTO DE ACCESIBILIDAD QUE SE MUESTRA CUANDO SE PRESIONA
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            }
        )

        if(stateError){
            Text(text = "El texto debe ser de tres letras")
        }

        Spacer(modifier = Modifier.height(height = 16.dp))

        TextField(
            value = campo,
            onValueChange = {value: TextFieldValue ->
                campo = value
                value.selection.collapsed //INDICA SI EXISTE UNA SUGERENCIA
            },
            readOnly = false,
            textStyle = TextStyle(
                fontSize = TextUnit(value = 16f, type = TextUnitType.Sp),
            ),
            trailingIcon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
            suffix = { Text(text = ".com") },
            prefix = { Text(text = "www.") },
            supportingText = { Text(text = "Este campo es requerido") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    keyBoardController?.hide()
                }
            )
        )
/*        TextField(
            value = campo,
            onValueChange = {
                campo = it
            },
            placeholder = {
                Text(text = "Este es un placeholder")
            }
        )*/
    }
}

fun validarTexto(campo : String):Boolean = (campo.length > 3)

@Composable
fun MyTexFieldExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    val keyBoardController = LocalSoftwareKeyboardController.current

    var fieldValue by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = fieldValue,
            onValueChange = { text: String ->
                fieldValue = text
            },
            enabled = true,
            label = {
                Text(text = "Ingrese su sitio web")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Wifi, contentDescription = "Abc")
            },
            prefix = {
                Text(text = "www.")
            },
            suffix = {
                Text(text = ".com")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            shape = CutCornerShape(size = 8.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        )

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyTextFieldExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            var nameText: String by rememberSaveable { mutableStateOf(value = "") }

            MyTextField(
                name = nameText,
                onNameChange = {
                    nameText = it
                }
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    apiLevel = 33
)
@Composable
fun MyExamplePreview() {
    JetPackComposeCatalogoElementosUiTheme {
        MyTexFieldExample()
    }
}