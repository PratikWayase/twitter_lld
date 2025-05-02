package com.twitter.model;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private int userId;
    private String username;
    private Set<Integer> followers = new HashSet<>();
    private Set<Integer> following = new HashSet<>();
    private List<Integer> tweets = new ArrayList<>();

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public Set<Integer> getFollowers() { return followers; }
    public Set<Integer> getFollowing() { return following; }
    public List<Integer> getTweets() { return tweets; }

    // Operations
    public void follow(int followeeId) {
        following.add(followeeId);
    }

    public void unfollow(int followeeId) {
        following.remove(followeeId);
    }

    public void addFollower(int followerId) {
        followers.add(followerId);
    }

    public void removeFollower(int followerId) {
        followers.remove(followerId);
    }

    public void addTweet(int tweetId) {
        tweets.add(tweetId);
    }

    public void removeTweet(int tweetId) {
        tweets.remove((Integer) tweetId);
    }
}