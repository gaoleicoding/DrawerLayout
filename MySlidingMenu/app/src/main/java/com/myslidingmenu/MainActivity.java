package com.myslidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

}
