package main.model;

import java.sql.Timestamp;

public class User {
    private String username;
    private Timestamp dateTime;
    private String avatar;
    private String lang;

    public User() { }

    public User(String username, Timestamp dateTime, String avatar, String language) {
        this.username = username;
        this.dateTime = dateTime;
        this.avatar = avatar;
        this.lang = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
