package com.example.appprueba


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appprueba.ui.theme.AppPruebaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            app()
        }
    }
}


@Preview
@Composable
fun app() {
    AppPruebaTheme {
        //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting("Android", Modifier.padding(16.dp))
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var contador by rememberSaveable{ mutableIntStateOf(value = 0) }

    LazyColumn(
        modifier = modifier
            .background(shape = CircleShape, color = Color.Yellow)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(painterResource(R.drawable.cerezo), contentDescription = "Imagen Cerezo")
            //Text("Hello $name!")
            Row {
                Image(
                    painter = painterResource(R.drawable.gilipollas),
                    contentDescription = "gilipollas de turno",
                    modifier = Modifier.clickable{
                        contador++
                    }

                )

                Text(text = contador.toString(), color = Color.LightGray, modifier = modifier.padding(start = 4.dp))
            }
            Text(
                text = "Hola",
                fontSize = 32.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
            Text("DAM")
            Text("Prueba")

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Primero")
                Text("Segundo")
                Text("Tercero")
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppPruebaTheme {
        Greeting("Android")
    }
}