package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MiEjemploDeDibujoDeRutasOCaminos(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color.DarkGray.copy(alpha = 0.7F))
            .drawWithCache {

                val path = Path()//ESTE OBJETO PERMITE DEFINIR LAS RUTAS PARA DIBUJAR LA RUTA O CAMINO

                path.moveTo(x = 0f, y = 0f)//INICIO O ORIGEN DEL DIBUJO QUÉ INDICA QUE MUEVA EL PUNTERO HACIA EL ORIGEN (0,0)

                path.lineTo(//INDICAMOS QUE SE TRACE UNA LINEA  DESDE EL PUNTO DE INICIO U ORIGEN HASTA EL CENTRO DEL LIENZO
                    x = size.width / 2f,
                    y = size.height / 2f
                )

                path.lineTo(x = size.width, y = 0f)//CONTINUAMOS EL TRAZADO DE LA RUTA HASTA EL ANCHO DEL LIENZO Y LA ALTURA EN CERO

                path.lineTo(x = (size.width - (size.width / 10F)), y = 0F)

                path.lineTo(x = (size.width / 2f), y = (size.height / 2f - size.height / 10f))

                path.lineTo(x = (size.width / 10f), y = 0f)

                path.close() //TRAZA UNA LINEA DESDE EL PUNTO FINAL HASTA EL PUNTO DE ORIGEN DEL TRAZO

                this.onDrawBehind {
                    this.drawPath(
                        path = path,//INDICAMOS QUE SE DIBUJE EL RUTA O CAMINO QUE CREAMOS
                        color = Color.Green,
                        style = Stroke(width = 5f)//INDICAMOS EL ANCHO DE LA LINEA
                    )
                }
            }
            .fillMaxSize()
    ){
        Text(
            text = "Ejemplo de dibujo de Rutas o caminos",
            color = Color.Red,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            letterSpacing = 7.sp
        )
    }
}


@Preview(
    showSystemUi = true,
)
@Composable
fun MiEjemploDeDibujoDeRutasOCaminosPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploDeDibujoDeRutasOCaminos()
        }
    }
}