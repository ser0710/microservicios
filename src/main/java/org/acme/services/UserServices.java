package org.acme.services;

import org.acme.entity.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserServices {

    List<User> getAllUsers();

    void crear(User usuario);

    User buscarPorId(ObjectId id);

//    void crearTweet(User usuario);
}
