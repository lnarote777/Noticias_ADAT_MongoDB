package org.example.utils

import org.example.controller.ComentarioController
import org.example.controller.NoticiaController
import org.example.controller.UserController

class Menu {
    private val noticiaController = NoticiaController()
    private val usuarioController = UserController()
    private val comentarioController = ComentarioController()

    fun menuUsuario() {
        while (true) {
            println("---- BIENVENIDO ----")
            println("1 - Registrarse")
            println("2 - Iniciar Sesión")
            println("0 - Salir")
            print("Elija una opción -> ")

            when (readln()) {
                "1" -> {
                    usuarioController.registerUser()
                    println("Registro exitoso. Ahora inicie sesión.")
                }
                "2" -> {
                    print("Ingrese su email: ")
                    val email = readln().trim()

                    val usuario = usuarioController.getUser(email)
                    if (usuario != null) {
                        println("Inicio de sesión exitoso. Bienvenido, ${usuario.nombre}!")
                        menuNoticia()
                    } else {
                        println("Error: Usuario no encontrado.")
                    }
                }
                "0" -> {
                    println("Saliendo del programa...")
                    return
                }
                else -> println("Opción no válida, intente de nuevo.")
            }
            println("Presione Enter para continuar...")
            readln()
        }
    }

    private fun menuNoticia() {
        while (true) {
            println("---- MENÚ NOTICIAS ----")
            println("1 - Publicar noticia")
            println("2 - Buscar noticia")
            println("3 - Buscar noticias de usuario")
            println("4 - Obtener últimas noticias")
            println("5 - Añadir comentario a una noticia")
            println("0 - Volver al menú principal")
            print("Elija una opción -> ")

            when (readln()) {
                "1" -> noticiaController.insertNoticia()
                "2" -> noticiaController.getNoticia()
                "3" -> noticiaController.getNoticiasUsuario()
                "4" -> noticiaController.getUltimasNoticias()
                "5" -> comentarioController.insertComentario()
                "0" -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
            println("Presione Enter para continuar...")
            readln()
        }
    }
}
