package com.example.lesson1s2.data.asyncData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.databinding.ItemHistoryBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate

class HistoryAdapter(private val values: MutableList<Values>) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>(){
    val no = "no"
    inner class HistoryHolder internal constructor(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(history: Values) = binding.run{
            cut.text = history.value+" по курсу "+history.cost
            valueName.text = "Итого:"+history.finalCost
            var date1 = java.text.SimpleDateFormat("yyyy-MM-dd").format(history.date)
            date.text = date1
        }
    }


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.HistoryHolder {
         val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return HistoryHolder(binding)
     }

     override fun getItemCount(): Int {
         return values.size
     }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val histories: MutableList<Values> = values
        val history = histories[position]
        holder.bind(history)
    }

}