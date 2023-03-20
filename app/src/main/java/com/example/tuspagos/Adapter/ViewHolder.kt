package com.example.tuspagos.Adapter

import android.view.View
//import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tuspagos.Model.AutResponseEntity
//import com.example.tuspagos.Model.AutTransaccionEntity
//import com.example.tuspagos.R
import com.example.tuspagos.databinding.DetallestransaccionBinding


//Indicamos a la clase que recibira un vista y extendera de "RecycleView" la vista que recibira
class ViewHolder(view:View): RecyclerView.ViewHolder(view) {

     val binding = DetallestransaccionBinding.bind(view)

    //Crear una variable para pasarle a la vista la busqueda por TextView del Layout "delallestransacciones"
    //val mostrarId = view.findViewById<TextView>(R.id.respIdentificador)
    //val mostrarCu = view.findViewById<TextView>(R.id.respCodigoComercio)
    //val mostrarTer = view.findViewById<TextView>(R.id.respTerminal)
    //val mostrarVal = view.findViewById<TextView>(R.id.respValor)
    //val mostrarNumTar = view.findViewById<TextView>(R.id.respNumeroTarjeta)


//Luego de crear las variables para las vistas que necesitamos lo renderizamos para que valide todas la variables
    fun render (autTransaccionEntityModel : AutResponseEntity){

        binding.respIdentificador.text = autTransaccionEntityModel.id
        binding.resprrn.text =  autTransaccionEntityModel.rrn
        binding.respStatus.text = autTransaccionEntityModel.statusCode
        binding.respdescripcion.text = autTransaccionEntityModel.statusDescription
        //binding.respNumeroTarjeta.text = autTransaccionEntityModel.numTarjeta


    }
}