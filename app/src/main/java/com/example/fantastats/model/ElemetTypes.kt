package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class ElemetTypes(
    var id: Int,

    @SerializedName("plural_name")
    var pluralName: String,

    @SerializedName("plural_name_short")
    var pluralNameShort: String,

    @SerializedName("singular_name")
    var singularName: String,

    @SerializedName("singular_name_short")
    var singularNameShort: String,

    @SerializedName("squad_select")
    var squadSelect: Int,

    @SerializedName("squad_min_play")
    var squadMinPlay: Int,

    @SerializedName("squad_max_play")
    var squadMaxPlay: Int,

    @SerializedName("ui_shirt_specific")
    var uiShirtSpecific: Boolean,

    @SerializedName("sub_positions_locked")
    var subPositionsLocked: List<Int>,

    @SerializedName("element_count")
    var elementCount: Int,

    )