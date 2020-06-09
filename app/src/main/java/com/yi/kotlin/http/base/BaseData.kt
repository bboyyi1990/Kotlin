package com.yi.kotlin.http.base

/**
 * @author Yi
 * @date 2020/5/8
 */
open abstract class BaseData {
    var code: Int = 0
    var data: Any? = null;
    var msg: String? = null;
}