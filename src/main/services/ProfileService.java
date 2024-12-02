package main.services;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.repositories.ProfileRepository;

public class ProfileService {
    private final ProfileRepository profileRepository = new ProfileRepository();

    public User getUserDetails(String username) {
        return profileRepository.getUserDetails(username);
    }

    public UserGamesDetails getUserGamesDetails(String username) {
        return profileRepository.getUserGamesDetails(username);
    }
}
