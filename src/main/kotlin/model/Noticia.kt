package org.example.model

import org.bson.codecs.pojo.annotations.BsonProperty
import java.util.Date

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    @BsonProperty("fecha_publicacion")
    val fechaPub: Date,
    val tag: List<String>,
    val user: String
)
