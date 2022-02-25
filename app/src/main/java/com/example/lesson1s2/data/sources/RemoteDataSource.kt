package com.example.lesson1s2.data.sources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lesson1s2.data.CurrencyApi
import com.example.lesson1s2.data.CurrencyRepository
import com.example.lesson1s2.data.CurrencyResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class RemoteDataSource {
    val _result = MutableLiveData<CurrencyResponse>()
    val result: MutableLiveData<CurrencyResponse> = _result
        suspend fun getSource() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("http://data.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(CurrencyApi::class.java)


            suspend fun getValues() = coroutineScope{
                launch(Dispatchers.IO as CoroutineContext){
                    val cur = service.getCurrencies()
                    _result.value = cur
                }
            }
            getValues()



//            GlobalScope.launch(Dispatchers.IO as CoroutineContext){
//                val cur = service.getCurrencies()
//                Log.d("MY_TAG","$cur")
//            }
        }
}