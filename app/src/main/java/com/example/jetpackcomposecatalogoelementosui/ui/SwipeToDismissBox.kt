package com.example.jetpackcomposecatalogoelementosui.ui

//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.SwipeToDismissBox
//import androidx.compose.material3.SwipeToDismissValue
//import androidx.compose.material3.rememberSwipeToDismissState
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

data class Persona(
    val id: Int,
    val name: String
)

object ApiPersona{
    val personas: List<Persona> = listOf(
        Persona(id= 0, name= "Sergio"),
        Persona(id= 1, name= "Darlyn"),
        Persona(id= 2, name= "Maleja"),
        Persona(id= 3, name= "Emma"),
        Persona(id= 4, name= "Matias"),
        Persona(id= 5, name= "Bebé Barrigas"),
        Persona(id= 6, name= "Bebé Barrigas 2")
    )
}


//AÚN ESTA EN BETA. HASTA QUE SALGA LA VERSION 1.3.0 DE MATERIAL 3
@OptIn(ExperimentalMaterial3Api::class)
/*@Composable
fun MySwipeToDismissExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    val listPerson = remember { ApiPersona.personas.toMutableStateList() }

    LazyColumn{
        items(items= listPerson, key= {persona: Persona -> persona.id}){persona: Persona ->

            val dismissState = rememberSwipeToDismissState(
                confirmValueChange = {
                    when(it){
                        SwipeToDismissValue.StartToEnd -> false//Al retornar false evitamos la acción
                        SwipeToDismissValue.EndToStart -> {
                            listPerson.remove(persona) // Implicitamente retorna true si pudo eliminar el elemento, si es false simplemente no hará nada
                        }
                        SwipeToDismissValue.Settled -> false
                    }
                },
                positionalThreshold = { screenDensityInPx: Float -> screenDensityInPx * 0.5f }
            )

            val directionSwipe: SwipeToDismissValue = dismissState.dismissDirection

            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    val color: Color by animateColorAsState(targetValue = getColorBackGround(dismissState.targetValue), label = "Animation color")
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        if(directionSwipe == SwipeToDismissValue.EndToStart){
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.RestoreFromTrash ,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        if(directionSwipe == SwipeToDismissValue.StartToEnd){
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                enableDismissFromStartToEnd = true,
                enableDismissFromEndToStart = true
            ) {
                Card {
                    ListItem(
                        headlineContent = { Text(text = "Titulo") },
                        supportingContent = { Text(text = persona.name) },
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}*/

/*@OptIn(ExperimentalMaterial3Api::class)
fun getColorBackGround(statusSwipe: SwipeToDismissValue): Color {
    return when(statusSwipe) {
        SwipeToDismissValue.Settled -> Color.LightGray
        SwipeToDismissValue.StartToEnd -> Color.Green
        SwipeToDismissValue.EndToStart -> Color.Red
    }
}*/



@Preview(
    name = "Swipe to dismiss example",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MySwipeToDismissExamplePreviewLight() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            //MySwipeToDismissExample()
        }
    }
}