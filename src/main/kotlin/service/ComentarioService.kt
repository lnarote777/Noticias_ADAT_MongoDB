package org.example.service

import com.mongodb.client.model.Filters
import org.example.model.Comentario
import org.example.model.Noticia
import org.example.utils.ConnectionDB

class ComentarioService {
    private val db = ConnectionDB.getDatabase("blogNoticias")
    private val collection = db.getCollection("collComentarios", Comentario::class.java)

    fun insertComentario(comentario: Comentario): Boolean {
        collection.insertOne(comentario)
        return true
    }

    fun getComentariosByNoticia(noticiaId: String): List<Comentario> {
        return collection.find(Filters.eq("noticia.titulo", noticiaId)).toList()
    }
}