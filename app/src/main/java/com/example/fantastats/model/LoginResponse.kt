package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("auth_token")
    var authToken: String,

    )