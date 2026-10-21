package com.example.damflix.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.damflix.model.Pelicula

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogoDAMFlix() {
    val peliculas = remember {
        mutableStateListOf(
            Pelicula(1, "Star Wars", false),
            Pelicula(2, "El Señor de los Anillos", true),
            Pelicula(3, "Pulp Fiction", false)
        )
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("DAMFlix - Catálogo") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ListaPeliculas(
                peliculas = peliculas,
                onTogglrFavorito = { id ->
                    val index = peliculas.indexOfFirst { it.id == id }
                    if (index != -1) {
                        peliculas[index] =
                            peliculas[index].copy(esFavorita = !peliculas[index].esFavorita)
                    }
                }
            )
        }
    }
}

@Composable
fun ListaPeliculas(peliculas:  List<Pelicula>, onTogglrFavorito: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = peliculas, key = { p -> p.id}) { pelicula ->
            Card(modifier = Modifier.fillMaxWidth()){
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = pelicula.titulo, modifier = Modifier.weight(1f))

                    IconButton(onClick = { onTogglrFavorito(pelicula.id)}) {
                        Icon(
                            imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (pelicula.esFavorita) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}