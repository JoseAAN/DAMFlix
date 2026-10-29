package com.example.damflix.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.damflix.R
import com.example.damflix.ui.components.CampoEntrada
import com.example.damflix.viewmodel.PeliculaViewModel



@Composable
fun FormularioPelicula(
    viewModel: PeliculaViewModel,
    onPeliculaGuardada: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anioTexto by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }

    val esFormularioValido =
        titulo.isNotBlank() && director.isNotBlank() && anioTexto.toIntOrNull() != null

    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize()) {
        CampoEntrada(
            valor = titulo,
            onCambio = { titulo = it },
            etiqueta = "Titulo de la Película",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        CampoEntrada(
            valor = director,
            onCambio = { director = it },
            etiqueta = "Director/a",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        CampoEntrada(
            valor = anioTexto,
            onCambio = { anioTexto = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            etiqueta = "Año de estreno",
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Checkbox(
                checked = esVista,
                onCheckedChange = { esVista = it }
            )
            Text("Ya he visto esta película")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val anio = anioTexto.toIntOrNull() ?:0
                viewModel.agregarPeliculas(
                    titulo = titulo,
                    director = director,
                    anio = anio,
                    esVista = esVista
                )
                titulo = ""
                director = ""
                anioTexto = ""
                esVista = false
                onPeliculaGuardada()
            },
            enabled = esFormularioValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.btn_accept))
        }
    }
}
