package com.example.damflix.ui.screens

import android.widget.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.damflix.model.Pelicula
import com.example.damflix.viewmodel.DetallePeliculaViewModel
import com.example.damflix.viewmodel.DetalleUiState

@Composable
fun PantallaDetalleScreen(
    peliculaId: Int,
    viewModel: DetallePeliculaViewModel,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(peliculaId) {
        viewModel.cargarDetallePelicula(peliculaId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onVolver) {
            Text(text = "<- Volver al Catálogo")
        }

        Spacer(modifier = Modifier.height(16.dp))


        when (val state = uiState) {
            is DetalleUiState.Cargando -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Cargando datos de Room en segundo plano")
                    }
                }
            }

            is DetalleUiState.Exito -> {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier.fillMaxWidth()) {
                        Text(
                            text = state.component1().titulo,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Director/a: ${state.component1().director}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Año de estreno: ${state.component1().anio}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = if (state.component1().esVista) "Estado: Ya vista" else "Estado: Pendiente de ver",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            is DetalleUiState.Error -> {
                Text(
                    text = state.mensaje,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}