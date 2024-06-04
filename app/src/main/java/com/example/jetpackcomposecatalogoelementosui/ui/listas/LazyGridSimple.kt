package com.example.jetpackcomposecatalogoelementosui.ui.listas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.model.SuperHero
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

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
        items(items = getSuperHeroes()) { superHero: SuperHero ->
            ItemHero(
                superHero = superHero,
                onItemSelected = {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
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
fun MyLazyGridSimpleExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            SuperHeroGridView()
        }
    }
}