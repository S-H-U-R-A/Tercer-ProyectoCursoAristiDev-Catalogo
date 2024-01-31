package com.example.jetpackcomposecatalogoelementosui

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldExample(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
){
    //CONTEXTO DE EN DONDE ESTOY
    val context: Context = LocalContext.current

    //ÁMBITO DE CORRUTINA
    val coroutineScope = rememberCoroutineScope()

    //MODAL BOTTOM SHEET
    val sheetState: SheetState = rememberModalBottomSheetState( skipPartiallyExpanded = false )
    var showBottomSheet by remember {
        mutableStateOf(value = false)
    }

    //SNACKBAR
    val snackBarHostState = remember { SnackbarHostState() }

    //TOPAPPBAR
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            MyTopAppBarBasic(
                scrollBehavior = scrollBehavior,
                onIconClick = { text: String ->
                    coroutineScope.launch {
                        val snackBarResult: SnackbarResult = snackBarHostState
                                .showSnackbar(
                                    message = text,
                                    actionLabel = "Ir",
                                    duration = SnackbarDuration.Long
                                )
                        when (snackBarResult) {
                            SnackbarResult.ActionPerformed -> {
                                Toast.makeText(
                                    context,
                                    "Se confirmo el SnackBar",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            SnackbarResult.Dismissed -> {
                                Toast.makeText(
                                    context,
                                    "Se oculto el SnackBar",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                },
                onMenuClick = onMenuClick
            )
        },
        bottomBar = {
            MyNavigationBarExample()
            //MyCustomBottomAppBar()
        },
        floatingActionButton = {
            MyFloatActionButtonExample(
                onFABClick = {
                    showBottomSheet = true
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues = it)
                .height(2000.dp)
                .fillMaxWidth()
                .background(color = Color.Gray)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                color = Color.Blue,
                text = "Hola solo existo para pruebas"
            )
        }

        if(showBottomSheet){ //La creación es más rapida con un condicional
            MyBottomSheetExampleBasic(
                sheetState = sheetState,
                coroutineScope = coroutineScope,
                onCloseSheet = { isShowSheet: Boolean ->
                    showBottomSheet = isShowSheet
                }
            )
        }
    }
}


@Composable
fun MyFloatActionButtonExample(
    onFABClick: () -> Unit,
){
    FloatingActionButton(
        onClick = {
            onFABClick()
        },
        containerColor = Color.Yellow,
        contentColor = Color.Black
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar"
        )
    }
}

@Composable
fun MyNavigationBarExample(
    modifier: Modifier = Modifier
){
    var index: Int by remember {
        mutableStateOf(value = 0)
    }

    NavigationBar(
        containerColor = Color.Red,
        contentColor = Color.White
    ) {

        val colorsActive: NavigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Red,
            selectedIconColor = Color.Blue
        )

        NavigationBarItem(
            selected = (index == 0) ,
            onClick = {
                      index = 0
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                )
            },
            label = {
                Text(text = "Home")
            },
            colors = colorsActive,
        )
        NavigationBarItem(
            selected = (index == 1),
            onClick = {
                      index = 1
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                )
            },
            label = {
                Text(text = "Favorite")
            },
            colors = colorsActive
        )
        NavigationBarItem(
            selected = (index == 2),
            onClick = {
                      index = 2
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person"
                )
            },
            label = {
                Text(text = "Person")
            },
            colors = colorsActive
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomSnackBarExample(
    modifier: Modifier = Modifier
){
    class SnackBarVisualsWithError(
        override val message: String,
        val isError: Boolean,
    ) : SnackbarVisuals {
        override val actionLabel: String
            get() = if (isError) "Error" else "OK"
        override val withDismissAction: Boolean
            get() = false
        override val duration: SnackbarDuration
            get() = SnackbarDuration.Indefinite
    }

    //SCOPE
    val coroutineScope = rememberCoroutineScope()

    //SNACKBAR
    val snackBarHostState = remember { SnackbarHostState() }

    //TOPAPPBAR
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState){ snackBarData: SnackbarData ->

                val isError = (snackBarData.visuals as? SnackBarVisualsWithError)?.isError ?: false

                val buttonColor: ButtonColors = if (isError) {
                    ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error
                    )
                } else {
                    ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.inversePrimary
                    )
                }

                Snackbar(
                    action = {
                        TextButton(
                            onClick = { if (isError) snackBarData.dismiss() else snackBarData.performAction() },
                            colors = buttonColor
                        ) { Text(snackBarData.visuals.actionLabel ?: "") }
                    },
                    dismissAction = {
                          IconButton(onClick = { snackBarData.dismiss() } ) {
                              Icon(
                                  imageVector = Icons.Filled.Close,
                                  contentDescription = "Cerrar"
                              )
                          }
                    },
                    modifier = Modifier
                        .border(2.dp, MaterialTheme.colorScheme.secondary)
                        .padding(12.dp),
                ) {
                    Row {
                        Text(snackBarData.visuals.message)
                    }
                }
            }
        },
        topBar = {
            MyTopAppBarBasic(
                scrollBehavior = scrollBehavior,
                onIconClick = { text: String ->
                    coroutineScope.launch {
                        val snackBarResult: SnackbarResult = snackBarHostState
                                .showSnackbar(
                                    SnackBarVisualsWithError(
                                        message = "$text Prueba de SnackBar personalizado",
                                        isError = true
                                    )
                                )
                        when(snackBarResult){
                           SnackbarResult.ActionPerformed -> {}
                           SnackbarResult.Dismissed -> {}
                        }
                    }
                },
                onMenuClick = {}
            )
        },
        modifier = modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection)
    ) {
        Box(
            modifier = Modifier
                .padding(paddingValues = it)
                .height(2000.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                color = Color.White,
                text = "Hola solo existo para pruebas"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
            containerColor = Color.Red,
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
                onClick = {
                    onMenuClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú"
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
fun MyCustomAlignTopAppBarBasic(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
){
    /*CenterAlignedTopAppBar(
        title = {
            Text(text = "TopAppBar Basic")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
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
    )*/

    /*MediumTopAppBar(
        title = {
            Text(text = "TopAppBar Custom Medium")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
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
    )*/

    LargeTopAppBar(
        title = {
            Text(text = "TopAppBar Custom Large")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
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

@Composable
fun MyCustomBottomAppBar(
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        containerColor = Color.Red
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Account"
            )
        }
        FloatingActionButton(
            onClick = { },
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check"
            )
        }
    }

    /*BottomAppBar(
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color.Red,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check"
                )
            }
        }
    )*/

}

















@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyScaffoldPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyScaffoldExample(
                onMenuClick = {}
            )
        }
    }
}