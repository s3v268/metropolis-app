package com.example.metropolis_app.models

import java.util.Date

data class Reserva(
    //prueba //hacer un fresh + migrate
    /*
     'client_id', int
        'event',  string
        'day',    datetime
        'estado', string

   //el que vale
    var email: String = "",
    var id_espacio: Int = 0,
    var fecha_inicio: Date,
    var fecha_final: Date,

    */
    var id_espacio: Int = 0,
    var event : String = "",
    var day : Date,
    var estado : String = "denegado"

)