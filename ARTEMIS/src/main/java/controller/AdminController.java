package main.java.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.models.Admin;
import main.java.services.AdminServices;

@Path("/admin")
public class AdminController {
    private final AdminServices adminService = new AdminServices();

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(Admin admin) {
        boolean created = adminService.signUp(admin);
        if (created) {
            return Response.status(Response.Status.CREATED).entity("Admin inscrit avec succès").build();
        }
        return Response.status(Response.Status.CONFLICT).entity("L'email est déjà utilisé").build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
        Admin admin = adminService.login(email, password);
        if (admin != null) {
            return Response.ok(admin).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Email ou mot de passe incorrect").build();
    }
}
