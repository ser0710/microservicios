package org.acme.entity;

import io.quarkus.arc.impl.Identified;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@MongoEntity(database = "tweetDB", collection = "users")
public class User extends PanacheMongoEntity {

    private String id;
    private String name;
    private String email;
    private String password;

//    private List<String> tweets = new ArrayList<>();

    public User(String id, String name, String email, String password ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
//        this.tweets = tweets;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<String> gettweets() {
//        return tweets;
//    }
}
