package com.example.firstlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recibir el nombre del usuario desde LauncherActivity
        val username = intent.getStringExtra("username") ?: "Jugador"

        setContent {
            MainScreen(username)
        }
    }
}

@Composable
fun MainScreen(username: String) {
    var score by remember { mutableStateOf(0) }
    var level by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre del usuario
            Text(
                text = "Bienvenido, $username",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Mostrar puntuación y nivel
            Text(
                text = "Puntuación: $score",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Nivel: $level",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Botón para incrementar puntuación
            Button(
                onClick = {
                    val incremento = Random.nextInt(1, level + 1)
                    score += incremento
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Incrementar puntuación (+ aleatorio)")
            }
        }
    }
}
