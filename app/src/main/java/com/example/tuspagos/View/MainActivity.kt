package com.example.tuspagos.View

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
//import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
//import com.example.tuspagos.DataBase.DataAplicacion
import com.example.tuspagos.DataBase.DataBase
import com.example.tuspagos.DataBase.DataBaseDao
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.tuspagos.Adapter.Adaptador
import com.example.tuspagos.DatosGlobales.DatosGlobales
import com.example.tuspagos.Http.ServiceCliente
import com.example.tuspagos.Model.AutResponseEntity
import com.example.tuspagos.Model.AutTransaccionEntity
//import com.example.tuspagos.Model.proporcionador
import com.example.tuspagos.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import com.example.tuspagos.databinding.DetallestransaccionBinding
//import com.example.tuspagos.databinding.ListadotxBinding

import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bookDao: DataBaseDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datos = DatosGlobales()//Exporte de datos previamente ingresados
        val Id = UUID.randomUUID()//Numero random para ID

        binding.id.setText(Id.toString())
        binding.cu.setText(datos.codigoComercio)
        binding.terminal.setText(datos.terminal)
        binding.totalPagar.addTextChangedListener(textWatcher)
        binding.numTarjeta.setText(datos.numeroTarjeta)

        val dataBase = Room.databaseBuilder(
            this,
            DataBase::class.java,
            "Database"
        )
            .build()
        bookDao = dataBase.dataBaseDao()


        binding.pago.setOnClickListener {

            if (!binding.totalPagar.text.isNullOrEmpty() &&
                binding.id.text.toString() != "" &&
                binding.cu.text.toString() != "" &&
                binding.terminal.text.toString() != "" &&
                binding.totalPagar.text.toString() != "" &&
                binding.numTarjeta.text.toString() != ""
            ) {


                val authReq = AutTransaccionEntity(
                    binding.id.text.toString(),
                    binding.cu.text.toString(),
                    binding.terminal.text.toString(),
                    binding.totalPagar.text.replace("""[$,.]""".toRegex(), "").toInt().toString(),
                    binding.numTarjeta.text.toString()
                )

                ServiceCliente.authorization(authReq) {
                    if (it != null) {

                        val gson = Gson()
                        Log.i("MyTAG", "*****   ${gson.toJson(it)} books there *****")
                        val receiptId = it.id
                        val rrn = it.rrn
                        val statusCode = it.statusCode
                        val statusDescription = it.statusDescription
                        //     val databaseHandler: DatabaseHandler = DatabaseHandler(this)
                        if (receiptId != "" && rrn != "" && statusCode != "" && statusDescription != "") {

                            lifecycleScope.launch(Dispatchers.IO) {

                                try {
                                    bookDao.addTransaccion(
                                        AutResponseEntity(
                                            receiptId,
                                            rrn,
                                            statusCode,
                                            statusDescription
                                        )
                                    )
                                    launch(Dispatchers.Main.immediate) {
                                        Toast.makeText(
                                            applicationContext,
                                            "transaccion alamacenada",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    binding.totalPagar.removeTextChangedListener(textWatcher)
                                    binding.totalPagar.text.clear()
                                    binding.totalPagar.addTextChangedListener(textWatcher)
                                } catch (e: Exception) {

                                    Log.i("MyTAG", "*****   ${gson.toJson(e)} books there *****")
                                    launch(Dispatchers.Main.immediate) {
                                        Toast.makeText(
                                            applicationContext,
                                            "transaccion no se almaceno  ${gson.toJson(e)} ",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(applicationContext, statusDescription, Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "error en api", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "diligencia todos los campos", Toast.LENGTH_LONG)
                    .show()
            }
        }


        binding.viewTransactions.setOnClickListener {
            val intent = Intent(this@MainActivity, Detallestransaccion::class.java).apply { }
            startActivity(intent)
        }

    }

    private val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {

            binding.totalPagar.removeTextChangedListener(this)

            val cleanString: String = s.replace("""[$,.]""".toRegex(), "")
            val parsed = cleanString.toDouble()
            val formatted = NumberFormat.getCurrencyInstance().format((parsed / 100))

            binding.totalPagar.setText(formatted)
            binding.totalPagar.setSelection(formatted.length)
            binding.totalPagar.addTextChangedListener(this)
        }
    }
}










