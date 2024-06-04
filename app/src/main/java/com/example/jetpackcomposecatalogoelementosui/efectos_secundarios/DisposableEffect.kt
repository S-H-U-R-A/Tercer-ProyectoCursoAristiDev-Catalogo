package com.example.jetpackcomposecatalogoelementosui.efectos_secundarios

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyDisposableEffectExampleBasic(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit ={},
    onStop: () -> Unit = {}
){
    val bloqueOnStart by rememberUpdatedState(newValue = onStart)//GUADAR DE FORMA SEGURA EL BLOQUE DE LAS LAMBDAS PARA QUE LAS RECOMPOSICIONES NO LOS HAGA EJECUTARSE CADA VEZ
    val bloqueOnStop by rememberUpdatedState(newValue = onStop)

    DisposableEffect(key1 = lifecycleOwner){//ESTE COMPOSABLE SE DISPARARÁ CUANDO EL ESTADO DEL CICLO DE VIDA CAMBIE

        val observer: LifecycleObserver = LifecycleEventObserver{ _, event ->//CREAMOS UN OBSERVADOR DEL CICLO DE VIDA
            if(event == Lifecycle.Event.ON_START){
                Log.d("LifeCycle", "ON START")
                bloqueOnStart()
            }else{
                Log.d("LifeCycle", "ON STOP")
                bloqueOnStop()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)//LO AÑADIMOS AL CICLO DE VIDA DE LA APP

        this.onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)//LIBERAMOS EL OBSERVADOR
        }
    }

    Box (
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Disposable Effect")
    }

}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyDisposableEffectExamples(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyDisposableEffectExampleBasic()
        }
    }
}