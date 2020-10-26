package com.example.pokemonLore.api.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PokemonLoreApiClient(baseUrl: String) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun createClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.interceptors().add(logging)

        return httpClient.build()
    }

   fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}