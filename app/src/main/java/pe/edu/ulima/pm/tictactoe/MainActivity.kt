package pe.edu.ulima.pm.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random // importamos la clase Random de java


class MainActivity : AppCompatActivity() {

    var turno = "j1"
    var jugador_1 = 'O'
    var jugador_2 = 'X'
    var reinicia = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = "Le toca al jugador ${jugador_1}.."

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

        textView.setOnClickListener() {
            reset()
        }
    }
    //Esta funcion sera ejecutada cada vez que se presiona un boton
    fun buttonClick(btn: Button) {
        //Se verifica que el juego necesita reiniciase
        if (reinicia == false) {
            if (btn.text == "") {
                //Cuando uno de los botones esta vacio
                if (turno == "j1") {
                    //Cambio de turno
                    turno = "j2"
                    //El boton pasa a tener el texto del jugador correspondiente
                    btn.text = "$jugador_1"
                    //Se cambia el textView para indicar al otro jugador
                    textView.text = "Le toca al jugador $jugador_2.."
                } else {
                    turno = "j1"
                    btn.text = "$jugador_2"
                    textView.text = "Le toca al jugador $jugador_1.."
                }
            }
            //VERIFICAR SI GANO
            win()
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
    * Si se da esta condicion, se debera de indicar el ganador y reiniciar el juego al siguiente
    * toque
    * */
    //Posibilidad 2: Empate
    //Se verificara si los botones no esta vacios. Si todos los botones poseen un texto significa
    //que los jugadores empataron, se debe de indicar empate y el juego debe de reiniciarse al
    //siguiente toque
    fun win() {
            //HORIZONTAL
        if ((b1.text == "$jugador_2" && b2.text == "$jugador_2" && b3.text == "$jugador_2") ||
            (b4.text == "$jugador_2" && b5.text == "$jugador_2" && b6.text == "$jugador_2") ||
            (b7.text == "$jugador_2" && b8.text == "$jugador_2" && b9.text == "$jugador_2") ||
            //DIAGONAL
            (b1.text == "$jugador_2" && b5.text == "$jugador_2" && b9.text == "$jugador_2") ||
            (b3.text == "$jugador_2" && b5.text == "$jugador_2" && b7.text == "$jugador_2") ||
            //COLUMNAS
            (b1.text == "$jugador_2" && b4.text == "$jugador_2" && b7.text == "$jugador_2") ||
            (b2.text == "$jugador_2" && b5.text == "$jugador_2" && b8.text == "$jugador_2") ||
            (b3.text == "$jugador_2" && b6.text == "$jugador_2" && b9.text == "$jugador_2")
        ) {
            textView.text = "El jugador $jugador_2 ganó!"
            reinicia = true
            turno = "j1"
        } else if ((b1.text == "$jugador_1" && b2.text == "$jugador_1" && b3.text == "$jugador_1") ||
            (b4.text == "$jugador_1" && b5.text == "$jugador_1" && b6.text == "$jugador_1") ||
            (b7.text == "$jugador_1" && b8.text == "$jugador_1" && b9.text == "$jugador_1") ||
            //DIAGONAL
            (b1.text == "$jugador_1" && b5.text == "$jugador_1" && b9.text == "$jugador_1") ||
            (b3.text == "$jugador_1" && b5.text == "$jugador_1" && b7.text == "$jugador_1") ||
            //COLUMNAS
            (b1.text == "$jugador_1" && b4.text == "$jugador_1" && b7.text == "$jugador_1") ||
            (b2.text == "$jugador_1" && b5.text == "$jugador_1" && b8.text == "$jugador_1") ||
            (b3.text == "$jugador_1" && b6.text == "$jugador_1" && b9.text == "$jugador_1")
        ) {
            textView.text = "El jugador $jugador_1 ganó!"
            reinicia = true
            turno = "j1"
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
                textView.text = "Empate!"
                reinicia = true
                turno = "j1"
            }
        }
    }
    //Esta funcion reininicara el texto de los botones y el valor booleano
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
    //Cambiara los botones a un mismo color aleatorio
    fun btnsFondo() {
        val rnd = Random()
        //alpha = transparencia de un píxel, rojo/verde/azul, rnd toma valores del 0 al 255
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
    //Asignara al jugador 1 y 2 letras diferentes aleatorias
    fun cambiarLetras() {
        val alphabets = ('A'..'Z')
        jugador_1 = alphabets.random()
        jugador_2 = alphabets.random()
        while(jugador_2 == jugador_1) {
            jugador_2 = alphabets.random()
        }

    }






}

