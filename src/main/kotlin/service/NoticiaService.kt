package org.example.service

import com.mongodb.client.model.Filters
import org.bson.Document
import org.example.model.Noticia
import org.example.utils.ConnectionDB

class NoticiaService {

    private val bd = ConnectionDB.getDatabase("blogNoticias")
    private val collection = bd.getCollection("collNoticias", Noticia::class.java)

    fun insertNoticia(noticia: Noticia): Boolean {
        val existingNoticia = collection.find(Filters.eq("titulo", noticia.titulo)).firstOrNull()
        return if (existingNoticia == null) {
            collection.insertOne(noticia)
            true
        } else {
            false
        }
    }

    fun getNoticiasUsuario(email: String): List<Noticia>? {
        return try {
            val noticias = collection.find(Filters.eq("user._id", email)).toList()

            noticias.ifEmpty {
                println("No se encontraron noticias para este usuario.")
                null
            }
        } catch (e: Exception) {
            println("Error al obtener las noticias del usuario: ${e.message}")
            null
        }
    }

    fun getUltimasNoticias(): List<Noticia>? {
        return try {
            val noticias = collection.find()
                .sort(Document("fecha_publicacion", -1))
                .limit(10)
                .toList()

            noticias.ifEmpty {
                println("No hay noticias disponibles.")
                null
            }
        } catch (e: Exception) {
            println("Error al obtener las últimas noticias: ${e.message}")
            null
        }
    }

    fun deleteNoticia(titulo: String): Boolean {
        return try {
            val result = collection.deleteOne(Filters.eq("titulo", titulo))

            if (result.deletedCount > 0) {
                println("Noticia eliminada con éxito.")
                true
            } else {
                println("No se encontró una noticia con ese ID.")
                false
            }
        } catch (e: Exception) {
            println("Error al eliminar la noticia: ${e.message}")
            false
        }
    }

    fun getNoticia(titulo: String): Noticia? {
        return try {
            val noticia = collection.find(Filters.eq("titulo", titulo)).firstOrNull()

            if (noticia != null) {
                noticia
            } else {
                println("No se encontró una noticia con ese titulo.")
                null
            }
        } catch (e: Exception) {
            println("Error al obtener la noticia: ${e.message}")
            null
        }
    }

    fun getNoticiasByTag(tag: String): List<Noticia>? {
        return try {
            val noticias = collection.find(Filters.eq("tags", tag)).toList()

            noticias.ifEmpty {
                println("No se encontraron noticias con la etiqueta '$tag'.")
                null
            }

            noticias
        } catch (e: Exception) {
            println("Error al obtener las noticias por etiqueta: ${e.message}")
            null
        }
    }



}