package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_historia_mad_libs.*
import java.util.*
import kotlin.collections.ArrayList

class HistoriaMadLibs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia_mad_libs)
        val inputs = intent.getStringArrayListExtra("inputs")
        write(inputs)
    }

    fun write(inputs: ArrayList<String>){
        val builder = StringBuilder()
        val reader = Scanner (resources.openRawResource(R.raw.madlib0_simple))
        val first_line = reader.nextLine()
        builder.append(first_line)
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            builder.append(" ")
            builder.append(line)
    }

        var story = builder.toString()

        val r = Regex("<.*?>")
        val blanks = r.findAll(story).map { it.value }
        var i: Int = 0
        for(blank in blanks){
            story = story.replaceFirst(blank, inputs.get(i))
            i++
        }
        textViewStory.text = "$story"
        }

    fun finish(view: View){
        val intent = Intent(this, BienvenidaMadlibs::class.java)
        startActivity(intent)
    }
}
