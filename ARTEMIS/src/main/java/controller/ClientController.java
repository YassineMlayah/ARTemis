package main.java.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.models.Client;
import main.java.services.ClientServices;

@Path("/clients")
public class ClientController {
    private ClientServices clientService = new ClientServices();

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signup(Client client) {
        boolean success = clientService.signup(client);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Client inscrit avec succès !").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Échec de l'inscription.").build();
        }
    }

    @GET
    @Path("/login/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("userID") int userID) {
        Client client = clientService.login(userID);
        if (client != null) {
            return Response.ok(client).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Client non trouvé.").build();
        }
    }
}

