package com.example.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lesson4.viewmodels.NodeViewModel
import com.example.lesson4.viewmodels.NodeViewModelFactory
import com.example.lesson4.fragments.*

class MainActivity : AppCompatActivity(), NodeFragment {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_main)
        val provider = ViewModelProvider(this, NodeViewModelFactory(application))[NodeViewModel::class.java]

        val mainFragment = FragmentMain(provider)
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.container, mainFragment).commit()
    }

    override fun nodeFragment(fragment: Fragment) {
        val bundle = Bundle()
        val manager = this.supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        manager.replace(R.id.container, fragment)
        manager.commit()
    }
}