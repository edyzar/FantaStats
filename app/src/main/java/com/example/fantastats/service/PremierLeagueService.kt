package com.example.fantastats.service

import com.example.fantastats.constant.ApiConstants
import com.example.fantastats.model.BootstrapStatic
import com.example.fantastats.model.LoginRequest
import com.example.fantastats.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PremierLeagueService {

    @POST(ApiConstants.LOGIN_URL)
    @FormUrlEncoded
    suspend fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(ApiConstants.BASE_URL + ApiConstants.BOOTSTRAP_STATIC_URL)
    suspend fun bootstrapStatic(): BootstrapStatic

}