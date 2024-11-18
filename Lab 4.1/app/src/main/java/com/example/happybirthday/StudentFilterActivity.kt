package com.example.happybirthday

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.happybirthday.models.StudentModel
import vn.edu.hust.listexamples.adapters.MailAdapter


class StudentFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.student_filter_activity)
        val students = mutableListOf<StudentModel>()
        students.add(StudentModel("Nguyễn Phúc Hiệp", "20215367"))
        students.add(StudentModel("Trần Văn Dũng", "20215368"))
        students.add(StudentModel("Lê Thị Bích", "20215369"))
        students.add(StudentModel("Phạm Minh Đức", "20215370"))
        students.add(StudentModel("Ngô Thái Hùng", "20215371"))
        students.add(StudentModel("Đỗ Thị Thu", "20215372"))
        students.add(StudentModel("Vũ Hoàng Anh", "20215373"))
        students.add(StudentModel("Bùi Khánh Hòa", "20215374"))
        students.add(StudentModel("Đặng Thanh Bình", "20215375"))
        students.add(StudentModel("Nguyễn Thị Mai", "20215376"))
        students.add(StudentModel("Phan Văn Khôi", "20215377"))
        students.add(StudentModel("Lý Hoàng Giang", "20215378"))
        students.add(StudentModel("Hồ Thị Ngọc", "20215379"))
        students.add(StudentModel("Dương Minh Khoa", "20215380"))
        students.add(StudentModel("Cao Ngọc Quỳnh", "20215381"))
        students.add(StudentModel("Vương Anh Tú", "20215382"))
        students.add(StudentModel("Trịnh Kim Hoa", "20215383"))
        students.add(StudentModel("Phạm Hoàng Long", "20215384"))
        students.add(StudentModel("Trần Ngọc Thủy", "20215385"))
        students.add(StudentModel("Nguyễn Thanh Tâm", "20215386"))
        val studentText = mutableListOf<String>()
        val temp = mutableListOf<String>()
        students.forEach {
            val text = it.name + it.id;
            studentText.add(text)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,studentText)
        var listResult = findViewById<ListView>(R.id.res)
        listResult.adapter = adapter
        val editText = findViewById<EditText>(R.id.input);
        editText.doOnTextChanged { text, start, before, count ->
            if(!(text?.length == 0 || text?.length == 1)) {
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,studentText.filter {
                    text.toString().toRegex().containsMatchIn(it);
                })
                var listResult = findViewById<ListView>(R.id.res)
                listResult.adapter = adapter
            }
        }

    }
}