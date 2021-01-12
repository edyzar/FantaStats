package com.example.fantastats

import com.example.fantastats.constant.ApiConstants
import com.example.fantastats.service.StatsService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private val gson = GsonBuilder()
    .setLenient()
    .disableHtmlEscaping()
    .create()

private val httpClient: OkHttpClient = OkHttpClient.Builder()
    .callTimeout(5, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .connectTimeout(5, TimeUnit.SECONDS)
    .addInterceptor { chain: Interceptor.Chain ->
        val builder = chain.request().newBuilder()
            .addHeader("accept", "application/json; charset=utf-8")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36"
            )
        chain.proceed(builder.build())
    }
    .build()

private val retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(ApiConstants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun createStatsService(): StatsService = retrofit.create()