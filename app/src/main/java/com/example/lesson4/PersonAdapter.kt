package com.example.lesson4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson4.databinding.CardItemBinding
import com.google.android.material.snackbar.Snackbar

class PersonAdapter (var listPerson: List<Persons>,
                     private val clickCard: (Persons) -> Unit,
                     private val clickLike: (Persons) -> Unit) : RecyclerView.Adapter<PersonAdapter.PersonHolder>()
{

    inner class PersonHolder internal constructor(
        private val binding: CardItemBinding,
        private val clickCard: (Persons) -> Unit,
        private val clickLike: (Persons) -> Unit) : RecyclerView.ViewHolder(binding.root)
    {

        fun bind(person: Persons) = binding.run{
            name.text = person.name
            years.text = person.years
            cut.text = person.cuts
            sex.text = person.sex
            portrait.setImageResource(person.photos)

            binding.like.setOnClickListener{
                clickLike(person)
            }
            binding.card.setOnClickListener{
                clickCard(person)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonHolder(binding, clickCard, clickLike)
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        val person = listPerson[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

}