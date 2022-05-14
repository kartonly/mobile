package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.R
import com.example.lesson1s2.data.asyncData.HistoryAdapter
import com.example.lesson1s2.databinding.HistoryFragmentBinding

class HistoryFragment(private var viewModel: MainViewModel, val filter: String): Fragment() {
    private lateinit var binding: HistoryFragmentBinding

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(layoutInflater, container,false)

        binding.filter.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, FilterFragment(viewModel), FilterFragment::class.java.simpleName)
                ?.commit()
        }

        setupRecycleView()

        return binding.root
    }

    fun setupRecycleView() {
        binding.card.layoutManager = verticalLinearLayoutManager
        val str = filter[0].toString()
        if (filter == "no"){
            viewModel.getAllValues.observe(viewLifecycleOwner) { values ->
                binding.card.adapter = HistoryAdapter(values) }
        } else if (filter == "m"){
            viewModel.getByMonth.observe(viewLifecycleOwner) { values ->
                binding.card.adapter = HistoryAdapter(values) }
        } else if (filter == "w"){
            viewModel.getByWeek.observe(viewLifecycleOwner) { values ->
                binding.card.adapter = HistoryAdapter(values) }
        } else if (str == "d"){
            viewModel.getBetween(filter).observe(viewLifecycleOwner) { values ->
                binding.card.adapter = HistoryAdapter(values) }
        } else {
            viewModel.getByValue(filter).observe(viewLifecycleOwner){
                    values -> binding.card.adapter = HistoryAdapter(values) }
        }
    }
}