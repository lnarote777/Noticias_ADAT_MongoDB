package org.example.model

import org.bson.codecs.pojo.annotations.BsonProperty
import java.time.LocalDateTime
import java.util.Date

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    @BsonProperty("fecha_publicacion")
    val fechaPub: Date = Date(),
    val tags: List<String>,
    val user: String
)
