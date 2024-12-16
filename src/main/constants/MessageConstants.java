package main.constants;

import main.utils.TranslationManager;

public enum MessageConstants {
    USERNAME_EMPTY(TranslationManager.get("messageConstants.usernameEmpty"), false),
    USERNAME_ALREADY_EXISTS(TranslationManager.get("messageConstants.usernameAlreadyExists"), false),
    USERNAME_DOESNT_EXIST(TranslationManager.get("messageConstants.usernameDoesntExist"), false),
    REGISTRATION_FAILED(TranslationManager.get("messageConstants.registrationFailed"), false),
    REGISTRATION_SUCCESSFUL(TranslationManager.get("messageConstants.registrationSuccessful"), true),
    LOGIN_SUCCESSFUL(TranslationManager.get("messageConstants.loginSuccessful"), true);

    private final String text;
    private final boolean bool;

    MessageConstants(String text, boolean bool) {
        this.text = text;
        this.bool = bool;
    }

    public String getText() {
        return text;
    }

    public boolean getBool() {
        return bool;
    }
}
