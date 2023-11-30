package com.example.newsapplication1

import android.util.Log
import com.example.newsapplication1.models.Articles
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class RequestManager(private val category:String)
{

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val newsApiService:NewsApiService = retrofit.create(NewsApiService::class.java)

     suspend fun getEverything():List<Articles>{
        try {

            val response = newsApiService.getEverything(
                "c6363f9014c64084946c62a710184b8b",
                "in",
                category,
                100 ,
            )

            if (response.isSuccessful) {
                return response.body()?.articles ?: emptyList()
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Log.d("response", errorMessage)
            }

        }catch (e: SocketTimeoutException){
            Log.d("Response",e.message.toString())

        }catch(e:java.lang.Exception){
            Log.d("Response",e.message.toString())
        }
        return emptyList()

    }


   /* suspend fun getSearchResult():List<Articles>{

        try {

            val response=newsApiService.getSearchResult(context.getString(R.string.api_key),query,100)

            if(response.isSuccessful){
                return response.body()?.articles ?: emptyList()
            }
            else{
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Log.d("ErrorMessage",errorMessage)
            }

        }catch (e:SocketTimeoutException){

            Log.d("Response",e.message.toString())

        }catch(e:java.lang.Exception){

            Log.d("Response",e.message.toString())

        }

        return emptyList()
    } */

}