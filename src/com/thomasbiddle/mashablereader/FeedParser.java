package com.thomasbiddle.mashablereader;

import java.util.List;

import android.os.Message;

public interface FeedParser {
    List<Message> parse();
}
