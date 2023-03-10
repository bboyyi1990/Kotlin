package com.yi.common.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.io.Reader
import java.lang.reflect.Type

inline fun <reified T> Gson.jsonToBean(string: String): T =
    fromJson(string, T::class.java)

inline fun <reified T> Gson.fromJsonByType(string: String): T =
    fromJson(string, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromJson(reader: Reader): T =
    fromJson(reader, T::class.java)

inline fun <reified T> Gson.fromJson(jsonElement: JsonElement): T =
    fromJson(jsonElement, T::class.java)

inline fun <reified T> gsonTypeExt(): Type = object : TypeToken<T>() {}.type

inline fun <reified T> String?.fromJson(): T? {
    return try {
        Gson().fromJson(this, gsonTypeExt<T>())
    } catch (e: Exception) {
//        "fromJson exception=$e".logE()
        null
    }
}