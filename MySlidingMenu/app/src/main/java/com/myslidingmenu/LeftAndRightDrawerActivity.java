package com.myslidingmenu;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LeftAndRightDrawerActivity extends FragmentActivity {
	// 抽屉菜单对象
	private ActionBarDrawerToggle drawerbar;
	public DrawerLayout drawerLayout;
	private TestFragment testFragment;
	private RelativeLayout left_menu_layout, right_xiaoxi_layout;
	
	private TextView text;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_left_right_drawer);
		initView();
		initEvent();
	}
	public void initView(){
			testFragment = new TestFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction f_transaction = fragmentManager.beginTransaction();
			f_transaction.replace(R.id.main_content_frame_parent, testFragment);
			f_transaction.commitAllowingStateLoss();
			initLeftLayout();
			initRightLayout();
	}
	public void initLeftLayout(){
		drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
		//设置透明
		drawerLayout.setScrimColor(0x00000000);
		//左边菜单
		left_menu_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
		View view2 = getLayoutInflater().inflate(R.layout.menu_layout, null);
		text=(TextView)view2.findViewById(R.id.text);
		text.setText("左边测试菜单");
		left_menu_layout.addView(view2);
	}
	public void initRightLayout(){
		//左边菜单
		right_xiaoxi_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
		View view = getLayoutInflater().inflate(R.layout.menu_layout, null);
		text=(TextView)view.findViewById(R.id.text);
		text.setText("右边测试菜单");
		right_xiaoxi_layout.addView(view);
	}
	private void initEvent() {
		drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_launcher, R.string.open, R.string.close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				
				super.onDrawerOpened(drawerView);
				Log.d("gaolei","onDrawerOpened-------------");
			}

			@Override
			public void onDrawerClosed(View drawerView) {

				super.onDrawerClosed(drawerView);
				Log.d("gaolei","onDrawerClosed-------------");

			}
		};
		drawerLayout.setDrawerListener(drawerbar);
	}

	public void openLeftLayout() {
		if (drawerLayout.isDrawerOpen(left_menu_layout)) {
			drawerLayout.closeDrawer(left_menu_layout);
		} else {
			drawerLayout.openDrawer(left_menu_layout);
		}
	}

	// 消息开关事件
	public void openRightLayout() {
		if (drawerLayout.isDrawerOpen(right_xiaoxi_layout)) {
			drawerLayout.closeDrawer(right_xiaoxi_layout);
		} else {
			drawerLayout.openDrawer(right_xiaoxi_layout);
		}
	}
}
