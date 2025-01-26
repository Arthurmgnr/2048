package main.services;

import main.model.User;
import main.model.UserGamesDetails;
import main.repositories.ProfileRepository;

public class ProfileService {
    // Permet d'appeler le Repository associe
    private final ProfileRepository profileRepository = new ProfileRepository();

    // Permet de recuperer les informations du joueur dans la table User
    public User getUserDetails(String username) {
        return profileRepository.getUserDetails(username);
    }

    // Permet de recuperer les informations du joueur dans la table Games
    public UserGamesDetails getUserGamesDetails(String username) {
        return profileRepository.getUserGamesDetails(username);
    }
}
