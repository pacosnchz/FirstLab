package com.example.foothub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SobreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SobrePantalla {
                enviarCorreo()
            }
        }
    }

    private fun enviarCorreo() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") //)pacosnchz@icloud.com")  // el correo original es: developer@foothub.com
            putExtra(Intent.EXTRA_EMAIL, arrayOf("pacosnchz@icloud.com", "developer@foothub.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Información sobre FootHub")
            putExtra(Intent.EXTRA_TEXT, "Hola, quiero saber más información sobre FootHub.")
        }
        startActivity(intent)
    }
}

@Composable
fun SobrePantalla(onMailClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {

        // Título principal
        Text(
            text = "FootHub",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Temática",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Repositorio de fútbol con API-Football",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "App para consultar datos de equipos, jugadores y ligas.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Versión",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "1.0",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Icono centrado bonito
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                IconButton(
                    onClick = { onMailClick() },
                    modifier = Modifier.size(72.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mail),
                        contentDescription = "Enviar correo",
                        tint = Color(0xFFFF7A1F),
                        modifier = Modifier.size(60.dp)
                    )
                }

                Text(
                    text = "Contactar por correo",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


