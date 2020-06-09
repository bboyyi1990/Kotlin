package com.yi.kotlin.http.base

import com.yi.kotlin.data.BannerData
import com.yi.kotlin.data.MainData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * @author Yi
 * @date 2020/5/8
 */
interface ApiService {

    companion object {
        const val AUTHORIZATION = "Authorization"
    }

    @GET("lottery/ssq/aim_lottery?expect=2018135")
    fun getTicket(): Observable<BannerData>

    @HTTP(method = "GET", path = "holiday/single/{date}")
    fun getDate(@Path("date") date: String = "20200512"): Observable<BannerData>

    @POST(value = "user/{path}")
    fun login(
        @Path(value = "path", encoded = true) path: String,
        @Header(AUTHORIZATION) token: String,
        @Body body: Map<String, Any?>
    ): Observable<MainData>

    @HTTP(
        method = "POST",
        path = "actions/{type}/{actionId}/like/{tenantId}/{userId}",
        hasBody = true
    )
    fun addLikeRequest(
        @Path("type") type: String,
        @Path("actionId") actionId: String,
        @Path("tenantId") tenantId: String,
        @Path("userId") userId: String,
        @Body body: Map<String, Any?>
    ): Call<BaseData>


}