package org.example.controller

import org.example.model.Comentario
import org.example.service.ComentarioService
import org.example.service.NoticiaService

class ComentarioController {

    private val comentarioService = ComentarioService()
    private val noticiaService = NoticiaService()
    fun insertComentario() {
        print("Ingrese el nombre de usuario que escribe el comentario: ")
        val usuarioId = readln().trim()

        print("Ingrese el ID de la noticia a la que desea comentar: ")
        val noticiaId = readln().trim()

        print("Ingrese el texto del comentario: ")
        val texto = readln().trim()

        val noticia = noticiaService.getNoticia(noticiaId)

        if (noticia == null) {
            println("No se econtró ninguna noticia con ese título.")
            return
        }

        val comentario = Comentario(usuarioId, noticia, texto)

        val success = comentarioService.insertComentario(comentario)
        if (success) {
            println("Comentario añadido con éxito.")
        } else {
            println("Error: No se pudo añadir el comentario.")
        }
    }

    fun getComentariosNoticia() {
        println("Indique el título de la noticia: ")
        val noticiaId = readln().trim()

        if (noticiaId.isBlank()) {
            print("No puede dejar este campo vacío.")
            return
        }

        val comentarios = comentarioService.getComentariosByNoticia(noticiaId)

        if (comentarios.isEmpty()) {
            println("No hay comentarios para esta noticia.")
        } else {
            comentarios.forEach { println(it) }
        }
    }
}