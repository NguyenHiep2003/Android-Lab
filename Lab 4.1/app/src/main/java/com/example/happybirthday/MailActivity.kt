package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.happybirthday.models.MailModel
import vn.edu.hust.listexamples.adapters.MailAdapter

class MailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mail)
        val mails = mutableListOf<MailModel>()
        repeat(8) {
            mails.add(
                MailModel(resources.getIdentifier("avatar0","drawable", packageName),"Test Email","Hom nay la ngay 28/10/2024, em la Nguyen Phuc Hiep xin phep nop bai","11:30 PM","Nguyen Hiep" )
            )
        }

        val adapter = MailAdapter(mails)

        val listMails = findViewById<ListView>(R.id.list_mail)
        listMails.adapter = adapter

    }
}