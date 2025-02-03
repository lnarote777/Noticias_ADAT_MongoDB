package org.example.service

import com.mongodb.client.model.Filters
import org.example.model.Usuario
import org.example.utils.ConnectionDB

class UserService {
    private val bd = ConnectionDB.getDatabase("blogNoticias")
    private val collection = bd.getCollection("collUsuarios", Usuario::class.java)

    fun registerUser(user: Usuario): Boolean {
        return try {
            val exist = getUser(user._id)

            if (exist != null){
                return false
            }

            collection.insertOne(user)
            true
        }catch (e: Exception){
            println("Error al registrar usuario: ${e.message}")
            false
        }

    }

    fun getUser(email: String): Usuario? {
        return try {
            collection.find(Filters.eq("_id", email)).firstOrNull() ?: return null
        } catch (e: Exception) {
            println("Error al buscar usuario: ${e.message}")
            null
        }
    }

    fun updateUser(email: String, newUser: Usuario): Boolean {
        return try {
            val result = collection.replaceOne(Filters.eq("_id", email), newUser)
            if (result.matchedCount > 0) {
                true
            } else {
                false
            }
        } catch (e: Exception) {
            println("Error al actualizar usuario: ${e.message}")
            false
        }
    }

    fun deleteUser(email: String): Boolean {
        return try {
            val result = collection.deleteOne(Filters.eq("_id", email))
            if (result.deletedCount > 0) {
                true
            } else {
                false
            }
        } catch (e: Exception) {
            println("Error al eliminar usuario: ${e.message}")
            false
        }
    }
}