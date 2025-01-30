package org.example

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.example.model.Direccion
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.utils.ConnectionDB
import java.time.Instant
import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
// Abrir la conexión con la BD
    val database = ConnectionDB.getDatabase("adaprueba")

    // Obtener la colección
    val collection: MongoCollection<Usuario> = database.getCollection("collUsuarios", Usuario::class.java)

    try {
        // Declarar un cliente y una direccion
        val direccion = Direccion("alamo", "24", "04638", "Mojacar")
        val cliente = Usuario("maria@gmail.com", "Maria", "mar14", true, listOf("950475656", "666888999"), direccion)

        collection.insertOne(cliente)

        val direccion2 = Direccion("desconocida", "24", "04003", "Almeria")
        val direccion3 = Direccion("principal", "2", "04003", "Almeria")
        val direccion4 = Direccion("principal", "1", "04003", "Almeria")

        val cliente2 = Usuario("pedro@gmail.com", "Pedro", "periko", true, listOf("950475656", "666888999"), direccion2)
        val cliente3 = Usuario("ana@gmail.com", "Ana", "anuski", true, listOf("950475656", "666888999"), direccion3)
        val cliente4 = Usuario("antonio@gmail.com", "Antonio", "toni", true, listOf("950475656", "666888999"), direccion4)
        val cliente5 = Usuario("agustin@gmail.com", "Agustin", "agus", true, listOf("950475656", "666888999"), direccion4)

        val listaClientes = listOf<Usuario>(
            cliente2, cliente3, cliente4, cliente5
        )

        //collection.insertMany(listaClientes)


        val filtro = Filters.eq("direcion.cp","04003")

        print(collection.find(filtro).count())

        val collNoticia: MongoCollection<Noticia> = database.getCollection("collNoticias", Noticia::class.java)

        val noticias = listOf(
            Noticia("10 Free Cozy Games on Steam", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ) ,
            Noticia("Nintendo Switch 2. Que hay nuevo ?", "Tiene bordes redondos e imanes :).", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente2._id  ),
            Noticia("Programador se tira 24h con el mismo problema", "Nico se tira 24h con un mismo problema y aun no lo soluciona.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente3._id  ),
            Noticia("Animal Crossing Pocket Camp rebaja su precio", "El juego para movil de animal crossing rebaja su precio a 9,99 hasta el 31-01 de este 2025.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ),
            Noticia("Descubre tu casa de Hogwarts", "Haz este test para saber a que casa de la famosa escuela de magia de harry potter perteneces..", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ),
            Noticia("Ganadores de la Malaga Jam", "Este es el grupo ganador de la malaga Jam. El grupo UwU gano este gran evento con una gran ventaja sobre los demás.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente._id  ),
            Noticia("", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ),
            Noticia("", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente._id  ),
            Noticia("", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ),
            Noticia("", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ),
            Noticia("", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  ),
            Noticia("", "10 juegos de Steam gratis para jugar relajado en casa.", Date.from(Instant.now()), listOf("Games", "Sateam", "free to play"), cliente4._id  )
        )





    } catch (e: Exception) {
        println("Clave duplicada")
    }



    ConnectionDB.close()
}