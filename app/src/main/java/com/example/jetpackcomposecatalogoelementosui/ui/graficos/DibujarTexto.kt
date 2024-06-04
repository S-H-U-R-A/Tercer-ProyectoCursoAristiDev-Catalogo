package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MiEjemploDeDibujarTexto(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CREA Y RECUERDA LAS MEDIDAS DEL TEXTO TENIENDO EN CUENTA [TAMAÑO DE LA FUENTE, LA FUENTE EN SI MISMA, ESPACIADO ENTRE LETRAS y MÁS]
    val medidorDeTexto = rememberTextMeasurer()

    //CONTENEDOR O LIENZO SOBRE EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize(),
    ){
        //ESTE ES UN MÉTODO QUE ES COSTOSO DE USAR  Y SE RECOMIENDA USAR DRAWWITHCONTEXT() O DRAWWITHCACHE()
        this.drawText(
           textMeasurer= medidorDeTexto,
           style = TextStyle(//ESTILO PARA EL TEXTO DIBUJADO
               color = Color.Red,
               fontSize = 48.sp,
               fontWeight = FontWeight.Bold
           ),
           text= "Hello",//TEXTO A MOSTRAR
           topLeft = center//COORDENADAS DE UBICACIÓN PARA LA PARTE SUPERIOR IZQUIERDA DEL CONTENEDOR DEL TEXTO, EN ESTE CASO DESDE EL CENTRO DEL CANVAS
        )
    }
}

@Composable
fun MiEjemploDeDibujarTextoAvanzado(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    //CREA Y RECURDA LAS MERDIDAS DEL TEXTO TENIENDO EN CUENTA [TAMAÑO DE LA FUENTE, LA FUENTE EN SI MISMA, ESPACIADO ENTRE LETRAS y MÁS]
    val medidorDeTexto = rememberTextMeasurer()//INSTANCIA DE UN TEXTO QUE PUEDE SER MEDIBLE

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .border(width = 1.dp, color = Color.Blue)
            //ESTE MODIFICADOR ES SIMILAR A LA LAMBDA DE UN COMPOSABLE CANVAS EN LA CUAL PODEMOS DIBUJAR, LA DIFERENCIA RADICA EN QUE EN ESTE MOPDIFICADOR
            //LOS DIBUJOS PERSISTEN A LAS RECOMPOSICIONES A NO SER QUE CAMBIE EL TAMAÑO DEL COMPOSABLE EN DONDE SE UTILICE
            .drawWithCache {
                //DEFINIMOS LAS MEDIDAS QUE VA A TENER EL TEXTO
                val medidaDelTexto: TextLayoutResult = medidorDeTexto.measure(//ESTE MÉTODO MEDIR, DEFINE LAS MEDIDAS QUE VA A TENER EL TEXTO CON BASE EN LAS CONFIGURACIONES QUE LE DEMOS
                    text = "Prueba de un texto largo de varias lineas para probar el funcionamiento del medidor de texto, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ",
                    constraints = Constraints.fixed(//ACÁ INDICAMOS UNA MEDIDA FIJA PARA EL RECTANGULO DONDE SE UBICARÁ EL TEXTO, EN ESTE CASO LO CALCULAMOS A PARTIR DEL TAMAÑO DEL LIENZO
                        width = (this.size.width / 2f).toInt(),
                        height = (this.size.height / 2f).toInt()
                    ),
                    overflow = TextOverflow.Ellipsis,//SI EL TEXTO NO CABE EN EL RECTANGULO PARA EL DIBUJO DEL TEXTO, ENTIONCES PONEMOS TRES PUNTOS QUE INDICA QUE EL TEXTO CONTINUA
                    style = TextStyle(//DEFINIMOS LOS ESTILOS BÁSICOS PARA EL TEXTO
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify
                    )
                )

                //ESTE MÉTODO ES SIMILAR AL MODIFICADOR .DRAWBEHIND QUE PERMITE DIBUJAR POR ATRÁS DE UN COMPOSABLE
                this.onDrawBehind {
                    this.drawRect(//DIBUJAMOS UN RECTANGULO PARA EL FONDO EN DONDE SE DIBUJA EL TEXTO
                        color = Color.DarkGray,
                        size = medidaDelTexto.size.toSize()//EL TAMAÑO DEL RECTANGULO LO CALCULAMOS A PARTIR DE LA MEDIDA QUE DEFINIMOS PARA EL TEXTO
                    )
                    this.drawText(
                        textLayoutResult = medidaDelTexto
                    )
                }
            }
    ){

    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MiEjemploDeDibujarTextosPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploDeDibujarTextoAvanzado()
        }
    }
}