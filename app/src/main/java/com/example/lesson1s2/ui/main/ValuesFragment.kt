package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.R
import com.example.lesson1s2.data.asyncData.CurrencyAdapter
import com.example.lesson1s2.data.asyncData.CurrencyHolder
import com.example.lesson1s2.databinding.ExchangeFragmentBinding
import com.example.lesson1s2.databinding.ValuesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ValuesFragment(private var viewModel: MainViewModel): Fragment() {
    private lateinit var binding: ValuesFragmentBinding

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ValuesFragmentBinding.inflate(layoutInflater, container,false)
        fun main() = runBlocking {
            launch {
                setupRecycleView()
            }
        }
        main()
        return binding.root
    }

    suspend fun setupRecycleView() {
        binding.card.layoutManager = verticalLinearLayoutManager
        binding.card.adapter = CurrencyAdapter(CurrencyHolder.getCur(), ::showSnackbarLike, ::showSnackbarVal)
    }

    private  fun showSnackbarLike(value: String, count: Int): Unit{
        if (count==0){
            Snackbar.make(binding.root, "Валюта " + value + " убрана из избранного", 3000).show()
        }
        if (count==1){
            Snackbar.make(binding.root, "Валюта " + value + " добавлена в избранное", 3000).show()
        }
    }

    private  fun showSnackbarVal(value: String, currency: Double): Unit{
        fragmentManager?.beginTransaction()?.replace(R.id.main_container, ExchangeFragment(viewModel, value, currency), ExchangeFragment::class.java.simpleName)
            ?.commit()
    }

    }