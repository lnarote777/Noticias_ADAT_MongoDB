package org.example.controller

import org.example.model.EstadoUsuario
import org.example.model.Noticia
import org.example.service.NoticiaService
import org.example.service.UserService

class NoticiaController {
    private val service = NoticiaService()
    private val usuarioService = UserService()

    fun insertNoticia()  {
        println("Ingrese los datos de la noticia:")
        print("Título: ")
        val titulo = readln()
        print("Cuerpo: ")
        val cuerpo = readln()
        print("Autor (email): ")
        val autor = readln()
        print("Etiquetas (separadas por coma): ")
        val tagsInput = readln().split(",").map { it.trim() }

        if (titulo.isEmpty()) {
            println("El título no puede estar vacío.")
            return
        }

        if (cuerpo.isEmpty()) {
            println("El contenido no puede estar vacío.")
            return
        }

        if (autor.isEmpty() || !autor.contains("@") || !comprobarAutor(autor)) {
            println("El autor debe ser un correo electrónico válido.")
            return
        }

        if (tagsInput.isEmpty()) {
            println("Las etiquetas no pueden estar vacías.")
            return
        }

        val noticia = Noticia(
            titulo = titulo,
            cuerpo = cuerpo,
            tags = tagsInput,
            user = autor
        )

        val success = service.insertNoticia(noticia)

        if (success) {
            println("Noticia publicada con éxito.")
        } else {
            println("No se pudo publicar la noticia.")
        }
    }

    fun getNoticias() {
        println("Indique la etiqueta que desea buscar: ")
        val tag = readln()

        val noticias = service.getNoticiasByTag(tag)

        if (noticias == null) {
            println("No hay noticias disponibles.")
        } else {
            println("Noticias disponibles:")
            noticias.forEach { println(it) }
        }
    }
    fun getNoticia() {
        print("Ingrese el título de la noticia: ")
        val id = readln()
        val noticia = service.getNoticia(id)

        if (noticia != null) {
            println("Noticia encontrada:")
            println(noticia)
        } else {
            println("No se encontró una noticia con ese título.")
        }
    }

    fun deleteNoticias() {
        print("Ingrese el ID de la noticia a eliminar: ")
        val id = readln()

        val success = service.deleteNoticia(id)

        if (success) {
            println("Noticia eliminada con éxito.")
        } else {
            println("No se pudo eliminar la noticia.")
        }
    }

    fun getUltimasNoticias(){
        val noticias = service.getUltimasNoticias()

        if (noticias != null) {
            if (noticias.isEmpty()) {
                println("No hay noticias recientes.")
            } else {
                println("Últimas noticias:")
                noticias.forEach { println(it) }
            }
        }
    }

    fun getNoticiasUsuario() {
        print("Ingrese el email del usuario: ")
        val email = readln()

        val noticias = service.getNoticiasUsuario(email)

        if (noticias== null) {
            println("No hay noticias publicadas por este usuario.")
        } else {
            println("Noticias de $email:")
            noticias.forEach { println(it) }
        }
    }

    private fun comprobarAutor(autor: String): Boolean{
        val usuario = usuarioService.getUser(autor) ?: return false

        return when (usuario.estado) {
            EstadoUsuario.BANNED -> false
            EstadoUsuario.INACTIVE -> false
            EstadoUsuario.ACTIVE -> true
            else -> true
        }
    }

}