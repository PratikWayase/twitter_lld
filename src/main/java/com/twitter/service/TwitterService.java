package com.twitter.service;



import com.twitter.model.*;
import java.util.*;

public class TwitterService {
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Tweet> tweets = new HashMap<>();
    private int tweetCounter = 0;

    // User management
    public int createUser(String username) {
        int userId = users.size() + 1;
        users.put(userId, new User(userId, username));
        return userId;
    }

    // Tweet operations
    public int postTweet(int userId, String content) {
        Tweet tweet = new Tweet(++tweetCounter, userId, content);
        tweets.put(tweet.getTweetId(), tweet);
        users.get(userId).addTweet(tweet.getTweetId());
        return tweet.getTweetId();
    }

    public void deleteTweet(int userId, int tweetId) {
        if (tweets.containsKey(tweetId) && tweets.get(tweetId).getUserId() == userId) {
            tweets.remove(tweetId);
            users.get(userId).removeTweet(tweetId);
        }
    }

    // Social operations
    public void follow(int followerId, int followeeId) {
        if (users.containsKey(followerId) && users.containsKey(followeeId)) {
            users.get(followerId).follow(followeeId);
            users.get(followeeId).addFollower(followerId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (users.containsKey(followerId) && users.containsKey(followeeId)) {
            users.get(followerId).unfollow(followeeId);
            users.get(followeeId).removeFollower(followerId);
        }
    }

    // Profile and feed
    public UserProfile getUserProfile(int userId) {
        User user = users.get(userId);
        List<Tweet> userTweets = new ArrayList<>();
        for (int tweetId : user.getTweets()) {
            if (tweets.containsKey(tweetId)) {
                userTweets.add(tweets.get(tweetId));
            }
        }
        return new UserProfile(user.getUserId(), user.getUsername(),
                user.getFollowers().size(), user.getFollowing().size(), userTweets);
    }

    public List<Tweet> getFeed(int userId, String sortBy) {
        User user = users.get(userId);
        List<Tweet> feed = new ArrayList<>();

        // Add user's own tweets
        for (int tweetId : user.getTweets()) {
            if (tweets.containsKey(tweetId)) {
                feed.add(tweets.get(tweetId));
            }
        }

        // Add followees' tweets
        for (int followeeId : user.getFollowing()) {
            for (int tweetId : users.get(followeeId).getTweets()) {
                if (tweets.containsKey(tweetId)) {
                    feed.add(tweets.get(tweetId));
                }
            }
        }

        // Sort based on criteria
        if (sortBy.equalsIgnoreCase("popularity")) {
            feed.sort((a, b) -> b.getLikes() - a.getLikes());
        } else { // default newest first
            feed.sort((a, b) -> b.getTime().compareTo(a.getTime()));
        }

        return feed;
    }

    // Helper methods
    public User getUser(int userId) {
        return users.get(userId);
    }

    public Tweet getTweet(int tweetId) {
        return tweets.get(tweetId);
    }

    public void likeTweet(int tweetId) {
        if (tweets.containsKey(tweetId)) {
            tweets.get(tweetId).like();
        }
    }
}