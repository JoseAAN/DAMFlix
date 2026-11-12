package com.example.damflix.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDao {

    @Query("SELECT * FROM peliculas ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<PeliculaEntity>>

    @Query("SELECT * FROM peliculas WHERE id = :id")
    suspend fun obtenerPorId(id: Int): PeliculaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(pelicula: PeliculaEntity)

    @Update
    suspend fun actualizar(pelicula: PeliculaEntity)

    @Delete
    suspend fun eliminar(pelicula: PeliculaEntity)
}