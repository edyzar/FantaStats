package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class BootstrapStatic(
    var events: List<Events>,

    @SerializedName("game_settings")
    var gameSettings: GameSettings,

    var phases: List<Phases>,

    var teams: List<Teams>,

    @SerializedName("total_players")
    var totalPlayers: Int,

    var elements: List<Elements>,

    @SerializedName("element_stats")
    var elementStats: List<ElementStats>,

    @SerializedName("element_types")
    var elementTypes: List<ElemetTypes>,
)