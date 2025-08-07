package com.justenough.notes_taker.controllers

import com.justenough.notes_taker.database.model.Note
import com.justenough.notes_taker.database.repository.NoteRepository
import com.justenough.notes_taker.database.requests.NoteRequest
import com.justenough.notes_taker.database.responses.NoteResponse
import com.justenough.notes_taker.mappers.toNote
import com.justenough.notes_taker.mappers.toNoteResponse
import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.http.HttpStatusCode
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(private val repository: NoteRepository) {


    @PostMapping
    fun save(@Valid @RequestBody body: NoteRequest): NoteResponse {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String

        println("Owner id: $ownerId")
        val note = repository.save(
            body.toNote().copy(ownerId = ObjectId(ownerId))
        )

        return note.toNoteResponse()
    }

    //https://note-taker.com/notes?ownerId=123
    @GetMapping
    fun findByOwnerId(): List<NoteResponse> {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        val notes = repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toNoteResponse()

        }
        return notes
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String) {
        val note = repository.findById(ObjectId(id)).orElseThrow {
            ResponseStatusException(HttpStatusCode.valueOf(404), "Note not found")
        }
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        if (note.ownerId.toHexString() == ownerId) {

            repository.deleteById(ObjectId(id))
        }
    }

}