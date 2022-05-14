package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson1s2.databinding.GraphFragmentBinding
import com.anychart.AnyChart
import com.anychart.charts.Cartesian
import com.example.lesson1s2.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class GraphFragment: Fragment() {
    private lateinit var binding: GraphFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GraphFragmentBinding.inflate(layoutInflater, container,false)


        return binding.root
    }
}