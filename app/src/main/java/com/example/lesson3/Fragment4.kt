package com.example.lesson3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson3.databinding.Fragment1Binding
import com.example.lesson3.databinding.Fragment2Binding
import com.example.lesson3.databinding.Fragment4Binding

class Fragment4 : Fragment(){
    private lateinit var binding: Fragment4Binding
    private var x :Int = 4

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = Fragment4Binding.inflate(layoutInflater)
        binding.resultOfOperation.text = Calculator.calculate().toString()
        val view = binding.root

        backButtonClick()
        return view
    }

    private fun backButtonClick(){
        binding.back.setOnClickListener {
            val activityCollback = requireActivity() as ActivityCollback
            activityCollback.showBackFragment(x)
        }
    }
}