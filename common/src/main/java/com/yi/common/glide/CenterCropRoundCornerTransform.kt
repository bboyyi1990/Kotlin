package com.yi.common.glide

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.CenterCrop

/**
 * create by Yi on 2021/4/3
 * 圆角裁剪
 */
class CenterCropRoundCornerTransform(radius: Int) :
    CenterCrop() {
    private var radius = 0
    private var isLeftTop = false
    private var isRightTop = false
    private var isLeftBottom = false
    private var isRightBotoom = false

    init {
        this.radius = radius
    }


    /**
     * 需要设置圆角的部分
     *
     * @param leftTop     左上角
     * @param rightTop    右上角
     * @param leftBottom  左下角
     * @param rightBottom 右下角
     */
    fun setNeedCorner(
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean
    ) {
        isLeftTop = leftTop
        isRightTop = rightTop
        isLeftBottom = leftBottom
        isRightBotoom = rightBottom
    }

//    public void setNeedCorner(boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom)
//    {
//        isLeftTop = leftTop
//        isRightTop = rightTop
//        isLeftBottom = leftBottom
//        isRightBotoom = rightBottom
//    }


    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val transform = super.transform(pool, toTransform, outWidth, outHeight)
        return roundCrop(pool, transform)
    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
        var result = pool.get(source.width, source.height, Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(
                source.width, source.height,
                Bitmap.Config.ARGB_8888
            )
        }
        var canvas = Canvas(result)
        var paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true

        var rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
        canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)

        //左上角圆角
        if (!isLeftTop) {
            canvas.drawRect(0f, 0f, radius.toFloat(), radius.toFloat(), paint)
        }

        //右上角圆角
        if (!isRightTop) {
            canvas.drawRect(
                (canvas.width - radius).toFloat(),
                0f,
                canvas.width.toFloat(),
                radius.toFloat(),
                paint
            )
        }
        //左下角圆角
        if (!isLeftBottom) {
            canvas.drawRect(
                0f,
                (canvas.height - radius).toFloat(),
                radius.toFloat(),
                canvas.height.toFloat(),
                paint
            )
        }
        //右下角圆角
        if (!isRightBotoom) {
            canvas.drawRect(
                (canvas.width - radius).toFloat(),
                (canvas.height - radius).toFloat(),
                (canvas.width).toFloat(),
                (canvas.height).toFloat(),
                paint
            )
        }
        return result
    }
}