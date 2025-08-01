package com.justenough.notes_taker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NotesTakerApplication

fun main(args: Array<String>) {
	runApplication<NotesTakerApplication>(*args)
}
