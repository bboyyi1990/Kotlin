package com.yi.kotlin.data

import com.yi.kotlin.http.base.BaseData

/**
 * @author Yi
 * @date 2020/4/16
 */
class MainData : BaseData {
    var title: String? = null

    constructor(title: String?) {
        this.title = title
        code
    }
}