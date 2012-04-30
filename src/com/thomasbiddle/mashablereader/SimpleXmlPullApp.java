package com.thomasbiddle.mashablereader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.os.Message;
import android.util.Log;
import android.util.Xml;

public class SimpleXmlPullApp extends BaseFeedParser {
	
    public SimpleXmlPullApp(String feedUrl) {
        super(feedUrl);
    }
    
    public List<Message> parse() {
        List<Message> messages = null;
        XmlPullParser parser = Xml.newPullParser();
        try {
            // auto-detect the encoding from the stream
            parser.setInput(this.getInputStream(), null);
            int eventType = parser.getEventType();
            Message currentMessage = null;
            boolean done = false;
            while (eventType != XmlPullParser.END_DOCUMENT && !done){
                String name = null;
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        messages = new ArrayList<Message>();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        ArrayList<String> categoryList = new ArrayList<String>();
                        if (name.equalsIgnoreCase(ITEM)){
                            currentMessage = new Message();  
                        } 
                        else if (currentMessage != null){
                            if (name.equalsIgnoreCase(LINK)){
                            	Log.i("linktag",(parser.nextText()));
                            } else if (name.equalsIgnoreCase(DESCRIPTION)){
                            	String desc = parser.nextText();
                            	Log.i("desctag",desc);
                            	//mItems.add(desc);
                            } else if (name.equalsIgnoreCase(PUB_DATE)){
                            	Log.i("datetag",parser.nextText());
                            } else if (name.equalsIgnoreCase(TITLE)){
                            	String title = parser.nextText();
                                Log.i("titletag",title);
                            } else if (name.equalsIgnoreCase(CATEGORY)) {
                            	String category = parser.nextText();
                            	Log.i("cattag",category);
                            	categoryList.add(category);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase(ITEM) && currentMessage != null){
                        	Iterator i = categoryList.iterator();
                        	while (i.hasNext()) {
                        		if (i.toString().equalsIgnoreCase("android"))
                        	}
                        	ViewPagerFragment.mAndroidItems.add(category);
                            messages.add(currentMessage);
                        } else if (name.equalsIgnoreCase(CHANNEL)){
                            done = true;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

}