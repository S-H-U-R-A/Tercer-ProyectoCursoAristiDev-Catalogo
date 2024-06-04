package com.example.jetpackcomposecatalogoelementosui.efectos_secundarios

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@Composable
fun MyProduceStateExample()  {
    //CREAMOS UN FLUJO DE DATOS DE EJEMPLO, CLARAMENTE LA INFO PODRÍA VENIR DESDE UN VIEWMODEL EN UNA CONSULTA DE RED
    val countFlow: Flow<Int> = flow {
        for (i in 1..20) {
            delay(2000) //SIMULAMOS UN RETRASO DE 1 SEGUNDO ANTES DE EMITIR EL RESULTADO
            emit(i)
        }
    }

    //USAMOS PRODUCE STATE PARA CONVERTIR EL FLUJO EN UN ESTADO QUÉ PUEDE USAR COMPOSE
    val countState by produceState(initialValue = 0) {
        countFlow.collect { value ->//CONSUMIMOS O RECOLECTAMOS EL FLUJO
            this.value = value//POR CADA ELEMENTO QUE EMITA EL FLUJO SE CAMBIARÁ EL VALOR DEL ESTADO Y GENERAMOS UNA RECOMPOSICIÓN
        }
    }

    //AHORA COUN STATE SE PUEDE USAR COMO SI FUERA UN ESTADO
    Text("Contador: $countState")
}


@Preview(
    name = "Ejemplo de Producir un estado a partir de un flujo",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyProduceStateExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyProduceStateExample()
        }
    }
}