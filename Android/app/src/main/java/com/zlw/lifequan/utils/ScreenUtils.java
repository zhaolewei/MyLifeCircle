package com.zlw.lifequan.utils;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * ScreenUtils
 * <ul>
 * <strong>Convert between dp and sp</strong>
 * <li>{@link ScreenUtils#dpToPx(Context, float)}</li>
 * <li>{@link ScreenUtils#pxToDp(Context, float)}</li>
 * </ul>
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-14
 */
public class ScreenUtils {

    private static final String TAG = ScreenUtils.class.getSimpleName();

    private ScreenUtils() {
        throw new AssertionError();
    }

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpCeilInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }

    /**
     * 根据  maxPixels 自动设置text大小
     * @param textView
     * @param text
     * @param maxPixels
     * @param textSizeSp
     */
    public static void resetTextViewSize(TextView textView, String text, int maxPixels, float textSizeSp) {
        if (textView == null) {
            Logger.i(TAG, "textView is null");
            return;
        }
        if (TextUtils.isEmpty(text)) {
            Logger.i(TAG, "text is null");
            return;
        }
        textView.setMaxWidth(maxPixels);
        textView.setTextSize(textSizeSp);
        textView.setText(text);
        TextPaint paint = textView.getPaint();
        float textWidth = paint.measureText(textView.getText().toString());
        float textSize = ScreenUtils.dpToPx(textView.getContext(), textSizeSp);
        float oneCharWidth = textSize / (textWidth / maxPixels);

        if (textSize > oneCharWidth) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, oneCharWidth);
            textView.setText(text);
//            Logger.i(TAG, "setTextSize compute " + textView.getText() + "     " + oneCharWidth);
//            Logger.i(TAG, "getTextSize compute " + textView.getText() + "     " + textView.getTextSize());
        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            textView.setText(text);
//            Logger.i(TAG, "setTextSize " + textView.getText() + "     " + textSize);
//            Logger.i(TAG, "getTextSize " + textView.getText() + "     " + textView.getTextSize());
        }
    }
}
