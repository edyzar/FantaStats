package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class BasicInformation (
    var id: Int,

    @SerializedName("joined_time")
    var joinedTime: String,

    @SerializedName("started_event")
    var startedEvent: Int,

    @SerializedName("favourite_team")
    var favouriteTeam: Int,

    @SerializedName("player_first_name")
    var playerFirstName: String,

    @SerializedName("player_last_name")
    var playerLastName: String,

    @SerializedName("player_region_id")
    var playerRegionId: Int,

    @SerializedName("player_region_name")
    var playerRegionName: String,

    @SerializedName("player_region_iso_code_short")
    var playerRegionIsoCodeShort: String,

    @SerializedName("player_region_iso_code_long")
    var playerRegionIsoCodeLong: String,

    @SerializedName("summary_overall_points")
    var summaryOverallPoints: Int,

    @SerializedName("summary_overall_rank")
    var summaryOverallRank: Int,

    @SerializedName("summary_event_points")
    var summaryEventPoints: Int,

    @SerializedName("summary_event_rank")
    var summaryEventRank: Int,

    @SerializedName("current_event")
    var currentEvent: Int,

    var leagues: Leagues,

    var name: String,

    var kit: String,

    @SerializedName("last_deadline_bank")
    var lastDeadlineBank: Int,

    @SerializedName("last_deadline_value")
    var lastDeadlineValue: Int,

    @SerializedName("last_deadline_total_transfers")
    var lastDeadlineTotalTransfers: Int,



)