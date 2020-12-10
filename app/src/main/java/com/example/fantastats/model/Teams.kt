package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Teams(
    var code: Int,

    var draw: Int,

    var form: Int,

    var id: Int,

    var loss: Int,

    var name: String,

    var played: Int,

    var points: Int,

    var position: Int,

    @SerializedName("short_name")
    var shortName: String,

    var strength: Int,

    @SerializedName("team_division")
    var teamDivision: Int,

    var unavailable: Boolean,

    var win: Int,

    @SerializedName("strength_overall_home")
    var strengthOverallHome: Int,

    @SerializedName("strength_overall_away")
    var strengthOverallAway: Int,

    @SerializedName("strength_attack_home")
    var strengthAttackHome: Int,

    @SerializedName("strength_attack_away")
    var strengthAttackAway: Int,

    @SerializedName("strength_defence_home")
    var strengthDefenceHome: Int,

    @SerializedName("strength_defence_away")
    var strengthDefenceAway: Int,

    @SerializedName("pulse_id")
    var pulseId: Int,
)