package com.yi.common.http

/**
 * @author Yi
 * @date 2020/5/8
 * 响应数据结构
 */
open class BaseResponse<T : BaseData> {
    var code: String = ""
    var data: T? = null
    var message: String? = null
    var timestamp: Long = 0L
    var success = false
}