package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


/**
 * Ejemplo de Como obtener el objeto CANVAS primitivo de Android,
 * porque cuando usamos el composable Canvas de compose estamos usando una abstracción
 * más generica y menos potente desde este objeto primitivo
 */
@Composable
fun MiEjemploDeObteneeElCanvasPrimitivoDeAndroid(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    //EJEMPLO DE OBTENER UN OBJETO DE FORMA DE TIPO PRIMITIVO
    val drawable: ShapeDrawable = ShapeDrawable( OvalShape() )

    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                this.drawIntoCanvas { canvas: Canvas ->//OBTENEMOS EL CANVAS PRIMITIVO DE ANDROID
                    drawable.setBounds(//DEFINIMOS EL RECTANGULO QUE REPRESENTA LOS LIMITES PARA EL DIBUJO ALGO ASI COMO EL LIENZO
                        0,//ESTO SERÍA LA POSICIÓN EN X PARA EL DIBUJO
                        0,//ESTO SERÍA LA POSICIÓN EN Y PARA EL DIBUJO
                        this.size.width.toInt(),//ESTABLECEMOS UN ANCHO
                        this.size.height.toInt()//ESTABLECEMOS UN ALTO
                    )
                    drawable.draw(
                        canvas.nativeCanvas
                    )
                }
            }
    )
}


@Preview(
    showSystemUi = true,
)
@Composable
fun MiEjemploDeObtenerCanvasPrimitivoDeAndroid() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            MiEjemploDeObteneeElCanvasPrimitivoDeAndroid()
        }
    }
}