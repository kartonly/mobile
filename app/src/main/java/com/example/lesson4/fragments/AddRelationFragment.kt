package com.example.lesson4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.lesson4.R
import com.example.lesson4.database.Node
import com.example.lesson4.viewmodels.NodeViewModel

class AddRelationFragment(private var first: Node, private var second: Node, private var nodeModel: NodeViewModel, private var isParent: Boolean):DialogFragment()
{
    val text = "Отношение добавлено"
    lateinit var add: Button
    lateinit var cancel: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View
    {
        val view: View = inflater.inflate(R.layout.activity_add_relation, container, false)

        cancel = view.findViewById(R.id.noButton)
        add = view.findViewById(R.id.yesButton)

        cancel.setOnClickListener{
            dismiss()
        }
        add.setOnClickListener{
            relation(isParent)
            dismiss()
            val toast: Toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
            toast.show()
        }
        return view
    }

    private fun relation(isParent: Boolean){
        if (isParent == true){
            nodeModel.updateNode(second.value,
                (second.nodes + mutableListOf(Node(first.value, first.nodes))) as MutableList<Node>)
        } else {
            nodeModel.updateNode(first.value,
                (first.nodes + mutableListOf(Node(second.value, second.nodes))) as MutableList<Node>)
        }

    }
}