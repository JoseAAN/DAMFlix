package com.example.damflix.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.damflix.ui.components.CampoEntrada


@Composable
fun FormularioPelicula(modifier: Modifier = Modifier) {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        CampoEntrada(
            valor = titulo,
            onCambio = { titulo = it },
            etiqueta = "Titulo de la Película",
        )

        Spacer(modifier = Modifier.height(8.dp))

        CampoEntrada(
            valor = director,
            onCambio = { director = it },
            etiqueta = "Director/a"
        )

        Spacer(modifier = Modifier.height(8.dp))

        CampoEntrada(
            valor = anio,
            onCambio = { anio = it },
            etiqueta = "Año de estreno"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = esVista,
                onCheckedChange = { esVista = it}
            )
            Text("Ya he visto esta película")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {"Por Implementar"},
            enabled = titulo.isNotBlank() && director.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Película")
        }
    }
}
