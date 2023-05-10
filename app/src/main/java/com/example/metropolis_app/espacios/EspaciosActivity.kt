package com.example.metropolis_app.espacios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityEspaciosBinding
import com.example.metropolis_app.models.Espacio
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class EspaciosActivity : AppCompatActivity() {
    private var espacios: MutableList<Espacio> = ArrayList()

    //Creamos el adapter del recyclerView
    private val espaciosAdapter: EspaciosAdapter = EspaciosAdapter(emptyList(), this)

    private lateinit var binding: ActivityEspaciosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_espacios)
        // Para trabajar con ViewBindig
        binding = ActivityEspaciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadJsonEspacios()

        binding.rvEspacios.adapter = espaciosAdapter

        // Cargamos los datos en el adapter
        espaciosAdapter.espacios = espacios
        espaciosAdapter.notifyDataSetChanged()


    }

    fun loadJsonEspacios() {

        // cargamos el asset en un string
        var json: String = """{
  "espacios": [
    {
      "id": 1,
      "nombre": "Sala B",
      "capacidad": 30,
      "area": 150.2,
      "disponibilidad": true
    },
    {
      "id": 2,
      "nombre": "Sala C",
      "capacidad": 20,
      "area": 100.3,
      "disponibilidad": false
    },
    {
      "id": 3,
      "nombre": "Sala D",
      "capacidad": 40,
      "area": 200.2,
      "disponibilidad": true
    },
    {
      "id": 4,
      "nombre": "Sala E",
      "capacidad": 10,
      "area": 50.2,
      "disponibilidad": false
    },
    {
      "id": 5,
      "nombre": "Sala F",
      "capacidad": 25,
      "area": 130.5,
      "disponibilidad": true
    },
    {
      "id": 6,
      "nombre": "Sala A",
      "capacidad": 15,
      "area": 80.1,
      "disponibilidad": false
    },
    {
      "id": 7,
      "nombre": "Sala G",
      "capacidad": 35,
      "area": 180.4,
      "disponibilidad": true
    },
    {
      "id": 8,
      "nombre": "Sala H",
      "capacidad": 18,
      "area": 90.3,
      "disponibilidad": false
    },
    {
      "id": 9,
      "nombre": "Sala I",
      "capacidad": 22,
      "area": 120.8,
      "disponibilidad": true
    },
    {
      "id": 10,
      "nombre": "Sala J",
      "capacidad": 50,
      "area": 250.5,
      "disponibilidad": false
    },
    {
      "id": 11,
      "nombre": "Sala K",
      "capacidad": 12,
      "area": 60.4,
      "disponibilidad": true
    },
    {
      "id": 12,
      "nombre": "Sala L",
      "capacidad": 28,
      "area": 145.7,
      "disponibilidad": false
    },
    {
      "id": 13,
      "nombre": "Sala M",
      "capacidad": 16,
      "area": 82.3,
      "disponibilidad": true
    },
    {
      "id": 14,
      "nombre": "Sala N",
      "capacidad": 32,
      "area": 160.6,
      "disponibilidad": false
    },
    {
      "id": 15,
      "nombre": "Sala O",
      "capacidad": 21,
      "area": 107.9,
      "disponibilidad": true
    },
    {
      "id": 16,
      "nombre": "Sala P",
      "capacidad": 42,
      "area": 220.1,
      "disponibilidad": false
    }
  ]
}
"""

        // convertimos el json a jsonObject
        var jo: JSONObject = JSONObject(json)

        // cogemos el jsonArray que es el atributo 'elements'
        var ja: JSONArray = jo.getJSONArray("espacios")

        // Ahora lo que hacemos es recorrer el jsonArray y parsear uno a uno.
        var gson: Gson = Gson()

        for (i in 0 until ja.length()) {
            // ahora parseamos el joElement, que es un element en formato json
            // a nuestra clase y lo añadimos a la List
            var espacio: Espacio = gson.fromJson(ja.get(i).toString(), Espacio::class.java)
            espacios.add(espacio)
        }

    }
}