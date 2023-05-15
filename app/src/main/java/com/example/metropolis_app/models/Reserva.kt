package com.example.metropolis_app.models

import java.util.Date

data class Reserva(
    var email: String = "",
    var id_espacio: Int = 0,
    var fecha_inicio: Date,
    var fecha_final: Date,

)