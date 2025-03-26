package com.example.laboratorio02

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio02.ui.theme.Blue
import com.example.laboratorio02.ui.theme.Laboratorio02Theme
import com.example.laboratorio02.ui.theme.LightBlue
import com.example.laboratorio02.ui.theme.OtherBlue
import com.example.laboratorio02.ui.theme.Red

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Home(modifier: Modifier = Modifier) {
    val estadoBotones: MutableState<Boolean> = remember { mutableStateOf(true) }
    val mostrarDialog: MutableState<Boolean> = remember { mutableStateOf(false) }

    if (mostrarDialog.value) {
        CustomAlertDialog(mostrarDialog, estadoBotones)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Blue)
    ) {
        Pulsaciones(estadoBotones.value)
        Nombres(estadoBotones.value)
        CustomButton(
            if (estadoBotones.value) "Inhabilitar botones" else "Habilitar botones"
        ) {
            mostrarDialog.value = true
        }
    }
}

@Composable
fun Pulsaciones(estadoBoton: Boolean) {
    val contadotClics: MutableState<Int> = remember { mutableStateOf(0) }
    var listaClics: MutableList<String> = remember { mutableListOf<String>() }

    Title(
        title = "Se ha pulsado el botón ${contadotClics.value}",
    )
    CustomButton("Aumentar contador", estadoBoton) {
        contadotClics.value++
        listaClics.add("Se ha pulsado el botón ${contadotClics.value}")
    }
    Title(
        title = "Pulsaciones",
    )
    LazyColumn(
        Modifier
            .padding(horizontal = 10.dp)
            .border(1.dp, Color.White)
            .fillMaxWidth()
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(listaClics) {
            Text(text = it, color = Color.White)
        }
    }
}

@Composable
fun Nombres(estadoBotones: Boolean) {
    val contadorNombres: MutableState<Int> = remember { mutableStateOf(0) }
    var listaNombres: MutableList<String> = remember { mutableListOf<String>() }
    val nombre: MutableState<String> = remember { mutableStateOf("") }

    Title(
        title = "Añadir nombres",
    )
    TextField(
        value = nombre.value,
        onValueChange = {
            nombre.value = it
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            cursorColor = Color.White,
        )
    )
    CustomButton("Añadir nombre", estadoBotones) {
        listaNombres.add("Posicion ${contadorNombres.value + 1}: ${nombre.value}")
        nombre.value = ""
        contadorNombres.value++
    }
    Title(
        title = "Nombres",
    )
    LazyRow(
        Modifier
            .padding(horizontal = 16.dp)
            .border(1.dp, Color.White)
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(listaNombres) {
            Text(text = "$it -", color = Color.White)
        }
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        color = Color.White,
        modifier = modifier.padding(16.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun CustomButton(text: String, estadoBoton: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlue,
            disabledContainerColor = Color.Gray
        ),
        enabled = estadoBoton
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(mostrarDialog: MutableState<Boolean>, estadoBotones: MutableState<Boolean>) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f))
    ) {

    }
    AlertDialog(
        onDismissRequest = {},
        modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(OtherBlue, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dialog_alert),
                contentDescription = "Alerta"
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Confirmación", color = Color.White, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "¿Desea ${if (estadoBotones.value) "inhabilitar" else "habilitar"} los botones?",
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = { mostrarDialog.value = false }, colors = ButtonDefaults.buttonColors(
                        Red
                    )
                ) {
                    Text(text = "Cancelar")
                }
                Button(onClick = {
                    estadoBotones.value = !estadoBotones.value
                    mostrarDialog.value = false
                }, colors = ButtonDefaults.buttonColors(LightBlue)) {
                    Text(text = "Confirmar")
                }
            }
        }
    }
}