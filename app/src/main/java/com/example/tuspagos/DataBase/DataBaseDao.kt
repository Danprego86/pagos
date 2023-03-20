package com.example.tuspagos.DataBase

import androidx.room.*
import com.example.tuspagos.Model.AutResponseEntity


@Dao
interface DataBaseDao {
    @Query("SELECT * FROM AutResponseEntity")
    fun getTransaccion(): MutableList<AutResponseEntity>


    @Query("SELECT * FROM AutResponseEntity WHERE id like:data or rrn like:data ")
    fun getTransaccionFilter(data: String): List<AutResponseEntity>

    @Insert
    fun addTransaccion(autResponseEntity: AutResponseEntity)

    @Update
    fun updateTransaccion(autResponseEntity: AutResponseEntity)

    @Delete
    fun deletetransaccion(autResponseEntity: AutResponseEntity)
}