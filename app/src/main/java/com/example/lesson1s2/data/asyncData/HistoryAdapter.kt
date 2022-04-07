package com.example.lesson1s2.data.asyncData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.databinding.ItemHistoryBinding
import com.google.android.material.snackbar.Snackbar

class HistoryAdapter(private val values: MutableList<Values>, private val filter: String) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>(){
    val no = "no"
    inner class HistoryHolder internal constructor(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(history: Values) = binding.run{
            if (filter == no){
                cut.text = history.value+" по курсу "+history.cost
                valueName.text = "Итого:"+history.finalCost
                date.text = history.date
            }
            if (filter != no){
                val month: String
                month = history.date[8].toString()+history.date[9].toString()

                if (month == filter){
                    cut.text = history.value+" по курсу "+history.cost
                    valueName.text = "Итого:"+history.finalCost
                    date.text = history.date
                }
            }
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