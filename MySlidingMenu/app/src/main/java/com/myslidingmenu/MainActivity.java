package com.myslidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myslidingmenu.qq.QQLeftAndRightDrawerActivity;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toViewGroupSlideMenuActivity(View view) {
        startActivity(new Intent(this, ViewGroupSlideMenuActivity.class));
    }

    public void toViewDragSlideMenuActivity(View view) {
        startActivity(new Intent(this, ViewDragSlideMenuActivity.class));
    }

    public void toDrawerLayoutActivity(View view) {
        startActivity(new Intent(this, DrawerLayoutActivity.class));
    }

    public void toLeftAndRightDrawerActivity(View view) {
        startActivity(new Intent(this, LeftAndRightDrawerActivity.class));
    }

    public void toQQLeftAndRightDrawerActivity(View view) {
        startActivity(new Intent(this, QQLeftAndRightDrawerActivity.class));
    }

}
