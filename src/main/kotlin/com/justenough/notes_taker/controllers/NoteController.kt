package com.justenough.notes_taker.controllers

import com.justenough.notes_taker.database.model.Note
import com.justenough.notes_taker.database.repository.NoteRepository
import com.justenough.notes_taker.database.requests.NoteRequest
import com.justenough.notes_taker.database.responses.NoteResponse
import com.justenough.notes_taker.mappers.toNote
import com.justenough.notes_taker.mappers.toNoteResponse
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(private val repository: NoteRepository) {


    @PostMapping
    fun save(@RequestBody body: NoteRequest): NoteResponse {
        val note = repository.save(
            body.toNote()

        )

        return note.toNoteResponse()
    }

    //https://note-taker.com/notes?ownerId=123
    @GetMapping
    fun findByOwnerId(@RequestParam(required = true) ownerId: String): List<NoteResponse> {
        val notes = repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toNoteResponse()

        }
        return notes
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String) {
        repository.deleteById(ObjectId(id))
    }

}