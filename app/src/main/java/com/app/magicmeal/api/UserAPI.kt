package com.app.magicmeal.api

import com.app.magicmeal.model.User
import com.app.magicmeal.response.LoginResponse
import com.app.magicmeal.response.ProfileUpdateResponse
import com.app.magicmeal.response.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    @POST("/signup")
    suspend fun register(
        @Body user: User
    ): Response<UserRegisterResponse>

    @FormUrlEncoded
    @POST("/signin")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>


    @GET("/user/profile/{id}")
    suspend fun profile(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ProfileUpdateResponse>

    @PUT("/profile/update")
    suspend fun update(
        @Header("Authorization") token: String,
        @Body user: User
    ): Response<ProfileUpdateResponse>
}
