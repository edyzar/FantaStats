package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Classic (
    var id: Int,

    var name: String,

    @SerializedName("short_name")
    var shortName: String,

    var created: String,

    var closed: Boolean,

    var rank: Int,

    @SerializedName("max_entries")
    var maxEntries: Int,

    @SerializedName("league_type")
    var leagueType: String,

    var scoring: String,

    @SerializedName("admin_entry")
    var adminEntry: Int?,

    @SerializedName("start_event")
    var startEvent: Int?,

    @SerializedName("entry_rank")
    var entryRank: Int?,

    @SerializedName("entry_last_rank")
    var entryLastRank: Int?,

    @SerializedName("entry_can_leave")
    var entryCanLeave: Boolean,

    @SerializedName("entry_can_admin")
    var entryCanAdmin: Boolean,

    @SerializedName("entry_can_invite")
    var entry_can_invite: Boolean,



        )