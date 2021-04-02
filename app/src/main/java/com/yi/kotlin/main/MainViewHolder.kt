package com.yi.kotlin.main

import android.view.View
import android.widget.TextView
import com.yi.kotlin.R
import com.yi.kotlin.base.AppBaseViewHolder
import com.yi.kotlin.data.LoginData

/**
 * @author Yi
 * @date 2020/4/16
 */
class MainViewHolder(view: View) : AppBaseViewHolder<LoginData?>(view) {
    private val titleTv = lazyBindView(TextView::class.java, R.id.tv_main_recycler_item)
    override fun setData(data: LoginData?) {
        titleTv.text = data?.title
    }
}