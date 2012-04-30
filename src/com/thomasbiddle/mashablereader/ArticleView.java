package com.thomasbiddle.mashablereader;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.view.MenuInflater;

public class ArticleView extends FragmentActivity {
	
	//private ArrayList<String> mItems;
	//private RefreshableListView mListView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
        
        ColorDrawable d = new ColorDrawable(0xff007FFF);
        
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Mashable");
        ab.setDisplayShowHomeEnabled(true);
        ab.setBackgroundDrawable(d);
        /*
        mItems = new ArrayList<String>();
        mItems.add("Mashable 1");
        mItems.add("Mashable 2");
        mItems.add("Mashable 3");
        mItems.add("Mashable 4");
        mItems.add("Mashable 5");
        mItems.add("Mashable 6");
        mItems.add("Mashable 7");
        mItems.add("Mashable 8");
        mItems.add("Mashable 9");
        mItems.add("Mashable 10");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mItems);
        
        mListView = (RefreshableListView) findViewById(R.id.listview);
        mListView.setAdapter(adapter);
        
        // Callback to refresh the list
        mListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new NewDataTask().execute();
            }
        });
        */
        
    }
    public boolean onCreateOptionsMenu(Menu menu) { 
         
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mashable_article_view, menu);
        return true;
    }
    public void shareArticle(MenuItem menu) {
    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    	sharingIntent.setType("text/plain");
    	String shareBody = "Article Title + Article URL";
    	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
    	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    	startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    /*
    private class NewDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            
            return "New Mashable";
        }

        @Override
        protected void onPostExecute(String result) {
            mItems.add(0, result);
            // This should be called after refreshing finished
            mListView.completeRefreshing();

            super.onPostExecute(result);
        }
    }
    */
}