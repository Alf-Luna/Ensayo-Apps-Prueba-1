import org.example.GestorPedidos
import kotlinx.coroutines.*


suspend fun main() {

    var pedidoExitoso: Boolean = false
    val operador: GestorPedidos = GestorPedidos()

    println("Bienvenido al sistema de compras FoodExpress")
    while (pedidoExitoso == false){
        pedidoExitoso = operador.tomarPedido()
    }
    operador.calculoPrecioFinal()
    operador.prepararPedido()
}