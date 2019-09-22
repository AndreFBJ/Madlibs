package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_ingresa_palabras.*
import java.util.*

class IngresaPalabras : AppCompatActivity() {

    private lateinit var myAdapter: ArrayAdapter<String>
    private val palabras = ArrayList<String>()
    private val palabrasType = ArrayList<String>()
    private var aux = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresa_palabras)
    }

    fun extractstory(){

        val builder = StringBuilder()
        val inputStream = resources.openRawResource(R.raw.madlib0_simple)
        val scanner = Scanner(inputStream)
        scanner.close()

        while(scanner.hasNextLine()){
            val line = scanner.nextLine()
            builder.append(line)
        }
        var story = builder.toString()
        val r = Regex("<.*?>")
        val found = r.findAll(R.raw.madlib0_simple.toString())
        found.forEach{ f ->
            val m = f.value
            palabrasType.add(m)
            aux++
        }
        val first_type = palabrasType.get(0)
        editText2.hint = "por favor ingrese $first_type"
    }



    fun addWordToStory(){
        if(editText2.text.toString().isEmpty() || editText2.text.toString().trim().isEmpty()){
            val Toast = Toast.makeText(this, "Ingrese la palabra!", Toast.LENGTH_SHORT)
            Toast.show()
        }
        else{
            val word = editText2.text.toString().trim()
            palabras.add(word)
            aux--
            editText2.setText("")
            if(aux >= 1){
                val next_type = palabrasType.get(palabrasType.size - aux)
                editText2.hint = "Ungrese $next_type"
            }
            if(aux == 1)
                buttonAddWord.text = "LISTO!"


            if(aux == 0){
                val intent = Intent(this, HistoriaMadLibs::class.java)
                intent.putExtra("inputs", palabras)
                startActivity(intent)
            }
        }
    }
}





