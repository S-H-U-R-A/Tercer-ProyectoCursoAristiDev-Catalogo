package com.example.jetpackcomposecatalogoelementosui.ui.imagenes_e_iconos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.R

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyImageAdvanceExample() {
    Image(
        imageVector = Icons.Default.Star,
        contentDescription = "Ejemplo", //Es útil para el testingusar los content description
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(shape = CircleShape)
            .border(
                width = 5.dp,
                color = Color.Red,
                shape = CircleShape
            )
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyImageExample() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        alpha = 0.6f,
        contentDescription = "Ejemplo", //Es útil para el testing usar los content description
        modifier = Modifier.clip(shape = CutCornerShape(50f)).background(color = Color.Blue)
    )
}