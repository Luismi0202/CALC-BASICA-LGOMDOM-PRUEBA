package es.iesraprog2425.pruebaes

import es.iesraprog2425.pruebaes.app.Calculadora
import es.iesraprog2425.pruebaes.app.ControlDeArgumentos
import es.iesraprog2425.pruebaes.ui.Consola

fun main(args: Array<String>) {
    val control = ControlDeArgumentos(args)
    control.iniciar()

    println("Pulsa ENTER para continuar")
    readln()
    Calculadora(Consola(),control.fecha,control.directorio).iniciar()
}


