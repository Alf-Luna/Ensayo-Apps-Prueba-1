package org.example

import java.lang.NumberFormatException
import kotlin.math.round
import kotlinx.coroutines.*
import EstadoPedido

class GestorPedidos () {

    //map para el menu, acceso mas facil. Lista para el pedido del usuario
    val pedido: MutableList<Producto>
    val menu: Map<Int, Producto>

    //inicia variables de gestor de pedidos
    init {
        pedido = mutableListOf<Producto>()
        menu = iniciarMenu()
    }

    //funcion para iniciar el menu
    fun iniciarMenu(): Map<Int, Producto> {
        val pizza = Comida(
            nombre = "Pizza Pepperoni",
            precio = 12000.0,
            categoria = "Comida Italiana",
            tiempoPreparacionSegundos = 1.0,
            calidad = "Normal"
        )

        val empanada = Comida(
            nombre = "Empanada de Pino",
            precio = 3000.0,
            categoria = "Comida Chilena",
            tiempoPreparacionSegundos = 1.0,
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

    fun mostrarMenu() {
        for (item in menu) {
            println("" + item.key + ". " + item.value.getNombre())
        }
    }

    fun tomarPedido(): Boolean {

        mostrarMenu()

        var itemDeseado = readln()

        //coleccion (String) con valores permitidos para ingresar productos. Filtrado de la coleccion.
        val inputsAdmitidas: String = "123456789"
        itemDeseado = itemDeseado.filter { it in inputsAdmitidas }

        try {
            //Para cada numero ingresado como pedido, se revisa si se encuentra en el menu
            for (num in itemDeseado){
                val itemNum = num.toString().toInt() //char.toInt() esta depreciada. Por ende char.toString().toInt()
                val nuevoItem = menu.get(itemNum)!! //!! para evitar que kotlin maneje nulos por si solo.
                pedido.add(nuevoItem)               //considerado en el try catch el manejo de estos
            }

        } catch (e: NumberFormatException) {
            println("Error. Debe ingresar solo un numero, sin letras ni otros caracteres.")
            return false

        } catch (e: NullPointerException) {
            println("Error. El numero ingresado no corresponde a un item del menu.")
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
        val caracteresNoDeseados = "`~!@#$%^&*()_={}[]<>|?,./*-+1234567890"
        tipoCliente = tipoCliente?.uppercase()
        //filtrado de el tipo de cliente ingresado por el usuario. Se deshace de cualquier caracter no del alfabeto
        tipoCliente = tipoCliente?.filterNot { it in caracteresNoDeseados }

        //ajuste descuento tipo cliente
        when (tipoCliente){
            "VIP" -> descuento = 0.1

            "PREMIUM" -> {
                descuento = 0.15
                tipoCliente = "Premium"
            }

            else ->  tipoCliente = "Regular"
        }

        //calculo total final
        totalConDescuento = kotlin.math.round(subtotal - subtotal * descuento)
        totalFinal = kotlin.math.round(totalConDescuento + totalConDescuento * iva)

        //print boleta
        println("------------RESUMEN PEDIDO------------")
        for (item in pedido){
            println("-" + item.getNombre() + " $" + item.getPrecio())
        }
        println("Subtotal: $" + subtotal)
        println("Descuento " + tipoCliente + ": -$" + (kotlin.math.round(subtotal * descuento)))
        println("IVA (" + (iva * 100) + "%): $" + (kotlin.math.round(subtotal * iva)))
        println("Total: $" + totalFinal)
    }

    suspend fun prepararPedido(){

        var tiempo: Double = 0.0

        //calculo tiempo de espera
        for (item in pedido){
            tiempo += item.getTiempoPreparacionSegundos()
        }
        val tiempoEspera: Long = tiempo.toLong()

        println("-----------------------")
        println("Pedido recibido")
        println("Tiempo de espera aproximado: " + tiempo + "s")

        //manejo de estados y delay
        mostrarEstado(EstadoPedido.Ingresado)
        mostrarEstado(EstadoPedido.EnPreparacion)
        delay(tiempoEspera * 1000)
        mostrarEstado(EstadoPedido.ListoParaRetiro)
    }

    //manejo de los distintos tipos de estado de pedido
    fun mostrarEstado(estadoPedido: EstadoPedido){
        when(estadoPedido){
            EstadoPedido.EnPreparacion -> println("Estado Pedido: En Preparacion")
            EstadoPedido.Ingresado -> println("Estado Pedido: Ingersado")
            EstadoPedido.ListoParaRetiro -> println("Estado Pedido: Listo para retirar")
        }
    }
}