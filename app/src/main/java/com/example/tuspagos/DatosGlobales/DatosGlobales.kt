package com.example.tuspagos.DatosGlobales

import android.util.Base64

class DatosGlobales {

    var codigoComercio:String="000123"
    var terminal :String="000ABC"
    var numeroTarjeta:String="1234567890123456"
    var keyApi:String= (Base64.encodeToString((codigoComercio+terminal).toByteArray(), Base64.DEFAULT)).replace("\n","")

}