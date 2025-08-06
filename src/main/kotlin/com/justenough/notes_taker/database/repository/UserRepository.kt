package com.justenough.notes_taker.database.repository

import com.justenough.notes_taker.database.model.JEUser
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<JEUser, ObjectId> {

    fun findByEmail(email: String): JEUser?

}