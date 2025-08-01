package com.justenough.notes_taker.mappers

import com.justenough.notes_taker.controllers.NoteController
import com.justenough.notes_taker.database.model.Note
import com.justenough.notes_taker.database.requests.NoteRequest
import com.justenough.notes_taker.database.responses.NoteResponse
import org.bson.types.ObjectId
import java.time.Instant

fun Note.toNoteResponse(): NoteResponse {
    return NoteResponse(
        title = title,
        content = content,
        id = id.toHexString(),
        color = color,
        createdAt = createdAt
    )
}

fun NoteRequest.toNote(): Note {
    return Note(
        title = title,
        content = content,
        color = color,
        id = id?.let { ObjectId(it) } ?: ObjectId.get(),
        ownerId = ObjectId.get(),
        createdAt = Instant.now()
    )
}