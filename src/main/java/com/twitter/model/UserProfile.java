package com.twitter.model;



import java.util.List;

public class UserProfile {
    private int userId;
    private String username;
    private int followerCount;
    private int followingCount;
    private List<Tweet> tweets;

    public UserProfile(int userId, String username, int followerCount,
                       int followingCount, List<Tweet> tweets) {
        this.userId = userId;
        this.username = username;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.tweets = tweets;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public int getFollowerCount() { return followerCount; }
    public int getFollowingCount() { return followingCount; }
    public List<Tweet> getTweets() { return tweets; }
}