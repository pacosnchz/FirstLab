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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    var showMessage by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // 🔹 Determinar color según nivel
    val backgroundColor = when (level) {
        in 0..2 -> colorResource(id = R.color.level_low)
        in 3..6 -> colorResource(id = R.color.level_mid)
        in 7..9 -> colorResource(id = R.color.level_high)
        else -> MaterialTheme.colorScheme.background
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre del jugador
            Text(
                text = "Bienvenido, $username",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Puntuación y nivel
            Text("Puntuación: $score", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text("Nivel: $level", fontSize = 20.sp, modifier = Modifier.padding(bottom = 24.dp))

            // Botón para incrementar puntuación
            Button(
                onClick = {
                    val incremento = Random.nextInt(1, level + 1)
                    score += incremento

                    // Subir de nivel cada 10 puntos
                    if (score >= level * 10) {
                        level++
                    }

                    // Mostrar mensaje al llegar a nivel 5
                    if (level >= 5) {
                        showMessage = true
                    }

                    // Transición a EndGameActivity si llega a nivel 10
                    if (level >= 10) {
                        val intent = Intent(context, EndGameActivity::class.java).apply {
                            putExtra("username", username)
                            putExtra("score", score)
                            putExtra("level", level)
                            putExtra("finishedByLevel10", true)
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text("Incrementar puntuación (+ aleatorio)")
            }

            // Botón para decrementar puntuación
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

            // Botón "End Game"
            Button(
                onClick = {
                    val intent = Intent(context, EndGameActivity::class.java).apply {
                        putExtra("username", username)
                        putExtra("score", score)
                        putExtra("level", level)
                        putExtra("finishedByLevel10", false)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("End Game")
            }

            // Mensaje al alcanzar nivel 5
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
