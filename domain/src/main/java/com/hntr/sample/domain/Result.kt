package com.hntr.sample.domain

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error<out T : Any>(val cause: Exception) : Result<T>()
    object Loading : Result<Nothing>()
}