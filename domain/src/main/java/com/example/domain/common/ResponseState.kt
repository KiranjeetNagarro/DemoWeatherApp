package com.example.domain.common

import com.example.domain.entity.Entity

sealed class ResponseState<T> {

    data class Success<T>(val data: T?) : ResponseState<T>()

    data class Error<T>(val error: Entity.Error) : ResponseState<T>()
}