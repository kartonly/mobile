package com.example.lesson1s2.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.renderscript.Sampler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson1s2.R
import com.example.lesson1s2.databinding.MainFragmentBinding
import com.example.lesson1s2.databinding.ValuesFragmentBinding
import kotlin.concurrent.fixedRateTimer

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container,false)
        fragmentManager?.beginTransaction()?.replace(R.id.main_container, ValuesFragment(), ValuesFragment::class.java.simpleName)
            ?.commit()

        binding.button1.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, ValuesFragment(), ValuesFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button2.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, HistoryFragment(), HistoryFragment::class.java.simpleName)
                ?.commit()
        }

        binding.button3.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, GraphFragment(), GraphFragment::class.java.simpleName)
                ?.commit()
        }

        return binding.root
    }
}