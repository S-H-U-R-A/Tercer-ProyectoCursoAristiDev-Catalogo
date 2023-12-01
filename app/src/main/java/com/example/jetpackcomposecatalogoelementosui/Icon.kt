package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MyIconExample() {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        //Creamos la referencia del icono
        val idIcon: ConstrainedLayoutReference = createRef()

        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Icono",
            tint = Color.Red,
            modifier = Modifier
                .constrainAs(idIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(48.dp)
        )
    }
}