package com.example.tuspagos.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "AutResponseEntity")
data class AutResponseEntity (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("receiptId")
    val id : String,
    @SerializedName("rrn")
    val rrn       : String,
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("statusDescription")
    val statusDescription: String

    )