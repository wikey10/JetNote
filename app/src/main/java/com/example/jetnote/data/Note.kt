package com.example.jetnote.data

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class Note(
    val id:UUID = UUID.randomUUID(), //CREATES Random UUIDS
    val title:String,
    val description :String,
    val entryDate: LocalDateTime = LocalDateTime.now()

)
