package com.example.lesson1s2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lesson1s2.data.CurrencyApi
import com.example.lesson1s2.data.CurrencyRepository
import com.example.lesson1s2.databinding.MainActivityBinding
import com.example.lesson1s2.ui.main.MainFragment
import androidx.lifecycle.ViewModelProviders
import com.example.lesson1s2.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import android.widget.Toast






class MainActivity() : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        onBackPressed()
        val model = ViewModelProviders.of(this).get(MainViewModel::class.java).main()
    }

    private var back_pressed: Long = 0

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(baseContext, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show()
        }
        back_pressed = System.currentTimeMillis()
    }

}