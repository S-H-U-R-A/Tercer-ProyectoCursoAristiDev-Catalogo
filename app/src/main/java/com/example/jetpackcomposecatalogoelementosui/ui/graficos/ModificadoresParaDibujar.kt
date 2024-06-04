package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MiEjemploDeModificadorDeDibujoDeContenidoBásico(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CREA Y RECUERDA LAS MEDIDAS DEL TEXTO TENIENDO EN CUENTA [TAMAÑO DE LA FUENTE, LA FUENTE EN SI MISMA, ESPACIADO ENTRE LETRAS y MÁS]
    val medidorDeTexto = rememberTextMeasurer()//SOLO SE PUEDE INVOCAR EN EL ÁMBITO DE UN COMPOSABLE

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            //ESTE MODIFICADOR ES COMO LA LAMBDA DEL OBJETO CANVAS Y PERMITE DIBUJAR ATRÁS O ADELANTE DEL COMPOSABLE EN DONDE SE APLICA, DEBE LLAMARSE DE PRIMERO ANTES DE OTRO MODIFICADOR
            .drawWithContent {

                this.drawContent()//DIBUJAMOS EL CONTENIDO ORIGINAL DEL COMPOSABLE ATRÁS

                //DIBUJAMOS POR DELANTE DEL CONTENIDO EN ESTE CASO UN TEXTO
                this.drawText(
                    textMeasurer = medidorDeTexto,
                    text = "HOLA",
                    style = TextStyle(//ESTILO PARA EL TEXTO DIBUJADO
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    topLeft = center,
                )
            }
            .border(width = 1.dp, color = Color.Red)
            .background(color = Color.DarkGray)
    ){
        Image(
            painter = painterResource(id = R.drawable.flash),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.size(size = 200.dp)
        )
    }

}

@Composable
fun MiEjemploDeModificadorDeDibujoDeContenidoPorAtras(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .border(width = 1.dp, color = Color.Red)
            .background(color = Color.DarkGray)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                //ESTE MODIFICADOR ES COMO LA LAMBDA DEL OBJETO CANVAS Y PERMITE DIBUJAR ATRÁS DEL COMPOSABLE EN DONDE SE APLICA, DEBE LLAMARSE DE PRIMERO ANTES DE OTRO MODIFICADOR
                .drawBehind {
                    this.drawCircle(//EN ESTE CASO DIBUJAMOS UN CIRCULO POR LA PARTE DE ATRÁS DEL COMPOSABLE BOX Y SU CONTENIDO
                        color = Color.Blue,
                        radius = 70.dp.toPx(),
                        center = Offset(
                            x = 100f,
                            y = 100f
                        )//EL CENTRO DEL CIRCULO LO UBICAMOS PARA EN X e Y IGUAL A 100 A PARTIR DEL TAMAÑO DEL LIENZO O CANVAS QUE TOMA EL TAMAÑO DEL MISMO COMPOSABLE AL QUE SE LE APLICO EL MODIFICADOR
                    )
                }
                .size(size = 200.dp)
                .border(width = 1.dp, color = Color.Green)
                .background(color = Color.LightGray.copy(alpha = 0.8F))

        ){
            Text(
                text = "HELLO WORLD",
                color = Color.Red
            )
        }
    }
}

@Composable
fun MiEjemploDeModificadorDeDibujoDeContenidoResistenteAComposicion(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .border(width = 1.dp, color = Color.Red)
            .background(color = Color.DarkGray)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                //ESTE MODIFICADOR EN UN ENVOLTORIO DE LOS MODIFICADORES DE DIBUJO DE CONTENIDO y DIBUJO EN LA PARTE DE ATRÁS
                //SE USA PARA EVITAR DIBUJOS EN LOS QUE QUEREMOS EVITAR LAS RECOMPOSICIONES POR EJEMPLO AL DIBUJAR UN TEXTO QUE HAY QUE MEDIR Y SERÍA UN DESPERDICIO DE RECURSOS
                .drawWithCache {
                    this.onDrawBehind {//MÉTODO QUE ES IGUAL A USAR .drawBehind()
                        this.drawCircle(//PARA ESTE EJEMPLO DIBUJAMOS UN CIRCULO POR LA PARTE DE ATRÁS DEL COMPOSABLE BOX Y SU CONTENIDO
                            color = Color.Magenta,
                            radius = 70.dp.toPx(),
                            center = Offset(
                                x = 100f,
                                y = 100f
                            )//EL CENTRO DEL CIRCULO LO UBICAMOS PARA EN X e Y IGUAL A 100 A PARTIR DEL TAMAÑO DEL LIENZO O CANVAS QUE TOMA EL TAMAÑO DEL MISMO COMPOSABLE AL QUE SE LE APLICO EL MODIFICADOR
                        )
                    }
                }
                .size(size = 200.dp)
                .border(width = 1.dp, color = Color.Green)
                .background(color = Color.LightGray.copy(alpha = 0.94F))
        ){
            Text(
                text = "HELLO WORLD",
                color = Color.Red
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MisEjemplosDeModificadoresParaHacerDibujosPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploDeModificadorDeDibujoDeContenidoResistenteAComposicion()
        }
    }
}