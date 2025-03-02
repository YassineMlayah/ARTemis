package main.java.services;

import main.java.models.Artist;
import main.java.repositories.ArtistRepository;

public class ArtistServices {
    private ArtistRepository artistRepository = new ArtistRepository();

    public boolean signup(Artist artist) {
        // Vérifier si l'email est déjà utilisé
        if (artistRepository.getArtistByUserID(artist.getUserID()) != null) {
            System.out.println("Email déjà utilisé !");
            return false;
        }

        return artistRepository.addArtist(artist);
    }

    public Artist login(int userID) {
        return artistRepository.getArtistByUserID(userID);
    }
}

