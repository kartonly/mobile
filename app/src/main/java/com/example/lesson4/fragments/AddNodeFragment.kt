package com.example.lesson4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.lesson4.R
import com.example.lesson4.database.Node
import com.example.lesson4.viewmodels.NodeViewModel

class MyDialogFragment : DialogFragment() {}
class AddNodeFragment(private var viewModel: NodeViewModel):DialogFragment() {
    private lateinit var add: Button
    private lateinit var cancel: Button
    private lateinit var edit: EditText
    private var AddVal: Int? = null


    private fun insertNodeInDB(viewModel: NodeViewModel, value:Int, nodesList:MutableList<Node>){
        viewModel.insertNode( Node(value, nodesList))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        val view: View = inflater.inflate(R.layout.activity_add_node, container)
        add = view.findViewById(R.id.addButton)
        cancel = view.findViewById(R.id.cancelButton)
        edit = view.findViewById(R.id.input)

        add.setOnClickListener{
            AddVal = edit.text.toString().toInt()
            insertNodeInDB(viewModel, AddVal!!, mutableListOf())
            dismiss()
            val toast: Toast = Toast.makeText(context, "Запись успешно добавлена!", Toast.LENGTH_LONG)
            toast.show()
        }
        cancel.setOnClickListener{ dismiss() }

        return view
    }
}