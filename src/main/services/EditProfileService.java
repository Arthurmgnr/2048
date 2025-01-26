package main.services;

import main.model.User;
import main.repositories.EditProfileRepository;

public class EditProfileService {
    // Permet d'appeler le Repository associe
    private final EditProfileRepository editProfileRepository = new EditProfileRepository();

    // Permet de recuperer les informations du joueur contenu dans la table User
    public User getUserDetails(String username) {
        return editProfileRepository.getUserDetails(username);
    }

    // Permet de mettre a jour les informations du joueur
    public boolean updateUser(String username, String language, String avatar) {
        User user = new User();
        user.setUsername(username);
        user.setLang(language);
        user.setAvatar(avatar);

        return editProfileRepository.updateUser(user);
    }
}
