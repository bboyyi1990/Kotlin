package com.yi.kotlin.action.base

import android.util.Base64
import com.yi.common.http.BaseResponse
import com.yi.common.http.RetrofitClient
import com.yi.common.util.MemoryUtil
import com.yi.kotlin.BuildConfig
import com.yi.kotlin.http.base.BaseCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

/**
 * @author Yi
 * @date 2020/5/9
 */
open abstract class BaseAction<BaseData> {
    private val PUBLIC_KEY = ""
    private val APPKEY = ""

    protected fun getApi() = RetrofitClient.api

    /**
     * final execute method
     * @param params
     * @param callback if don't care about result,can enter null
     */
    protected fun execute(
        callback: BaseCallback<BaseData>?,
        params: MutableMap<String, Any> = mutableMapOf()
    ) {
        buildBody(params)
        val headers = buildHeaders()
        val api = getApiObservable(headers, params)
        var task = api.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        if (null == callback) task.subscribe() else task.subscribe(callback)
    }

    /**
     * 获取api接口
     * 参数交由子类包装接口
     */
    abstract fun getApiObservable(
        headers: MutableMap<String, Any>,
        params: MutableMap<String, Any>
    ): Observable<BaseResponse<BaseData>>

    /**
     * 构建请求参数
     */
    private fun buildBody(params: MutableMap<String, Any> = mutableMapOf()): MutableMap<String, Any> {
        params["common"] = 1
        params["value"] = "1"
        params["sign"] = createSign(params)
        return params
    }

    /**
     * 构建请求头
     */
    private fun buildHeaders(headers: MutableMap<String, Any> = mutableMapOf()): MutableMap<String, Any> {
        //TODO add header content
        headers["userToken"] = MemoryUtil.getString("userToken","")!!
        headers["version"] = BuildConfig.VERSION_CODE
        headers["language"] = "ZH"
        headers["device"] = "Android"
        return headers
    }

    /**
     * 生成签名
     */
    private fun createSign(params: MutableMap<String, Any>): String {
        val keys = params.keys.toList()
        Collections.sort<String>(keys)
        val buffer = StringBuffer()
        for (index in keys.indices) {
            val key = keys[index]
            val value = params[key]
            if (value == null) {
                params.remove(key)
            } else {
                buffer.append(key).append("=").append(value).append("&")
            }
        }
        buffer.append("APPKEY").append("=").append(APPKEY)
        var sign = buffer.toString()
        sign = rsaEncode(sign)
        return sign
    }

    /**
     * rsa加密
     * TODO 加密应该使用外部工具
     */
    private fun rsaEncode(str: String): String {
        var outStr = ""
        try {
            // base64编码的公钥
            val decoded: ByteArray = Base64.decode(PUBLIC_KEY, Base64.DEFAULT)
            val pubKey = KeyFactory.getInstance("RSA")
                .generatePublic(X509EncodedKeySpec(decoded)) as RSAPublicKey
            // RSA加密
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, pubKey)
            outStr = Base64.encodeToString(
                cipher.doFinal(str.toByteArray(charset("UTF-8"))),
                Base64.DEFAULT
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return outStr
    }

}