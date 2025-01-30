package org.example.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty

data class Usuario(
    @BsonId
    val _id: String, //email
    val nombre: String,
    val nick: String,
    val estado: Boolean, //banned /not banned , active / offline
    @BsonProperty("telefonos")
    val telf: List<String>,
    val direcion: Direccion


)
