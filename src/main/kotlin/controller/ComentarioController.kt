package org.example.controller

import org.example.model.Comentario
import org.example.model.EstadoUsuario
import org.example.model.Usuario
import org.example.service.ComentarioService
import org.example.service.NoticiaService
import org.example.service.UserService

class ComentarioController {

    private val comentarioService = ComentarioService()
    private val noticiaService = NoticiaService()
    private val usuarioService = UserService()
    fun insertComentario() {

        var usuarioId: String
        while (true){
            print("Ingrese el email de usuario que escribe el comentario: ")
            usuarioId = readln().trim()

            if (usuarioId.isEmpty() || comprobarUsuario(usuarioId) == null ) {
                println("El contenido no puede estar vacío y debe ser un usuario activo.")
            }else{
                break
            }
        }

        var noticiaId: String
        while (true){
            print("Ingrese el título de la noticia a la que desea comentar: ")
            noticiaId = readln().trim()

            if (noticiaId.isEmpty()) {
                println("Introduce el titulo de la noticia.")
            }else{
                break
            }
        }

        print("Ingrese el texto del comentario: ")
        val texto = readln().trim()

        if (texto.isEmpty()) {
            println("No se pudo añadir el comentario.")
            return
        }

        val noticia = noticiaService.getNoticia(noticiaId)

        if (noticia == null) {
            println("No se econtró ninguna noticia con ese título.")
            return
        }

        val usuario = usuarioService.getUser(usuarioId)

        if (usuario == null) {
            println("No se econtró ningún usuario con ese email.")
            return
        }

        val comentario = Comentario(usuario.nick, noticia, texto)

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

        val noticia = noticiaService.getNoticia(noticiaId)
        if (noticia == null) {
            println("La noticia no existe.")
            return
        }

        val comentarios = comentarioService.getComentariosByNoticia(noticiaId)

        if (comentarios.isEmpty()) {
            println("No hay comentarios para esta noticia.")
        } else {
            comentarios.forEach { println(it) }
        }
    }

    private fun comprobarUsuario(email: String): Usuario?{
        val usuario = usuarioService.getUser(email) ?: return null

        return when (usuario.estado) {
            EstadoUsuario.BANNED -> null
            EstadoUsuario.INACTIVE -> null
            else -> usuario
        }
    }
}