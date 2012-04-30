package com.thomasbiddle.mashablereader;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.PageIndicator;

public abstract class BaseActivity extends FragmentActivity {
	
	ViewPagerFragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;

}
