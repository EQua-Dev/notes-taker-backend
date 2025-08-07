package com.justenough.notes_taker.database.requests

import jakarta.validation.constraints.NotBlank

data class NoteRequest(
    val id: String?,
    @NotBlank(message = "Title can't be blank")
    val title: String,
    val content: String,
    val color: Long,
//    val ownerId: String,
)