package utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class Utils {
    private static float scale = -1f;

    public static int dp2px(Context context, int dp) {
        if (scale <= 0) {
            setScale(context);
        }
        return (int) (dp * scale);
    }

    public static int px2dp(Context context, int px) {
        if (scale <= 0) {
            setScale(context);
        }
        return (int) (px / scale);
    }

    private static void setScale(Context context) {
        scale = context.getResources().getDisplayMetrics().density;
    }

    //屏幕宽度的尺寸
    private static int displayWithPixels;
    //屏幕高度的尺寸
    private static int displayHeightPixels;
    /**
     * 获取屏幕的宽度
     * @param context
     * @return
     */
    public static int getDisplayWidthPixels(Context context) {
        if(context == null){
            return -1;
        }else if(displayWithPixels == 0){
            getScreenPixels(context);
        }
        return displayWithPixels;
    }

    /**
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getDisplayHeightPixels(Context context) {
        if(context == null){
            return -1;
        }else if(displayHeightPixels == 0){
            getScreenPixels(context);
        }
        return displayHeightPixels;
    }

    /**
     * 方法内函数，获取屏幕尺寸
     * @param context
     */
    private static void getScreenPixels(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        displayWithPixels = metrics.widthPixels;
        displayHeightPixels = metrics.heightPixels;
    }

    /**
     * 将dip转换成px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px转换成dip
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


  

}
