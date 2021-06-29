package com.example.sevenminutesworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.workout_history_row.view.*

class HistoryAdapter
constructor(val context: Context, val items: ArrayList<String>):
RecyclerView.Adapter<HistoryAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(
                R.layout.workout_history_row,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items[position]

        holder.position.text = (position+1).toString()
        holder.item.text = date

        if (position % 2 == 0) {
            holder.historyCard.setBackgroundColor(Color.parseColor("#EBEBEB"))
        }
        else {
            holder.historyCard.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyCard: LinearLayout = view.card_history
        val item: TextView = view.card_item
        val position: TextView = view.position
    }
}