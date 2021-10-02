package com.app.magicmeal.response

import com.app.magicmeal.model.User

data class ProfileUpdateResponse(
    val success: Boolean? = true,
    val message: String? = null,
    val data: User? = null
)