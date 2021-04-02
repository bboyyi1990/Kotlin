package com.yi.common.util

import com.tencent.mmkv.MMKV

/**
 * @author Yi
 * @date 2020/5/19
 */
object MemoryUtil {

    private val mmkv = MMKV.defaultMMKV()

    fun saveString(key: String, value: String?) =
        if (null == value) {
            mmkv.remove(key)
        } else {
            mmkv.encode(key, value)
        }


    fun getString(key: String, defaultValue: String?) = mmkv.getString(key, defaultValue)

    fun saveLong(key: String, value: Long?) =
        if (null == value) {
            mmkv.remove(key)
        } else {
            mmkv.encode(key, value)
        }


    fun getLong(key: String, defaultValue: Long?) = mmkv.getLong(key, defaultValue ?: 0)

    fun saveBoolean(key: String, value: Boolean) =
        if (null == value) {
            mmkv.remove(key)
        } else {
            mmkv.encode(key, value)
        }


    fun getBoolean(key: String, defaultValue: Boolean?) =
        mmkv.getBoolean(key, defaultValue ?: false)
}