package org.acme.services;

import org.acme.entity.User;
import org.bson.Document;

import java.util.List;

public interface UserServices {

    List<Document> getAllUsers();
}
