package org.example.repository

import org.example.model.Usuario
import org.example.utils.ConnectionDB

class UserRepository {
    private val bd = ConnectionDB.getDatabase("blogNoticias")
    private val collection = bd.getCollection("collUsuarios")

    fun registerUser(user: Usuario){

    }

    fun getUser(): Usuario? {
        return null
    }
}