package org.acme.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.quarkus.mongodb.MongoClientName;
import org.acme.entity.User;
import org.acme.services.UserServices;
import org.bson.Document;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserController {
//    @Inject
//    UserServices userServices;

    @Inject
    @MongoClientName("tweetDB")
    MongoClient mongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> getAllUsers(){
       MongoCollection<Document> collection = mongoClient.getDatabase("tweetDB").getCollection("users");
        return collection.find().into(new ArrayList<>());
    }

}
