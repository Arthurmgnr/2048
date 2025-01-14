package main.services;

import main.model.User;
import main.model.UserGamesDetails;
import main.repositories.ProfileGameRepository;

public class ProfileGameService {
    private final ProfileGameRepository profileGameRepository = new ProfileGameRepository();

    public UserGamesDetails getBestScore(String username) {
        return profileGameRepository.getBestScore(username);
    }

    public User getUserDetails(String username) { return profileGameRepository.getUserDetails(username); }
}
