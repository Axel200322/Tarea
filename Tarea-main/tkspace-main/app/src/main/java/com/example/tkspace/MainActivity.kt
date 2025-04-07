package com.example.tkspace

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tkspace.ui.theme.TkspaceTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TkspaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    TaskForm()
                }
            }
        }
    }
}

@Composable
fun TaskForm() {
    var titulo by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("Alta") }
    var descripcion by remember { mutableStateOf("") }
    var materia by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "AGREGAR UNA TAREA",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFB39DDB),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        DropdownMenuPrioridad(selectedPrioridad = prioridad) { prioridad = it }

        Text(
            "¡Recuerda priorizar su importancia!",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción de la Tarea") },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = materia,
            onValueChange = { materia = it },
            label = { Text("Materia") },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "DD/MM/AA 10:00:00",
            onValueChange = {},
            label = { Text("Fecha y hora") },
            readOnly = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        var checked by remember { mutableStateOf(false) }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = {  checked = it})
            Column {
                Text("Enviar notificaciones", fontWeight = FontWeight.Medium)
                Text("Te notificaremos antes de la fecha de entrega", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* Acción para guardar tarea */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Guardar Tarea", color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Add, contentDescription = "Guardar", tint = Color.Black)
        }
    }
}

@Composable
fun DropdownMenuPrioridad(selectedPrioridad: String, onPrioridadSelected: (String) -> Unit) {
    val prioridades = listOf("Alta", "Media", "Baja")
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedPrioridad,
            onValueChange = {},
            label = { Text("Prioridad") },
            readOnly = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            prioridades.forEach { prioridad ->
                DropdownMenuItem(
                    text = { Text(prioridad) },
                    onClick = {
                        onPrioridadSelected(prioridad)
                        expanded = false
                    }
                )
            }
        }
    }
}
