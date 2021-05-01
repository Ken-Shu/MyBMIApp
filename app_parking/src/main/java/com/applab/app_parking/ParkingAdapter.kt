package com.applab.app_parking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

class ParkingAdapter(val list : List<Map<String , Objects>>) : RecyclerView.Adapter<ParkingAdapter.SalesHolder>(){
    private lateinit var context : Context
    //這裡的itemView 是要抓取 itemView 內的內容
    class SalesHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //宣告要顯示的物件
        val parkname : TextView = itemView.text_parkName
        val surplusSpace : TextView = itemView.text_surplusSpace
        val totalSpace : TextView = itemView.text_totalSpace
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
        holder.parkname.text = position.toString() + " : " +currentItem["parkName"].toString()
        holder.surplusSpace.text = currentItem["surplusSpace"].toString()
        holder.totalSpace.text = currentItem["totalSpace"].toString().toDouble().toInt().toString()

        holder.parkname.setOnClickListener {
            Toast.makeText(context,list[position].toString(), Toast.LENGTH_SHORT).show()
        }
    }

}
