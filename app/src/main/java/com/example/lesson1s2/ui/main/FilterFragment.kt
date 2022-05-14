package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.lesson1s2.R
import com.example.lesson1s2.databinding.FilterFragmentBinding
import com.example.lesson1s2.databinding.ValuesFragmentBinding
import java.text.SimpleDateFormat
import java.time.LocalDate

class FilterFragment(private var viewModel: MainViewModel): Fragment() {
    private lateinit var binding: FilterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterFragmentBinding.inflate(layoutInflater, container,false)

        binding.button1.setOnClickListener(){
            val filter = "no"
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, filter), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button2.setOnClickListener(){
            val filter = "m"

            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, filter), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button3.setOnClickListener(){
            val filter = "w"

            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, filter), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button5.setOnClickListener {
            val value = binding.editValue.getText().toString()

            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, value), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button4.setOnClickListener {
            if (binding.editStart.getText().toString() == ""){
                binding.editStart.setText("Введите число умоляю")
            } else if (binding.editEnd.getText().toString() == ""){
                binding.editEnd.setText("Введите число умоляю")
            } else {
                val date1 = SimpleDateFormat("dd-MM-yyyy").parse(binding.editStart.getText().toString()).time
                val date2 = SimpleDateFormat("dd-MM-yyyy").parse(binding.editEnd.getText().toString()).time
                val filter = "d"+date1+","+date2

                fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(viewModel, filter), HistoryFragment::class.java.simpleName)
                    ?.commit()
            }
        }

        return binding.root
    }
}