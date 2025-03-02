package main.java.services;

import main.java.models.Client;
import main.java.repositories.ClientRepository;

public class ClientServices {
    private ClientRepository clientRepository = new ClientRepository();

    public boolean signup(Client client) {
        // Vérifier si l'email est déjà utilisé
        if (clientRepository.getClientByUserID(client.getUserID()) != null) {
            System.out.println("Email déjà utilisé !");
            return false;
        }

        return clientRepository.addClient(client);
    }

    public Client login(int userID) {
        return clientRepository.getClientByUserID(userID);
    }
}

