package com.example.damflix.viewmodel
import com.example.damflix.data.local.PeliculaEntity

sealed interface DetalleUiState {
    object Cargando : DetalleUiState
    data class Exito(val peliculaEntity: PeliculaEntity) : DetalleUiState
    data class Error(val mensaje: String) : DetalleUiState
}
