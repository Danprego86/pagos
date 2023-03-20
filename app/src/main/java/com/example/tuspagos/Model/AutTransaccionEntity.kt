package com.example.tuspagos.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "AutTransaccionEntity")
data class AutTransaccionEntity (
     @PrimaryKey(autoGenerate = false)
     @SerializedName("id")
     val id: String,
     @SerializedName("commerceCode")
     val cu: String,
     @SerializedName("terminalCode")
     val terminal: String,
     @SerializedName("amount")
     val totalPagar: String,
     @SerializedName("card")
     val numTarjeta: String

     )



