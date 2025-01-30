package org.example.model

import java.util.Date

data class Comentario(
    val usuarioId: String, //nombre del usuario
    val noticia: Noticia, // titulo
    val texto: String,
    val fecha: Date

)
