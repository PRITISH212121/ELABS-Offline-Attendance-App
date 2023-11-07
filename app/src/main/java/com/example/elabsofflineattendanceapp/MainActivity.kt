package com.example.elabsofflineattendanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.startscanbutton)
        rollButton.setOnClickListener {
            val intent = Intent(this, QR_Scanner::class.java)
            startActivity(intent)
        }


    }


    }
