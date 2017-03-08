package com.zlw.lifequan.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by zlw on 2017/1/4.
 */

public class BitmapUtil {


    // 1)Drawable → Bitmap
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        return drawable2Bitmap(drawable, Bitmap.Config.ARGB_4444);
    }

    // 1)Drawable → Bitmap
    public static Bitmap drawable2Bitmap(Drawable drawable, Bitmap.Config config) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * 裁剪
     *
     * @param bitmap      原图
     * @param startXRatio 起始位置(百分比0-1)
     * @param startYRatio
     * @return
     */
    public static Bitmap cropBitmap(Bitmap bitmap, float startXRatio, float startYRatio) {

        return cropBitmap(bitmap, startXRatio, startYRatio, 1, 1);
    }


    /**
     * 裁剪图片
     *
     * @param bitmap      原图
     * @param startXRatio 起始位置(百分比0-1)
     * @param startYRatio
     * @param endXRatio   结束位置(百分比0-1)
     * @param endYRatio
     * @return
     */
    public static Bitmap cropBitmap(Bitmap bitmap, float startXRatio, float startYRatio, float endXRatio, float endYRatio) {
        if (bitmap == null)
            return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int startX = (int) (w * startXRatio);
        int startY = (int) (h * startYRatio);

        int cropWidth = (int) (w * endXRatio) - startX;
        int cropHeight = (int) (h * endYRatio) - startY;

        return Bitmap.createBitmap(bitmap, startX, startY, cropWidth, cropHeight, null, false);
    }

    /**
     * 裁剪
     *
     * @param drawable    原图
     * @param startXRatio 起始位置(百分比0-1)
     * @param startYRatio
     * @return
     */
    public static Bitmap cropBitmap(Drawable drawable, float startXRatio, float startYRatio) {

        return cropBitmap(drawable, startXRatio, startYRatio, 1, 1);
    }

    /**
     * 裁剪图片
     *
     * @param drawable    原图
     * @param startXRatio 起始位置(百分比0-1)
     * @param startYRatio
     * @param endXRatio   结束位置(百分比0-1)
     * @param endYRatio
     * @return
     */
    public static Bitmap cropBitmap(Drawable drawable, float startXRatio, float startYRatio, float endXRatio, float endYRatio) {
        if (drawable == null)
            return null;
        return cropBitmap(drawable2Bitmap(drawable), startXRatio, startYRatio, endXRatio, endYRatio);
    }

    /**
     * 横向拼接
     *
     * @param first
     * @param second
     * @return
     */
    public static Bitmap jointBitmap(Bitmap first, Bitmap second) {
        int width = first.getWidth() + second.getWidth();
        int height = Math.max(first.getHeight(), second.getHeight());
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, first.getWidth(), 0, null);
        return result;
    }


    /**
     * 纵向拼接
     *
     * @param first
     * @param second
     * @return
     */
    public static Bitmap jointBitmap_vertical(Bitmap first, Bitmap second) {
        int width = Math.max(first.getWidth(), second.getWidth());
        int height = first.getHeight() + second.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, first.getHeight(), 0, null);
        return result;
    }

//    /**
//     * 纵向拼接
//     *
//     * @return
//     */
//    public static Bitmap jointBitmaps_vertical(int maxWidth, Bitmap... bitmaps) {
//        if (bitmaps == null || bitmaps.length == 0) {
//            return null;
//        }
//        if (bitmaps.length == 1) {
//            return bitmaps[0];
//        }
//
//        int h = 0; //计算总高度
//        int pointH = 0;//绘制起点高度
//        for (Bitmap bitmap : bitmaps) {
//            h += bitmap.getHeight();
//        }
//
//
//        Bitmap result = Bitmap.createBitmap(maxWidth, h, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(result);
//
//        for (Bitmap bitmap : bitmaps) {
//            canvas.drawBitmap(bitmap, 0, pointH, null);
//            pointH += bitmap.getHeight();
//        }
//        return result;
//    }

    /**
     * 纵向拼接
     *
     * @return
     */
    public static Bitmap jointBitmaps_vertical(int maxWidth, Bitmap[] bitmaps) {
        if (bitmaps == null || bitmaps.length == 0) {
            return null;
        }
        if (bitmaps.length == 1) {
            return bitmaps[0];
        }

        int h = 0; //计算总高度
        int pointH = 0;//绘制起点高度
        for (Bitmap bitmap : bitmaps) {
            h += bitmap.getHeight();
        }


        Bitmap result = Bitmap.createBitmap(maxWidth, h, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(result);

        for (Bitmap bitmap : bitmaps) {
            canvas.drawBitmap(bitmap, 0, pointH, null);
            pointH += bitmap.getHeight();
        }
        return result;
    }

    /**
     * 纵向拼接
     *
     * @return
     */
    public static Bitmap jointBitmaps_vertical(int maxWidth, List<Bitmap> bitmaps) {
        if (bitmaps == null || bitmaps.size() == 0) {
            return null;
        }
        if (bitmaps.size() == 1) {
            return bitmaps.get(0);
        }

        int h = 0; //计算总高度
        int pointH = 0;//绘制起点高度
        for (Bitmap bitmap : bitmaps) {
            if (bitmap == null) {
                continue;
            }
            h += bitmap.getHeight();
        }


        Bitmap result = Bitmap.createBitmap(maxWidth, h, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(result);

        for (Bitmap bitmap : bitmaps) {
            if (bitmap == null) {
                continue;
            }
            canvas.drawBitmap(bitmap, 0, pointH, null);
            pointH += bitmap.getHeight();
        }
        return result;
    }
}
