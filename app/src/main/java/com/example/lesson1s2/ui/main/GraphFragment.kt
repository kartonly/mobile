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
import com.example.lesson1s2.data.asyncData.HistoryAdapter
import com.example.lesson1s2.data.database.Values
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.LegendEntry


class GraphFragment(var viewModel: MainViewModel): Fragment() {
    private lateinit var binding: GraphFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View {
        binding = GraphFragmentBinding.inflate(layoutInflater, container,false)

        binding.button5.setOnClickListener {
            val value = binding.editValue.getText().toString()

            val barChart = binding.barChart

            viewModel.getByValue(value).observe(viewLifecycleOwner) { values ->
                val entries: ArrayList<BarEntry> = ArrayList()
                val labels = ArrayList<String>()
                var value = ""

                for (i in values) {
                    entries.add(BarEntry(i.cost.toFloat(), i.cost.toFloat()))
                    labels.add(i.value)
                    value = i.value
                }

                val barDataSet = BarDataSet(entries, value)
                barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

                val data = BarData(barDataSet)
                barChart.data = data

//            val l: Legend = barChart.getLegend()
//            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
//            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT)
//            l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
//            l.setDrawInside(false)
//            l.setForm(LegendForm.SQUARE)
//            l.setFormSize(9f)
//            l.setTextSize(11f)
//            l.setXEntrySpace(4f)
//            barChart.getLegend().setEnabled(true)


                //hide grid lines
                barChart.axisLeft.setDrawGridLines(true)
                barChart.xAxis.setDrawGridLines(true)
                barChart.xAxis.setDrawAxisLine(true)

                //remove right y-axis
                barChart.axisRight.isEnabled = true

                //remove legend
                barChart.legend.isEnabled = true

                //remove description label
                barChart.description.isEnabled = true

                //add animation
                barChart.animateY(3000)

                //draw chart
                barChart.invalidate()
            }
        }



        return binding.root
    }
}