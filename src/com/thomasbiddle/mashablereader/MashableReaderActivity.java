package com.thomasbiddle.mashablereader;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.view.MenuInflater;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitleProvider;

public class MashableReaderActivity extends BaseActivity {
	
	ViewPagerFragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	
	private static final String[] CONTENT = new String[] { "All", "Social Media", "Android", "Mobile", "Tech", "Business", "Video", "Dev & Design", "Entertainment", "Social Good", "Startups", "US & World" };
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ColorDrawable d = new ColorDrawable(0xff559CFF);
        
        final ActionBar ab = getSupportActionBar();
        
        ab.setDisplayShowHomeEnabled(true);
        ab.setBackgroundDrawable(d);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
		mAdapter = new ArticleListAdapter(getSupportFragmentManager());
		
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		
		mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
				
        
    }
    public boolean onCreateOptionsMenu(Menu menu) {  	
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mashable_main, menu);
        return true;
    }
    public void refreshArticles(MenuItem menu) {

    }
    
    
    class ArticleListAdapter extends ViewPagerFragmentAdapter implements TitleProvider {
		public ArticleListAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return ViewPagerFragment.newInstance(MashableReaderActivity.CONTENT[position % MashableReaderActivity.CONTENT.length]);
		}

		@Override
		public int getCount() {
			return MashableReaderActivity.CONTENT.length;
		}

		@Override
		public String getTitle(int position) {
			return MashableReaderActivity.CONTENT[position % MashableReaderActivity.CONTENT.length].toUpperCase();
		}
	}
   
}