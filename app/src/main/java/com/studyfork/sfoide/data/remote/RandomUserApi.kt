package com.studyfork.sfoide.data.remote

import android.util.Log
import com.studyfork.sfoide.data.remote.response.RandomUserResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RandomUserApi {

    @GET("api/")
    fun getRandomUsers(): Single<RandomUserResponse>

    companion object {
        private const val API_URL = "https://randomuser.me/"

        fun create(): RandomUserApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API_LOGGER", it)
            })

            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .callTimeout(5000, TimeUnit.MILLISECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RandomUserApi::class.java)
        }
    }
}
