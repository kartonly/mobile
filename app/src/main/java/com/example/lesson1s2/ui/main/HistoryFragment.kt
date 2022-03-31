package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson1s2.R
import com.example.lesson1s2.databinding.HistoryFragmentBinding
import com.example.lesson1s2.databinding.MainFragmentBinding

class HistoryFragment: Fragment() {
    private lateinit var binding: HistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(layoutInflater, container,false)

        binding.filter.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, FilterFragment(), FilterFragment::class.java.simpleName)
                ?.commit()
        }

        return binding.root
    }
}