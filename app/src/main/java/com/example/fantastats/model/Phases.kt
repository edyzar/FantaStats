package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Phases(
    var id: Int,

    var name: String,

    @SerializedName("start_event")
    var startEvent: Int,

    @SerializedName("stop_event")
    var stopEvent: Int
)