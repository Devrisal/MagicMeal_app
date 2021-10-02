package com.app.magicmeal.response

import com.app.magicmeal.api.ServiceBuilder
import com.app.magicmeal.model.User

data class UserRegisterResponse(
    val success: Boolean? = true,
    val result: User? = null
)