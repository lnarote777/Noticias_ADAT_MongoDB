package org.example.controller

import org.example.model.Direccion
import org.example.model.EstadoUsuario
import org.example.model.Usuario
import org.example.service.UserService

class UserController {

    private val usuarioService = UserService()

    fun registerUser() {
        println("Ingrese sus datos")

        var email: String
        while (true) {
            print("Email: ")
            email = readln().trim()

            if (email.isBlank() || !email.contains("@")) {
                println("Error: El email no es válido.")
            }else{
                break
            }
        }

        var nombre : String
        while (true) {
            print("Nombre: ")
            nombre = readln().trim()

            if (nombre.isBlank()) {
                println("Error: El nombre no puede estar vacío.")
            }else{
                break
            }
        }

        var nick: String
        while (true) {
            print("Nick: ")
            nick = readln().trim()

            if (nick.isBlank()) {
                println("Error: El nick no puede estar vacío.")
            }else{
                break
            }
        }

        var telf: List<String>
        while (true) {
            print("Teléfonos (separados por coma y espacio: <telf1, telf2>): ")
            telf = readln().split(", ").map { it.trim() }

            if (telf.isEmpty()) {
                println("Error: Debe ingresar al menos un número de teléfono.")
            }else{
                break
            }
        }

        var direc: List<String>
        while (true) {
            print("Dirección (calle, número, código postal, ciudad, puerta (opcional)): ")
            direc = readln().split(", ").map { it.trim() }

            if (direc.size < 4) {
                println("Error: Dirección incompleta. Formato esperado: calle, número, código postal, ciudad, puerta (opcional).")
            }else {
                break
            }
        }

        val direccion: Direccion = if (direc.size == 5) {
            Direccion(
                calle = direc[0],
                numero = direc[1],
                cp = direc[2],
                ciudad = direc[3],
                puerta = direc[4]
            )
        } else {
            Direccion(
                calle = direc[0],
                numero = direc[1],
                cp = direc[2],
                ciudad = direc[3]
            )
        }

        val newUser = Usuario(
            _id = email,
            nombre = nombre,
            nick = nick,
            estado = EstadoUsuario.ACTIVE,
            telf = telf,
            direcion = direccion
        )

        val result = usuarioService.registerUser(newUser)
        if (result) {
            println("Usuario registrado correctamente.")
        } else {
            println("Error: El usuario ya existe.")
        }
    }

    fun getUser(): Usuario? {
        print("Email: ")
        val email = readln().trim()
        return usuarioService.getUser(email)
    }

    fun updateUser(email: String, newUser: Usuario): Boolean {
        val existeUser = usuarioService.getUser(email) ?: return false
        usuarioService.updateUser(email, newUser)
        return true
    }

    fun deleteUser(email: String) {
        val usuario = usuarioService.getUser(email)
        if (usuario != null) {
            usuarioService.deleteUser(email)
            println("Usuario eliminado correctamente.")
        } else {
            println("Error: Usuario no encontrado.")
        }
    }
}