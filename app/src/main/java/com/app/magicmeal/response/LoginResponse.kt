package com.app.magicmeal.response

import com.app.magicmeal.model.User

data class LoginResponse(
    val success: Boolean? = true,
    val token: String? = null,
    val role: String? = null,
    val message: String? = null,
    val Data: User? = null

)