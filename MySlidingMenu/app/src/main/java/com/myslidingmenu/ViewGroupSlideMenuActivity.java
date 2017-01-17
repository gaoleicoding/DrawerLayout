package com.myslidingmenu;

import android.os.Bundle;
import android.view.View;


public class ViewGroupSlideMenuActivity extends BaseActivity {

    ViewGroupSlideMenu activity_viewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroupslidemenu);
        activity_viewgroup=(ViewGroupSlideMenu) findViewById(R.id.activity_viewgroup);
        //设置为透明状态栏后，布局和屏幕顶端没有距离，所以需要，空出状态栏的距离

        setStatusBar();
    }
    public void openMenu(View view){
        activity_viewgroup.openMenu();
    }
    public void closeMenu(View view){
        activity_viewgroup.closeMenu();
    }
}
