package com.example.metropolis_app.models

data class Espacio(
    var area: Double = 0.0,
    var capacidad: Int = 0,
    var disponibilidad: Boolean = false,
    var id: Int = 0,
    var nombre: String = ""
)