Q Design an in-memory Twitter for \~100 users and \~10,000 tweets, supporting:
Post & delete tweets
Follow & unfollow users
View any user’s profile (user\_id, followers, following, tweets)
Retrieve a personalized feed, sortable by different criteria

ouput 

Alice posted tweet #1
Bob posted tweet #2
Charlie posted tweet #3


Alice followed Bob
Alice is now following 1 users (Alice follows Bob)
Alice followed Charlie
Alice is now following 2 users (Alice follows Charlie)
Bob followed Alice
Bob is now following 1 users (Bob follows Alice)


Alice unfollowed Charlie
Alice is now following 1 users (Alice follows Charlie)


Alice's tweets before deletion:
 - [22:32:39] Hello Twitter! #myfirsttweet (Likes: 2)

Alice deleted tweet #1
Alice's tweets after deletion:
 - [No tweets]


Alice's profile:
USER PROFILE: Alice
User ID: 1
Followers: 1
Following: 1
Tweets (0):
 - [No tweets]

Alice's feed (newest first):
 - [22:32:39] Bob: Just joined Twitter! Excited! (Likes: 0)

Process finished with exit code 0
