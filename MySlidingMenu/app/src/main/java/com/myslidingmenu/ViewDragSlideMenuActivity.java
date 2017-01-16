package com.myslidingmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static com.myslidingmenu.R.id.shadow;


public class ViewDragSlideMenuActivity extends BaseActivity implements ViewDragSlideMenu.OnMenuSlideListener {

    ViewDragSlideMenu activity_viewdrag;
    View shadowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdragslidemenu);
        setStatusBar();
        activity_viewdrag = (ViewDragSlideMenu) findViewById(R.id.activity_viewdrag);
        shadowView = (View) findViewById(shadow);


        activity_viewdrag.setOnMenuSlideListener(this);
    }

    @Override
    public void onMenuSlide(float offset) {
        shadowView.setVisibility(offset == 0 ? View.INVISIBLE : View.VISIBLE);
        int alpha = (int) Math.round(offset * 255 * 0.4);
        String hex = Integer.toHexString(alpha).toUpperCase();
        Log.d("gaolei", "color------------" + "#" + hex + "000000");
        shadowView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
    }
    public void openMenu(View view){
        activity_viewdrag.openDrawer();
        shadowView.setVisibility(View.VISIBLE);
    }
    public void closeMenu(View view){
        activity_viewdrag.closeDrawer();
        shadowView.setVisibility(View.GONE);

    }
}
