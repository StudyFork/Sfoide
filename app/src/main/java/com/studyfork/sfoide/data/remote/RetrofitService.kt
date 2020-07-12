package com.studyfork.sfoide.data.remote

import com.studyfork.sfoide.BuildConfig
import com.studyfork.sfoide.data.remote.api.FriendApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api")
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(createHttpLoggingInterceptor()).build()
    }

    private fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    val friendApi = retrofit.create(FriendApi::class.java)
}