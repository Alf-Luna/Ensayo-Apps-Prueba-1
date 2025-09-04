package org.example

class GestorPedidos () {

    val pedido: MutableList<Producto>
    val menu: Map<Int, Producto>

    init {
        pedido = mutableListOf<Producto>()
        menu = iniciarMenu()
    }

    fun iniciarMenu(): Map<Int, Producto>{
        val pizza = Comida(
            nombre = "Pizza Pepperoni",
            precio = 12000.0,
            categoria = "Comida Italiana",
            tiempoPreparacionSegundos = 3.0,
            calidad = "Normal"
        )

        val empanada = Comida(
            nombre = "Empanada de Pino",
            precio = 3000.0,
            categoria = "Comida Chilena",
            tiempoPreparacionSegundos = 3.0,
            calidad = "Premium"
        )

        val jugo = Bebida(
            nombre = "Jugo de Naranja",
            precio = 2100.0,
            categoria = "Bebida Natural",
            tiempoPreparacionSegundos = 1.0,
            volumen = "Grande"
        )

        val cafe = Bebida(
            nombre = "Cafe",
            precio = 3500.0,
            categoria = "Bebida Caliente",
            tiempoPreparacionSegundos = 1.0,
            volumen = "chico"
        )

        val inventario = mapOf<Int, Producto>(1 to pizza, 2 to empanada, 3 to jugo, 4 to cafe)
        return inventario
    }

    fun mostrarMenu(){
        for (item in menu){
            println("" + item.key + ". " + item.value.getNombre())
        }
    }

    fun agregarItem(nuevoItem: Producto){
        pedido.add(nuevoItem)
    }
}