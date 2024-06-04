package com.example.jetpackcomposecatalogoelementosui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.jetpackcomposecatalogoelementosui.BuildConfig
import com.example.jetpackcomposecatalogoelementosui.R

@Composable
fun Screen1(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Cyan),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text(text = stringResource(id = R.string.flavor))
            Image(
                painter = painterResource(id = R.drawable.logan),
                contentDescription = null
            )
            Text(text = BuildConfig.SHOW_POPUP.toString())
        }

    }
}

@Composable
fun Screen2(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Green),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Pantalla 2")
    }
}


@Composable
fun Screen3(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Magenta),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Pantalla 3")
    }
}