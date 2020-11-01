package com.example.pokemonLore.api

sealed class Response<out T> {
    data class Success<out T>(val data: T): Response<T>()
    data class Error(val exception: Throwable): Response<Nothing>()
    object Loading: Response<Nothing>()
    object NotInitialized: Response<Nothing>()
}