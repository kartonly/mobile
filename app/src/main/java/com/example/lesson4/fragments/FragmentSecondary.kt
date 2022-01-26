package com.example.lesson4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.lesson4.R
import com.example.lesson4.database.Node
import com.example.lesson4.viewmodels.NodeViewModel

class FragmentSecondary(private var nodeModel: NodeViewModel, private var first: Node, private var isParent: Boolean): Fragment() {
    private var linearLayout: LinearLayout? = null
    private lateinit var fragment: NodeFragment
    private lateinit var parent: Button
    private lateinit var child: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.second_fragment, container, false)
        linearLayout = view.findViewById(R.id.nodesContainer)
        nodeModel.getAllNodes.observe(viewLifecycleOwner) { nodes ->
            listNodes(nodes, linearLayout) }
        fragment = activity as NodeFragment
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            fragment.nodeFragment(FragmentMain(nodeModel))
        }


        child = view.findViewById(R.id.childButton)
        child.setOnClickListener{
            fragment.nodeFragment(FragmentSecondary(nodeModel, first, false))
            val toast: Toast = Toast.makeText(context, "Ребёнок объекта", Toast.LENGTH_LONG)
            toast.show()
        }
        parent = view.findViewById(R.id.parentButton)
        parent.setOnClickListener{
            fragment.nodeFragment(FragmentSecondary(nodeModel, first, true))
            val toast: Toast = Toast.makeText(context, "Родитель объекта", Toast.LENGTH_LONG)
            toast.show()
        }
        return view
    }

    private fun listNodes(list:MutableList<Node>, linearLayout: LinearLayout?){
        val manager = (activity as FragmentActivity).supportFragmentManager
        fun contain(nodeValue: Node, nodeNodes: Node, isContain:Boolean): Boolean {
            return (
            if (isContain==true){
                return nodeValue.value in nodeNodes.nodes.map { it.value }
            } else {
                nodeValue.value !in nodeNodes.nodes.map { it.value }
            })
        }
        println(list)
        linearLayout?.removeAllViews()

        for (node in list){
            val firstValue = first.value.toString()
            val secondValue = node.value.toString()
            val green = 0xFF228B22.toInt()
            val white = 0xFFFFFFFF.toInt()

            if (secondValue!=firstValue){
                if ((contain(node, first, false) && !isParent)||(contain(first, node, false) && isParent))
                {
                    val textView = TextView(context)
                    if ((contain(first, node, true) && !isParent)||(contain(node, first, true) && isParent))
                    {
                        textView.setBackgroundColor(green)
                        textView.setOnClickListener{
                            val deleteRel = DeleteRelationFragment(nodeModel, first, node, isParent)
                            deleteRel.show(manager, "DeleteRel")

                        }
                    } else{
                        textView.setBackgroundColor(white)
                        textView.setOnClickListener{
                            val addRel = AddRelationFragment(first,node, nodeModel,  isParent)
                            addRel.show(manager, "AddRel")
                        }
                    }
                    textView.text = "Ваш объект: $firstValue, отношение с объектом: $secondValue"
                    linearLayout?.addView(textView)
                }
            }
        }
    }
}