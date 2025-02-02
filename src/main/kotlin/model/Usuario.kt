package org.example.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty

data class Usuario(
    @BsonId
    val _id: String, //email
    val nombre: String,
    val nick: String,
    val estado: EstadoUsuario , //banned / not banned , active / inactive
    @BsonProperty("telefonos")
    val telf: List<String>,
    val direcion: Direccion
){
    override fun toString(): String {
        return "$_id  -  $nick ($nombre)"
    }
}

enum class EstadoUsuario {
    BANNED, NOT_BANNED, ACTIVE, INACTIVE
}
