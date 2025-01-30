package org.example.utils

import org.example.controller.ComentarioController
import org.example.controller.NoticiaController
import org.example.controller.UserController

class Menu {
    private val noticiaController = NoticiaController()
    private val usuarioController = UserController()
    private val comentario = ComentarioController()

    fun menuUsuario(){
        while(true){
            println("---- BIENVENIDO ----")
            println("1 - Registrarse")
            println("2 - Iniaciar Sesion")
            println("0 - Salir")
            print("Elija una opción -> ")

            val opcion = readln()

            when(opcion){
                "1" -> {
                    usuarioController.registerUser()
                    menuNoticia()
                }
                "2" -> {
                    usuarioController.getUser()
                    menuNoticia()
                }
                "0" -> break
                else -> println("Opción no válida")
            }
        }
    }

    private fun menuNoticia(){
        while(true){
            println("---- MENÚ NOTICIAS ----")
            println("1 - Publicar noticia")
            println("2 - Buscar noticia")
            println("3 - Buscar noticias de usuaurio")
            println("4 - Obtener ultimas noticias")
            println("5 - Añadir comentario a una noticia")
            println("0 - Salir")
            print("Elija una opción -> ")

            val opcion = readln()

            when(opcion){
                "1" -> noticiaController.insertNoticia()
                "2" -> noticiaController.getNoticia()
                "3" -> noticiaController.getNoticias()
                "4" -> noticiaController.getUltimasNoticias()
                "5" -> comentario.insertComentario()
                "0" -> break
                else -> println("Opción no válida")
            }
        }
    }
}