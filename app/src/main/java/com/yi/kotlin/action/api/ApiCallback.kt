package com.yi.kotlin.action.api

import com.yi.common.http.BaseResponse
import com.yi.common.http.BaseCallback
import com.yi.common.http.BaseData

/**
 * @author Yi
 * @date 2020/5/11
 */
open class ApiCallback<T:BaseData> : BaseCallback<T>() {

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


    override fun onError(e: Throwable) {
        super.onError(e)

    }
}