package com.example.servigo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val historyList: List<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item_layout, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyList[position]
        holder.nameTextView.text = historyItem.name
        holder.titleTextView.text = historyItem.title
        holder.descriptionTextView.text = historyItem.description
        holder.dateTextView.text = historyItem.date
        holder.priceTextView.text = historyItem.price
        holder.ratingTextView.text = historyItem.rating
        Log.d("RecyclerView", "Binding item: ${historyItem.name}")  // Debug log
    }

    override fun getItemCount() = historyList.size
}
