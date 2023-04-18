package org.acme.services.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.quarkus.mongodb.MongoClientName;
import org.acme.entity.User;
import org.acme.services.UserServices;
import org.bson.Document;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserServicesImpl implements UserServices {
//
//    @Inject
//    @MongoClientName("users")
//    MongoClient mongoClient;

    @Override
    public List<Document> getAllUsers() {
//        MongoCollection<Document> collection = mongoClient.getDatabase("tweetDB").getCollection("users");
//        return collection.find().into(new ArrayList<>());
        return null;
    }
}
