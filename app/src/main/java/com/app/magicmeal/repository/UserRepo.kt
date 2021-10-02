package com.app.magicmeal.repository

import com.app.magicmeal.api.APIRequest
import com.app.magicmeal.api.ServiceBuilder
import com.app.magicmeal.api.UserAPI
import com.app.magicmeal.model.User
import com.app.magicmeal.response.LoginResponse
import com.app.magicmeal.response.ProfileUpdateResponse
import com.app.magicmeal.response.UserRegisterResponse

class UserRepo : APIRequest() {

    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java);

    suspend fun register(user: User): UserRegisterResponse {
        return apiRequest {
            userAPI.register(user)
        }
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiRequest {
            userAPI.login(email, password)
        }
    }

    suspend fun profile(id: String): ProfileUpdateResponse {
        return apiRequest {
            userAPI.profile(ServiceBuilder.token!!, id)
        }
    }

    suspend fun update(user: User): ProfileUpdateResponse {
        return apiRequest {
            userAPI.update(ServiceBuilder.token!!, user)
        }
    }
}