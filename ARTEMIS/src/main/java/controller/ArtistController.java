package main.java.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.models.Artist;
import main.java.services.ArtistServices;

@Path("/artists")
public class ArtistController {
    private ArtistServices artistService = new ArtistServices();

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signup(Artist artist) {
        boolean success = artistService.signup(artist);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Artiste inscrit avec succès !").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Échec de l'inscription.").build();
        }
    }

    @GET
    @Path("/login/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("userID") int userID) {
        Artist artist = artistService.login(userID);
        if (artist != null) {
            return Response.ok(artist).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Artiste non trouvé.").build();
        }
    }
}


