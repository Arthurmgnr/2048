package main.services;

import main.constants.MessageConstants;
import main.repositories.LoginRepository;

public class LoginService {
    // Permet d'appeler le Repository associe
    private final LoginRepository loginRepository = new LoginRepository();

    /**
     * Permet de verifier si le joueur peut se connecter a son compte
     * @param username l'identifiant du joueur
     * @return un message indiquant si la connexion a ete realise ou non
     */
    public MessageConstants loginUser(String username) {
        // Renvoie un message d'erreur car aucun username n'a ete recu
        if (username == null || username.isEmpty()) {
            return MessageConstants.USERNAME_EMPTY;
        }

        // Renvoie un message d'erreur car l'username existe deja
        if (loginRepository.usernameDoesntExists(username)) {
            return MessageConstants.USERNAME_DOESNT_EXIST;
        }

        // Renvoie un message indiquant que la connexion est realisee
        return MessageConstants.LOGIN_SUCCESSFUL;
    }
}
