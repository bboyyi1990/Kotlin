package com.yi.kotlin.main

import android.view.View
import com.yi.kotlin.R
import com.yi.kotlin.base.AppBaseAdapter
import com.yi.kotlin.base.AppBaseViewHolder
import com.yi.kotlin.data.LoginData
import kotlinx.android.synthetic.main.item_main_recycler.view.*

/**
 * @author Yi
 * @date 2020/4/16
 */
class MainAdapter :
    AppBaseAdapter<LoginData, MainAdapter.ViewHolder<LoginData>>(R.layout.item_main_recycler) {

    inner class ViewHolder<T>(view: View) : AppBaseViewHolder<LoginData>(view) {
        override fun setData(data: LoginData) {
            itemView.tv_main_recycler_item.text = data.title
        }
    }
}
