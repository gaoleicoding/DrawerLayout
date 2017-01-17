package com.myslidingmenu;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by gaolei on 16/12/30.
 */

public class Utils {



    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static void setDrawerEdgeSize(Activity activity,DrawerLayout drawerLayout,  float proportion){
        if(drawerLayout==null||activity==null){
            return;
        }
        try {
            Field field = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            field.setAccessible(true);
            ViewDragHelper mLeftDragger = (ViewDragHelper) field.get(drawerLayout);
            Field field1 = mLeftDragger.getClass().getDeclaredField("mEdgeSize");
            field1.setAccessible(true);
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            field1.setInt(mLeftDragger, (int) (metrics.widthPixels*proportion));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
