package com.example.happybirthday

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged

class CalculatorCurrency : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var symbol: Array<String>
    lateinit var cur1: TextView
    lateinit var cur2: TextView
    lateinit var editText1: EditText
    lateinit var editText2: EditText

    var state = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.currency_calculator)
        val currencies = resources.getStringArray(R.array.currency)
        symbol = resources.getStringArray(R.array.symbol)
        val ratioWithVND = arrayOf(1,25345,750,32907,166)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        if (spinner2 != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, currencies
            )
            spinner2.adapter = adapter
            spinner2.setSelection(0)
        }
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        if (spinner3 != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, currencies
            )
            spinner3.adapter = adapter
            spinner3.setSelection(1)
        }
        editText1 = findViewById<EditText>(R.id.edit1)
        editText2 = findViewById<EditText>(R.id.edit2)
         cur1 = findViewById<TextView>(R.id.currency1)
         cur2 = findViewById<TextView>(R.id.currency2)
        cur1.text = symbol[0]
        cur2.text = symbol[1]
        editText1.setOnClickListener {
            state = 0
            editText1.setTypeface(null, Typeface.BOLD)
            editText2.setTypeface(null, Typeface.NORMAL)
        }

        editText2.setOnClickListener {
            state = 1
            editText2.setTypeface(null, Typeface.BOLD)
            editText1.setTypeface(null, Typeface.NORMAL)
        }
        spinner2.onItemSelectedListener = this;
        spinner3.onItemSelectedListener = this
        editText1.doOnTextChanged { text, start, before, count ->
            if(text?.length != 0 && state == 0) {
                val source = text.toString().toDouble();
                val dest =
                    source * ratioWithVND[spinner2.selectedItemPosition].toDouble() / ratioWithVND[spinner3.selectedItemPosition].toDouble()
                Log.d("hi", dest.toString())
                editText2.setText(String.format("%.2f",dest))
            } else if (text?.length == 0 && state == 0) {
                editText2.setText("0")
            }
        }
        editText2.doOnTextChanged { text, start, before, count ->
            if(text?.length != 0 && state == 1) {
                val source = text.toString().toDouble();
                val dest = source * ratioWithVND[spinner3.selectedItemPosition].toDouble() / ratioWithVND[spinner2.selectedItemPosition].toDouble()
                editText1.setText(String.format("%.2f",dest))
            } else if (text?.length == 0 && state == 1) {
                editText1.setText("0")
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val id = p0?.id;
        if(id == R.id.spinner2) {
            findViewById<TextView>(R.id.currency1).setText(symbol[p2])

        }
        else if(id == R.id.spinner3) {
            findViewById<TextView>(R.id.currency2).setText(symbol[p2])
        }
        if(state == 0)  {
            editText1.text = editText1.text
        } else if (state ==1) {
            editText2.text = editText2.text
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}