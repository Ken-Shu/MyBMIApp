package com.applab.app_sqlite_lucky

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.applab.app_sqlite_lucky.db.DBHelper
import com.applab.app_sqlite_lucky.models.Lucky
import kotlinx.android.synthetic.main.row.view.*
import java.util.ArrayList

class RecyclerAdapterLucky : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Lucky> = ArrayList()
    private lateinit var context: Context
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.view_id
        val color: TextView = itemView.view_color
        val num: TextView = itemView.view_num

        fun bind(lucky: Lucky) {
            id.setText(lucky.id.toString())
            color.setText(lucky.color)
            num.setText(lucky.num.toString())
        }

    }

    fun submitList(list: List<Lucky>) {
        items = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val lucky = items[position]
        when(holder) {
            is ViewHolder -> {
                holder.bind(lucky)
                // 按下 id 修改
                holder.id.setOnClickListener {
                    val db = DBHelper(context)
                    val lucky = Lucky(
                        holder.id.text.toString().toInt(),
                        "Red",
                        77,
                        111
                    )
                    db.updateLucky(lucky)
                    submitList(db.readOdds())
                    this.notifyDataSetChanged()
                }
                // 長按下 id 刪除
                holder.id.setOnLongClickListener {
                    val db = DBHelper(context)
                    db.deleteLucky(holder.id.text.toString().toInt())
                    submitList(db.readOdds())
                    this.notifyDataSetChanged()
                    true
                }

            }
        }
    }

}