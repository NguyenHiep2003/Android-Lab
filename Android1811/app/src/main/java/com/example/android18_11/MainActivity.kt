package com.example.android18_11
import android.os.Bundle
import android.app.Dialog
import android.app.AlertDialog

import android.app.TimePickerDialog
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar.LayoutParams
import android.util.Log

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )
        val dialog = Dialog(this)
        val deleteDialogBuilder = AlertDialog.Builder(this)
        val contextView = findViewById<View>(R.id.main)

        val snackBar = Snackbar.make(contextView,"1 Sinh viên đã được xóa",Snackbar.LENGTH_LONG)
        val studentAdapter = StudentAdapter(students,dialog,deleteDialogBuilder,snackBar)

        findViewById<RecyclerView>(R.id.recycler_view_students).run {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        findViewById<Button>(R.id.btn_add_new).setOnClickListener {
              dialog.setContentView(R.layout.layout_dialog_add)
              dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

              dialog.findViewById<Button>(R.id.button_ok).setOnClickListener {
                val editHoten = dialog.findViewById<EditText>(R.id.edit_hoten)
                val editMssv = dialog.findViewById<EditText>(R.id.edit_mssv)
                val hoten = editHoten.text.toString()
                val mssv = editMssv.text.toString()
                Log.v("TAG", "$hoten - $mssv")
                students.add(0, StudentModel(hoten, mssv))
                  studentAdapter.notifyDataSetChanged()
                dialog.dismiss()
              }

              dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
                dialog.dismiss()
              }

              dialog.show()
        }

}
    }