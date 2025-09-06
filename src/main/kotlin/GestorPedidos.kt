package org.example

import java.lang.NumberFormatException
import kotlin.math.round

class GestorPedidos () {

    val pedido: MutableList<Producto>
    val menu: Map<Int, Producto>

    init {
        pedido = mutableListOf<Producto>()
        menu = iniciarMenu()
    }

    fun iniciarMenu(): Map<Int, Producto> {
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
            volumen = "Chico"
        )

        val inventario = mapOf<Int, Producto>(1 to pizza, 2 to empanada, 3 to jugo, 4 to cafe)
        return inventario
    }

    fun mostrarMenu() {
        for (item in menu) {
            println("" + item.key + ". " + item.value.getNombre())
        }
    }

    fun tomarPedido(): Boolean{

        pedido.clear()
        mostrarMenu()
        print("Ingrese el numero de los productos que desea ordenar: ")
        var itemDeseado = readln()
        val inputsAdmitidas: String = "1234567890"
        var itemNum: Int = 0

        try {
            itemDeseado = itemDeseado.filter { it in inputsAdmitidas }
            for (num in itemDeseado){
                itemNum = num.toString().toInt() //char.toInt() esta depreciada. Por ende char.toString().toInt()
                val nuevoItem = menu.get(itemNum)!!
                pedido.add(nuevoItem)
            }

        } catch (e: NumberFormatException) {
            println("Error. Debe ingresar solo un numero, sin letras ni otros caracteres.")
            return false

        } catch (e: NullPointerException) {
            println("Error. El numero '" + itemNum +"' no corresponde a un item del menu. Ingrese su pedido nuevamente")
            return false
        }
        return true
    }

    fun calculoPrecioFinal(){

        val iva = 0.19
        var descuento: Double = 0.05
        var subtotal: Double = 0.0
        var totalConDescuento: Double
        var totalFinal: Double

        for (item in pedido){
            subtotal += item.getPrecio()
        }

        print("Ingrese el tipo de cliente que usted es (VIP / Premium / normal): ")
        var tipoCliente: String? = readln()
        val caracteresNoDeseados = "`~!@#$%^&*()_={}[]<>|?,./*-+1234567890 "
        tipoCliente = tipoCliente?.uppercase()
        tipoCliente = tipoCliente?.filterNot { it in caracteresNoDeseados }

        when (tipoCliente){
            "VIP" -> descuento = 0.1

            "PREMIUM" -> {
                descuento = 0.15
                tipoCliente = "Premium"
            }

            else ->  tipoCliente = "Regular"
        }

        totalConDescuento = subtotal - subtotal * descuento
        totalFinal = totalConDescuento + totalConDescuento * iva

        println("------------RESUMEN PEDIDO------------")
        for (item in pedido){
            println("-" + item.getNombre() + " $" + item.getPrecio())
        }
        println("Subtotal: $" + subtotal)
        println("Descuento " + tipoCliente + ": -$" + (subtotal * descuento))
        println("IVA (" + (iva * 100) + "%): $" + (subtotal * iva))
        println("Total: $" + totalFinal)
    }
}