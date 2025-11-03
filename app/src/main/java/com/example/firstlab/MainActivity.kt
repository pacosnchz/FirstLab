package com.example.firstlab

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    // Variables principales del juego
    var score by remember { mutableStateOf(0) }
    var level by remember { mutableStateOf(1) }
    var showMessage by remember { mutableStateOf(false) }

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

            // 🔹 Nombre del jugador (Actividad 1)
            Text(
                text = "Bienvenido, $username",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // 🔹 Mostrar puntuación y nivel
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

            // 🔹 Botón para incrementar puntuación (Actividad 2)
            Button(
                onClick = {
                    val incremento = Random.nextInt(1, level + 1)
                    score += incremento

                    // Subir de nivel cada 10 puntos
                    if (score >= level * 10) {
                        level++
                    }

                    // Mostrar mensaje al llegar a nivel 5 (Actividad 4)
                    if (level >= 5) {
                        showMessage = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text("Incrementar puntuación (+ aleatorio)")
            }

            // 🔹 Botón para decrementar puntuación (Actividad 3)
            Button(
                onClick = {
                    val decremento = level * 2
                    score = (score - decremento).coerceAtLeast(0)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text("Decrementar puntuación (- doble del nivel)")
            }

            // 🔹 Botón "End Game" (se usará más adelante en la actividad 5)
            // Botón "End Game"
            val context = LocalContext.current
            Button(
                onClick = {
                    val intent = Intent(context, EndGameActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("End Game")
            }


            // 🔹 Mensaje al alcanzar nivel 5 (Actividad 4)
            if (showMessage) {
                Text(
                    text = "¡Vas en buen camino!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}
