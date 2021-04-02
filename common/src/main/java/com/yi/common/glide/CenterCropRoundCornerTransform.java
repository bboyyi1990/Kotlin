//package com.yi.common.glide;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.RectF;
//
//import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
//import com.bumptech.glide.load.resource.bitmap.CenterCrop;
//
///**
// * @author Yi
// * @date 2020/3/6
// * 圆角裁剪转换器
// */
//public class CenterCropRoundCornerTransform extends CenterCrop {
//    private int radius = 0;
//    private boolean isLeftTop, isRightTop, isLeftBottom, isRightBotoom;
//    private int height, weight;
//
////    public CenterCropRoundCornerTransform(int radius) {
////        this.radius = radius;
////    }
//
//    public CenterCropRoundCornerTransform(int height, int weight, int radius) {
//        this.height = height;
//        this.weight = weight;
//        this.radius = radius;
//    }
//
//
//    /**
//     * 需要设置圆角的部分
//     *
//     * @param leftTop     左上角
//     * @param rightTop    右上角
//     * @param leftBottom  左下角
//     * @param rightBottom 右下角
//     */
//    public void setNeedCorner(boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
//        isLeftTop = leftTop;
//        isRightTop = rightTop;
//        isLeftBottom = leftBottom;
//        isRightBotoom = rightBottom;
//    }
//
//    @Override
//    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//        Bitmap transform = super.transform(pool, toTransform, weight, height);
//        return roundCrop(pool, transform);
//    }
//
//    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
//        if (source == null) {
//            return null;
//        }
//        Bitmap result = pool.get(source.getWidth(), source.getHeight(),
//                Bitmap.Config.ARGB_8888);
//        if (result == null) {
//            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
//                    Bitmap.Config.ARGB_8888);
//        }
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP,
//                BitmapShader.TileMode.CLAMP));
//        paint.setAntiAlias(true);
//
//        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rectF, radius, radius, paint);
//
//
//        //左上角圆角
//        if (!isLeftTop) {
//            canvas.drawRect(0, 0, radius, radius, paint);
//        }
//
//        //右上角圆角
//        if (!isRightTop) {
//            canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
//        }
//        //左下角圆角
//        if (!isLeftBottom) {
//            canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
//        }
//        //右下角圆角
//        if (!isRightBotoom) {
//            canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
//        }
//        return result;
//    }
//}
