package main.services;

import main.constants.MessageConstants;
import main.repositories.LoginRepository;

public class LoginService {
    private final LoginRepository loginRepository = new LoginRepository();

    public MessageConstants loginUser(String username) {
        if (username == null || username.isEmpty()) {
            return MessageConstants.USERNAME_EMPTY;
        }

        if (loginRepository.usernameDoesntExists(username)) {
            return MessageConstants.USERNAME_DOESNT_EXIST;
        }

        return MessageConstants.LOGIN_SUCCESSFUL;
    }
}
