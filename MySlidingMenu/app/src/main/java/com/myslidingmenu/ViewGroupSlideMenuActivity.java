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
        setStatusBar();
    }
    public void openMenu(View view){
        activity_viewgroup.openMenu();
    }
    public void closeMenu(View view){
        activity_viewgroup.closeMenu();
    }
}
