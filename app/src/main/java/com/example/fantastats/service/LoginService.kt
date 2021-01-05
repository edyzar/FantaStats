package com.example.fantastats.service

import com.example.fantastats.constant.ApiConstants
import com.example.fantastats.model.LoginRequest
import com.example.fantastats.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @POST(ApiConstants.LOGIN_URL)
    //@FormUrlEncoded
    suspend fun login(@Body request: LoginRequest): LoginResponse


}