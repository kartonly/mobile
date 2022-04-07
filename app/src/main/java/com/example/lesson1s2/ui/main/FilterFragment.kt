package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson1s2.R
import com.example.lesson1s2.databinding.FilterFragmentBinding
import com.example.lesson1s2.databinding.ValuesFragmentBinding
import java.time.LocalDate

class FilterFragment(private var viewModel: MainViewModel): Fragment() {
    private lateinit var binding: FilterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterFragmentBinding.inflate(layoutInflater, container,false)

        binding.button1.setOnClickListener(){
            val date = "no"
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, date), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button2.setOnClickListener(){
            var dateMonth: String = "01"
            val date = LocalDate.now().toString()
            dateMonth = date[8].toString()+date[9].toString()

            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, dateMonth), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        return binding.root
    }
}