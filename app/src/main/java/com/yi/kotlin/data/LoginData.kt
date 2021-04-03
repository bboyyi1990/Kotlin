package com.yi.kotlin.data

import com.yi.common.http.BaseData

/**
 * create by Yi on 2021/4/2
 *
 */
class LoginData(title: String) : BaseData() {
    var title: String? = null

    init {
        this.title = title
    }
}