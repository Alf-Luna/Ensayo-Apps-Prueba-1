package org.example

open class Producto(private var nombre: String, private var precio: Double, private var categoria: String,
                    private var tiempoPreparacionSegundos: Double) {

    fun getNombre(): String {
        return nombre
    }

    fun getPrecio(): Double {
        return precio
    }

    fun getCategoria(): String {
        return categoria
    }

    fun getTiempoPreparacionSegundos(): Double {
        return tiempoPreparacionSegundos
    }
}