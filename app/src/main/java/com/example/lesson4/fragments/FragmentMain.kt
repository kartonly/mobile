package com.example.lesson4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.lesson4.R
import com.example.lesson4.database.Node
import com.example.lesson4.viewmodels.NodeViewModel

class FragmentMain(private var nodeModel: NodeViewModel): Fragment() {
    private var linearLayout: LinearLayout? = null
    private lateinit var fragment: NodeFragment
    private lateinit var add: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        linearLayout = view.findViewById(R.id.nodesContainer)
        nodeModel.getAllNodes.observe(viewLifecycleOwner) { nodes ->
            listNodes(nodes, linearLayout) }

        fragment = activity as NodeFragment
        add = view.findViewById(R.id.addButton)

        add.setOnClickListener{
            val addNode = AddNodeFragment(nodeModel)
            addNode.show(fragmentManager, "addFragment")
        }
        return view
    }

    fun setColor(node: Node, nodesList: MutableList<Node>): Int {
        fun hasChild(node: Node, nodesList: MutableList<Node>): Boolean {
            val allChildren = nodesList.map { it -> it.nodes.map { it.value } }
            val contain = allChildren.map { it.contains(node.value) }
            return true in contain
        }
        fun hasParent(node: Node): Boolean {
            return !node.nodes.isNullOrEmpty()
        }
        when {
            hasParent(node)&&(hasChild(node, nodesList)) -> return 0xFFF00000.toInt()
            hasParent(node) -> return 0xFFFFF000.toInt()
            hasChild(node, nodesList) -> return 0xFF4682B4.toInt()
        }
        return 0xFFFFFFF.toInt()
    }

    private fun listNodes(list:MutableList<Node>, linearLayout: LinearLayout?){
        linearLayout?.removeAllViews()

        for (node in list){
            val textView = TextView(context)
            val value = node.value.toString()
            textView.text = "Объект: $value"
            textView.setBackgroundColor(setColor(node, list))
            textView.setOnClickListener{
                fragment.nodeFragment(FragmentSecondary(nodeModel, node, false))
            }
            linearLayout?.addView(textView)
        }
    }
}