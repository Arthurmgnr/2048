package main.constants;

public enum MessageConstants {
    USERNAME_EMPTY("Username cannot be empty!", false),
    USERNAME_ALREADY_EXISTS("Username already exists!", false),
    USERNAME_DOESNT_EXIST("Username doesn't exist!", false),
    REGISTRATION_FAILED("Registration failed", false),
    REGISTRATION_SUCCESSFUL("Registration successful", true),
    LOGIN_SUCCESSFUL("Login successful", true);

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
