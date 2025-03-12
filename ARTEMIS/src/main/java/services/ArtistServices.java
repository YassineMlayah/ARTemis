package main.java.services;

import main.java.models.Artist;
import main.java.models.Product;
import main.java.repositories.ArtistRepository;
import main.java.repositories.ProductRepository;

public class ArtistServices {
    private ArtistRepository artistRepository = new ArtistRepository();
    private final ProductRepository productRepository = new ProductRepository();
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
        public boolean addProductToArtist(int artistID, Product product) {
        Artist artist = artistRepository.getArtistByUserID(artistID);
        if (artist != null) {
            boolean added = productRepository.addProduct(product, artistID);
            if (added) {
                artist.addProduct(product);
                return true;
            }
        }
        return false;
    }
}

