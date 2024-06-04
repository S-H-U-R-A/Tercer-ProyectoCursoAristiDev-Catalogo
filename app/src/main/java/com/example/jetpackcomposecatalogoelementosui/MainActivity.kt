package com.example.jetpackcomposecatalogoelementosui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.MyWindowInsetExampleImeAndList
import com.example.jetpackcomposecatalogoelementosui.ui.MyWindowInsetsExampleGetInsets
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge(//DE ESTA FORMA ESTAMOS HABILITANDO EL BORDE A BORDE PARA LA APP
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim =  Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim =  Color.Transparent.toArgb()
            )
        )

        super.onCreate(savedInstanceState)

        setContent {
            JetPackComposeCatalogoElementosUiTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyWindowInsetExampleImeAndList()
/*                    val navHostController: NavHostController = rememberNavController()

                    NavHost(
                        navController = navHostController,
                        startDestination = "pantalla1"
                    ){
                        composable(
                            route = "pantalla1"
                        ){
                            Screen1()
                        }
                        composable(
                            route = "pantalla2"
                        ){
                            Screen2()
                        }
                        composable(
                            route = "pantalla2"
                        ){
                            Screen3()
                        }
                    }*/

                }
            }
        }
    }
}

@Preview(
    name = "Insets",
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize().border(width = 3.dp, color = Color.Red)
        ) {
            MyWindowInsetsExampleGetInsets()
        }
    }
}
