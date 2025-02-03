package org.example.controller

import org.example.model.EstadoUsuario
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.service.NoticiaService
import org.example.service.UserService

class NoticiaController {
    private val service = NoticiaService()
    private val usuarioService = UserService()

    fun insertNoticia()  {

        println("Ingrese los datos de la noticia:")

        var titulo: String
        while (true) {
            print("Título: ")
            titulo = readln()
            if (titulo.isEmpty()) {
                println("El título no puede estar vacío.")
            }else{
                break
            }
        }

        var cuerpo: String
        while (true) {
            print("Cuerpo: ")
            cuerpo = readln()

            if (cuerpo.isEmpty()) {
                println("El contenido no puede estar vacío.")
            }else{
                break
            }
        }

        var autor: String
        while (true) {
            print("Autor (email): ")
            autor = readln()

            if (autor.isEmpty() || !autor.contains("@") || comprobarEmail(autor) == null) {
                println("El email del usuario debe ser un correo electrónico válido y el usuario debe estar activo y no estar baneado.")
            }else{
                break
            }
        }

        var tagsInput : List<String>
        while (true) {
            print("Etiquetas (separadas por coma): ")
            tagsInput = readln().split(",").map { it.trim() }

            if (tagsInput.isEmpty()) {
                println("Las etiquetas no pueden estar vacías.")
            }else{
                break
            }
        }
        val usuario = comprobarEmail(autor)
        if (usuario != null) {
            val noticia = Noticia(
                titulo = titulo,
                cuerpo = cuerpo,
                tags = tagsInput,
                user = usuario
            )

            val success = service.insertNoticia(noticia)

            if (success) {
                println("Noticia publicada con éxito.")
            } else {
                println("No se pudo publicar la noticia.")
            }
        }

    }

    fun getNoticiasByTag() {
        var tag: String
        while (true){
            print("Indique la etiqueta que desea buscar: ")
            tag = readln()

            if (tag.isEmpty()){
                println("No puede dejar el campo vacío.")
            }else{
                break
            }
        }

        val noticias = service.getNoticiasByTag(tag)

        if (noticias == null) {
            println("No hay noticias disponibles.")
        } else {
            println("Noticias disponibles:")
            noticias.forEach { println(it) }
        }
    }
    fun getNoticia() {

        var titulo: String
        while (true) {
            print("Ingrese el título de la noticia: ")
            titulo = readln()

            if (titulo.isEmpty()) {
                println("No puede dejar el campo vacío.")
            }else{
                break
            }
        }

        val noticia = service.getNoticia(titulo)

        if (noticia != null) {
            println("Noticia encontrada:")
            println(noticia)
        } else {
            println("No se encontró una noticia con ese título.")
        }
    }

    fun deleteNoticias() {

        var titulo: String
        while (true) {
            print("Ingrese el titulo de la noticia a eliminar: ")
            titulo = readln()

            if (titulo.isEmpty()) {
                println("No puede dejar el campo vacío")
            }else{
                break
            }
        }

        val success = service.deleteNoticia(titulo)

        if (success) {
            println("Noticia eliminada con éxito.")
        } else {
            println("No se pudo eliminar la noticia.")
        }
    }

    fun getUltimasNoticias(){
        val noticias = service.getUltimasNoticias()


        if (noticias.isNullOrEmpty()) {
            println("No hay noticias recientes.")
        } else {
            println("Últimas noticias:")
            noticias.forEach { println(it) }
        }

    }

    fun getNoticiasUsuario() {

        var email: String
        while (true) {
            print("Ingrese el email del usuario: ")
            email = readln()

            if (email.isEmpty() || !email.contains("@") || comprobarEmail(email) == null) {
                println("Asegurese de introducir un email válido.")
            }else{
                break
            }
        }
        val noticias = service.getNoticiasUsuario(email)

        if (noticias== null) {
            println("No hay noticias publicadas por este usuario.")
        } else {
            println("Noticias de $email:")
            noticias.forEach { println(it) }
        }
    }

    private fun comprobarEmail(email: String): Usuario?{
        val usuario = usuarioService.getUser(email) ?: return null

        return when (usuario.estado) {
            EstadoUsuario.BANNED -> null
            EstadoUsuario.INACTIVE -> null
            else -> usuario
        }
    }

}