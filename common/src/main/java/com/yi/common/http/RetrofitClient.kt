package com.yi.common.http

import com.yi.common.util.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * @author Yi
 * @date 2020/5/8
 */
object RetrofitClient {
    private const val TAG = "retrofit"

    private var api: Any? = null

    fun <T> getApi(java: Class<T>, baseUrl: String, interceptor: Interceptor): T {
        if (api == null) {
            api = create(java, baseUrl, interceptor)
        }
        return api as T
    }

    private fun <T> create(java: Class<T>, baseUrl: String, interceptor: Interceptor) {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getHttpClient(interceptor))
            .build()
        retrofit.create(java)
    }

    private fun getHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Logger.e(
                        TAG,
                        message
                    )
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }
}
