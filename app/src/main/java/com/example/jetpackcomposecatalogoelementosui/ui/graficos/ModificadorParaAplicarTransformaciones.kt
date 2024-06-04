package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

//MODIFICADOR PARA APLICAR ALGUNAS TRANSFORMACIONES SOBRE EL ELEMENTO EN EL QUE SE APLIQUE
@Composable
fun MiEjemploDeAplicarTransformacionesSobreElMismoComposable(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .border(width = 1.dp, color = Color.Red)
    ) {
        Image(
            painter = painterResource(id = R.drawable.batman),
            contentDescription = null,
            modifier = Modifier
                .size(size = 150.dp)
                .graphicsLayer {//ESTE MODIFICADOR TIENE OTRA SOBRECARGA, PERO ESTA ES MÁS FLEXIBLE AL SE UNA LAMBDA PORQUE PODEMOS HACER VALIDACIONES Y MÁS EN EL BLOQUE
                    this.scaleX = 2F
                    this.rotationY = 30F
                    this.rotationZ = 25F
                    this.clip = true
                }
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MisEjemplosDeModificadoresParaAplicarTransformacionesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploDeAplicarTransformacionesSobreElMismoComposable()
        }
    }
}