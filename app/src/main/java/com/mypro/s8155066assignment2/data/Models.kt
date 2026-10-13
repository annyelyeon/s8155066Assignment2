package com.mypro.s8155066assignment2.data

import java.io.Serializable

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

data class Entity(
    val title: String,
    val director: String,
    val genre: String,
    val releaseYear: Int,
    val description: String
) : Serializable