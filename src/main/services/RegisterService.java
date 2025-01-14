package main.services;

import main.constants.MessageConstants;
import main.model.User;
import main.repositories.RegisterRepository;

import java.sql.Timestamp;

public class RegisterService {
    private final RegisterRepository registerRepository = new RegisterRepository();

    public MessageConstants registerUser(String username, Timestamp date, String avatar, String language) {
        if (username == null || username.isEmpty()) {
            return MessageConstants.USERNAME_EMPTY;
        }

        if (registerRepository.usernameExists(username)) {
            return MessageConstants.USERNAME_ALREADY_EXISTS;
        }

        User user = new User(username, date, avatar, language);
        boolean isSaved = registerRepository.saveUser(user);

        return isSaved ? MessageConstants.REGISTRATION_SUCCESSFUL : MessageConstants.REGISTRATION_FAILED;
    }
}
