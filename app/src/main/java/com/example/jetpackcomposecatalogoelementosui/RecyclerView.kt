package com.example.jetpackcomposecatalogoelementosui

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.model.SuperHero
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@Composable
fun MySimpleRecyclerViewExample(
    modifier: Modifier = Modifier,
) {
    val myList: List<String> = listOf("Aris", "Pepe", "Manolo", "Jaime")

    LazyColumn() {
        item {
            Text(text = "Header")
        }
        items(7) {
            Text(text = "Este es el item $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
        item {
            Text(text = "Footer")
        }
    }
}


@Composable
fun SuperHeroGridView(
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp), //Padding para la lista
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
        modifier = modifier
            .fillMaxSize()
    ) {
        items(getSuperHeroes()){superHeroe: SuperHero ->
            ItemHero(
                superHero = superHeroe,
                onItemSelected = {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Composable
fun SuperHeroView(
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(8.dp), //Padding para la lista
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
        modifier = modifier
            .fillMaxSize()
    ) {
        items(getSuperHeroes()) { superhero: SuperHero ->
            ItemHero(
                superHero = superhero,
                onItemSelected = {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SuperHeroWithSpecialControlsView(
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current

    val rvState: LazyListState = rememberLazyListState() //Esta variable nos permite recuperar los estados posibles de la lista y poder hacer operaciones con ellos

    val stateElement by remember {
        derivedStateOf {
            rvState.firstVisibleItemIndex > 0
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(getSuperHeroes()) { superhero: SuperHero ->
                ItemHero(
                    superHero = superhero,
                    onItemSelected = {
                        Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if(stateElement){
            Button(
                onClick = {
                    coroutineScope.launch() {
                        rvState.scrollToItem(0)
                    }
                },
            ) {
                Text(text = "Soy un botón cool")
            }
        }
        

    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView( //Ejemplo de como poner una cabecera que se quede fija a ciertos elementos agrupados
    modifier: Modifier = Modifier,
){
    val context: Context = LocalContext.current

    val superHeros: Map<String, List<SuperHero> > =
        getSuperHeroes().groupBy { it.publisher }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
    ) {

        superHeros.forEach { ( key: String, value: List<SuperHero> ): Map.Entry<String, List<SuperHero>> -> //Acá lo que se hizo fue una desestructuración de los valores de mapa

            stickyHeader {
                Text(
                    text = key,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Blue)
                )
            }

            items(value) { superhero: SuperHero ->
                ItemHero(
                    superHero = superhero,
                    onItemSelected = {
                        Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

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

fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero(
            superHeroName = "Spiderman",
            realName = "Peter Parker",
            publisher = "Marvel",
            R.drawable.spiderman
        ),
        SuperHero(
            superHeroName = "Wolverine",
            realName = "James Howlett",
            publisher = "Marvel",
            R.drawable.logan
        ),
        SuperHero(
            superHeroName = "Batman",
            realName = "Bruce Wayne",
            publisher = "DC",
            R.drawable.batman
        ),
        SuperHero(
            superHeroName = "Thor",
            realName = "Thor Odinson",
            publisher = "Marvel",
            R.drawable.thor
        ),
        SuperHero(
            superHeroName = "Flash",
            realName = "Barry Allen",
            publisher = "DC",
            R.drawable.flash
        ),
        SuperHero(
            superHeroName = "Green Lantern",
            realName = "Alan Scott",
            publisher = "DC",
            R.drawable.green_lantern
        ),
        SuperHero(
            superHeroName = "Wonder Woman",
            realName = "Princess Diana",
            publisher = "DC",
            R.drawable.wonder_woman
        ),

        )
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MySimpleRecyclerViewExamplePreview() {
    JetPackComposeCatalogoElementosUiTheme {
        SuperHeroStickyView()
    }
}