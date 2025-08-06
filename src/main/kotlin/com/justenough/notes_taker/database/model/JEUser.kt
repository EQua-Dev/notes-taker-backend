package com.justenough.notes_taker.database.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class JEUser(
    val id: ObjectId = ObjectId.get(),
    val email: String,
    val hashedPassword: String,
)
