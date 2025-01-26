package main.services;

import main.model.User;
import main.model.UserGamesDetails;
import main.repositories.ProfileGameRepository;

public class ProfileGameService {
    // Permet d'appeler le Repository associe
    private final ProfileGameRepository profileGameRepository = new ProfileGameRepository();

    // Permet de recuperer le meilleur score du joueur
    public UserGamesDetails getBestScore(String username) {
        return profileGameRepository.getBestScore(username);
    }

    // Permet de recuperer les informations du joueur dans la table User
    public User getUserDetails(String username) { return profileGameRepository.getUserDetails(username); }
}
