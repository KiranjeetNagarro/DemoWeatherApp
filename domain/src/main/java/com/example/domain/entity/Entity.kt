package com.example.domain.entity

/**
 * Entity for Error Handling
 */
sealed class Entity {
    data class Error(val errorCode: Int, val errorMessage: String) : Entity()
}