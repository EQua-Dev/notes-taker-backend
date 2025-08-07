package com.justenough.notes_taker.database.model

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)
