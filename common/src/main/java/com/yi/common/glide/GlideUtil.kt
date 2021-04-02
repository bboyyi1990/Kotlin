package com.yi.common.glide

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.yi.common.R
import com.yi.common.util.Logger
import java.util.*

/**
 * @author Yi
 * @date 2020/5/15
 */
object GlideUtil {
    val TAG = GlideUtil::class.java.simpleName

    const val ROUND = 1
    const val TOP_ROUND = 2
    const val RECT = 3
    const val CIRCLE = 4

    /**
     * 核心方法
     *
     * @param path   资源地址
     * @param radius 圆角-px 没有填0
     * @param shape  加载形状 参照顶部定义常量
     * @param blur   是否模糊
     * @param width  宽-px (如果给0默认为图片尺寸)
     * @param height 高-px (如果给0默认为图片尺寸)
     * @param view   imageView
     */
    fun loadImage(
        path: String?, radius: Int, shape: Int,
        width: Int, height: Int, view: ImageView?, blur: Boolean = false
    ) {
        var isDestroy = isDestroy(view?.context)
        if (view == null || isDestroy) {
            return
        }
        //尺寸计算
        var w = width
        var h = height
        if (w == 0) {
            w = view.width
        }
        if (h == 0) {
            h = view.height
        }
        //定义图片处理参数
        val list: MutableList<RequestOptions> =
            ArrayList()
        when (shape) {
            CIRCLE -> {
                val requestOptions = RequestOptions()
                requestOptions.circleCrop()
                list.add(requestOptions)
            }
            ROUND -> {
                val transform = CenterCropRoundCornerTransform(h, w, radius)
                transform.setNeedCorner(true, true, true, true)
                val options: RequestOptions =
                    RequestOptions().placeholder(R.color.common_transparent_color)
                        .transform(transform)
                list.add(options)
            }
            TOP_ROUND -> {
                val transform = CenterCropRoundCornerTransform(h, w, radius)
                transform.setNeedCorner(true, true, false, false)
                val options: RequestOptions =
                    RequestOptions().placeholder(R.color.common_transparent_color)
                        .transform(transform)
                list.add(options)
            }
        }
        //模糊处理逻辑
        if (blur) {
            val multi: MultiTransformation<Bitmap> = MultiTransformation(GlideBlurTransform())
            val blurOptions = RequestOptions.bitmapTransform(multi)
            list.add(blurOptions)
        }
        //最终框架载入
        val requestBuilder: RequestBuilder<*> = Glide.with(view).load(path)
        for (i in list.indices) {
            requestBuilder.apply(list[i])
        }
        requestBuilder.into(view)
    }

    private fun isDestroy(context: Context?): Boolean {
        return when (context) {
            null -> {
                true
            }
            !is Activity -> {
                true
            }
            else -> {
                var isDestroy = context.isDestroyed
                Logger.e(TAG, "isDestroy - $isDestroy")
                isDestroy
            }
        }
    }
}