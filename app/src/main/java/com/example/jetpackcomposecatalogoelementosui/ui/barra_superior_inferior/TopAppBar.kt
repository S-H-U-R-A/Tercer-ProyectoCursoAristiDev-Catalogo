package com.example.jetpackcomposecatalogoelementosui.ui.barra_superior_inferior

import android.widget.Toast
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

enum class TypeTopBar {
    SIMPLE,
    CENTER,
    MEDIUM,
    LARGE
}


/**
 * My simple scaffold for top app bar examples
 *
 * @param typeTopBar Indica el tipo de topAppBar que queremos ver en el ejemplo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySampleScaffoldForTopAppBarExamples(
    typeTopBar: TypeTopBar
){
    //CONTEXT
    val context = LocalContext.current

    //PERMITE DEFINIR EL COMPORTAMIENTO DE LA BARRA CUANDO LA PANTALLA SE PUEDE DESPLAZAR, POR EJEMPLO CUANDO USAMOS LAS LISTAS
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            when(typeTopBar){
                TypeTopBar.SIMPLE -> {
                    MyTopAppBarBasic(
                        scrollBehavior = scrollBehavior,
                        onIconClick = {
                            Toast.makeText(context, "Se hizo clic en el icono de herramientas $it", Toast.LENGTH_SHORT).show()
                        },
                        onMenuClick = {
                            Toast.makeText(context, "Se hizo clic en el icono del menú", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                TypeTopBar.CENTER -> {
                    MyCenterAlignedTopAppBar(
                        scrollBehavior = scrollBehavior
                    )
                }
                TypeTopBar.MEDIUM -> {
                    MyMediumAlignedTopAppBar(
                        scrollBehavior = scrollBehavior
                    )
                }
                TypeTopBar.LARGE -> {
                    MyLargeAlignedTopAppBar(
                        scrollBehavior = scrollBehavior
                    )
                }
            }
        },
        modifier = Modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection)
    ) { paddingValues: PaddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
        ) {
            items(200){
                Text(text = "Te amo Darlyn")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationGraphicsApi::class)
@Composable
fun MyTopAppBarBasic(
    scrollBehavior: TopAppBarScrollBehavior,
    onIconClick: (String) -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(text = "TopAppBar Basic")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White
        ),
        navigationIcon = {
/*                IconButton(
                    onClick = {
                        onIconClick("Atrás")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }*/
            IconButton(
                onClick = { onMenuClick() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú"
                )
            }

        },
        actions = {
            IconButton(
                onClick = { onIconClick("Buscar") },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar"
                )
            }
            IconButton(
                onClick = { onIconClick("Favorito") },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos"
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCenterAlignedTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier:Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = {Text(text = "Center TopAppBar Basic") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar"
                )
            }
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos"
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMediumAlignedTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
){
    MediumTopAppBar(
        title = { Text(text = "Middle TopAppBar Basic") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar"
                )
            }
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos"
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyLargeAlignedTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
){
    LargeTopAppBar(
        title = { Text(text = "Large TopAppBar Basic") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar"
                )
            }
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos"
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyTopAppBarBasicExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MySampleScaffoldForTopAppBarExamples(typeTopBar = TypeTopBar.SIMPLE)
        }
    }
}


