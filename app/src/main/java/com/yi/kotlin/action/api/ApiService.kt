package com.yi.kotlin.action.api

import com.yi.common.http.BaseData
import com.yi.common.http.BaseResponse
import com.yi.kotlin.data.LoginData
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @author Yi
 * @date 2020/5/8
 * 接口注册表
 */
interface ApiService {

    @GET("user/{path}")
    fun userGet(
        @Path(value = "path", encoded = true) path: String,
        @HeaderMap headers: MutableMap<String, Any>,
        @QueryMap(encoded = true) params: MutableMap<String, Any>
    ): Observable<BaseResponse<BaseData>>

    @POST("user/{path}")
    fun userPOST(
        @Path("path") path: String,
        @HeaderMap headers: MutableMap<String, Any>,
        @Body() params: MutableMap<String, Any>
    ): Observable<BaseResponse<LoginData>>

    @HTTP(method = "GET", path = "user/{path}")
    fun user(
        @Path("path") path: String,
        @HeaderMap headers: MutableMap<String, Any>,
        @Body() params: MutableMap<String, Any>
    ): Observable<BaseResponse<LoginData>>
}