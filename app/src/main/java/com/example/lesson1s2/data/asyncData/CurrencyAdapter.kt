package com.example.lesson1s2.data.asyncData

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.R
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.database.SavedValues
import com.example.lesson1s2.data.models.Currency
import com.example.lesson1s2.databinding.ItemBinding
import com.example.lesson1s2.ui.main.ValuesFragment

class CurrencyAdapter(private val rates:MutableList<SavedValues>,
                      private val clickLike: (SavedValues)->Unit,
                      private val clickVal: (String, Double)->Unit): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>()  {


    class CurrencyHolder constructor(
        private val binding: ItemBinding,
        private val clickLike: (SavedValues) -> Unit,
        private val clickVal: (String, Double) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: SavedValues) = binding.run {
            name.text = currency.value
            cut.text = currency.cost.toString()

            if (currency.like){
                like.setBackgroundResource(R.drawable.outline_favorite_24)
            } else{
                like.setBackgroundResource(R.drawable.fav)
            }

            binding.like.setOnClickListener {
                if (currency.like){
                    like.setBackgroundResource(R.drawable.fav)
                    currency.like = false
                    clickLike.invoke(currency)
                } else {
                    like.setBackgroundResource(R.drawable.outline_favorite_24)
                    currency.like = true
                    clickLike.invoke(currency)
                }

            }
            binding.name.setOnClickListener {
                clickVal(currency.value, currency.cost)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyHolder(binding, clickLike, clickVal)
    }
    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
       val currencies: MutableList<SavedValues> = rates
       val currency = currencies[position]

       holder.bind(currency)
   }

    override fun getItemCount(): Int {
        return rates.size
    }

}