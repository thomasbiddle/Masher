package com.thomasbiddle.mashablereader;

import java.util.ArrayList;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.woozzu.android.widget.RefreshableListView;
import com.woozzu.android.widget.RefreshableListView.OnRefreshListener;

public final class ViewPagerFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	
	private ArrayList<String> mItems;
	private RefreshableListView mListView;
	private String mContent = "???";
	
	public static ArrayList<String> mAndroidItems;
	
	public static ViewPagerFragment newInstance(String content) {
		ViewPagerFragment fragment = new ViewPagerFragment();

		StringBuilder builder = new StringBuilder();
		builder.append(content);
		fragment.mContent = builder.toString();
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT);
		}
		/*
		TextView text = new TextView(getActivity());
		text.setGravity(Gravity.CENTER);
		text.setText(mContent);
		text.setTextSize(20 * getResources().getDisplayMetrics().density);
		text.setPadding(20, 20, 20, 20);
		
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.setGravity(Gravity.CENTER);
		layout.addView(text);
		*/
		View layout = inflater.inflate(R.layout.article, null);
		//protected static final String[] CONTENT = new String[] { "All", "Social Media", "Android", "Mobile", "Tech", "Business", "Video", "Dev & Design", "Entertainment", "Social Good", "Startups", "US & World" };
		mItems = new ArrayList<String>();
		if (mContent.equals("All")) {
			mItems.add("All");
       		mItems.add("Articles");
		}
		else if (mContent.equals("Social Media")) {
			mItems.add("Social");
			mItems.add("Media");
		}
		else if (mContent.equals("Android")) {
			mItems.add("Android");
			mItems.add("Stuff");
		}
		else if (mContent.equals("Mobile")) {
			mItems.add("Mobile");
			mItems.add("Stuff");
		}
		else if (mContent.equals("Tech")) {
			mItems.add("Tech");
			mItems.add("Stuff");
		}
		else if (mContent.equals("Business")) {
			mItems.add("Business");
			mItems.add("Stuff");
		}
		else if (mContent.equals("Video")) {
			mItems.add("Video");
			mItems.add("Stuff");
		}
		else if (mContent.equals("Dev & Design")) {
			mItems.add("Dev &");
			mItems.add("Design");
		}
		else if (mContent.equals("Entertainment")) {
			mItems.add("Entertainment");
			mItems.add("Stuff");
		}
		else if (mContent.equals("Social Good")) {
			mItems.add("Social");
			mItems.add("Good");
		}
		else if (mContent.equals("Startups")) {
			mItems.add("Startup");
			mItems.add("Stuff");
		}
		else if (mContent.equals("US & World")) {
			mItems.add("US &");
			mItems.add("World");
		}
		
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mItems);
        
        mListView = (RefreshableListView) layout.findViewById(R.id.listview);
        mListView.setAdapter(adapter);
        
        // Callback to refresh the list
        mListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new NewDataTask().execute();
            }
        });
		
		return layout;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}
	
	private class NewDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            
        	SimpleXmlPullApp sxpa = new SimpleXmlPullApp(Uri.parse("http://feeds.mashable.com/Mashable?format=xml").toString());
        	try {
        	sxpa.parse();
        	Log.i("runningtag","running!");
        	}
        	catch (RuntimeException e) {
        		Log.i("rtetag", e.toString());
        	}
            
            return mContent;
        }

        @Override
        protected void onPostExecute(String result) {
            mItems.add(0, result);
            // This should be called after refreshing finished
            mListView.completeRefreshing();

            super.onPostExecute(result);
        }
    }
}
