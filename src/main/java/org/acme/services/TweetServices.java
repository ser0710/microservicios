package org.acme.services;

import org.acme.entity.Tweet;

import java.util.List;

public interface TweetServices {

    List<Tweet> getAllTweets();

    List<Tweet> getTweetsByUser(String user);

    void createTweet(Tweet tweet);

    void deleteTweet(String tweet);

    List<String> getTweetsIds(String user, String tweet);
}
