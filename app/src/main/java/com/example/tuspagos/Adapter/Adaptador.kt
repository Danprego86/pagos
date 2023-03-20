package com.example.tuspagos.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tuspagos.Model.AutResponseEntity
import com.example.tuspagos.Model.AutTransaccionEntity
import com.example.tuspagos.R


// A la clase Adaptador se le pasa la lista "AutTransaccion" y se extiende a la clase "ViewHolder"
class Adaptador //(private val reciveLista: List<AutResponseEntity>): RecyclerView.Adapter<ViewHolder>() {

    (private val mList: ArrayList<AutResponseEntity>) :
    RecyclerView.Adapter<Adaptador.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.detallestransaccion, parent, false)
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val ItemsViewModel = mList[position]
            holder.textViewReceiptId.text = ItemsViewModel.id
            holder.textViewRRN.text = ItemsViewModel.rrn
            holder.textViewStatusCode.text = ItemsViewModel.statusCode
            holder.textViewStatusDescription.text = ItemsViewModel.statusDescription
            //(holder as ViewHolder).bind(mList[position],clickListener)
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val textViewReceiptId: TextView
            val textViewRRN: TextView
            val textViewStatusCode: TextView
            val textViewStatusDescription: TextView


            init {
                // Define click listener for the ViewHolder's View.
                textViewReceiptId = itemView.findViewById(R.id.respIdentificador)
                textViewRRN = itemView.findViewById(R.id.resprrn)
                textViewStatusCode = itemView.findViewById(R.id.respStatus)
                textViewStatusDescription = itemView.findViewById(R.id.respdescripcion)
            }
//            fun bind(part: AuthorizationResponse, clickListener: (AuthorizationResponse) -> Unit){
//                itemView.setOnClickListener { clickListener(part)}
//            }

        }

    }