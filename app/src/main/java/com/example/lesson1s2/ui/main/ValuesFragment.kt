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
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.data.database.ValuesLiked
import com.example.lesson1s2.data.models.Currency
import com.example.lesson1s2.databinding.ExchangeFragmentBinding
import com.example.lesson1s2.databinding.ValuesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ValuesFragment(private var viewModel: MainViewModel): Fragment() {
    private lateinit var binding: ValuesFragmentBinding

    ///db(liked)
    private fun insertLiked(viewModel: MainViewModel, value: ValuesLiked){
        viewModel.insertLiked(value)
    }
    private fun deleteLiked(viewModel: MainViewModel, value: ValuesLiked){
        viewModel.deleteLiked(value)
    }

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

//        viewModel.getAllLiked.observe(viewLifecycleOwner) { values ->
//            binding.card.adapter = CurrencyAdapter(viewModel.getCur(), ::showSnackbarLike, ::showSnackbarVal) }

        binding.card.adapter = CurrencyAdapter(viewModel.getCur(), ::showSnackbarLike, ::showSnackbarVal)
    }

    private  fun showSnackbarLike(value: Currency): Unit{
        if (value.icon == R.drawable.fav){
            val like = ValuesLiked(value.name, R.drawable.outline_favorite_24)
            deleteLiked(viewModel, like)
            Snackbar.make(binding.root, "Валюта " + value.name + " убрана из избранного", 3000).show()
        }
        if (value.icon == R.drawable.outline_favorite_24){
            val like = ValuesLiked(value.name, R.drawable.outline_favorite_24)
            insertLiked(viewModel, like)
            Snackbar.make(binding.root, "Валюта " + value.name + " добавлена в избранное", 3000).show()
        }
    }

    private  fun showSnackbarVal(value: String, currency: Double): Unit{
        fragmentManager?.beginTransaction()?.replace(R.id.main_container, ExchangeFragment(viewModel, value, currency), ExchangeFragment::class.java.simpleName)
            ?.commit()
    }

    }