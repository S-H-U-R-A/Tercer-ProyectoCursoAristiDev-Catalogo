package com.example.jetpackcomposecatalogoelementosui.ui.elementos_basicos

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Esto es un ejemplo"
        )
        Text(
            text = "Esto es un ejemplo",
            color = Color.Blue //Color de la letra
        )
        Text(
            text = "Esto es un ejemplo",
            fontWeight = FontWeight.Bold //Negrita
        )
        Text(
            text = "Esto es un ejemplo",
            fontWeight = FontWeight.Light //Delgada
        )
        Text(
            text = "Esto es un ejemplo",
            fontSize = 30.sp, //Tamaño de la letra
            fontFamily = FontFamily.Cursive //Tipo de letra
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.LineThrough //Subrayado
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.combine( //Combinación de estilos de decoración
                listOf(
                    TextDecoration.LineThrough, //Subrayado
                    TextDecoration.Underline //Tachado
                )
            )

        )
        //Normalmente el TextStyle se usa para reutilizar código y definir un estilo de texto y compartirlo entre varios Text
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle( //Text style tiene las mismas propiedades que Text y un par de propiedades más
                textDecoration = TextDecoration.LineThrough //Subrayado
            )
        )

    }

}


@Composable
fun MyTextExampleAnnotatedString(
    modifier: Modifier = Modifier
){
    //CON ESTE CONSTRUCTOR PODEMOS CONSTRUIR UN STRING PERSONALIZADO POR PARTES
    val annotatedString: AnnotatedString = buildAnnotatedString {
        //POR EJEMPLO PODEMOS DEFINIR ESTILOS PARA LA MISMA
        withStyle(
            style = SpanStyle(
                color = Color.Red,
                fontSize = 16.sp)
        ) {
            this.append("Mensaje: ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )
        ) {
            this.append("Te Amo Darlyn")
        }
    }
    Box(
        contentAlignment = Alignment.Center
    ){
        Text(text = annotatedString)
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTextExampleAnnotatedStringPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MyTextExampleAnnotatedString()
        }
    }
}






