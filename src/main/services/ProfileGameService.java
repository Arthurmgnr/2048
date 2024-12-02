package main.services;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.repositories.ProfileGameRepository;

public class ProfileGameService {
    private final ProfileGameRepository profileGameRepository = new ProfileGameRepository();

    public UserGamesDetails getBestScore(String username) {
        return profileGameRepository.getBestScore(username);
    }

    public User getUserAvatar(String username) { return profileGameRepository.getUserAvatar(username); }
}
