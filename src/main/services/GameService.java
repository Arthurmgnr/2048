package main.services;

import main.entities.User;
import main.repositories.GameRepository;

public class GameService {
    private final GameRepository gameRepository = new GameRepository();

    public String getUserAvatar(String username) { return gameRepository.getUserAvatar(username); }

    public int getUserBestScore(String username) { return gameRepository.getUserBestScore(username); }
}

