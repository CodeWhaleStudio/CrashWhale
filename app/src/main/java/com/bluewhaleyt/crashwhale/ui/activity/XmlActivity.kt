package com.bluewhaleyt.crashwhale.ui.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bluewhaleyt.crashwhale.R
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.utilities.DynamicColor

class XmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)

        val btnCrash = findViewById<Button>(R.id.btn_crash)

        btnCrash.setOnClickListener {
            throw Exception("This is a test exception.")
        }
    }
}