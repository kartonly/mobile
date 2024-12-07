package com.example.lesson1s2.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1s2.R
import com.example.lesson1s2.data.asyncData.CurrencyAdapter
import com.example.lesson1s2.data.database.SavedValues
import com.example.lesson1s2.databinding.ValuesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ValuesFragment(private var viewModel: MainViewModel): Fragment() {
    private lateinit var binding: ValuesFragmentBinding

//    ///db(liked)
//    private fun insertLiked(viewModel: MainViewModel, value: ValuesLiked){
//        viewModel.insertLiked(value)
//    }
//    private fun deleteLiked(viewModel: MainViewModel, value: ValuesLiked){
//        viewModel.deleteLiked(value)
//    }


    ///db(saved)
    private fun updateSaved(viewModel: MainViewModel, savedValues: SavedValues){
        viewModel.updateSavedValue(savedValues)
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
        val values = viewModel.getAllSavedValues

        var f = viewModel.changeValues()
        Log.d("MY_TAG", f)

        viewModel.getAllSavedValues.observe(viewLifecycleOwner) { values ->
            binding.card.adapter = CurrencyAdapter(values, ::showSnackbarLike, ::showSnackbarVal) }

    }

    private  fun showSnackbarLike(value: SavedValues): Unit{
        if (!value.like){
            val like = SavedValues(value.value, value.cost, value.like)
            updateSaved(viewModel, like)
            Snackbar.make(binding.root, "Валюта " + value.value + " убрана из избранного", 3000).show()
        }
        if (value.like){
            val like = SavedValues(value.value, value.cost, value.like)
            updateSaved(viewModel, like)
            Snackbar.make(binding.root, "Валюта " + value.value + " добавлена в избранное", 3000).show()
        }
    }

    private  fun showSnackbarVal(value: String, currency: Double): Unit{
        fragmentManager?.beginTransaction()?.replace(R.id.main_container, ExchangeFragment(viewModel, value, currency), ExchangeFragment::class.java.simpleName)
            ?.commit()
    }

    }