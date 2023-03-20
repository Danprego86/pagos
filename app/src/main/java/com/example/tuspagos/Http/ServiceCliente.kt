package com.example.tuspagos.Http

import com.example.tuspagos.DatosGlobales.DatosGlobales
import com.example.tuspagos.Model.AutResponseEntity
import com.example.tuspagos.Model.AutTransaccionEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceCliente {

    //Se utiliza  Interceptor para mostrar los resultados de la peticion
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var global = DatosGlobales()
    //Asociamos el interceptor a las peticiones
  //  private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // Attempting to add headers to every request
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic ${global.keyApi}")
            chain.proceed(request.build())
        }
        .build()

 //   val httpClient = OkHttpClient.Builder()

    //Creacion de Retrofid
     private val retrofid = Retrofit.Builder()
          .baseUrl("http://192.168.3.10:8080") //
          .addConverterFactory(GsonConverterFactory.create())
        .client(client)
          .build()
    //Crear una variable la cual invoca la interfaz
    val service = retrofid.create(APISercice::class.java)


    fun authorization(userData: AutTransaccionEntity, onResult: (AutResponseEntity?) -> Unit){
        service
        service.getAutorizacion(userData).enqueue( object : Callback<AutResponseEntity> {
            override fun onFailure(call: Call<AutResponseEntity>, t: Throwable) {
                onResult(null)
            }                override fun onResponse(
                call: Call<AutResponseEntity>,
                response: Response<AutResponseEntity>
            ) {
                val addedUser = response.body()
                if (addedUser!=null) {
                    onResult(addedUser)
                }
                else
                {
//                    val gson = Gson()
//                    val type = object : TypeToken<AutResponseEntity>() {}.type
//                    var errorResponse: AutResponseEntity? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    onResult(null)
                }
            }
        }
        )
    }

}