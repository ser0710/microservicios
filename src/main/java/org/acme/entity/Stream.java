package org.acme.entity;


import java.util.ArrayList;

public class Stream {

    private ArrayList<Tweet> tweets = new ArrayList<>();

    public Stream(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Stream() {
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }
}
