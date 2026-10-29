package com.example.damflix.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.damflix.data.PeliculaRepository
import com.example.damflix.data.DAMFlixDbHelper

//Generado con IA
class PeliculaViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(PeliculaViewModel::class.java)){
            val dbHelper = DAMFlixDbHelper(context)
            val repository = PeliculaRepository(dbHelper)
            @Suppress("UNCHECKED_CAST")
            return PeliculaViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
        }
    }