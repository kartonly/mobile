package com.example.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.example.lesson4.PersonHolder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycleView()
    }

    private fun setupRecycleView(){
        binding.cardPerson.layoutManager = verticalLinearLayoutManager
        binding.cardPerson.adapter = PersonAdapter(PersonHolder.createCollectionPersons(), ::showSnackbar, ::showSnackbarLike)

    }

    private fun showSnackbar(person: Persons): Unit{
        Snackbar.make(binding.root, "Нажата карточка: " + person.name, 3000).show()
    }
    private  fun showSnackbarLike(person: Persons): Unit{
        Snackbar.make(binding.root, "Нажат лайк:  " + person.name, 3000).show()
    }
}