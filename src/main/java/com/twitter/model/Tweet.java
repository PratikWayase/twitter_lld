package com.twitter.model;


import java.util.Date;
import java.text.SimpleDateFormat;

public class Tweet {
    private int tweetId;
    private int userId;
    private String content;
    private Date timestamp;
    private int likes;
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public Tweet(int tweetId, int userId, String content) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.content = content;
        this.timestamp = new Date();
        this.likes = 0;
    }

    // Getters
    public int getTweetId() { return tweetId; }
    public int getUserId() { return userId; }
    public String getContent() { return content; }
    public int getLikes() { return likes; }
    public String getTime() { return timeFormat.format(timestamp); }

    public void like() { likes++; }
}