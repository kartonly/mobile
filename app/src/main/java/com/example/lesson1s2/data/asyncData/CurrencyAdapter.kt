package com.example.lesson1s2.data.asyncData

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.R
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.models.Currency
import com.example.lesson1s2.databinding.ItemBinding
import com.example.lesson1s2.ui.main.ValuesFragment

class CurrencyAdapter(private val rates:MutableList<Currency>,
                      private val clickLike: (Currency)->Unit,
                      private val clickVal: (String, Double)->Unit): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>()  {


    class CurrencyHolder constructor(
        private val binding: ItemBinding,
        private val clickLike: (Currency) -> Unit,
        private val clickVal: (String, Double) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) = binding.run {
            name.text = currency.name
            cut.text = currency.value.toString()
            like.setBackgroundResource(currency.icon)

            var count = 0
            binding.like.setOnClickListener {

                if (count == 0){
                    currency.icon = R.drawable.outline_favorite_24
                    like.setBackgroundResource(currency.icon)
                    clickLike.invoke(currency)
                    count = 1
                } else {
                    currency.icon = R.drawable.fav
                    like.setBackgroundResource(currency.icon)
                    clickLike.invoke(currency)
                    count = 0
                }

            }
            binding.name.setOnClickListener {
                clickVal(currency.name, currency.value)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyHolder(binding, clickLike, clickVal)
    }
    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
       val currencies: MutableList<Currency> = rates
       val currency = currencies[position]

       holder.bind(currency)
   }

    override fun getItemCount(): Int {
        return rates.size
    }

}