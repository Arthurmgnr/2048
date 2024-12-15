package main.services;

import main.entities.User;
import main.repositories.EditProfileRepository;

public class EditProfileService {
    private final EditProfileRepository editProfileRepository = new EditProfileRepository();

    public User getUserDetails(String username) {
        return editProfileRepository.getUserDetails(username);
    }

    public boolean updateUser(String username, String avatar) {
        User user = new User();
        user.setUsername(username);
        user.setAvatar(avatar);

        return editProfileRepository.updateUser(user);
    }
}
