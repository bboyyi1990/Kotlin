package com.yi.kotlin.action.base

import com.yi.common.http.BaseResponse
import com.yi.kotlin.http.base.BaseCallback

/**
 * @author Yi
 * @date 2020/5/11
 */
open class ApiCallback<T> : BaseCallback<T>() {

    /**
     *  TODO dispose common business
     * if want intercept base callback common business dispose
     * please remove super.onNext()
     */
    override fun onNext(t: BaseResponse<T>) {
        super.onNext(t)
        if (t.success){

        }
    }

    override fun requestComplete() {
        super.requestComplete()
    }
}