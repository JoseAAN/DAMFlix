package com.example.damflix.data

import com.example.damflix.data.local.PeliculaDao
import com.example.damflix.data.local.PeliculaEntity
import kotlinx.coroutines.flow.Flow
class PeliculaRepository(private val dao: PeliculaDao) {

    val todasPeliculas: Flow<List<PeliculaEntity>> = dao.obtenerTodas()
    suspend fun obtenerPorId(id: Int): PeliculaEntity? {
        return dao.obtenerPorId(id)
    }
    suspend fun insertar(pelicula: PeliculaEntity){
        dao.insertar(pelicula)
    }

    suspend fun actualizar(pelicula: PeliculaEntity){
        dao.actualizar(pelicula)
    }
}