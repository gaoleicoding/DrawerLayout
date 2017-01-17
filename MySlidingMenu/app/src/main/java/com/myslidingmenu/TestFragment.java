package com.myslidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class TestFragment extends Fragment {
    private View view;
    public ImageButton menuBtn,mesBtn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.drawermenu_fragment, null);
		initView();
		return view;
	}
    public void initView(){
    	menuBtn=(ImageButton)view.findViewById(R.id.menu_btn);
		mesBtn=(ImageButton)view.findViewById(R.id.message_btn);
    	//点击打开左边菜单
    	menuBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((LeftAndRightDrawerActivity)getActivity()).openLeftLayout();
			}
		});
    	//点击打开右边菜单
    	mesBtn.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			((LeftAndRightDrawerActivity)getActivity()).openRightLayout();
    		}
    	});
    	
    }
}
