package org.acme.entity;


import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;

@MongoEntity(database = "tweetDB", collection = "stream")
public class Stream {

    private ArrayList<String> tweets = new ArrayList<>();

    public Stream(ArrayList<String> tweets) {
        this.tweets = tweets;
    }

    public Stream() {
    }

    public ArrayList<String> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<String> tweets) {
        this.tweets = tweets;
    }

    public void setUsuario(User usuario) {
    }
}
