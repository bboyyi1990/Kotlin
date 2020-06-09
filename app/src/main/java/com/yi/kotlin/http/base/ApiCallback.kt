package com.yi.kotlin.http.base

/**
 * @author Yi
 * @date 2020/5/11
 */
open class ApiCallback<T : BaseData> : BaseCallback<T>() {

    /**
     * if want intercept base callback common business deal
     * please remove super.onNext()
     */
    override fun onNext(t: T) {
        super.onNext(t)
    }
}