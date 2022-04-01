package com.example.lesson1s2.ui.main

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.lesson1s2.databinding.ExchangeFragmentBinding
import com.example.lesson1s2.databinding.FilterFragmentBinding
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.lesson1s2.data.asyncData.CurrencyHolder
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ExchangeFragment(private val value: String, private val currency: Double): Fragment() {
    private lateinit var binding: ExchangeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExchangeFragmentBinding.inflate(layoutInflater, container,false)
        binding.val2.setText(value)
        val cur = currency.toString()
        binding.editText2.setText(cur)

        return binding.root
    }

}