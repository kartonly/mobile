package com.example.lesson1s2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lesson1s2.data.CurrencyApi
import com.example.lesson1s2.data.CurrencyRepository
import com.example.lesson1s2.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor



class MainActivity(private val CurrencyRepository: CurrencyRepository) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        main()
    }
    fun main() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            val cur = CurrencyRepository.getAll()
            Log.d("MY_TAG", "$cur")
        }
    }

}