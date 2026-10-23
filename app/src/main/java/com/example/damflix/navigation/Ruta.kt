package com.example.damflix.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Ruta (val path: String, val label: String, val icon: ImageVector){
    object Perfil: Ruta(
        path = "perfil",
        label = "Perfil",
        icon = Icons.Filled.AccountCircle
    )
    object Listado: Ruta(
        path = "listado",
        label = "Mis Películas",
        icon = Icons.AutoMirrored.Filled.List
    )

    object Formulario: Ruta(
        path = "alta",
        label = "Añadir",
        icon = Icons.Filled.Add
    )
}