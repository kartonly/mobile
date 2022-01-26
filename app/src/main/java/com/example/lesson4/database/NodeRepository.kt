package com.example.lesson4.database

import com.example.lesson4.database.Node
import com.example.lesson4.database.NodeDao

class NodeRepository(private val nodeDao: NodeDao) {

    var getAllNodes = nodeDao.getAllNodes();
    suspend fun insertNode(node: Node){
        nodeDao.insertNode(node)
    }
    @JvmName("updateNode1")
    suspend fun updateNode(value: Int, nodes: MutableList<Node>){
        nodeDao.updateNode(value, nodes)
    }

}