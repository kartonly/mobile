package com.example.lesson4

import android.app.Person
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.example.lesson4.PersonHolder
import java.util.ArrayList
import java.util.Calendar.getAvailableLocales
import java.util.concurrent.TimeUnit
import android.R
import android.view.View


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var curEl = 0
    var mInfoTextView: TextView? = null

    var Data = arrayOf("Eins", "Zwei")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mInfoTextView = findViewById(R.id.text);
        AsTask().execute()
    }

    inner class AsTask() : AsyncTask<Void, Void, Void>() {
        private fun onPostExecute(text: String){
            mInfoTextView?.setText(text)
        }

        override fun doInBackground(vararg p: Void?): Void? {
            for (i in Data) {
                TimeUnit.SECONDS.sleep(5)
                publishProgress()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate()
            onPostExecute(Data[curEl])
            curEl++
        }
    }
}