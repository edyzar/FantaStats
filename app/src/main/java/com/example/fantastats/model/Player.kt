package com.example.fantastats.model

import com.google.gson.annotations.SerializedName

data class Player(

    @SerializedName("date_of_birth")
    var dateOfBirth: String,

    var dirty: Boolean,

    @SerializedName("first_name")
    var firstName: String,

    var gender: String,

    var id: Int,

    @SerializedName("last_name")
    var lastName: String,

    var region: Int,

    var email: String,

    var entry: Int,

    @SerializedName("entry_email")
    var entryEmail: Boolean
)