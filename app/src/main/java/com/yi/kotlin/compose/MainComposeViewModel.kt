package com.yi.kotlin.compose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.yi.common.util.ToastUtil
import com.yi.kotlin.base.BaseViewModel
import java.util.UUID

class MainComposeViewModel : BaseViewModel() {
    val result = MutableLiveData<String>()
    val _result = mutableStateOf("-")

    fun test() {
//        result.postValue(UUID.randomUUID().toString())
        _result.value = UUID.randomUUID().toString()
    }

    fun getText() = _result.value

    override fun onCleared() {
        super.onCleared()
        ToastUtil.showToast(" compose cleared")
    }
}