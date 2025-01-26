package main.services;

import main.model.Games;
import main.repositories.GameRepository;

public class GameService {
    // Permet d'appeler le Repository associe
    private final GameRepository gameRepository = new GameRepository();

    // Permet de recuperer le chemin d'acces a l'avatar du joueur
    public String getUserAvatar(String username) { return gameRepository.getUserAvatar(username); }

    // Permet de recuperer le meilleur score du joueur
    public int getUserBestScore(String username) { return gameRepository.getUserBestScore(username); }

    // Permet de sauvegarder les donnees de la partie dans la DB
    public boolean registerGames(Games games) { return gameRepository.registerGames(games); }
}

