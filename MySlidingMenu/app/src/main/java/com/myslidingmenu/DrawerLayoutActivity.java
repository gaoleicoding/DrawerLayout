package com.myslidingmenu;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
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
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu_layout = (LinearLayout) findViewById(R.id.menu_layout);

        String[] str = new String[]{"item1", "item2", "item3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, str);
        mLv.setAdapter(adapter);
        //设置为透明状态栏后，布局和屏幕顶端没有距离，所以需要，空出状态栏的距离
        setStatusBar();

        initEvent();

    }

    public void openLeftLayout(View view) {
        if (drawer_layout.isDrawerOpen(menu_layout)) {
            drawer_layout.closeDrawer(menu_layout);
        } else {
            drawer_layout.openDrawer(menu_layout);
        }
    }

    private void initEvent() {
        DrawerLayout.DrawerListener drawerbar = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                Log.d("gaolei", "onDrawerOpened-------------");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.d("gaolei", "onDrawerClosed-------------");

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };
        drawer_layout.addDrawerListener(drawerbar);
    }
}
