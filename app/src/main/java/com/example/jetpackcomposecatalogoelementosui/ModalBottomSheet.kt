package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//MODAL INFERIOR EN LA PANTALLA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetExampleBasic(
    sheetState: SheetState,
    coroutineScope: CoroutineScope,
    onCloseSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onCloseSheet(false)
        },
        dragHandle = {
            IconButton(
                onClick = {
                    onCloseSheet(false)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Hide Sheet"
                )
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) onCloseSheet(false)
                    }
                }
            ) {
                Text(text = "Hide bottom sheet")
            }
            Spacer(modifier = Modifier.height(height = 16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(50) {
                    ListItem(
                        headlineContent = { Text("Item $it") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetExampleAdvance(
    modifier: Modifier = Modifier,
) {
    //SABER SI SE DEBE MOSTRAR EL SHEET
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    //SELECCIONAR SI QUEREMOS DOS ESTADOS O QUEREMOS EL ESTADO INTERMEDIO
    var skipPartiallyExpanded by remember { mutableStateOf(false) }

    //BORDE A BORDE
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }

    //ÁMBITO DE CORRUTINA
    val scope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

// App content
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.toggleable(
                value = skipPartiallyExpanded,
                role = Role.Checkbox,
                onValueChange = { checked -> skipPartiallyExpanded = checked }
            )
        ) {
            Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("Skip partially expanded State")
        }
        Row(
            Modifier.toggleable(
                value = edgeToEdgeEnabled,
                role = Role.Checkbox,
                onValueChange = { checked -> edgeToEdgeEnabled = checked }
            )
        ) {
            Checkbox(checked = edgeToEdgeEnabled, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("Toggle edge to edge enabled.")
        }

        Button(onClick = { openBottomSheet = !openBottomSheet }) {
            Text(text = "Show Bottom Sheet")
        }
    }

// Sheet content
    if (openBottomSheet) {

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                    // you must additionally handle intended state cleanup, if any.
                    onClick = {
                        scope.launch {
                            bottomSheetState.hide()
                        }
                            .invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                openBottomSheet = false
                            }
                        }
                    }
                ) {
                    Text("Hide Bottom Sheet")
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(50) {
                    ListItem(
                        headlineContent = { Text("Item $it") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }
    }
}