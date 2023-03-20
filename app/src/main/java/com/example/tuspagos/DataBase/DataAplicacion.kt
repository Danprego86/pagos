//package com.example.tuspagos.DataBase
//
//import android.app.Application
//
//import androidx.room.Room
//
//
//class DataAplicacion: Application() {
//    companion object{
//        lateinit var dataBase: DataBase
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        dataBase = Room.databaseBuilder(
//            this,
//            DataBase::class.java,
//            "Database")
//            .build()
//    }
//
//
//}
//
//
