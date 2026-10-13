package com.mypro.s8155066assignment2.data

import javax.inject.Inject

private const val LOCATION = "footscray"

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(LOCATION, LoginRequest(username, password))
    }

    suspend fun getDashboard(keypass: String): DashboardResponse {
        return apiService.getDashboard(keypass)
    }
}