package com.example.fantastats.model

data class LoginRequest(

    var login: String,

    var password: String,

    val redirectApi: String = "https://fantasy.premierleague.com/a/login",

    val app: String = "plfpl-web"

)
