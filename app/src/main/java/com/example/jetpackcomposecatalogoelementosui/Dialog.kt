package com.example.jetpackcomposecatalogoelementosui

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyAlertDialogBasicExample(
    stateDialog: Boolean,
    onConfirmButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
){ //Este elemento no se oculta o no, se crea o no se crea asi de simple
    if(stateDialog){
        AlertDialog(
            onDismissRequest = { onCancelButtonClicked() },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmButtonClicked()
                    }
                ) {
                    Text(
                        text = "Confirmar"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onCancelButtonClicked()
                    }
                ) {
                    Text(
                        text = "Cerrar"
                    )
                }
            },
            title = {
                Text(
                    text = "Titulo"
                )
            },
            text = {
                Text(
                    text = "Contenido del dialogo"
                )
            },
            icon = {
               Icon(
                   imageVector = Icons.Filled.AccountCircle,
                   contentDescription = null
               )
            },
            shape = CutCornerShape(size = 25f),
            containerColor = Color.Yellow,
            titleContentColor = Color.Cyan,
            textContentColor = Color.Red,
            tonalElevation = 5.dp,
            properties = DialogProperties()
        )
    }
}


@Composable
fun MySimpleCustomDialogExample(
    stateDialog: Boolean,
    onCancelButtonClicked: () -> Unit
){
    if(stateDialog){
        Dialog(
            onDismissRequest = {
                onCancelButtonClicked()
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red.copy(alpha = 0.1f))
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Esto es un ejemplo 1",
                        color = Color.Red
                    )
                    Text(
                        text = "Esto es un ejemplo 2",
                        color = Color.Green
                    )
                    Text(
                        text = "Esto es un ejemplo 3",
                        color = Color.Blue
                    )
                }
            }

        }
    }
}


@Composable
fun MyCustomDialogExample(
    stateDialog: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
){
    if(stateDialog){
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(percent = 5)
                    )
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleCustomDialog(
                    text = "Set backup account"
                )
                repeat(4){ index: Int ->
                    MyAccountItem(
                        email = "Ejemplo$index@gmail.com",
                        image = R.drawable.avatar,
                        identity = index
                    )
                }
                MyAccountItem(
                    email = "Add account",
                    image = R.drawable.add,
                    identity = 4
                )
            }

        }
    }
}

@Composable
fun MyTitleCustomDialog(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
            .padding(
                start = 16.dp,
                bottom = 12.dp
            )
    )
}

@Composable
fun MyAccountItem(
    email: String,
    @DrawableRes image: Int,
    identity: Int,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                Log.i("ClickAccount", "Click in account $identity")
            }
    ) {
        Image(
            painter = painterResource(id = image),
            contentScale = ContentScale.Crop,
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(all = 8.dp)
                .size(size = 40.dp)
                .clip(CircleShape)
        )
        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(all = 8.dp)
        )
    }
}


@Composable
fun MyConfirmationDialogExample(
    stateDialog: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
){
    if(stateDialog){

        var optionSelected: String by rememberSaveable {
            mutableStateOf("")
        }

        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(percent = 2)
                    )
            ) {
                MyTitleCustomDialog(
                    text = "Phone RingTone",
                    modifier = Modifier
                        .padding( all = 24.dp)
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.5f)

                )
                MyRadioButtonMultipleExample(
                    optionSelected = optionSelected,
                    onOptionSelected = { option: String ->
                        optionSelected = option
                    },
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .height(height = 200.dp)
                        .verticalScroll(rememberScrollState())
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.5f)

                )
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "CANCEL",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    TextButton(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "OK",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

    }
}














//Composable solo necesario para mostrar un botón que despliegue las alertas
@Composable
fun ButtonDisplayAlert(
    onClick : (Boolean) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                onClick(true)
            }
        ) {
            Text(
                text = "Abrir Alerta",
            )
        }
    }
}