package org.acme.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.quarkus.mongodb.MongoClientName;
import org.acme.entity.Stream;
import org.acme.entity.User;
import org.acme.services.UserServices;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserServices userServices;

    @GET
    public List<User> getAllUsers(){
       return userServices.getAllUsers();
    }

    @POST
    public Response crearUsuario(User usuario) {
        userServices.crear(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("{id}")
    public User obtenerUsuarioPorId(@PathParam("id") String id) {
        ObjectId objectId = new ObjectId(id);
        User usuario = userServices.buscarPorId(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return usuario;
    }

    @GET
    @Path("{id}/hilos")
    public List<String> obtenerHilosDeUsuario(@PathParam("id") String id) {
        ObjectId objectId = new ObjectId(id);
        User usuario = userServices.buscarPorId(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return null;
    }


    @POST
    @Path("{id}/hilos")
    public Response crearTweetParaUsuario(@PathParam("id") String id, Stream hilo) {
        ObjectId objectId = new ObjectId(id);
        User usuario = userServices.buscarPorId(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        //usuario.getHilos();
        System.out.println("HILOOOO " + hilo.getTweets());
        hilo.setUsuario(usuario);
        userServices.actualizar(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

}



