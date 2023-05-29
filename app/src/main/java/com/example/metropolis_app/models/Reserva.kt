package com.example.metropolis_app.models

data class Reserva(
    var event: String,
    var email: String,
    var company_name: String,
    var space: String,
    var start_date: String,
    var end_date: String,
    var n_attendees: Int,
    var n_bus_pass: Int,
    var n_staff_pass: Int,
    var n_parking_pass: Int,
    var accepted: String
)