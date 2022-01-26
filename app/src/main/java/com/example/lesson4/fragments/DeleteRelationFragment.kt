package com.example.lesson4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment
import com.example.lesson4.R
import com.example.lesson4.database.Node
import com.example.lesson4.viewmodels.NodeViewModel

class DeleteRelationFragment(private var nodeModel: NodeViewModel, private var first: Node, private var second: Node, private var isParent: Boolean):DialogFragment()
{
    val text = "Отношение удалено"
    lateinit var delete: Button
    lateinit var cancel: Button
    private lateinit var fragment: NodeFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View
    {
        val view: View = inflater.inflate(R.layout.activity_del_relation, container, false)
        cancel = view.findViewById(R.id.noButton)
        delete = view.findViewById(R.id.yesButton)

        cancel.setOnClickListener{
            dismiss()
        }
        delete.setOnClickListener{
            delete(isParent)
            dismiss()
            val toast: Toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
            toast.show()
            fragment = activity as NodeFragment
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                fragment.nodeFragment(FragmentMain(nodeModel))
            }
        }
        return view
    }

    private fun delete(isParent: Boolean){
        if (isParent == true){
            nodeModel.updateNode(first.value,
                first.nodes.filter { it.value!=second.value } as MutableList<Node>)
        } else {
            nodeModel.updateNode(second.value,
                second.nodes.filter { it.value!=first.value } as MutableList<Node>)
        }
    }


}