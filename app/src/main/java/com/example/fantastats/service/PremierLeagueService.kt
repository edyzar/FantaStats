package com.example.fantastats.service

import com.example.fantastats.model.BootstrapStatic
import retrofit2.http.GET

interface PremierLeagueService {

    @GET("/api/bootstrap-static/")
    suspend fun bootstrapStatic(): BootstrapStatic
}