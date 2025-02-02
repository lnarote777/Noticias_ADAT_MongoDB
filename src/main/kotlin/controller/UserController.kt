package org.example.controller

import org.example.model.Direccion
import org.example.model.EstadoUsuario
import org.example.model.Usuario
import org.example.service.UserService

class UserController {

    private val usuarioService = UserService()

    fun registerUser() {
        println("Ingrese sus datos")
        print("Email: ")
        val email = readln().trim()
        print("Nombre: ")
        val nombre = readln().trim()
        print("Nick: ")
        val nick = readln().trim()
        print("Teléfonos (separados por coma y espacio: <telf1, telf2>): ")
        val telf = readln().split(", ").map { it.trim() }
        print("Dirección (calle, número, código postal, ciudad, puerta (opcional)): ")
        val direc = readln().split(", ").map { it.trim() }

        // Validaciones
        if (email.isBlank() || !email.contains("@")) {
            println("Error: El email no es válido.")
            return
        }
        if (nombre.isBlank()) {
            println("Error: El nombre no puede estar vacío.")
            return
        }
        if (nick.isBlank()) {
            println("Error: El nick no puede estar vacío.")
            return
        }
        if (telf.isEmpty()) {
            println("Error: Debe ingresar al menos un número de teléfono.")
            return
        }
        if (direc.size < 4) {
            println("Error: Dirección incompleta. Formato esperado: calle, número, código postal, ciudad, puerta (opcional).")
            return
        }

        // Creación de la dirección
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

        // Creación del usuario
        val newUser = Usuario(
            _id = email,
            nombre = nombre,
            nick = nick,
            estado = EstadoUsuario.ACTIVE, // Estado por defecto
            telf = telf,
            direcion = direccion
        )

        // Guardar usuario en la base de datos
        usuarioService.registerUser(newUser)
        println("Usuario registrado correctamente.")
    }

    fun getUser(email: String): Usuario? {
        return usuarioService.buscarPorEmail(email)
    }

    fun updateUser(email: String, newUser: Usuario): Boolean {
        val existeUser = usuarioService.buscarPorEmail(email) ?: return false
        usuarioService.updateUser(email, newUser)
        return true
    }

    fun deleteUser(email: String) {
        val usuario = usuarioService.buscarPorEmail(email)
        if (usuario != null) {
            usuarioService.deleteUser(email)
            println("Usuario eliminado correctamente.")
        } else {
            println("Error: Usuario no encontrado.")
        }
    }



}