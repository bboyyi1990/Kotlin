package com.yi.kotlin.glide;


import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.yi.kotlin.base.BaseApplication;

import java.security.MessageDigest;

/**
 * @author Yi
 * @date 2020/3/6
 * glide 模糊处理
 */
public class GlideBlurTransform extends BitmapTransformation {

    private static final int BLUR_RADIUS = 25;

    public GlideBlurTransform() {
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return BlurBitmapUtil.instance().blurBitmap(BaseApplication.Companion.getInstance(), toTransform, BLUR_RADIUS, outWidth, outHeight);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }
}
