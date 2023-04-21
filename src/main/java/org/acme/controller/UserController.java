package org.acme.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.quarkus.mongodb.MongoClientName;
import io.quarkus.security.Authenticated;
import org.acme.configuration.CognitoLogin;
import org.acme.entity.User;
import org.acme.services.UserServices;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserServices userServices;

    CognitoLogin cognitoLogin = new CognitoLogin();

    @GET
    public List<User> getAllUsers(){
       return userServices.getAllUsers();
    }

    @POST
    public Response crearUsuario(User usuario) {
        cognitoLogin.signUp(usuario.getName(),usuario.getEmail(),usuario.getPassword());
        userServices.crear(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("login")
    public String login(User usuario) {
        return cognitoLogin.Login(usuario.getEmail(),usuario.getPassword());
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


}



