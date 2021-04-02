package com.yi.kotlin.http.base

import com.yi.common.http.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @author Yi
 * @date 2020/5/8
 */
interface ApiService {

    companion object {
        const val AUTHORIZATION = "Authorization"
    }

//    @GET("lottery/ssq/aim_lottery?expect=2018135")
//    fun getTicket(): Observable<BannerData>

    //使用参数url 注解替换 baseUrl
//    @GET("${BuildConfig.TEST_SERVICE}holiday/single/{date}")
//    fun changeBaseUrl(
//        @Url url: String,
//        @Path("date") date: String
//    ): Observable<BannerData>
//    @GET(value = "www.baidu.com")
//    fun changeBaseUrl(): Observable<BannerData>

    @HTTP(method = "GET", path = "holiday/single/{date}")
    fun <T> getDate(
        @Path("date") date: String = "20200512",
        method: String
    ): Observable<BaseResponse<T>>

    @POST(value = "user/{path}")
    fun <T> login(
        @Path(value = "path", encoded = true) path: String,
        @Header(AUTHORIZATION) token: String,
        @HeaderMap headers: MutableMap<String, Any>,
        @Body body: MutableMap<String, Any>
    ): Observable<BaseResponse<T>>

    @HTTP(
        method = "POST",
        path = "actions/{type}/{actionId}/like/{tenantId}/{userId}",
        hasBody = true
    )
    fun <T> addLikeRequest(
        @Path("type") type: String,
        @Path("actionId") actionId: String,
        @Path("tenantId") tenantId: String,
        @Path("userId") userId: String,
        @Body body: MutableMap<String, Any>
    ): Observable<BaseResponse<T>>
}