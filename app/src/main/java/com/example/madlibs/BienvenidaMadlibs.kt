package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BienvenidaMadlibs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida_mad_libs)
    }

    fun passSecondActivity(view: View){
        openIngresaPalabras()
    }

    fun openIngresaPalabras(){
        val intent = Intent(this, IngresaPalabras::class.java)
        startActivity(intent)
    }
}
