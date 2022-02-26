package com.yi.kotlin.action.api

import android.util.Base64
import com.yi.common.http.BaseAction
import com.yi.common.http.BaseData
import com.yi.common.http.RetrofitClient
import com.yi.common.util.MemoryUtil
import com.yi.kotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

/**
 * @author Yi
 * @date 2020/5/9
 */
open abstract class ApiAction<T : BaseData> : BaseAction<T>() {
    private val PUBLIC_KEY = ""
    private val APPKEY = ""
    private val BASE_URL = ""

    override fun getApi(): ApiService =
        RetrofitClient.getApi(ApiService::class.java, BASE_URL, Interceptor { chain ->
            //TODO customize your request params
            chain.proceed(chain.request())
        })

    override fun buildParams(params: MutableMap<String, Any>): MutableMap<String, Any> {
        params["sign"] = createSign(params)
        return params
    }

    override fun buildHeaders(headers: MutableMap<String, Any>): MutableMap<String, Any> {
        headers["userToken"] = MemoryUtil.getString("userToken", "")!!
        headers["version"] = BuildConfig.VERSION_CODE
        headers["language"] = "ZH"
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