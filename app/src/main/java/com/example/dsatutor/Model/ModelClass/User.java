package com.example.dsatutor.Model.ModelClass;

public class User {
    private String id;
    private String user_name;
    private String user_email;
    private String user_pass;

    public User() {
    }

    public User(String id, String user_name, String user_email, String user_pass) {
        this.id = id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
