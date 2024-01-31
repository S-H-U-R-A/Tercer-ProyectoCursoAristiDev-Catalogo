package com.example.jetpackcomposecatalogoelementosui

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldBottomSheetExample(
    modifier: Modifier = Modifier
){
    val context: Context = LocalContext.current

    //TOPAPPBAR
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()

    val coroutineScope = rememberCoroutineScope()

    val scaffoldBottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = false
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldBottomSheetState,
        sheetPeekHeight = 256.dp,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Swipe up to expand sheet")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sheet content")
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldBottomSheetState.bottomSheetState.partialExpand()
                        }
                    }
                ) {
                    Text("Click to collapse sheet")
                }
            }
        },
        topBar = {
            MyTopAppBarBasic(
                scrollBehavior = scrollBehavior,
                onIconClick = { text: String -> },
                onMenuClick = {  }
            )
        },
        sheetShape = CutCornerShape(
            topStartPercent = 10,
            topEndPercent = 10
        ),
        sheetContainerColor = Color.Red,
        sheetContentColor = Color.White,
        sheetShadowElevation = 32.dp,
    ) { innerPadding: PaddingValues ->
        Box(
            Modifier
                .padding(innerPadding)
        ) {
            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Click",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("Scaffold Content")
            }

        }
    }
}





@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyScaffoldBottomSheetExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyScaffoldBottomSheetExample()
        }
    }
}