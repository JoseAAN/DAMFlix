package com.example.damflix.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CampoEntrada(valor: String, onCambio: (String) -> Unit, etiqueta: String){
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta)},
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}