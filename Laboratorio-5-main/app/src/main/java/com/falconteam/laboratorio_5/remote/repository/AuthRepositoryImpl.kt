package com.falconteam.laboratorio_5.remote.repository
import com.falconteam.laboratorio_5.remote.resource.ErrorResponse
import com.falconteam.laboratorio_5.remote.resource.Resource
import com.falconteam.laboratorio_5.remote.services.AuthService
import com.falconteam.laboratorio_5.remote.services.LoginRequest
import com.falconteam.laboratorio_5.remote.services.LoginResponse
import com.google.gson.Gson;

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val gson: Gson
): AuthRepository {
    override suspend fun login(username: String, password: String): Resource<LoginResponse> {
        val loginRequest = LoginRequest(username, password)

        try {
            val response = authService.login(loginRequest)
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                return Resource.Success(loginResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message
                    } catch (e: Exception) {
                        "Error message could not be obtained"
                    }
                } else {
                    "Error message not found"
                }
                return Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Network error")
        }
    }
}