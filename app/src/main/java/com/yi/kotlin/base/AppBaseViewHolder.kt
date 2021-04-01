package com.yi.kotlin.base

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author Yi
 * @date 2020/4/16
 */
abstract class AppBaseViewHolder<T>(view: View) : BaseViewHolder(view) {

    open abstract fun setData(data: T)

    open fun <E : View> lazyBindView(clazz: Class<E>, id: Int): E =
        lazy { itemView.findViewById<E>(id) }.value
}