package com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase;

public class UserSession {
    private static UserSession instance;
    private String loggedInUsername;

    private UserSession() {

    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }
}
