package main.constants;

import main.utils.TranslationManager;

/**
 * Permet de definir les messages a renvoyer dans les fenetres Login et Register selon que le joueur ne peut pas se
 * creer un compte ou se connecter, ou bien que la connexion ou la creation a reussi
 */
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
