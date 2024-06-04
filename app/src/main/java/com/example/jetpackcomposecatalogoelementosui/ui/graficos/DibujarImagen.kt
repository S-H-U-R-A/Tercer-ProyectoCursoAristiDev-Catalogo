package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyCanvasDrawImageExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    //CARGAMOS UN IMAGE-BITMAP A PARTIR DE UN RECURSO DE DRAWABLE
    val flashImage: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.flash)

    Canvas(
        modifier = Modifier.fillMaxSize(),
    ){
        this.drawImage(
            image = flashImage,
            topLeft = center,
            style = Stroke()//FILL OR STROKE
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MiEjemploDeDibujarImagenPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {
            MyCanvasDrawImageExample()
        }
    }
}