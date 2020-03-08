package com.example.myapplication.present

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Item
import com.example.myapplication.R


class ListViewAdapter(private val context: Context, private val items: List<Item>) :
    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowView = LayoutInflater.from(context).inflate(R.layout.list_item_row, parent, false)
        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = items[position].name
        val num = items[position].num
        holder.num.text = String.format("%,d", num)
        holder.date.text = items[position].date
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.itemIcon)
        val itemName = view.findViewById<TextView>(R.id.itemName)
        val date = view.findViewById<TextView>(R.id.inputDate)
        val num = view.findViewById<TextView>(R.id.num)
        //+,-ボタン
//        val addButton = view.findViewById<Button>(R.id.addButton)
//        val removeButton = view.findViewById<Button>(R.id.removeButton)
    }

    fun update(){
        notifyDataSetChanged()
    }
}