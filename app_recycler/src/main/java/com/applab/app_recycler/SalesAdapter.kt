package com.applab.app_recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import java.util.*


//json 是個 list 裡面都有 <key , value>
//繼承RecyclerView.Adapter<SalesAdapter.SalesHolder>
class SalesAdapter(val list : List<Map<String , Objects>>) : RecyclerView.Adapter<SalesAdapter.SalesHolder>(){
    private lateinit var context : Context
    //這裡的itemView 是要抓取 itemView 內的內容
    class SalesHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val region : TextView = itemView.text_region
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesHolder {
        context = parent.context
        //把item layout 丟給 itemView 做物件 再給 SalesHolder
        val itemView = LayoutInflater.from(context).inflate(
            R.layout.item , parent , false
        )

        return SalesHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SalesHolder, position: Int) {
        val currentItem = list[position]
        holder.region.text = position.toString() + " : " +currentItem["Region"].toString()
        holder.region.setOnClickListener {
            Toast.makeText(context,list[position].toString(),Toast.LENGTH_SHORT).show()
        }
    }

}
