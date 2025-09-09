package org.example

class Comida(nombre: String, precio: Double, categoria: String, tiempoPreparacionSegundos: Double,
             private val calidad: String): Producto (nombre, precio, categoria, tiempoPreparacionSegundos) {

    fun getCalidad(): String{
        return calidad;
    }

    override fun mostrarInformacion(): String{
        return getNombre() + "(" + calidad + ") $" + getPrecio()
    }

}