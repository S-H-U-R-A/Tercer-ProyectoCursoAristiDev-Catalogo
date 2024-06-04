package com.example.jetpackcomposecatalogoelementosui.ui.imagenes_e_iconos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyIconExample() {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val idIcon: ConstrainedLayoutReference = createRef()

        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Icono",
            tint = Color.Red,
            modifier = Modifier
                .constrainAs(ref = idIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(size = 48.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyIconExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyIconExample()
        }
    }
}