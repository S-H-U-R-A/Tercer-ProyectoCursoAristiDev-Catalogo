package com.example.jetpackcomposecatalogoelementosui.ui.graficos.pincel_brush

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MiEjemploBasicoDePintarConUnSoloColor(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    Box(
        modifier = modifier
            .requiredSize(200.dp)//SE DA UNA ALTURA Y ANCHURA FIJA Y PRIORITARIA
            .background(
                brush = SolidColor(value = Color.DarkGray),//PINTAMOS EL FONDO DE LA CAJA A PARTIR DE USAR UN PINCEL CON UN UNICO COLOR FIJO
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(all = 16.dp)

    ) {
        Text(
            color = Color.White,
            text = "El fondo Gris oscuro de la caja fue pintado con un Pincel/Brush"
        )
    }
}

@Composable
fun MiEjemploBasicoDePintarUnGradienteVertical(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val brush =
        Brush.verticalGradient(//CREAMOS EL PINCEL Y SU TIPO DE RELLENO QUE VA A SER UN GRADIENTE EN SENTIDO VERTICAL
            listOf(
                Color.Red,
                Color.Blue,
                Color.Green
            ),//EL COLOR INICIAL DEL GRADIENTE SERÁ ROJO E IRA HASTA LLEGAR AL COLOR AZUL
            //ACÁ SE INDICA DESDE QUE PUNTO EN Y SE EMPIEZA HA HACER EL DEGRADADO, ESTO CON RESPECTO AL TAMAÑO DEL LIENZO/CANVAS, ENTONCES EN ESTE CASO
            //ESTAMOS INDICANDO QUE EL DEGRADADO LO COMIENCE DESDE 70 PÍXELES EN Y HASTA 400 PÍXELES EN Y, SOLO EN ESE ESPACIO DE 330 PÍXELES DEL LIENZO
            //SE PINTARÁ CON BASE EN EL DEGRADADO
            startY = 70F,
            endY = 400F,
            tileMode = TileMode.Clamp//CUANDO YA SE ALCANZA LOS LIMITES PARA HACER EL DEGRADADO, ESTE MODO INDICA QUE SE PINTE EL ESPACIO SOBRANTE CON EL ÚLTIMO COLOR DE LA LISTA DE COLORES
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Canvas(
            modifier = Modifier
                .size(size = 300.dp)
                .border(width = 1.dp, color = Color.Red),
        ) {
            this.drawCircle(//INDICAMOS QUE SE SIBUJE UN CIRCULO AL QUE SE VA A PINTAR CON EL PINCEL
                brush = brush,//EL RELLENO DE LA FORMA VA A SER EL PINCEL CON EL GRADIENTE VERTICAL
                center = this.center
            )
        }
    }
}


//CUANDO USAMOS LAS SOBRECARGAS DE LOS TIPOS DE RELLENO PARA EL PINCEL QUE RECIBEN UN ARREGLO DE OBJETOS PAIR
//TENEMOS MÁS CONTROL SOBRE EN QUE PARTE DEL LIENZO DEBE APARECER CADA COLOR PARA REALIZAR LOS DEGRADADOS
@Composable
fun MiEjemploBasicoDePintarUnGradienteVerticalConIntervalosDeColor(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //DEFINIMOS EL ARREGLO DE COLORES A USAR POR EL PINCEL PARA PINTAR EL GRADIENTE E INDICAR LA POSICIÓN DE CADA COLOR SOLIDO EN EL LIENZO
    val intervalosDeColor: Array< Pair<Float, Color> > = arrayOf(
        0.0f to Color.Yellow,//LA POSICIÓN INDICA EN QUE PARTE DEL LIENZO DEBE APRECER EL COLOR
        0.3F to Color.Blue,//ESTA ES LA FORMA BÁSICA DE DEFINIR UN OBJETO PAIR, MUY SIMILAR A LOS MAPAS
        Pair<Float, Color>(0.7F, Color.Red),//ESTA ES LA FORMA HABITUAL DE DEFINIR UN OBJETO PAIR
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .requiredSize(size = 300.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = intervalosDeColor,
                    )
                )
        )
    }
}


@Composable
fun MiEjemploBasicoDePintarUnGradienteEnDiagonal(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //DEFINIMOS EL ARREGLO DE COLORES A USAR POR EL PINCEL PARA PINTAR EL GRADIENTE DIAGONAL E INDICAR LA POSICIÓN DE CADA COLOR SOLIDO EN EL LIENZO
    val intervalosDeColor: Array< Pair<Float, Color> > = arrayOf(
        0.0f to Color.Yellow,//LA POSICIÓN INDICA EN QUE PARTE DEL LIENZO DEBE APRECER EL COLOR
        0.3F to Color.Blue,//ESTA ES LA FORMA BÁSICA DE DEFINIR UN OBJETO PAIR, MUY SIMILAR A LOS MAPAS
        Pair<Float, Color>(0.7F, Color.Red),//ESTA ES LA FORMA HABITUAL DE DEFINIR UN OBJETO PAIR
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .requiredSize(size = 300.dp)
                .background(
                    brush = Brush.linearGradient(
                        colorStops = intervalosDeColor,
                    )
                )
        )
    }
}


@Composable
fun MiEjemploBasicoDePintarUnGradienteDifuminado(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //DEFINIMOS EL ARREGLO DE COLORES A USAR POR EL PINCEL PARA PINTAR EL GRADIENTE DIAGONAL E INDICAR LA POSICIÓN DE CADA COLOR SOLIDO EN EL LIENZO
    val intervalosDeColor: Array< Pair<Float, Color> > = arrayOf(
        0.0f to Color.Yellow,//LA POSICIÓN INDICA EN QUE PARTE DEL LIENZO DEBE APRECER EL COLOR
        0.3F to Color.Blue,//ESTA ES LA FORMA BÁSICA DE DEFINIR UN OBJETO PAIR, MUY SIMILAR A LOS MAPAS
        Pair<Float, Color>(0.7F, Color.Red),//ESTA ES LA FORMA HABITUAL DE DEFINIR UN OBJETO PAIR
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .requiredSize(size = 300.dp)
                .background(
                    brush = Brush.sweepGradient(//ESTE GRADIENTE HACE UN BARRIDO EN EL SENTIDO DE LAS MANECILLAS DEL RELOJ COMENZANDO A LAS 3 COMO PUNTO DE ORIGEN
                        colorStops = intervalosDeColor,
                        center = Offset(
                            x = 100F,
                            y = 100F
                        )//ACÁ INDICAMOS EL CENTRO DEL GRADIENTE DESDE DONDE SE APLICARA EL BARRIDO, EL VALOR DE X E Y LOS TOMA DEL COMPOSABLE DONDE SE APLIQUE
                    )
                )
        )
    }
}


@Composable
fun MiEjemploBasicoDePintarUnGradienteRadial(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //DEFINIMOS EL ARREGLO DE COLORES A USAR POR EL PINCEL PARA PINTAR EL GRADIENTE DIAGONAL E INDICAR LA POSICIÓN DE CADA COLOR SOLIDO EN EL LIENZO
    val intervalosDeColor: Array< Pair<Float, Color> > = arrayOf(
        0.0f to Color.Yellow,//LA POSICIÓN INDICA EN QUE PARTE DEL LIENZO DEBE APRECER EL COLOR
        0.35F to Color.Blue,//ESTA ES LA FORMA BÁSICA DE DEFINIR UN OBJETO PAIR, MUY SIMILAR A LOS MAPAS
        Pair<Float, Color>(0.8F, Color.Red),//ESTA ES LA FORMA HABITUAL DE DEFINIR UN OBJETO PAIR
        1F to Color.Green
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .requiredSize(size = 300.dp)
                .background(
                    brush = Brush.radialGradient(
                        colorStops = intervalosDeColor,
                    )
                )
        )
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun MisEjemplosDePintarConBrushPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface (
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxSize()
        ){
            MiEjemploBasicoDePintarConUnSoloColor()
        }
    }
}