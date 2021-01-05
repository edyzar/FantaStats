package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("sessionid")
    var sessionId: String,

    @SerializedName("pl_profile")
    var plProfile: String,

    )