package com.example.travelguide;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static String currentEmail;
    public static List<Destination> favorites = new ArrayList<>();
    public static int flag = 0;

    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String pDestination;

    public User() {
    }

    public User(String email, String firstname, String lastname, String password, String pDestination) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.pDestination = pDestination;
        currentEmail = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getpDestination() {
        return pDestination;
    }

    public void setpDestination(String pDestination) {
        this.pDestination = pDestination;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", pDestination='" + pDestination + '\'' +
                '}';
    }
}
