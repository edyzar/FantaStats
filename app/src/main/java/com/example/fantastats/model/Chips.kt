package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Chips (
    @SerializedName("status_for_entry")
    var statusForEntry: String,

    var name: String,

    var number: Int,

    @SerializedName("start_event")
    var startEvent: Int,

    @SerializedName("stop_event")
    var stopEvent: Int,

    @SerializedName("chip_type")
    var chipType: String
        )