package es.iesraprog2425.pruebaes.app


import es.iesraprog2425.pruebaes.model.Operadores
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ControlDeArgumentos(val args: Array<String>) {
    var directorio = File("./log")
    private val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm")
    val fecha = LocalDateTime.now().format(formatter)

    fun gestionarOperacion(num1:Double,operador:Operadores,num2:Double):Double{
        return when(operador){
            Operadores.MULTIPLICACION -> num1 * num2
            Operadores.SUMA -> num1 + num2
            Operadores.RESTA -> num1 - num2
            Operadores.DIVISION -> num1 / num2
        }
    }

    fun iniciar(){

        if(args.isEmpty()){
            sinArgumentos()
        }

        else{
            when(args.size){
                1-> {
                    directorio = File(args[0])
                }

                4-> {
                    var mensaje= ""

                    try {
                        directorio = File(args[0])
                        val num1 = args[1].toDouble()
                        val operador = Operadores.getOperador(args[2].single()) ?: throw Exception("Operador inválido")
                        val num2 = args[3].toDouble()
                        val solucion = gestionarOperacion(num1,operador,num2)
                        mensaje = "$num1 $operador $num2 = $solucion "
                    }catch(e:Exception){
                        println("¡Error! Detalle: $e")
                        mensaje = "¡Error! Detalle: $e"
                    }

                    mensaje += fecha

                    val fichero = File("log$fecha.txt")
                    val creacion = fichero.createNewFile()

                    if(creacion){
                        println("$fichero creado con éxito")
                        fichero.appendText(mensaje + "\n")
                    }
                    else{
                        println("El fichero no se ha podido crear")
                    }
                }
                else-> throw IllegalArgumentException("ARGUMENTOS INTRODUCIDOS INVÁLIDOS")
            }
        }
    }

    private fun mostrarLineas(fichero:File){
        val ficheroStr = fichero.readText()
        val lineas = ficheroStr.split("\n")

        for(linea in lineas){
            println(linea)
        }
    }

    fun sinArgumentos() {
        if (!directorio.exists()) {

            val creacion = directorio.mkdir()

            if (creacion) {
                println("Ruta ./log creada")
            } else {
                println("Error al crear ruta log (Desconocido)")
            }
        } else {
            val listado = directorio.listFiles()

            if (listado.isEmpty()) {
                println("No existen ficheros en el log")
            } else {
                val ultimoModificado = directorio.lastModified()

                for (fichero in listado) {
                    if (fichero.lastModified() == ultimoModificado) {
                        val ficheroUltimo = fichero
                        val fichero = fichero
                        println("Abriendo fichero $fichero")
                        mostrarLineas(ficheroUltimo)
                    }
                }

            }
        }
    }
}