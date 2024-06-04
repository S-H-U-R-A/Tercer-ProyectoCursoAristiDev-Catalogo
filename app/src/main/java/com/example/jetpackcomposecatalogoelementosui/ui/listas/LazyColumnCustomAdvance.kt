package com.example.jetpackcomposecatalogoelementosui.ui.listas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.model.SuperHero
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

/**
 * Get super heroes: Método que crea una lista de Objetos de tipo [SuperHero]
 *
 * @return un [List] de [SuperHero]
 */
fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero(
            idSuperHero = 1,
            superHeroName = "Spiderman",
            realName = "Peter Parker",
            publisher = "Marvel",
            R.drawable.logan
        ),
        SuperHero(
            idSuperHero = 2,
            superHeroName = "Wolverine",
            realName = "James Howlett",
            publisher = "Marvel",
            R.drawable.logan
        ),
        SuperHero(
            idSuperHero = 3,
            superHeroName = "Batman",
            realName = "Bruce Wayne",
            publisher = "DC",
            R.drawable.batman
        ),
        SuperHero(
            idSuperHero = 4,
            superHeroName = "Thor",
            realName = "Thor Odinson",
            publisher = "Marvel",
            R.drawable.thor
        ),
        SuperHero(
            idSuperHero = 5,
            superHeroName = "Flash",
            realName = "Barry Allen",
            publisher = "DC",
            R.drawable.flash
        ),
        SuperHero(
            idSuperHero = 6,
            superHeroName = "Green Lantern",
            realName = "Alan Scott",
            publisher = "DC",
            R.drawable.green_lantern
        ),
        SuperHero(
            idSuperHero = 7,
            superHeroName = "Wonder Woman",
            realName = "Princess Diana",
            publisher = "DC",
            R.drawable.wonder_woman
        ),
    )
}


/**
 * Item hero es un composable que sabe como mostrar una tarjeta con la información del super heroe
 *
 * @param superHero es el super heroe a mostrar en la tarjeta.
 * @param onItemSelected es una lambda que debe saber manejar el superHeroe seleccionado.
 * @param modifier por buenas practicas y de forma opcional para pasar configuraciones visuales
 * @receiver
 */
@Composable
fun ItemHero(
    superHero: SuperHero,
    onItemSelected: (SuperHero) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        border = BorderStroke(
            width = 1.5.dp,
            color = Color.Red
        ),
        modifier = modifier
            .width(200.dp)
            .clickable {
                onItemSelected(superHero)
            }
    ) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "SuperHero Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = superHero.superHeroName,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(all = 8.dp)
            )
        }
    }
}

@Composable
fun SuperHeroView(
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current//Obtenemos el contexto

    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),//Padding para la lista
        verticalArrangement = Arrangement.spacedBy(8.dp),//Espaciados entre los elementos, en este caso de forma vertical
        userScrollEnabled = true,
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            items= getSuperHeroes(),
            key = { superHeroe: SuperHero -> superHeroe.idSuperHero }
        ) { superhero: SuperHero ->
            ItemHero(
                superHero = superhero,
                onItemSelected = { superHero: SuperHero ->
                    Toast.makeText(context, superHero.superHeroName, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyLazyColumnCustomAdvanceExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            SuperHeroView()
        }
    }
}