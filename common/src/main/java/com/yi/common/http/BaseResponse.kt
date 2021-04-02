package com.yi.common.http

/**
 * @author Yi
 * @date 2020/5/8
 * 响应数据结构
 */
open abstract class BaseResponse<BaseData> {
    var code: String = ""
    var data: BaseData? = null
    var message: String? = null
    var timestamp: Long = 0L
    var success = false
}