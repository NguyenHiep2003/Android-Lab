package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.happybirthday.models.MailModel
import vn.edu.hust.listexamples.adapters.MailAdapter

class SimpleListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.simple_list_activity)
        val list = mutableListOf<Int>()


        val showButton = findViewById<Button>(R.id.show)
        showButton.setOnClickListener {
            val inputText = findViewById<EditText>(R.id.input).text
            val errorText = findViewById<TextView>(R.id.error);
            if(inputText.length == 0) {
                errorText.setText("Enter your input first!")
                errorText.visibility = View.VISIBLE
            } else {
                errorText.visibility = View.GONE
                val input = inputText.toString().toInt();
                val groupRadio = findViewById<RadioGroup>(R.id.groupRadio);
                val selectedId = groupRadio.checkedRadioButtonId;
                var list: MutableList<Int>;
                if(selectedId == R.id.even) {
                    list = getEven(input);
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
                    val listResult = findViewById<ListView>(R.id.list)
                    listResult.adapter = adapter
                } else if(selectedId == R.id.odd) {
                    list = getOdd(input);
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
                    val listResult = findViewById<ListView>(R.id.list)
                    listResult.adapter = adapter
                } else if(selectedId == R.id.cp) {
                    list = getCP(input);
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
                    val listResult = findViewById<ListView>(R.id.list)
                    listResult.adapter = adapter
                }

            }
        }

    }

    fun getEven(n : Int): MutableList<Int> {
        val res = mutableListOf<Int>()
        for(i in 0..n) {
            if(i % 2 == 0) res.add(i);
        }
        return res;
    }
    fun getOdd(n : Int): MutableList<Int> {
        val res = mutableListOf<Int>()
        for(i in 0..n) {
            if(i % 2 != 0) res.add(i);
        }
        return res;
    }
    fun getCP(n : Int): MutableList<Int> {
        val res = mutableListOf<Int>()
        var i = 0;
        while(i * i <= n) {
            res.add(i * i);
            i++;
        }
        return res;
    }
}