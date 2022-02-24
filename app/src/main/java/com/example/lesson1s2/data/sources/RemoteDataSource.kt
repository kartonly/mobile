package com.example.lesson1s2.data.sources

import android.util.Log
import com.example.lesson1s2.data.CurrencyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class RemoteDataSource {
        suspend fun getSource(){
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("http://data.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(CurrencyApi::class.java)

            GlobalScope.launch(Dispatchers.IO as CoroutineContext){
                val cur = service.getCurrencies()
                Log.d("MY_TAG","$cur")
//                return@launch cur
            }
        }
}