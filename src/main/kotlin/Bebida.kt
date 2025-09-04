package org.example

class Bebida(nombre: String, precio: Double, categoria: String, tiempoPreparacionSegundos: Double,
             private val volumen: String): Producto (nombre, precio, categoria, tiempoPreparacionSegundos){

    fun getVolumen(): String{
        return volumen;
    }
}