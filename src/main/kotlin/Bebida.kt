package org.example

class Bebida(nombre: String, precio: Double, categoria: String, tiempoPreparacionSegundos: Double,
             private val volumen: String): Producto (nombre, precio, categoria, tiempoPreparacionSegundos){

    fun getVolumen(): String{
        return volumen;
    }

    override fun mostrarInformacion(): String{
        return getNombre() + "(" + volumen + ") $" + getPrecio()
    }

    //override de get de precio con logica para bebida por volumen
    override fun getPrecio(): Double {
        var precioBebida: Double = super.getPrecio()
        if (volumen == "Grande"){
            precioBebida *= 1.15
        }else if(volumen == "Chico"){
            precioBebida *=0.95
        }
        precioBebida = kotlin.math.round(precioBebida)
        return precioBebida
    }
}