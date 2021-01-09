package com.example.fantastats.service

import com.example.fantastats.constant.ApiConstants
import com.example.fantastats.model.*
import retrofit2.Call
import retrofit2.http.*

interface StatsService {

    @GET(ApiConstants.BOOTSTRAP_STATIC_URL)
    suspend fun bootstrapStatic(): BootstrapStatic

    @GET(ApiConstants.MY_TEAM_URL + "{id}")
    suspend fun myTeam(@Header("Cookie") cookie: String, @Path("id") id: Int): MyTeam

    @GET(ApiConstants.ME_URL)
    suspend fun me(@Header("Cookie") cookie: String): Me

    @GET(ApiConstants.ENTRY_URL + "{id}" + "/")
    suspend fun basicInformation(@Path("id") id: Int): BasicInformation

}