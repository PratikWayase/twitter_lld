package com.twitter;



import com.twitter.model.*;
import com.twitter.service.TwitterService;
import java.util.List;

public class main {
    public static void main(String[] args) throws InterruptedException {
        TwitterService twitter = new TwitterService();

        // Create users
        int aliceId = twitter.createUser("Alice");
        int bobId = twitter.createUser("Bob");
        int charlieId = twitter.createUser("Charlie");

        // 1. DEMO POSTING TWEETS
        System.out.println("=== TWEET OPERATIONS ===");
        int tweet1 = twitter.postTweet(aliceId, "Hello Twitter! #myfirsttweet");
        System.out.println("Alice posted tweet #" + tweet1);

        int tweet2 = twitter.postTweet(bobId, "Just joined Twitter! Excited!");
        System.out.println("Bob posted tweet #" + tweet2);

        int tweet3 = twitter.postTweet(charlieId, "Programming is fun! #coding");
        System.out.println("Charlie posted tweet #" + tweet3);

        // Add some likes
        twitter.likeTweet(tweet1);
        twitter.likeTweet(tweet1);
        twitter.likeTweet(tweet3);

        // 2. DEMO FOLLOW/UNFOLLOW
        System.out.println("\n=== FOLLOW OPERATIONS ===");
        twitter.follow(aliceId, bobId);
        System.out.println("Alice followed Bob");
        viewSocialStats(twitter, aliceId, bobId);

        twitter.follow(aliceId, charlieId);
        System.out.println("Alice followed Charlie");
        viewSocialStats(twitter, aliceId, charlieId);

        twitter.follow(bobId, aliceId);
        System.out.println("Bob followed Alice");
        viewSocialStats(twitter, bobId, aliceId);

        // 3. DEMO UNFOLLOW
        System.out.println("\n=== UNFOLLOW OPERATION ===");
        twitter.unfollow(aliceId, charlieId);
        System.out.println("Alice unfollowed Charlie");
        viewSocialStats(twitter, aliceId, charlieId);

        // 4. DEMO TWEET DELETION
        System.out.println("\n=== TWEET DELETION ===");
        System.out.println("Alice's tweets before deletion:");
        viewUserTweets(twitter, aliceId);

        twitter.deleteTweet(aliceId, tweet1);
        System.out.println("\nAlice deleted tweet #" + tweet1);

        System.out.println("Alice's tweets after deletion:");
        viewUserTweets(twitter, aliceId);

        // 5. DEMO FINAL STATE
        System.out.println("\n=== FINAL STATE ===");
        System.out.println("Alice's profile:");
        viewUserProfile(twitter, aliceId);

        System.out.println("\nAlice's feed (newest first):");
        viewUserFeed(twitter, aliceId, "newest", "");
    }

    private static void viewSocialStats(TwitterService twitter, int followerId, int followeeId) {
        User follower = twitter.getUser(followerId);
        User followee = twitter.getUser(followeeId);
        System.out.printf("%s is now following %d users (%s follows %s)\n",
                follower.getUsername(),
                follower.getFollowing().size(),
                follower.getUsername(),
                followee.getUsername());
    }

    private static void viewUserTweets(TwitterService twitter, int userId) {
        List<Tweet> tweets = twitter.getUserProfile(userId).getTweets();
        if (tweets.isEmpty()) {
            System.out.println(" - [No tweets]");
        } else {
            tweets.forEach(tweet ->
                    System.out.printf(" - [%s] %s (Likes: %d)\n",
                            tweet.getTime(),
                            tweet.getContent(),
                            tweet.getLikes()));
        }
    }

    private static void viewUserProfile(TwitterService twitter, int userId) {
        UserProfile profile = twitter.getUserProfile(userId);
        System.out.println("USER PROFILE: " + profile.getUsername());
        System.out.println("User ID: " + profile.getUserId());
        System.out.println("Followers: " + profile.getFollowerCount());
        System.out.println("Following: " + profile.getFollowingCount());
        System.out.println("Tweets (" + profile.getTweets().size() + "):");
        viewUserTweets(twitter, userId);
    }

    private static void viewUserFeed(TwitterService twitter, int userId, String sortBy, String title) {
        List<Tweet> feed = twitter.getFeed(userId, sortBy);
        if (!title.isEmpty()) {
            System.out.println(title + " (" + sortBy + ")");
        }
        for (Tweet tweet : feed) {
            System.out.printf(" - [%s] %s: %s (Likes: %d)\n",
                    tweet.getTime(),
                    twitter.getUserProfile(tweet.getUserId()).getUsername(),
                    tweet.getContent(),
                    tweet.getLikes());
        }
    }
}