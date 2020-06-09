package com.yi.kotlin.main

import android.view.View
import android.widget.TextView
import com.yi.kotlin.R
import com.yi.kotlin.base.AppBaseViewHolder
import com.yi.kotlin.data.MainData

/**
 * @author Yi
 * @date 2020/4/16
 */
class MainViewHolder(view: View) : AppBaseViewHolder<MainData?>(view) {
    private val titleTv = lazyBindView(TextView::class.java, R.id.tv_main_recycler_item)
    override fun setData(data: MainData?) {
        titleTv.text = data?.title
    }
}