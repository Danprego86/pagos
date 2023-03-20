package com.example.tuspagos.View

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuspagos.Adapter.Adaptador
import com.example.tuspagos.DataBase.DataAplicacion
//import com.example.tuspagos.DataBase.DataBase
//import com.example.tuspagos.DataBase.DataBaseDao
import com.example.tuspagos.Model.AutResponseEntity
import com.example.tuspagos.databinding.ListadotxBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Detallestransaccion : AppCompatActivity() {

    private lateinit var binding: ListadotxBinding
    var listTrx: ArrayList<AutResponseEntity> = ArrayList<AutResponseEntity>()
    lateinit var myListAdapter: Adaptador
    private lateinit var bookDao: DataAplicacion.Companion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListadotxBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /* val dataBase = Room.databaseBuilder(
             this,
             DataBase::class.java,
             "Database"
         )
             .build()
         bookDao = dataBase.dataBaseDao()*/



        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        myListAdapter = Adaptador(listTrx)
        binding.recyclerview.adapter = myListAdapter



        testDB("")
        binding.FilterTransactions.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                testDB(s.toString())
                val gson = Gson()
                Log.i("MyTAG", "*****   ${gson.toJson(listTrx)} books there *****")
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun testDB(data: String) {

        val myArrayList: List<AutResponseEntity> = emptyList()
        lifecycleScope.launch(Dispatchers.IO) {

            val gson = Gson()
            val myArrayList =
                ArrayList(bookDao.dataBase.dataBaseDao().getTransaccionFilter("%" + data + "%"))
            Log.i("MyTAG", "*****   ${gson.toJson(myArrayList)} *****")

            launch(Dispatchers.Main.immediate) {

                listTrx.clear()
                listTrx.addAll(myArrayList)
                myListAdapter.notifyDataSetChanged()
            }
        }
    }
}