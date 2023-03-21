package com.example.tuspagos.Http

import com.example.tuspagos.DatosGlobales.DatosGlobales
import com.example.tuspagos.Model.AutResponseEntity
import com.example.tuspagos.Model.AutTransaccionEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceCliente {

    //Se utiliza  Interceptor para mostrar los resultados de la peticion
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var global = DatosGlobales()

    //Asociamos el interceptor a las peticiones
    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

        // Anadimos las cabeceras en la solicitud
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic ${global.keyApi}")
            chain.proceed(request.build())
        }
        .build()


    //Creacion de Retrofit
    private val retrofid = Retrofit.Builder()
        .baseUrl("http://192.168.3.10:8080") //
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    //Crear una variable la cual invoca la interfaz
    val service = retrofid.create(APISercice::class.java)

    //Funcion que se llama desde la MainActivity
    fun authorization(userData: AutTransaccionEntity, onResult: (AutResponseEntity?) -> Unit) {

        service.getAutorizacion(userData).enqueue(object : Callback<AutResponseEntity> {
            override fun onFailure(call: Call<AutResponseEntity>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(
                call: Call<AutResponseEntity>,
                response: Response<AutResponseEntity>
            ) {
                val addedUser = response.body()
                if (addedUser != null) {
                    onResult(addedUser)
                } else {
                    onResult(null)
                }
            }
        }
        )
    }

}