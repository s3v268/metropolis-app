package com.example.metropolis_app.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiConnection {
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(this)
            // Agregar el interceptor de autenticación
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer " + "BocA8RrpqfAd38lq4oEdAYeK1qIAkn5MhIZ5Xzmv")
                    .build()
                chain.proceed(request)
            }


            .build()

    }

    private val builder = Retrofit.Builder()
        .baseUrl("http://10.0.2.2/metropolis-cdc/public/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    // creamos un objeto retrofit.builder con la interface de endpoints declarada
    val service: ApiEndPoints = builder.create()
}

