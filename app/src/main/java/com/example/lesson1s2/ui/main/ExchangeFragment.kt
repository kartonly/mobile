package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson1s2.databinding.ExchangeFragmentBinding
import android.text.Editable

import android.text.TextWatcher
import android.widget.Toast
import com.example.lesson1s2.data.database.Values
import java.time.LocalDate
import java.util.*


class ExchangeFragment(private var viewModel: MainViewModel, private val value: String, private val currency: Double): Fragment() {
    private lateinit var binding: ExchangeFragmentBinding
    var final: Double = 0.0
    var Add = 0

    private fun insertValueInDB(viewModel: MainViewModel, values: Values){
        viewModel.insertValue(values)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExchangeFragmentBinding.inflate(layoutInflater, container,false)
        binding.val1.setText(value)
        val cur = currency.toString()
        binding.editText2.setText(cur)


        binding.editText1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (binding.editText1.getText().toString() == ""){
                    binding.editText2.setText("Введите число умоляю")
                } else {
                    final = Integer.parseInt(binding.editText1.getText().toString())*currency
                    binding.editText2.setText(final.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        binding.button2.setOnClickListener{
            Add += 1
            val id = (1..1000000000).random()
            val values = Values(id, value, currency, final, LocalDate.now().toString())

            insertValueInDB(viewModel, values)
            val toast: Toast = Toast.makeText(context, "Вы купили валюту!", Toast.LENGTH_LONG)
            toast.show()
        }

        return binding.root
    }

}