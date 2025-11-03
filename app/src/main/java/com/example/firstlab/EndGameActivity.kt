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

class EndGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datos recibidos del Intent
        val username = intent.getStringExtra("username") ?: "Jugador"
        val score = intent.getIntExtra("score", 0)
        val level = intent.getIntExtra("level", 1)
        val finishedByLevel10 = intent.getBooleanExtra("finishedByLevel10", false)

        setContent {
            EndGameScreen(username, score, level, finishedByLevel10)
        }
    }
}

@Composable
fun EndGameScreen(username: String, score: Int, level: Int, finishedByLevel10: Boolean) {
    val context = LocalContext.current

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
            Text(
                text = "Fin del juego",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Mostrar puntuación y nivel
            Text("Jugador: $username", fontSize = 20.sp)
            Text("Puntuación final: $score", fontSize = 20.sp)
            Text("Nivel alcanzado: $level", fontSize = 20.sp, modifier = Modifier.padding(bottom = 24.dp))

            // ✅ Mensaje personalizado según cómo llegó
            if (finishedByLevel10) {
                Text(
                    text = "¡Felicidades, alcanzaste el nivel 10!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            } else {
                Text(
                    text = "Juego terminado. Pulsa el botón para volver a empezar.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }

            // 🔁 Botón para volver al inicio (opcional)
            Button(onClick = {
                val intent = Intent(context, LauncherActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Volver a iniciar")
            }
        }
    }
}
