package com.yi.kotlin.base

import androidx.lifecycle.ViewModel

/**
 * create by Yi on 2021/4/3
 * 基类viewModel 通用业务写此处
 * 原则上viewModel 不会持有context
 * 如果需要context情况无发避免application
 */
abstract class BaseViewModel : ViewModel() {
}