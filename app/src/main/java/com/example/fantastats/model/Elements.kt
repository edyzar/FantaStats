package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Elements(
    @SerializedName("chance_of_playing_next_round")
    var chanceOfPlayingNextRound: Int,

    @SerializedName("chance_of_playing_this_round")
    var chanceOfPlayingThisRound: Int,

    var code: Int,

    @SerializedName("cost_change_event")
    var costChangeEvent: Int,

    @SerializedName("cost_change_start")
    var costChangeStart: Int,

    @SerializedName("cost_change_start_fall")
    var costChangeStartFall: Int,

    @SerializedName("dreamteam_count")
    var dreamteamCount: Int,

    @SerializedName("element_type")
    var elementType: Int,

    @SerializedName("ep_next")
    var epNext: String,

    @SerializedName("ep_this")
    var epThis: String,

    @SerializedName("event_points")
    var eventPoints: Int,

    @SerializedName("first_name")
    var firstName: String,

    var form: String,

    var id: Int,

    @SerializedName("in_dreamteam")
    var inDreamteam: Boolean,

    var news: String,

    @SerializedName("news_added")
    var newsAdded: String,

    @SerializedName("now_cost")
    var photo: String,

    @SerializedName("points_per_game")
    var pointsPerGame: String,

    @SerializedName("second_name")
    var secondName: String,

    @SerializedName("selected_by_percent")
    var selectedByPercent: String,

    var special: Boolean,

    @SerializedName("squad_number")
    var squadNumber: Int,

    var status: String,

    var team: Int,

    @SerializedName("team_code")
    var teamCode: Int,

    @SerializedName("total_points")
    var totalPoints: Int,

    @SerializedName("transfers_in")
    var transfersIn: Int,

    @SerializedName("transfers_in_event")
    var transfersInEvent: Int,

    @SerializedName("transfers_out")
    var transfersOut: Int,

    @SerializedName("transfers_out_event")
    var transfersOutEvent: Int,

    @SerializedName("varue_form")
    var varueForm: String,

    @SerializedName("varue_season")
    var varueSeason: String,

    @SerializedName("web_name")
    var webName: String,

    var minutes: Int,

    @SerializedName("goals_scored")
    var goalsScored: Int,

    var assists: Int,

    @SerializedName("clean_sheets")
    var cleanSheets: Int,

    @SerializedName("goals_conceded")
    var goalsConceded: Int,

    @SerializedName("own_goals")
    var ownGoals: Int,

    @SerializedName("penalties_saved")
    var penaltiesSaved: Int,

    @SerializedName("penalties_missed")
    var penaltiesMissed: Int,

    @SerializedName("yellow_cards")
    var yellowCards: Int,

    @SerializedName("red_cards")
    var redCards: Int,

    var saves: Int,

    var bonus: Int,

    var bps: Int,

    var influence: String,

    var creativity: String,

    var threat: String,

    @SerializedName("ict_index")
    var ictIndex: String,

    @SerializedName("influence_rank")
    var influenceRank: Int,

    @SerializedName("influence_rank_type")
    var influenceRankType: Int,

    @SerializedName("creativity_rank")
    var creativityRank: Int,

    @SerializedName("creativity_rank_type")
    var creativityRankType: Int,

    @SerializedName("threat_rank")
    var threatRank: Int,

    @SerializedName("threat_rank_type")
    var threatRankType: Int,

    @SerializedName("ict_index_rank")
    var ictIndexRank: Int,

    @SerializedName("ict_index_rank_type")
    var ictIndexRankType: Int,

    @SerializedName("corners_and_indirect_freekicks_order")
    var cornersAndIndirectFreekicksOrder: Int,

    @SerializedName("corners_and_indirect_freekicks_text")
    var cornersAndIndirectFreekicksText: String,

    @SerializedName("direct_freekicks_order")
    var directFreekicksOrder: Int,

    @SerializedName("direct_freekicks_text")
    var directFreekicksText: String,

    @SerializedName("penalties_order")
    var penaltiesOrder: Int,

    @SerializedName("penalties_text")
    var penaltiesText: String,
)