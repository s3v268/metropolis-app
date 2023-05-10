package com.example.metropolis_app.server

import com.example.metropolis_app.models.Espacio
import okhttp3.Response
import retrofit2.http.GET

interface ApiEndPoints {
    @GET("espacios")
    suspend fun listEspacios(): Response

}