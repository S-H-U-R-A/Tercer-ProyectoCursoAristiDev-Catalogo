package com.example.jetpackcomposecatalogoelementosui

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyCanvasDrawImageExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    val flashImage = ImageBitmap.imageResource(id = R.drawable.flash)

    Canvas(
        modifier = Modifier.fillMaxSize(),
    ){
        this.
        drawImage(
            image = flashImage,
            topLeft = center
        )
    }
}

@Composable
fun MyCanvasDrawTextExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
/*    val medidorDeTexto = rememberTextMeasurer()

    Canvas(
        modifier = Modifier.fillMaxSize(),
    ){
       drawText(medidorDeTexto, "Hello")
    }*/

    val medidorDeTexto = rememberTextMeasurer()

    Spacer(
        modifier = modifier
            .drawWithCache {
                val medidaDelTexto: TextLayoutResult = medidorDeTexto.measure(
                    text = "Prueba de un texto largo de varias lineas para probar el funcionamiento del medidor de texto",
                    constraints = Constraints.fixed(
                        width = (this.size.width / 3f).toInt(),
                        height = (this.size.height / 6f).toInt()
                    ),// Acá indicamos un ancho y alto fijo para el texto, en este caso usamos el ancho del lienzo para calcular el ancho del texto
                    overflow = TextOverflow.Ellipsis, //Si el texto no cabe pone tres puntos indicando que continua
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    )
                )
                this.onDrawBehind {
                    drawRect(
                        color = Color.Red,
                        size = medidaDelTexto.size.toSize()// A partir de la medida calculada para el texto configuramos el tamaño del rectangulo
                    )
                    drawText(
                        textLayoutResult = medidaDelTexto
                    )
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun MyDrawPathExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    Spacer(
        modifier = modifier
            .drawWithCache {
                val path = Path() //DEFINIMOS LAS INSTRUCCIONES PARA DIBUJAR UN CAMINO
                path.moveTo(x = 0f, y = 0f) // MUEVA EL PUNTERO HACIA EL ORIGEN (0,0)
                path.lineTo(
                    x = size.width / 2f,
                    y = size.height / 2f
                ) //TRACE UNA LINEA HASTA EL CENTRO DEL LIENZO
                path.lineTo(x = size.width, y = 0f)
                path.lineTo(x = (size.width - size.width / 10f), y = 0f)
                path.lineTo(x = (size.width / 2f), y = (size.height / 2f - size.height / 10f))
                path.lineTo(x = (size.width / 10f), y = 0f)
                path.close() // TRAZA UNA LINEA DESDE EL PUNTO FINAL HASTA EL PUNTO DE ORIGEN DEL TRAZO

                this.onDrawBehind {
                    drawPath(
                        path = path,// INDICAMOS QUE SE DIBUJE EL RUTA O CAMINO QUE CREAMOS
                        color = Color.Magenta,
                        style = Stroke(width = 15f)
                    )
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun GetCanvasFromModifierExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
    val drawable = ShapeDrawable(OvalShape())

    Spacer(
        modifier = Modifier
            .drawWithContent {
                drawIntoCanvas { canvas: Canvas ->
                    drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
                    drawable.draw(
                        canvas.nativeCanvas
                    )
                }
            }
            .fillMaxSize()
    )

}

@Composable
fun MyCanvasTransformationExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {
/*    Canvas( // AUMENTAR O ESCALAR UN DIBUJO EN LOS EJES X e Y
        modifier = Modifier.fillMaxSize()
    ) {
        scale(scaleX = 10f, scaleY = 10f) {
            drawCircle(Color.Blue, radius = 20.dp.toPx())
        }
    }*/

/*    Canvas( // TRANSLADAR UN DIBUJO EN CUALQUIERA DE LOS CUATRO SENTIDOS
        modifier = Modifier.fillMaxSize()
    ) {
        translate( //ESTOS MOVIMIENTOS SE DAN A PARTIR DEL CENTRO DEL DIBUJO
            left = 100f,
            top = -300f
        ){
            drawCircle(
                color = Color.Blue,
                radius = 200.dp.toPx()
            )
        }
    }*/

/*    Canvas( // ROTAR UN DIBUJO
        modifier = Modifier.fillMaxSize()
    ) {
        rotate(
            degrees = 15f, //ANGULO QUE QUEREMOS QUE ROTE EL DIBUJO
            pivot = center //POSICIÓN SOBRE LA CUAL VA A ROTAR EL ELEMENTO
        ){
            drawRect(
                color = Color.Gray,
                size = size / 3F, //Tamaño de la figura
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F) // LA UBICACIÓN SE DA EN LA TERCERA PARTE DE LA ANCHURA Y ALTURA
            )
        }
    }*/

/*    Canvas( // INSERTAR PADDING
        modifier = Modifier.fillMaxSize()
    ) {
        val tamañoDelLienzo = size / 2f

        inset(
            horizontal = 50f,
            vertical = 80f
        ){
            drawRect(
                color = Color.Green,
                size = tamañoDelLienzo
            )
        }
    }*/

/*    Canvas( // TRANSFORMACIONES MULTIPLES
        modifier = Modifier.fillMaxSize()
    ) {
        val tamañoDelLienzo = size / 2f

        inset(
            horizontal = 50f,
            vertical = 80f
        ){
            drawRect(
                color = Color.Green,
                size = tamañoDelLienzo
            )
        }
    }*/

/*    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        withTransform(
            transformBlock = {
                translate(left = size.width / 5F )
                rotate(degrees = 0F)
            }
        ) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 4F, y = size.height / 3F),//POSICION INICIAL ANTES DE LA TRANSFORMACIÓN
                size = size / 3F
            )
        }
    }*/

}

@Composable
fun MyCanvasExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
) {

    var lienzo: Size by remember {
        mutableStateOf( Size(width = 0f, height = 0f) )
    }

    val context = LocalContext.current

    val densidad = LocalDensity.current

    Column(
        modifier = Modifier
    ){
/*        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .weight(weight = 1f)
        ){
            lienzo = size //Obtenemos la mitad del ancho y la mitad del alto del lienzo

            drawRect(
                color = Color.Magenta,
                size = lienzo
            )
        }
        Text(text = "Ancho: ${lienzo.width} X Altura: ${lienzo.height}")
        Text(
            text = with( Density(context) ){
                lienzo.width.toDp().toString()
            },

        )*/

/*        Canvas( //DIBUJAR UNA LÍNEA
                modifier = Modifier.fillMaxSize()
                ){
            val anchoCanvas = size.width
            val altoCanvas = size.height

            this.drawLine(
                color = Color.Red,
                start = Offset(x= anchoCanvas, y= 0f),
                end = Offset(x= 0f, y= altoCanvas),
                strokeWidth = 10f
            )

            this.drawLine(
                color = Color.Red,
                start = Offset(x= 0f, y= 0f),
                end = Offset(x= anchoCanvas, y= altoCanvas),
                strokeWidth = 10f
            )
        }*/

/*        Canvas( //DIBUJAR UN CIRCULO
            modifier = Modifier.fillMaxSize()
        ){
            val anchoCanvas = size.width
            val altoCanvas = size.height

            drawCircle(
                Color.Red,
                center = Offset(x= 0f, y = 0f),
                radius = 250f
            )
        }*/

/*        Canvas( //DIBUJAR UN CIRCULO
            modifier = Modifier.fillMaxSize()
        ){
            val anchoCanvas = size.width
            val altoCanvas = size.height

            drawCircle(
                Color.Red,
                center = Offset(x= 0f, y = 0f),
                radius = 250f
            )
        }*/

/*        Canvas( //DIBUJAR UN RECTANGULO
            modifier = Modifier.fillMaxSize()
        ){
            drawRect(
                color = Color.Blue,
                size = size / 2f,
                topLeft = Offset(x= center.x, y = center.y)
            )
        }*/

/*        Canvas( //DIBUJAR UN RECTANGULO CON ESQUINAS REDONDEADAS
            modifier = Modifier.fillMaxSize()
        ){
            drawRoundRect(
                color = Color.Blue,
                size = size / 2f,
                topLeft = Offset(x= center.x, y = center.y),
                cornerRadius = CornerRadius(x= 25f, y= 50f)
            )
        }*/

/*        Canvas( //DIBUJAR UN ÓVALO
            modifier = Modifier.fillMaxSize()
        ){
            val esquinaIzquierda: Offset = center / 3f

            drawOval(
                color = Color.Blue,
                topLeft = esquinaIzquierda,
                size = Size(width = 200f, height = 400f)
            )
        }*/

/*        Canvas( //DIBUJAR ARCO
            modifier = Modifier.fillMaxSize()
        ){
            val esquinaIzquierda: Offset = center / 2f

            drawArc(
                color = Color.Magenta,
                startAngle = 0f, //ANGULO DE LA RECTA
                sweepAngle = 270f, //ANGULO HASTA DONDE SE LLENARÁ EL ESPACIO
                useCenter = true, // INDICA SI SE DEBE RELLENAR EL ESPACIO DESDE EL CENTRO
                topLeft = esquinaIzquierda,
                size = Size(width = 400f, height = 800f)
            )
        }*/

        Canvas( //DIBUJAR UN PUNTO
            modifier = Modifier.fillMaxSize()
        ){
            drawPoints(
                points = listOf(
                    Offset(x= 25f, y= 25f),
                    Offset(x= 100f, y= 100f),
                    Offset(x= 200f, y= 200f),
                    Offset(x= 300f, y= 300f),
                    Offset(x= 400f, y= 400f),
                ),
                pointMode = PointMode.Polygon, //El modo en que se dibujan los puntos [ Puntos, Lineas, Poligonos[trazar una punto hasta el otro] ]
                color = Color.Red,
                strokeWidth = 50f, //Ancho de los puntos
                cap = StrokeCap.Butt, //Forma de los puntos [Circulo, Cuadrado, Ovalado]
                pathEffect = PathEffect.cornerPathEffect(50f), //Efecto a aplicar el punto
            )
        }
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun MyCanvasExamplePreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            GetCanvasFromModifierExample()
        }
    }
}