package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Picks (

    var element: Int,

    var position: Int,

    @SerializedName("selling_price")
    var sellingPrice: Int,

    var multiplier: Int,

    @SerializedName("purchase_price")
    var purchasePrice: Int,

    @SerializedName("is_captain")
    var isCaptain: Boolean,

    @SerializedName("is_vice_captain")
    var isViceCaptain: Boolean,

        )