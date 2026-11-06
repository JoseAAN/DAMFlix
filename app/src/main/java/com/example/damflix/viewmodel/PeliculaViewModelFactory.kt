package com.example.damflix.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.damflix.data.PeliculaRepository
import com.example.damflix.data.local.AppDatabase

class PeliculaViewModelFactory(private val repository: PeliculaRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(PeliculaViewModel::class.java)){
            return PeliculaViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
        }
    }