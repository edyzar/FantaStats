package com.example.fantastats.model

data class LoginRequest(

    var password: String,

    var login: String,

    val redirectApi: String = "https://fantasy.premierleague.com/a/login",

    val app: String = "plfpl-web"

)
