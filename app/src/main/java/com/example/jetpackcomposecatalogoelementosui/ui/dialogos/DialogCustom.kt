package com.example.jetpackcomposecatalogoelementosui.ui.dialogos

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyCustomDialogExample(
    stateDialog: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (stateDialog) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(percent = 5)
                    )
                    .padding(all = 24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleCustomDialog(
                    text = "Set backup account",
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                repeat(4) { index: Int ->
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
) {
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
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { Log.i("ClickAccount", "Click in account $identity") }
    ) {
        Image(
            painter = painterResource(id = image),
            contentScale = ContentScale.Crop,
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(all = 8.dp)
                .size(size = 40.dp)
                .clip(shape = CircleShape)
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


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyCustomDialogExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            var stateDialog: Boolean by  remember { mutableStateOf(false) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {stateDialog = !stateDialog}
                ) {
                    Text(text = "Abrir Alerta")
                }
            }

            MyCustomDialogExample(
                stateDialog = stateDialog,
                onDismiss = { stateDialog = false }
            )
        }
    }
}