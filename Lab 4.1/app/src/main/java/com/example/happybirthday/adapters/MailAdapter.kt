package vn.edu.hust.listexamples.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.happybirthday.models.MailModel
import com.example.happybirthday.R

class MailAdapter(val mails: List<MailModel>): BaseAdapter() {
    override fun getCount(): Int = mails.size

    override fun getItem(position: Int): Any = mails[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_mail, parent, false)
            Log.v("TAG", "Create layout item $position")
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        val mail = mails[position]
        viewHolder.imageAvatar.setImageResource(mail.avatarResId)
        viewHolder.subject.text = mail.subject
        viewHolder.content.text = mail.content
        viewHolder.time.text = mail.time
        viewHolder.from.text = mail.from
        return itemView
    }

    class ViewHolder(itemView: View) {
        val imageAvatar: ImageView
        val subject: TextView
        val content: TextView
        val time: TextView
        val from: TextView
        init {
            imageAvatar = itemView.findViewById(R.id.avatar)
            subject = itemView.findViewById(R.id.subject)
            content = itemView.findViewById(R.id.content)
            time = itemView.findViewById(R.id.time)
            from = itemView.findViewById(R.id.from)

        }
    }
}