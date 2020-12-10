package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Events(
    var id: Int,

    var name: String,

    @SerializedName("deadline_time")
    var deadline_time: String,

    @SerializedName("average_entry_score")
    var avarageEntryScore: Int,

    var finished: Boolean,

    @SerializedName("data_checked")
    var dataChecked: Boolean,

    @SerializedName("highest_scoring_entry")
    var highestScoringEntry: Int,

    @SerializedName("deadline_time_epoch")
    var deadlineTimeEpoch: Int,

    @SerializedName("deadline_time_game_offset")
    var deadlineTimeGameOffset: Int,

    @SerializedName("highest_score")
    var highestScore: Int,

    @SerializedName("is_previous")
    var isPrevious: Boolean,

    @SerializedName("is_current")
    var isCurrent: Boolean,

    @SerializedName("is_next")
    var isNext: Boolean,

    @SerializedName("chip_plays")
    var chipPlays: List<ChipPlays>,

    @SerializedName("most_selected")
    var mostSelected: Int,

    @SerializedName("most_transferred_in")
    var mostTransferredIn: Int,

    @SerializedName("top_element")
    var topElement: Int,

    @SerializedName("top_element_info")
    var topElementInfo: TopElementInfo,

    @SerializedName("transfers_made")
    var transfersMade: Int,

    @SerializedName("most_captained")
    var mostCaptained: Int,

    @SerializedName("most_vice_captained")
    var mostViceCaptained: Int,
)


data class ChipPlays(
    @SerializedName("chip_name")
    var chipName: String,

    @SerializedName("num_played")
    var numPlayed: Int,
)

data class TopElementInfo(
    var id: Int,

    var points: Int
)