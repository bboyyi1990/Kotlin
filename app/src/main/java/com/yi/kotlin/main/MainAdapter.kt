package com.yi.kotlin.main

import com.yi.kotlin.R
import com.yi.kotlin.base.AppBaseAdapter
import com.yi.kotlin.data.MainData

/**
 * @author Yi
 * @date 2020/4/16
 */
class MainAdapter: AppBaseAdapter<MainData, MainViewHolder>(R.layout.item_main_recycler) {
    override fun convert(helper: MainViewHolder, item: MainData?) {
        helper.setData(item)
    }
}