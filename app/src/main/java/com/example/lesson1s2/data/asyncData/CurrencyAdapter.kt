package com.example.lesson1s2.data.asyncData

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.R
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.databinding.ItemBinding
import com.example.lesson1s2.ui.main.ValuesFragment

class CurrencyAdapter(private val rates:Map<String,Double>,
                      private val clickLike: (String, Int)->Unit,
                      private val clickVal: (String, Double)->Unit): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>()  {

    inner class CurrencyHolder internal constructor(
        private val binding: ItemBinding,
        private val clickLike: (String, Int)->Unit,
        private val clickVal: (String, Double)->Unit): RecyclerView.ViewHolder(binding.root){

        var count = 0
        fun bind(currency: Pair<String, Double>) = binding.run{
            name.text = currency.first
            cut.text = currency.second.toString()

            binding.like.setOnClickListener{
                if(count==1){
                    count--
                    clickLike(currency.first, count)
                    like.setBackgroundResource(R.drawable.fav)
                } else {
                    count++
                    clickLike(currency.first, count)
                    like.setBackgroundResource(R.drawable.outline_favorite_24)
                }
            }

            binding.name.setOnClickListener{
                clickVal(currency.first, currency.second)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyHolder(binding, clickLike, clickVal)
    }
    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
       val currencies: List<Pair<String, Double>> = rates.toList()
       val currency = currencies[position]
       holder.bind(currency)
   }

    override fun getItemCount(): Int {
        return rates.size
    }

}