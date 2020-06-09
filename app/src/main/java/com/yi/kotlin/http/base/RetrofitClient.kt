package com.yi.kotlin.http.base

import com.yi.kotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Yi
 * @date 2020/5/8
 */
class RetrofitClient {

    companion object {
        private const val TAG = "retrofit"
        private var httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().addHeader("content-type", "application/json")
                        .build()
                )
            }
            .addInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    com.yi.kotlin.uitl.Logger.e(
                        TAG,
                        message
                    )
                }).setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            ).build()

        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()

        var api: ApiService = retrofit.create(
            ApiService::class.java
        )
    }
}
