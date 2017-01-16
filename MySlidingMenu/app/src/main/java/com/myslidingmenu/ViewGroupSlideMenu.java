package com.myslidingmenu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

import static com.myslidingmenu.R.id.content_layout;
import static com.myslidingmenu.R.id.menu_layout;

/**
 * Created by gaolei on 17/1/11.
 */

public class ViewGroupSlideMenu extends LinearLayout {
    //记录上次滑动后的坐标值
    private int lastX;
    private int lastY;
    View mLeftMenuView, mContentView;
    // 左边部分, 即从开始就显示的部分的长度
    int width_left = 0;
    // 右边部分, 即在开始时是隐
    int width_right = 0;
    int mMinDrawerMargin;
    int screenWidth, menuWidth;
    private static final int MIN_DRAWER_MARGIN = 64; // dp
    boolean isOpen = false;
    int downX, moveX = 0;
    /**
     * Minimum velocity that will be detected as a fling
     */
    private static final int MIN_FLING_VELOCITY = 400; // dips per second


    public ViewGroupSlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        final float density = getResources().getDisplayMetrics().density;

        mMinDrawerMargin = (int) (MIN_DRAWER_MARGIN * density + 0.5f);

        screenWidth = Utils.getScreenWidth(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);

        MarginLayoutParams lp = (MarginLayoutParams)
                mLeftMenuView.getLayoutParams();

        final int drawerWidthSpec = getChildMeasureSpec(widthMeasureSpec,
                mMinDrawerMargin + lp.leftMargin + lp.rightMargin,
                lp.width);
        final int drawerHeightSpec = getChildMeasureSpec(heightMeasureSpec,
                lp.topMargin + lp.bottomMargin,
                lp.height);
        mLeftMenuView.measure(drawerWidthSpec, drawerHeightSpec);

        lp = (MarginLayoutParams) mContentView.getLayoutParams();
        final int contentWidthSpec = MeasureSpec.makeMeasureSpec(
                widthSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
        final int contentHeightSpec = MeasureSpec.makeMeasureSpec(
                heightSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);

        mContentView.measure(contentWidthSpec, contentHeightSpec);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        View menuView = mLeftMenuView;
        View contentView = mContentView;
        Log.d("gaolei", "onLayout-------------------");

        MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
        contentView.layout(lp.leftMargin, lp.topMargin,
                lp.leftMargin + contentView.getMeasuredWidth(),
                lp.topMargin + contentView.getMeasuredHeight());

        lp = (MarginLayoutParams) menuView.getLayoutParams();

        menuWidth = menuView.getMeasuredWidth();
        int childLeft = -menuWidth + (int) (menuWidth * 0);
        menuView.layout(childLeft, lp.topMargin, childLeft + menuWidth,
                lp.topMargin + menuView.getMeasuredHeight());

    }

    public boolean onTouchEvent(MotionEvent event) {
        int totalMoveX = 0;
        // 获取view相对于手机屏幕的xy值
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                Log.d("gaolei", "deltaX--------------------｜" + deltaX);
                moveX = x - downX;
                Log.d("gaolei", "moveX--------------------｜" + moveX);
                Log.d("gaolei", "menuWidth--------------------｜" + menuWidth);
                Log.d("gaolei", "isOpen--------------------｜" + isOpen);
                if (moveX >= menuWidth) {
                    isOpen = true;
                }
                if (!isOpen) {
                    if (moveX < menuWidth) {
                        int translationX = (int) (ViewHelper.getTranslationX(mLeftMenuView) + deltaX);
                        ViewHelper.setTranslationX(mLeftMenuView, translationX);
                    }
                } else {
                    if (moveX < 0) {
                        int translationX = (int) (ViewHelper.getTranslationX(mLeftMenuView) + deltaX);
                        ViewHelper.setTranslationX(mLeftMenuView, translationX);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
//                Log.d("gaolei", "downX--------------------｜" + downX);
//                Log.d("gaolei", "moveX--------------------｜" + moveX);
//                Log.d("gaolei", "menuWidth/2--------------------｜" + menuWidth / 2);
                if (!isOpen) {
                    if (moveX >= menuWidth / 2) {
                        isOpen = true;
                        ViewHelper.setTranslationX(mLeftMenuView, menuWidth);
                    }
                    if (moveX < menuWidth / 2) {
                        ViewHelper.setTranslationX(mLeftMenuView, 0);
                    }
                } else {
                    if (Math.abs(moveX) < menuWidth / 2) {
                        ViewHelper.setTranslationX(mLeftMenuView, menuWidth);
                    }
                    if (Math.abs(moveX) > menuWidth / 2) {
                        ViewHelper.setTranslationX(mLeftMenuView, 0);
                        isOpen = false;
                    }
                }
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;

        return true;
    }

    public void openMenu() {
//      ViewHelper.setTranslationX(mLeftMenuView, menuWidth);
        isOpen = true;
        float curTranslationX = mLeftMenuView.getTranslationX();
        Log.d("gaolei","curTranslationX------------"+curTranslationX);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLeftMenuView, "translationX",0f,menuWidth);
        animator.setDuration(300);
        animator.start();
    }

    public void closeMenu() {
        isOpen = false;
        float curTranslationX = mLeftMenuView.getTranslationX();
        Log.d("gaolei","curTranslationX------------"+curTranslationX);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLeftMenuView, "translationX",menuWidth,0f);
        animator.setDuration(300);
        animator.start();
    }

    protected void onFinishInflate() {
        Log.d("gaolei", "onFinishInflate------------------");
        mLeftMenuView = (View) findViewById(menu_layout);
        mContentView = (View) findViewById(content_layout);
    }


}
