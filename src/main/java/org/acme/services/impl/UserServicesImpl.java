package org.acme.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.smallrye.mutiny.Uni;
import org.acme.entity.User;
import org.acme.services.UserServices;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class UserServicesImpl implements UserServices {

    @Inject MongoClient mongoClient;
    @Inject ObjectMapper objectMapper;
//
//    @Inject
//    @MongoClientName("users")
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setId(String.valueOf(document.getObjectId("_id")));
                user.setName(document.getString("nombre"));
                user.setEmail(document.getString("email"));
                user.setPassword(document.getString("password"));
                list.add(user);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public void crear(User usuario) {
        Document document = new Document()
                .append("nombre", usuario.getName())
                .append("email", usuario.getEmail())
                .append("password", usuario.getPassword())
                .append("hilos", usuario.gettweets());
        getCollection().insertOne(document);
    }


    @Override
    public User buscarPorId(ObjectId id) {
        Document userDocument = (Document) getCollection().find(new Document("_id", id)).first();
        User user = new User();
        user.setId(String.valueOf(userDocument.getObjectId("_id")));
        user.setName(userDocument.getString("nombre"));
        user.setEmail(userDocument.getString("email"));
        user.setPassword(userDocument.getString("password"));
        return user;
    }


    @Override
    public void actualizar(User usuario) {

    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("tweetDB").getCollection("users");
    }
}
