package com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase;

public class User {
    private String user_name;
    private String name;
    private String email;
    private String password;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String user_name, String name, String email, String password) {
        this.user_name = user_name;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}