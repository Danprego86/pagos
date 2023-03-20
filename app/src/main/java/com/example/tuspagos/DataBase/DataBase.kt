package com.example.tuspagos.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tuspagos.Model.AutResponseEntity
//import com.example.tuspagos.Model.AutTransaccionEntity

@Database(entities = arrayOf(AutResponseEntity::class), version = 1)
abstract class DataBase:RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}