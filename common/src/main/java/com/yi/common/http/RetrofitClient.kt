package com.yi.common.http

import com.yi.common.util.Logger
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Yi
 * @date 2020/5/8
 */
object RetrofitClient {
    private const val TAG = "retrofit"

    private var api: Any? = null

    fun <T> getApi(java: Class<T>, baseUrl: String, interceptor: Interceptor): T {
        return api as? T ?: create(java, baseUrl, interceptor)
    }

    private fun <T> create(java: Class<T>, baseUrl: String, interceptor: Interceptor): T {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            //指定call 适配器，把call 转化为RxJava 的observable，并且指定所有操作在io线程执行
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getHttpClient(interceptor))
            .build()
        return retrofit.create(java)
    }

    private fun getHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Logger.e(TAG, message)
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }
}
