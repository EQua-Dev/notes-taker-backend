package com.justenough.notes_taker.database.requests

data class NoteRequest(
    val id: String?,
    val title: String,
    val content: String,
    val color: Long,
//    val ownerId: String,
)