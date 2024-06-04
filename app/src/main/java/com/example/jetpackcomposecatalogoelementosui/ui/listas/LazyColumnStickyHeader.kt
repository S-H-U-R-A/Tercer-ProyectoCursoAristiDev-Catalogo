package com.example.jetpackcomposecatalogoelementosui.ui.listas

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.model.SuperHero
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView(
    //Ejemplo de como poner una cabecera que se quede fija a ciertos elementos agrupados
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current

    val superHero: Map<String, List<SuperHero>> = getSuperHeroes().groupBy { it.publisher }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
    ) {

        superHero.forEach { (key: String, value: List<SuperHero>): Map.Entry<String, List<SuperHero>> -> //Acá lo que se hizo fue una desestructuración de los valores de mapa

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


@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyLazyColumStickyHeaderExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            SuperHeroStickyView()
        }
    }
}







