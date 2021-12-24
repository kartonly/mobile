package com.example.lesson4

import android.annotation.SuppressLint
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
import android.view.View


class MainActivity : AppCompatActivity() {

    var curEl = 0
    lateinit var context: Context
    var mInfoTextView: TextView? = null
    var linearLayout: LinearLayout? = null

    var Data = arrayOf("Eins", "Zwei", "Три", "Четыре", "Пять", "Шесть")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
//        mInfoTextView = findViewById(R.id.text);
        linearLayout = findViewById(R.id.text)
        AsTask().execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsTask() : AsyncTask<Void, Void, Void>() {
        private fun onPostExecute(text: String){
            val textView = TextView(context)
            textView.setText(text)
            linearLayout?.addView(textView)
        }

        override fun doInBackground(vararg p: Void?): Void? {
            for (i in Data) {
                TimeUnit.SECONDS.sleep(2)
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