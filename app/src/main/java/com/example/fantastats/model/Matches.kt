package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Matches (
    var id: Int,

    @SerializedName("entry_1_entry")
    var entry1Entry: Int,

    @SerializedName("entry_1_name")
    var entry1Name: String,

    @SerializedName("entry_1_player_name")
    var entry1PlayerName: String,

    @SerializedName("entry_1_points")
    var entry1Points: Int,

    @SerializedName("entry_1_win")
    var entry1Win: Int,

    @SerializedName("entry_1_draw")
    var entry1Draw: Int,

    @SerializedName("entry_1_loss")
    var entry1Loss: Int,

    @SerializedName("entry_1_total")
    var entry1Total: Int,

    @SerializedName("entry_2_entry")
    var entry2Entry: Int,

    @SerializedName("entry_2_name")
    var entry2Name: String,

    @SerializedName("entry_2_player_name")
    var entry2PlayerName: String,

    @SerializedName("entry_2_points")
    var entry2Points: Int,

    @SerializedName("entry_2_win")
    var entry2Win: Int,

    @SerializedName("entry_2_draw")
    var entry2Draw: Int,

    @SerializedName("entry_2_loss")
    var entry2Loss: Int,

    @SerializedName("entry_2_total")
    var entry2Total: Int,

    @SerializedName("is_knockout")
    var isKnockout: Boolean,

    var winner: Int,

    @SerializedName("seed_value")
    var seedValue: Int,

    var tiebreak: Boolean,

    var event: Int,
        )