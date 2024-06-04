package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


//PARA AJUSTAR EL TAMAÑO DEL CANVAS ES NECESARIO PONERLO DENTRO DE UN COMPOSABLE CONTENEDOR
//DE LO CONTRARIO OCUPARÁ EL TOTAL DEL ESPACIO DE LA PANTALLA

//CUANDO EN ESTOS EJEMPLOS USO THIS ES PARA QUE SE VEA QUE LAS PROPIEDADES O MÉTODOS
//VIENEN DADAS POR EL ALCANCE DE DRAWSCOPE

@Composable
fun MiEjemploDeDibujarPuntos(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize()//OCUPARÁ EL ESPACIO EN EL QUE SE USE
    ){
       this.drawPoints(
            points = listOf(
                Offset(x= 25f, y= 25f),//EN EL PUNTO 25 EN X y 25 EN Y, DIBUJAMOS UN PUNTO, Y DE ESTA MISMA FORMA CON EL RESTO DE PUNTOS
                Offset(x= 100f, y= 100f),
                Offset(x= 200f, y= 200f),
                Offset(x= 300f, y= 300f),
                Offset(x= 400f, y= 400f),
            ),
            pointMode = PointMode.Polygon, //El modo en que se dibujan los puntos [ Puntos, Lineas, Poligonos[trazar una punto hasta el otro] ]
            color = Color.Red,//COLOR DE LOS PUNTOS
            strokeWidth = 20f, //ANCHO DE CADA PUNTO DIBUJADO EN PIXELES: EN COMPOSE SE REPRESENTAN CON UN NÚMERO FLOTANTE.
            cap = StrokeCap.Round,//FORMA DE LOS PUNTOS [CIRCULO, CUADRADO, OVALADO]
            pathEffect = PathEffect.cornerPathEffect(radius = 50f),//EFECTO DE REDONDEAR LOS ANGULOS DE LAS ESQUINAS CON BASE EN EL RADIO ESPECIFICADO
        )
    }
}

@Composable
fun MiEjemploDeDibujarLineas(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
)
{
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize()//OCUPARÁ EL ESPACIO EN EL QUE SE USE
    ) {
        //OBTENEMOS EL ANCHO Y EL ALTO DEL LIENZO PARA DIBUJAR
        val anchoCanvas = this.size.width
        val altoCanvas = this.size.height

        //LA LÍNEA SE DIBUJA EN AUTOMÁTICO CUANDO DAMOS LAS COORDENADAS DE INICIO Y FIN  REPRESENTADAS POR EL OBJETO OFFSET()

        this.drawLine(//MÉTODO QUE DIBUJA UNA LINEA SEGUN LAS COORDENADAS PASADAS PARA UN PUNTO INICIAL Y UN PUNTO FINAL
            color = Color.Red,
            start = Offset(x = 0f, y = 0f),//SE COMIENZA A DIBUJAR LA LINEA EN EL PUNTO (x=0, y=0)
            end = Offset(x = anchoCanvas, y = altoCanvas),//SE INDICA EL PUNTO FINAL DE LA LINEA EN(x= ANCHO TOTAL DEL LIENZO, y= ALTO TOTAL DEL LIENZO)
            strokeWidth = 20f//ANCHO DE LA LINEA
        )

        this.drawLine(//MÉTODO QUE DIBUJA UNA LINEA SEGUN LAS COORDENADAS PASADAS PARA UN PUNTO INICIAL Y UN PUNTO FINAL
            color = Color.Red,
            start = Offset(x = anchoCanvas, y = 0f),//EL PUNTO INICIAL PARA LA LINEA COMIENZA EN (x= ANCHO TOTAL DEL LIENZO, y= 0)
            end = Offset(x = 0f, y = altoCanvas),//SE INDICA EL PUNTO FINAL DE LINEA EN (x= 0, y= ALTO TOTAL DEL LIENZO)
            strokeWidth = 10f
        )

    }
}

@Composable
fun MiEjemploDeDibujarCirculos(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize()//OCUPARÁ EL ESPACIO EN EL QUE SE USE
    ){
        //OBTENEMOS EL ANCHO Y EL ALTO DEL LIENZO PARA DIBUJAR
        val anchoCanvas = size.width
        val altoCanvas = size.height

        this.drawCircle(
            color = Color.Red,
            center = Offset(x= 0f, y = 0f),//ACÁ INDICAMOS EL CENTRO DEL CIRUCLO QUE QUEREMOS DIBUJAR
            radius = 100f//ÁREA DEL CIRCULO
        )
    }

    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize()//OCUPARÁ EL ESPACIO EN EL QUE SE USE
    ){
        //OBTENEMOS EL ANCHO Y EL ALTO DEL LIENZO PARA DIBUJAR
        val anchoCanvas = size.width
        val altoCanvas = size.height

        this.drawCircle(
            color = Color.Blue,
            center = Offset(x= 200f, y = 200f),
            radius = 100f
        )
    }

    Canvas(
        modifier = Modifier.fillMaxSize()//OCUPARÁ EL ESPACIO EN EL QUE SE USE
    ){
        //OBTENEMOS EL ANCHO Y EL ALTO DEL LIENZO PARA DIBUJAR
        val anchoCanvas = size.width
        val altoCanvas = size.height

        this.drawCircle(
            color = Color.Green,
            center = Offset(x= 400f, y = 400f),
            radius = 100f
        )
    }
}

@Composable
fun MiEjemploDeDibujarRectangulos(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = modifier
    ){
        this.drawRect(
            color = Color.Magenta,
            size = Size(width = 400f, height = 800f)//ACÁ INDICAMOS EL TAMAÑO DEL RECTANGULO.
        )
    }

    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier
    ) {
        this.drawRect(
            color = Color.Blue,
            topLeft = Offset(x = center.x, y = center.y),//ACÁ INDICAMOS EN QUE COORDENADAS SE POSICIONARÁ LA ESQUINA SUPERIOR IZQUIERDA DEL RECTANGULO
            size = Size(width = 250f, height = 500f),//ACÁ INDICAMOS EL TAMAÑO DEL RECTANGULO.
        )
    }
}

@Composable
fun MiEjemploDeDibujarRectanguloDeBordeRedondeado(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    Canvas(
        modifier = Modifier.fillMaxSize()
    ){
        drawRoundRect(
            color = Color.Blue,
            size = size / 2.5f,//ACÁ INDICAMOS QUE TAMAÑO DEL RECTANGULO EN ESTE CASO SEA LA MITAD DE LA ALTURA Y LA ANCHURA DEL CANVAS.
            topLeft = Offset(x= center.x, y = center.y),//ACÁ UBICAMOS EL RECTANGULO A PARTIR DEL CENTRO DEL CANVAS.
            cornerRadius = CornerRadius(x= 50f, y= 50f)//REDONDEO DE LAS ESQUINAS DEL RECTANGULO
        )
    }
}

@Composable
fun MiEjemploDeDibujarOvalos(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = modifier
    ){
        //CALCULAMOS LA POSICIÓN EN DONDE DEBE IR LA PARTE SUPERIOR IZQUIERDA DE LA FIGURA
        //EN ESTE CASO OBTENEMOS LAS COORDENADAS X E Y DEL CENTRO Y A CADA UNA LA DIVIDIMOS EN 3
        //ES DECIR LA ALTURA/3 Y LA ANCHURA/3
        val esquinaIzquierda: Offset = center / 3f

        this.drawOval(
            color = Color.Blue,
            topLeft = esquinaIzquierda,
            size = Size(width = 200f, height = 300f)//PODER DEFINIR EL TAMAÑO ES LO QUE NOS PERMITE HACER UN OVALO, CUANDO DIBUJAMOS UN CIRCULO NO TENEMOS ESTA OPCIÓN.
        )
    }
}


//ESTA FIGURA ES PARA DIBUJAR UN CIRCULO PARECIDO A UN DE DIAGRAMA DE TORTA
@Composable
fun MiEjemploDeDibujarUnArco(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //LIENZO O CONTENEDOR DONDE SE PODRÁ DIBUJAR
    Canvas(
        modifier = Modifier.fillMaxSize()
    ){
        //CALCULAMOS LA POSICIÓN EN DONDE DEBE IR LA PARTE SUPERIOR IZQUIERDA DE LA FIGURA
        //EN ESTE CASO OBTENEMOS LAS COORDENADAS X E Y DEL CENTRO DEL CANVAS Y A CADA UNA, LA DIVIDIMOS EN 2
        //ES DECIR LA ALTURA/2 Y LA ANCHURA/2
        val esquinaSuperiorIzquierda: Offset = center / 2f

        this.drawArc(
            color = Color.Magenta,
            startAngle = 0f,//ACÁ SE INDICA EL ANGULO INICIAL, ESTO SERÍA EL BORDE DE LA DERECHA
            sweepAngle = 200f,//ACÁ SE INDICA EL ANGULO HASTA DONDE SE LLENARÁ EL ESPACIO
            useCenter = false, //INDICA SI SE DEBE RELLENAR EL ESPACIO DESDE EL CENTRO CREANDO UN DIGRAMA DE TORTA O NO.
            topLeft = esquinaSuperiorIzquierda,
            size = Size(width = 400f, height = 800f)//TAMAÑO DE LA FIGURA.
        )
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun EjemploDeDibujoDeFigurasBasicas(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = Color.LightGray,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploDeDibujarUnArco()
        }
    }
}