package org.acme.controller;

import org.acme.entity.Tweet;
import org.acme.entity.User;
import org.acme.services.TweetServices;
import org.acme.services.UserServices;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tweets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TweetController {
    @Inject
    TweetServices tweetServices;

    @Inject
    UserServices userServices;

    @GET
    public List<Tweet> getAllTweets(){
        return tweetServices.getAllTweets();
    }

    @GET
    @Path("{userId}")
    public List<Tweet> getTweetsByUser(@PathParam("userId") String user){
        ObjectId objectId = new ObjectId(user);
        User usuario = userServices.buscarPorId(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tweetServices.getTweetsByUser(user);
    }

    @POST
    public Response createTweet(Tweet tweet){
        ObjectId objectId = new ObjectId(tweet.getUser());
        User usuario = userServices.buscarPorId(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        tweetServices.createTweet(tweet);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{tweetId}/{userId}")
    public Response deleteTweet(@PathParam("tweetId") String tweetId, @PathParam("userId") String userId){
        ObjectId objectId = new ObjectId(userId);
        User usuario = userServices.buscarPorId(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else if(!tweetServices.getTweetsIds(userId,tweetId).contains(tweetId)){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        tweetServices.deleteTweet(tweetId);
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
