package org.acme.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.acme.entity.Tweet;
import org.acme.entity.User;
import org.acme.services.TweetServices;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class TweetServicesImpl implements TweetServices {

    @Inject
    MongoClient mongoClient;
    @Inject
    ObjectMapper objectMapper;

    @Override
    public List<Tweet> getAllTweets() {
        List<Tweet> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Tweet tweet = new Tweet();
                tweet.setId(String.valueOf(document.getObjectId("_id")));
                tweet.setMessage(document.getString("message"));
                tweet.setUser(document.getString("user"));
                list.add(tweet);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public List<Tweet> getTweetsByUser(String user) {
        List<Tweet> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                if(Objects.equals(document.getString("user"), user)) {
                    Tweet tweet = new Tweet();
                    tweet.setId(String.valueOf(document.getObjectId("_id")));
                    tweet.setMessage(document.getString("message"));
                    tweet.setUser(document.getString("user"));
                    list.add(tweet);
                }
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public void createTweet(Tweet tweet) {
        Document document = new Document()
                .append("message", tweet.getMessage())
                .append("user", tweet.getUser());
        getCollection().insertOne(document);
    }

    @Override
    public void deleteTweet(String tweet) {
        getCollection().deleteOne(new Document("_id",new ObjectId(tweet)));

    }

    @Override
    public List<String> getTweetsIds(String user, String tweet) {
        List<Tweet> tweets = getTweetsByUser(user);
        List<String> tweetsIds = new ArrayList<>();
        for (Tweet t:tweets){
            tweetsIds.add(t.getId().toString());
        }
        return tweetsIds;
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("tweetDB").getCollection("tweets");
    }


}
