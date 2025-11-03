package com.example.firstlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Text("Fin del juego", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("Jugador: $username")
            Text("Puntuación: $score")
            Text("Nivel alcanzado: $level")
            if (finishedByLevel10) {
                Text("Llegaste al nivel 10 🎉")
            } else {
                Text("Finalizaste el juego manualmente.")
            }
        }
    }
}
