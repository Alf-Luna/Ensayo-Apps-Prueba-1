package org.example

class Comida(nombre: String, precio: Double, categoria: String, tiempoPreparacionSegundos: Double,
             private val calidad: String): Producto (nombre, precio, categoria, tiempoPreparacionSegundos) {

    fun getCalidad(): String{
        return calidad;
    }

    override fun mostrarInformacion(): String{
        return getNombre() + "(" + calidad + ") $" + getPrecio()
    }

    //override de get de precio con logica para comida premium
    override fun getPrecio(): Double {
        var precioComida: Double = super.getPrecio()
        if (calidad == "Premium"){
            precioComida*= 1.15
            precioComida = kotlin.math.round(precioComida)
        }
        return precioComida
    }
}