package com.yoel.pruebacorrutinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background // Importar para el fondo
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Importar para usar Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoel.pruebacorrutinas.ui.theme.PruebaCorrutinasTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaCorrutinasTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFd88aff))) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            coroutineScope.launch {
                currentTime = getCurrentTime()
                // Cada segundo actualiza el tiempo en pantalla llamando a la funcion.
                delay(1000)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Hora actual:", fontSize = 34.sp, fontWeight = FontWeight.Bold)
        Text(text = currentTime, fontSize = 62.sp, fontWeight = FontWeight.Bold)
    }
}

fun getCurrentTime(): String {
    val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return format.format(Date())
}
