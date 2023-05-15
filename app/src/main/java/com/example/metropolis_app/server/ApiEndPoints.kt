package com.example.metropolis_app.server

import com.example.metropolis_app.models.Espacio

import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoints {
    @GET("espacios/")
    suspend fun listEspacios(): Response <List<Espacio>>

}