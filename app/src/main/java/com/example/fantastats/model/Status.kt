package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("qualification_event")
    var qualificationEvent: Int,

    @SerializedName("qualification_numbers")
    var qualificationNumbers: Int,

    @SerializedName("qualification_rank")
    var qualificationRank: Int,

    @SerializedName("qualification_state")
    var qualificationState: String,

    )