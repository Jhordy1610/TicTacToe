package pe.edu.ulima.pm.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random // importamos la clase Random de java


class MainActivity : AppCompatActivity() {

    //Inicializamos las variables globales a nivel de la clase
    var turno = "j1"
    var jugador_1 = 'O'
    var jugador_2 = 'X'
    var reinicia = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //La función buttonClick() se ejecutará al hacer click en el respectivo botón
        b1.setOnClickListener() {
            buttonClick(b1)
        }
        b2.setOnClickListener() {
            buttonClick(b2)
        }
        b3.setOnClickListener() {
            buttonClick(b3)
        }
        b4.setOnClickListener() {
            buttonClick(b4)
        }
        b5.setOnClickListener() {
            buttonClick(b5)
        }
        b6.setOnClickListener() {
            buttonClick(b6)
        }
        b7.setOnClickListener() {
            buttonClick(b7)
        }
        b8.setOnClickListener() {
            buttonClick(b8)
        }
        b9.setOnClickListener() {
            buttonClick(b9)
        }

        //La función reset() se ejecutará al hacer click en el cuadro de texto
        textView.setOnClickListener() {
            reset()
        }
    }

    //Esta funcion será ejecutada cada vez que se haga click en un botón
    fun buttonClick(btn: Button) {
        //Se verifica si el juego necesita reiniciarse
        if (reinicia == false) {
            //Cuando uno de los botones esta vacío
            if (btn.text == "") {
                if (turno == "j1") {
                    turno = "j2" //Cambio de turno
                    btn.text =
                        "$jugador_1" //El boton pasa a tener el texto del jugador correspondiente
                    textView.text =
                        "Le toca al jugador $jugador_2.." //Se cambia el textView para indicar el turno del otro jugador
                } else {
                    turno = "j1"
                    btn.text = "$jugador_2"
                    textView.text = "Le toca al jugador $jugador_1.."
                }
            }
            //Por cada turno se verifica si hay un ganador o un posible empate
            win()
            //Cuando el juego debe reiniciarse, se disparan las 3 funciones
        } else {
            cambiarLetras()
            btnsFondo()
            reset()
        }
    }

    //Esta funcion determinara si un jugador gano o si resulto el juego en empate.
    //Verifica si la matriz de 9x9 tiene los siguientes estado
    //Posiblilidad 1: Gano un jugador, "X" se entiendo como jugador 1 o 2
    //Estados validos
    /*Arreglo       Arreglo         Arreglo         Arreglo
    * Vertical      Horizontal      Diagonal(1)     Diagonal(2)
    * | |X| |       | | | |         |X| | |         | | |X|
    * | |X| |       |X|X|X|         | |X| |         | |X| |
    * | |X| |       | | | |         | | |X|         |X| | |
    * Si se da esta condición, se debera de indicar el ganador y reiniciar el juego al siguiente
    * toque
    * */
    //Posibilidad 2: Empate
    //Se verificara si los botones no estan vacíos. Si todos los botones poseen un texto significa
    //que los jugadores empataron, se debe de indicar empate y el juego debe de reiniciarse al
    //siguiente toque.
    fun win() {
        //HORIZONTAL
        if ((b1.text == "$jugador_2" && b2.text == "$jugador_2" && b3.text == "$jugador_2") ||
            (b4.text == "$jugador_2" && b5.text == "$jugador_2" && b6.text == "$jugador_2") ||
            (b7.text == "$jugador_2" && b8.text == "$jugador_2" && b9.text == "$jugador_2") ||
            //DIAGONAL
            (b1.text == "$jugador_2" && b5.text == "$jugador_2" && b9.text == "$jugador_2") ||
            (b3.text == "$jugador_2" && b5.text == "$jugador_2" && b7.text == "$jugador_2") ||
            //VERTICAL
            (b1.text == "$jugador_2" && b4.text == "$jugador_2" && b7.text == "$jugador_2") ||
            (b2.text == "$jugador_2" && b5.text == "$jugador_2" && b8.text == "$jugador_2") ||
            (b3.text == "$jugador_2" && b6.text == "$jugador_2" && b9.text == "$jugador_2")
        ) {
            textView.text = "El jugador $jugador_2 ganó!" //Se indica el jugador ganador
            reinicia = true //La variable global indica que el juego debe reiniciarse
            turno = "j1" //Luego del reinicio, el turno siempre debe comenzar con j1
        } else if ((b1.text == "$jugador_1" && b2.text == "$jugador_1" && b3.text == "$jugador_1") ||
            (b4.text == "$jugador_1" && b5.text == "$jugador_1" && b6.text == "$jugador_1") ||
            (b7.text == "$jugador_1" && b8.text == "$jugador_1" && b9.text == "$jugador_1") ||
            //DIAGONAL
            (b1.text == "$jugador_1" && b5.text == "$jugador_1" && b9.text == "$jugador_1") ||
            (b3.text == "$jugador_1" && b5.text == "$jugador_1" && b7.text == "$jugador_1") ||
            //VERTICAL
            (b1.text == "$jugador_1" && b4.text == "$jugador_1" && b7.text == "$jugador_1") ||
            (b2.text == "$jugador_1" && b5.text == "$jugador_1" && b8.text == "$jugador_1") ||
            (b3.text == "$jugador_1" && b6.text == "$jugador_1" && b9.text == "$jugador_1")
        ) {
            textView.text = "El jugador $jugador_1 ganó!" //Se indica el jugador ganador
            reinicia = true //La variable global indica que el juego debe reiniciarse
            turno = "j1" //Luego del reinicio, el turno siempre debe comenzar con j1
        } else {
            if (
                b1.text != "" &&
                b2.text != "" &&
                b3.text != "" &&
                b4.text != "" &&
                b5.text != "" &&
                b6.text != "" &&
                b7.text != "" &&
                b8.text != "" &&
                b9.text != ""
            ) {
                textView.text = "Empate!" //Se indica el empate
                reinicia = true //La variable global indica que el juego debe reiniciarse
                turno = "j1" //Luego del reinicio, el turno siempre debe comenzar con j1
            }
        }
    }

    //Esta funcion reiniciará el texto de los botones a vacío y el valor booleano
    fun reset() {
        if (reinicia == true) {
            b1.text = ""
            b2.text = ""
            b3.text = ""
            b4.text = ""
            b5.text = ""
            b6.text = ""
            b7.text = ""
            b8.text = ""
            b9.text = ""
            textView.text = "Le toca al jugador $jugador_1.."
            reinicia = false
        }
    }

    //Esta función cambiará el fondo de los botones a un mismo color aleatorio
    fun btnsFondo() {
        val rnd = Random()
        //alpha : transparencia de un píxel. Rojo/verde/azul. rnd toma valores del 0 al 255
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        b1.setBackgroundColor(color)
        b2.setBackgroundColor(color)
        b3.setBackgroundColor(color)
        b4.setBackgroundColor(color)
        b5.setBackgroundColor(color)
        b6.setBackgroundColor(color)
        b7.setBackgroundColor(color)
        b8.setBackgroundColor(color)
        b9.setBackgroundColor(color)
    }

    //Asignará al jugador 1 y 2 nuevas letras diferentes y aleatorias
    fun cambiarLetras() {
        val alphabets = ('A'..'Z') //Arreglo de letras de A hasta la Z
        jugador_1 = alphabets.random()
        jugador_2 = alphabets.random()
        //Si las letras coinciden, se cambia la del jugador 2 hasta ser diferentes
        while (jugador_2 == jugador_1) {
            jugador_2 = alphabets.random()
        }

    }


}

