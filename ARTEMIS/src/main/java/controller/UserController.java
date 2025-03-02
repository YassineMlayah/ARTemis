package main.java.controller;

import main.java.services.UserServices;
import main.java.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {
    private UserServices userService = new UserServices();

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(User user) {
        boolean success = userService.signup(user);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Utilisateur inscrit avec succès !").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Email déjà utilisé !").build();
    }
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            return Response.ok(loggedInUser).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
    }
}
