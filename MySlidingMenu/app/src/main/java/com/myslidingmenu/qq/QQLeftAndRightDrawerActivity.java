package com.myslidingmenu.qq;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.myslidingmenu.R;
import com.myslidingmenu.Utils;
import com.nineoldandroids.view.ViewHelper;

/**
 * http://blog.csdn.net/lmj623565791/article/details/41531475
 * @author zhy
 *
 */
public class QQLeftAndRightDrawerActivity extends FragmentActivity
{

	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_qq);

		initView();
		initEvents();
		//反射设置滑动区域来调出左侧菜单，现在区域为0.5
		Utils.setDrawerEdgeSize(this,mDrawerLayout,0.5f);
	}

	public void OpenRightMenu(View view)
	{
		//设置右侧菜单只能点击弹出
		mDrawerLayout.openDrawer(Gravity.RIGHT);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.RIGHT);
	}
	


	private void initEvents()
	{
		mDrawerLayout.setDrawerListener(new DrawerListener()
		{
			@Override
			public void onDrawerStateChanged(int newState)
			{
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset)
			{
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT"))
				{

					float leftScale = 1 - 0.3f * scale;

					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,
							mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else
				{
					ViewHelper.setTranslationX(mContent,
							-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView)
			{
			}

			@Override
			public void onDrawerClosed(View drawerView)
			{
				mDrawerLayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

	private void initView()
	{
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
	}

}
