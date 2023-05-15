package com.yi.kotlin.main

import android.view.View
import com.yi.kotlin.R
import com.yi.kotlin.base.AppBaseAdapter
import com.yi.kotlin.base.AppBaseViewHolder
import com.yi.kotlin.data.LoginData

/**
 * @author Yi
 * @date 2020/4/16
 */
class MainAdapter :
    AppBaseAdapter<LoginData, MainAdapter.ViewHolder>(R.layout.item_main_recycler) {

    inner class ViewHolder(view: View) : AppBaseViewHolder<LoginData>(view) {
        override fun setData(data: LoginData) {
//            itemView.tv_main_recycler_item.text = data.title
        }
    }
}
