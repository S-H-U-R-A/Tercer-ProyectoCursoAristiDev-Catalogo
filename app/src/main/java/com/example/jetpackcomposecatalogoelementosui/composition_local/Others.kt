package com.example.jetpackcomposecatalogoelementosui.composition_local

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LocalPinnableContainer
import androidx.compose.ui.layout.PinnableContainer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


data class LocalComposition(
    val name: String,
    val description: String
)

object ListDeLocalComposition{
    val objectsLocalComposition: List<LocalComposition> = listOf(
        LocalComposition(
            name = "LocalContext.current",
            description = "Sirve para obtener el contexto de la aplicación, a partir de esta podemos obtener la actividad y muchas más cosas."
        ),
        LocalComposition(
            name = "LocalView.current",
            description = "Sirve para obtener el contexto de la aplicación, a partir de esta podemos obtener la actividad y muchas más cosas."
        ),
        LocalComposition(
            name = "LocalDensity.current",
            description = "Sirve para obtener la densidad de patalla y apartir de ella podemos transformar de DP a pixeles y viceversa."
        ),
        LocalComposition(
            name = "LocalConfiguration.current",
            description = "Es útil para obtener información del sistema como: Orientación del dispositivo, Densidad de la pantalla, Escala de la fuente, Modo Oscuro, El tipo de teclado, etc. "
        ),
        LocalComposition(
            name = "LocalLifecycleOwner.current",
            description = "Es una forma de obtener al ciclo de vida de donde estamos, por ejemplo de la Activity u Fragment."
        ),
        LocalComposition(
            name = "LocalTextStyle.current",
            description = "Es una forma de obtener los estilos del color, alpha/transparencia, tamaño, weight/grosor, etc."
        ),
        LocalComposition(
            name = "LocalContentColor.current",
            description = "Es una forma de obtener el objeto Color de compose donde tenemos los colores básicos."
        ),
        LocalComposition(
            name = "LocalAutofill.current",
            description = "Mediante este podemos obtener métodos para usar en las acciones de autocompletar."
        ),
        LocalComposition(
            name = "LocalFocusManager.current",
            description = "Sirve para controlar los estados de focus en los elementos de la UI."
        ),
        LocalComposition(
            name = "LocalFontFamilyResolver.current",
            description = "Sirve para resolver o encontrar una fuente que pasamos como argumento al método resolve(), si la encuentra en el proyecto a partir de ella podemos obtener información de la fuente."
        ),
        LocalComposition(
            name = "LocalViewConfiguration.current",
            description = "Permite obtener información de la View en donde se pintan los composables como: duracción del doble clic, duracción del evento de pulsar prolongado, tamaño minimo para activar el touch."
        ),
        LocalComposition(
            name = "LocalWindowInfo.current",
            description = "Permite obtener información del Window que aloja los composables como: detectar teclas pulsadas del teclado y saber si la ventana tiene el foco cuando hay multitarea. Es más para cuando en una tablet conectamos un teclado fisico."
        ),
        LocalComposition(
            name = "LocalUriHandler.current",
            description = "Nos sirve para manejar la apertura de una URL de forma simple en el navegador."
        ),
        LocalComposition(
            name = "LocalSoftwareKeyboardController.current",
            description = "Nos permite tener acceso a métodos de control sobre el teclado como: mostrar u ocultar el teclado."
        ),
        LocalComposition(
            name = "LocalIndication.current",
            description = "Nos permite recordar un objeto InteractionSource."
        ),
        LocalComposition(
            name = "LocalTextSelectionColors.current",
            description = "Nos permite acceder a los colores usados para resaltar un texto, como: El color de fondo y color de letra cuando se selecciona."
        ),
        LocalComposition(
            name = "LocalClipboardManager.current",
            description = "Nos permite obtener y configurar el portapapeles del dispositivo, esto es útil para copiar y pegar información entre apps."
        ),
        LocalComposition(
            name = "LocalOverscrollConfiguration.current",
            description = "Nos permite obtener la cantidad de padding que será aplicado desde los limites del contenido desplazable al effecto antes de ser dibujado."
        ),
        LocalComposition(
            name = "LocalSaveableStateRegistry.current",
            description = "Nos permite acceder a métodos que sirve para guardar y recuperear un objeto guardado, su funcionamiento es similar a usar rememberSaveable pero con más control sovbre cuando se guarda."
        ),
        LocalComposition(
            name = "LocalInputModeManager.current",
            description = "Nos permite obtener y configuar el tipo de entrada (entrada Touch, teclado fisico)."
        ),
        LocalComposition(
            name = "LocalLayoutDirection.current",
            description = "Nos permite obtener información sobre la orientación del texto, si es de derecha izquierda o viceversa."
        ),
        LocalComposition(
            name = "LocalTextInputService.current",
            description = "Nos permite controlar un objeto TextInputSessión que se crea cuando se muestra el teclado virtual del dispositivo, además podemos crear un nuevas instancias de este objeto como si usaramos un textField y cuando la creamos se cierra cualquier otra session del teclado abierta previamente ."
        ),
        LocalComposition(
            name = "LocalTextToolbar.current",
            description = "Nos permite acceder y controlar un objeto TextToolbar que es como un cuadro de dialogo pequeño asociado a un TextField con opciones como: copiar, pegar, seleccionar todo, etc ."
        ),
        LocalComposition(
            name = "LocalPinnableContainer.current",
            description = "A partir de esta podemos obtener un composable fijable e indicarle que se quede fijo o liberirarlo haci su scroll lo saque de la pantalla, sin embargo no funciona."
        )
    )
}


@Composable
fun MyDocumentationOFCommonLocalComposition(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "CompositionLocal Examples",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {

            items(items = ListDeLocalComposition.objectsLocalComposition){

                val pinnedHandle: PinnableContainer.PinnedHandle? = if(it.name == "LocalDensity.current") LocalPinnableContainer.current?.pin() else null

                MyOwnItemList(item = it)

                pinnedHandle?.release()
            }

        }
    }
}

@Composable
fun MyOwnItemList(
    item: LocalComposition,
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() }
){
    Column(
        modifier = Modifier.background(color = Color.LightGray)
    ) {
        Text(
            text = "Nombre: ${item.name}",
            fontWeight = FontWeight.Bold,
            color = Color(color = 0xFF592321)
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(text = "Description: ${item.description}")
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyDocumentationOFCommonLocalCompositionPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MyDocumentationOFCommonLocalComposition()
        }
    }
}


