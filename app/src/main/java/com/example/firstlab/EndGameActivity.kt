package com.example.firstlab

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class EndGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            // Título
            Text(
                text = "Fin del juego",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Información del jugador
            Text("Jugador: $username", fontSize = 20.sp)
            Text("Puntuación final: $score", fontSize = 20.sp)
            Text("Nivel alcanzado: $level", fontSize = 20.sp, modifier = Modifier.padding(bottom = 24.dp))

            // Mensaje personalizado
            if (finishedByLevel10) {
                Text(
                    text = "¡Felicidades, alcanzaste el nivel 10!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            } else {
                Text(
                    text = "Juego terminado. Pulsa el botón para volver a empezar.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            // 🔹 Nueva fila con imagen y botón "Enviar datos..."
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trophy), // tu imagen
                    contentDescription = "Trophy",
                    modifier = Modifier.size(80.dp)
                )

                Button(
                    onClick = {
                        // funcionalidad se implementará en Actividad 9
                    },
                    modifier = Modifier.height(48.dp)
                ) {
                    Text("Enviar datos...")
                }
            }

            // Botón para volver al inicio
            Button(
                onClick = {
                    val intent = Intent(context, LauncherActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text("Volver a iniciar")
            }
        }
    }
}
