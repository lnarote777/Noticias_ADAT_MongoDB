package org.example.model

data class Direccion(
    val calle: String,
    val numero: String,
    val puerta: String? = null,
    val cp: String,
    val ciudad: String
){
    override fun toString(): String {
        if (puerta == null){
            return "C/ $calle $numero  -  $cp $ciudad\n"
        }else{
            return "C/ $calle $numero, $puerta  -  $cp $ciudad\n"
        }

    }
}