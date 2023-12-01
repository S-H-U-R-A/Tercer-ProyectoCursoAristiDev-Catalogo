package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MyImageAdvanceExample() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo", //Es útil para el testingusar los content description
        modifier = Modifier
            .clip(CircleShape)
            .border(
                width = 5.dp,
                color = Color.Red,
                shape = CircleShape
            )
    )
}

@Composable
fun MyImageExample() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        alpha = 0.5f,
        contentDescription = "Ejemplo", //Es útil para el testingusar los content description
    )
}