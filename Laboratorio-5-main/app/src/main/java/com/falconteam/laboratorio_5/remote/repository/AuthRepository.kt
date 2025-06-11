package com.falconteam.laboratorio_5.remote.repository

import com.falconteam.laboratorio_5.remote.resource.Resource
import com.falconteam.laboratorio_5.remote.services.LoginResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
}