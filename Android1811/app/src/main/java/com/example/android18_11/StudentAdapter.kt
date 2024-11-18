package com.example.android18_11

import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(val students: MutableList<StudentModel>, val dialog: Dialog, val builder: AlertDialog.Builder, val snackbar: Snackbar): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
  lateinit var latestDeleteName: String
  var latestDeletePosition = 0
  lateinit var  latestDeleteId: String
  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById<ImageView?>(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    var student = students[position]
    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
    holder.imageEdit.setOnClickListener {
      dialog.setContentView(R.layout.layout_dialog_add)
      dialog.findViewById<TextView>(R.id.add).setText("Chinh sua thong tin sinh vien")
      dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
      dialog.findViewById<EditText>(R.id.edit_hoten).setText(holder.textStudentName.text)
      dialog.findViewById<EditText>(R.id.edit_mssv).setText(holder.textStudentId.text)
      dialog.findViewById<Button>(R.id.button_ok).setOnClickListener {
        val editHoten = dialog.findViewById<EditText>(R.id.edit_hoten)
        val editMssv = dialog.findViewById<EditText>(R.id.edit_mssv)
        val hoten = editHoten.text.toString()
        val mssv = editMssv.text.toString()
        students[position].studentId = mssv
        students[position].studentName = hoten
        this.notifyDataSetChanged()
        dialog.dismiss()
      }

      dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
        dialog.dismiss()
      }

      dialog.show()
    }
    holder.imageRemove.setOnClickListener {
      builder.setTitle("Xóa sinh viên")
      .setMessage("Bạn có chắc chắn muốn xóa sinh viên: ${student.studentName} - ${student.studentId}")
      .setPositiveButton("OK") {dialog, which ->
        latestDeletePosition=position;
        latestDeleteId=student.studentId;
        latestDeleteName=student.studentName;
        students.removeAt(position);
        notifyDataSetChanged();
        snackbar.setAction("Undo"){
          students.add(latestDeletePosition,StudentModel(latestDeleteName,latestDeleteId))
          notifyDataSetChanged()
        }.show()
      }
      builder.create().show()
      }
  }

}