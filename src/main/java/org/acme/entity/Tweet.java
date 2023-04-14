package org.acme.entity;

public class Tweet {

    private String id;
    private String message;
    private User user;

    public Tweet(String id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public Tweet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
