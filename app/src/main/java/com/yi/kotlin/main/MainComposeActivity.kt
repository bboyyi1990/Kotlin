package com.yi.kotlin.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.yi.common.util.ToastUtil
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.BaseComposeActivity
import com.yi.kotlin.base.Router

/**
 * create by yi on 2023/3/17
 */
@Route(path = "${Router.GROUP}MainComposeActivity")
class MainComposeActivity : BaseComposeActivity() {

    private val model by viewModels<MainViewModel>()

    override fun onCreate() {
        initBar(true)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { Greeting() }
    }
}

@Composable
fun Greeting() {
//    Scaffold {
//        it
//
//    }
    Text(
        text = "Hello Everyone!",
        modifier = Modifier
            .background(Color.Red)
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable {
                ToastUtil.showToast("MainCompose")
            },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting()
}