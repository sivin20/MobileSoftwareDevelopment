package com.example.myfirstapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movies WHERE uid IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<Movie>

    @Query("SELECT * FROM movies WHERE title LIKE :first AND " +
            "overview LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Movie

    @Insert
    fun insertAll(vararg movies: Movie)

    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}