package com.example.tuspagos.Http


import com.example.tuspagos.Model.AutResponseEntity
import com.example.tuspagos.Model.AutTransaccionEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APISercice {

    @POST("/api/payments/authorization")
    fun getAutorizacion(@Body AuthRequest: AutTransaccionEntity): Call<AutResponseEntity>
}