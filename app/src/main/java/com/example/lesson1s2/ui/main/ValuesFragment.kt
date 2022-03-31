package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson1s2.databinding.HistoryFragmentBinding
import com.example.lesson1s2.databinding.ValuesFragmentBinding

class ValuesFragment: Fragment() {
    private lateinit var binding: ValuesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ValuesFragmentBinding.inflate(layoutInflater, container,false)
        return binding.root
    }
}