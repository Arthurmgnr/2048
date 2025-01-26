package main.services;

import main.constants.MessageConstants;
import main.model.User;
import main.repositories.RegisterRepository;

import java.sql.Timestamp;

public class RegisterService {
    // Permet d'appeler le Repository associe
    private final RegisterRepository registerRepository = new RegisterRepository();

    /**
     * Permet de controler que le joueur peut bien se creer un compte
     * @param username l'identifiant a verifier
     * @param date la date de creation du compte
     * @param avatar le chemin vers l'avatar choisi par le joueur
     * @param language la langue du joueur
     * @return un message indiquant si la creation du compte a reussi ou non
     */
    public MessageConstants registerUser(String username, Timestamp date, String avatar, String language) {
        // Renvoie un message d'erreur car aucun username n'a ete recu
        if (username == null || username.isEmpty()) {
            return MessageConstants.USERNAME_EMPTY;
        }

        // Renvoie un message d'erreur car l'username existe deja
        if (registerRepository.usernameExists(username)) {
            return MessageConstants.USERNAME_ALREADY_EXISTS;
        }

        // Creer un objet contenant les informations du joueur
        User user = new User(username, date, avatar, language);

        // Permet de savoir si la creation a eut lieu
        boolean isSaved = registerRepository.saveUser(user);

        // Renvoie un message indiquant si la creation de compte a reussi ou non
        return isSaved ? MessageConstants.REGISTRATION_SUCCESSFUL : MessageConstants.REGISTRATION_FAILED;
    }
}
