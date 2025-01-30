package org.example.model

data class Direccion(
    val calle: String,
    val numero: String,
    val puerta: String? = null,
    val cp: String,
    val ciudad: String
)