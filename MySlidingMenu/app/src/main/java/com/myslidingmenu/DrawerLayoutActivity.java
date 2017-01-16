package com.myslidingmenu;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import android.widget.ListView;

public class DrawerLayoutActivity extends BaseActivity {

    DrawerLayout drawer_layout;
    LinearLayout menu_layout;
    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);

        mLv = (ListView) findViewById(R.id.id_lv);
        drawer_layout= (DrawerLayout) findViewById(R.id.drawer_layout);
        menu_layout= (LinearLayout) findViewById(R.id.menu_layout);

        String[] str = new String[] { "item1", "item2", "item3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, str);
        mLv.setAdapter(adapter);
        setStatusBar();
//        setTranslucentForDrawerLayout(this,drawer_layout);
    }

    public void openLeftLayout(View view) {
        if (drawer_layout.isDrawerOpen(menu_layout)) {
            drawer_layout.closeDrawer(menu_layout);
        } else {
            drawer_layout.openDrawer(menu_layout);
        }
    }

    public static void setTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置内容布局属性
            ViewGroup contentLayout = (ViewGroup) drawerLayout.getChildAt(0);
            contentLayout.setFitsSystemWindows(true);
            contentLayout.setClipToPadding(true);
            // 设置抽屉布局属性
            ViewGroup vg = (ViewGroup) drawerLayout.getChildAt(1);
            vg.setFitsSystemWindows(false);
            // 设置 DrawerLayout 属性
            drawerLayout.setFitsSystemWindows(false);
        }
    }
}
