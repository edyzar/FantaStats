package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class GameSettings(
    @SerializedName("league_join_private_max")
    var leagueJoinPrivateMax: Int,

    @SerializedName("league_join_public_max")
    var leagueJoinPublicMax: Int,

    @SerializedName("league_max_size_public_classic")
    var leagueMaxSizePublicClassic: Int,

    @SerializedName("league_max_size_public_h2h")
    var leagueMaxSizePublicH2h: Int,

    @SerializedName("league_max_size_private_h2h")
    var leaguemaxSizePrivateH2h: Int,

    @SerializedName("league_max_ko_rounds_private_h2h")
    var leagueMaxKoRoundsPrivateH2h: Int,

    @SerializedName("league_prefix_public")
    var leaguePrefixPublic: String,

    @SerializedName("league_points_h2h_win")
    var leaguePointsH2hWin: Int,

    @SerializedName("league_points_h2h_lose")
    var league_points_h2h_lose: Int,

    @SerializedName("league_points_h2h_draw")
    var leaguePointsH2hDraw: Int,

    @SerializedName("league_ko_first_instead_of_random")
    var leagueKoFirstInsteadOfRandom: Boolean,

    @SerializedName("cup_start_event_id")
    var cupStartEventId: Int,

    @SerializedName("cup_stop_event_id")
    var cupStopEventId: Int,

    @SerializedName("cup_qualifying_method")
    var cupQualifyingMethod: String,

    @SerializedName("cup_type")
    var cupType: String,

    @SerializedName("squad_squadplay")
    var squadSquadplay: Int,

    @SerializedName("squad_squadsize")
    var squadSquadsize: Int,

    @SerializedName("squad_team_limit")
    var squadTeamLimit: Int,

    @SerializedName("squad_total_spend")
    var squadTotalSpend: Int,

    @SerializedName("ui_currency_multiplier")
    var uiCurrencyMultiplier: Int,

    @SerializedName("ui_use_special_shirts")
    var uiUseSpecialShirts: Boolean,

    @SerializedName("ui_special_shirt_exclusions")
    var uiSpecialShirtExclusions: List<String>,

    @SerializedName("stats_form_days")
    var statsFormDays: Int,

    @SerializedName("sys_vice_captain_enabled")
    var sysViceCaptainEnabled: Boolean,

    @SerializedName("transfers_cap")
    var transfersCap: Int,

    @SerializedName("transfers_sell_on_fee")
    var transfersSellOnFee: Float,

    @SerializedName("league_h2h_tiebreak_stats")
    var leagueH2hTiebreakStats: List<String>,

    var timezone: String,
)