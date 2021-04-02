package com.yi.kotlin.base

import androidx.lifecycle.ViewModel

/**
 * create by Yi on 2021/4/3
 * 基类viewModel 通用业务写此处
 * 任何情况下都不要持有activity
 * 如果需要context使用application
 */
abstract class BaseViewModel : ViewModel() {
}