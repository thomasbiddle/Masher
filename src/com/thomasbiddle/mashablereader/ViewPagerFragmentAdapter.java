package com.thomasbiddle.mashablereader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
	protected static final String[] CONTENT = new String[] { "All", "Social Media", "Android", "Mobile", "Tech", "Business", "Video", "Dev & Design", "Entertainment", "Social Good", "Startups", "US & World" };
	
	private int mCount = CONTENT.length;

	public ViewPagerFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return ViewPagerFragment.newInstance(CONTENT[position % CONTENT.length]);
	}

	@Override
	public int getCount() {
		return mCount;
	}
	
	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			mCount = count;
			notifyDataSetChanged();
		}
	}
}