package org.example.utils

import org.example.controller.ComentarioController
import org.example.controller.NoticiaController
import org.example.controller.UserController

class Menu {
    private val noticiaController = NoticiaController()
    private val usuarioController = UserController()
    private val comentarioController = ComentarioController()

    fun menu() {
        while (true) {
            println("---- BIENVENIDO ----")
            println("1 - Registrarse")
            println("2 - Publicar noticia")
            println("3 - Ver noticias de un usuario")
            println("4 - Buscar noticias por etiqueta")
            println("5 - Buscar noticia por titulo")
            println("6 - Ver últimas 10 noticias")
            println("7 - Añadir comentario")
            println("8 - Ver comentarios de una noticia")
            println("0 - Salir")
            print("Elija una opción -> ")

            val opcion = readln()
            when (opcion) {
                "1" -> usuarioController.registerUser()
                "2" -> noticiaController.insertNoticia()
                "3" -> noticiaController.getNoticiasUsuario()
                "4" -> noticiaController.getNoticiasByTag()
                "5" -> noticiaController.getNoticia()
                "6" -> noticiaController.getUltimasNoticias()
                "7" -> comentarioController.insertComentario()
                "8" -> comentarioController.getComentariosNoticia()
                "0" -> {
                    println("Saliendo del programa...")
                    return
                }
                else -> println("Opción no válida, intente de nuevo.")
            }
            println("Presione Enter para continuar...")
            readln()
            continue
        }
    }
}
