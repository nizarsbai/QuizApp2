package com.sbai.quizapp;

public class User {
    public String name, email, password, password1;

    public User() {

    }

    public User( String email, String password) {
        //this.name=name;
        this.email = email;
        this.password = password;
        //this.password1=password1;
    }
}
