package com.example.lesson4.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.lesson4.database.databases.NodeDB
import com.example.lesson4.database.Node
import com.example.lesson4.database.NodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NodeRepository
    val getAllNodes: LiveData<MutableList<Node>>

    init {
        val nodeDao = NodeDB.getDatabase(application).NodeDao()
        repository = NodeRepository(nodeDao)
//        fun getAllNodes(): LiveData<MutableList<Node>> {
//            return runBlocking {
//                repository.getAllNodes.asLiveData()
//            }
//        }
        getAllNodes = repository.getAllNodes.asLiveData()
    }


    fun insertNode(node: Node) = viewModelScope.launch(Dispatchers.IO) { repository.insertNode(node) }

    fun updateNode(value: Int, nodes: MutableList<Node>) = viewModelScope.launch(Dispatchers.IO) { repository.updateNode(value, nodes) }
}