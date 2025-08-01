package com.justenough.notes_taker.database.repository

import com.justenough.notes_taker.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note, ObjectId> {

    fun findByOwnerId(ownerId: ObjectId): List<Note>

}