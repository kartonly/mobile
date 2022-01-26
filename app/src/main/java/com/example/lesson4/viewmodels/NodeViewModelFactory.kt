package com.example.lesson4.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NodeViewModelFactory(private var application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NodeViewModel::class.java)){
            return NodeViewModel(application) as T
        }
        throw IllegalArgumentException("ViewModel did not found")
    }
}