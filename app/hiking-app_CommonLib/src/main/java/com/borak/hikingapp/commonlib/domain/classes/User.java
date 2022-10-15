/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.classes;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Despot
 */
public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Place place;

    public static final String DB_ID = "id";
    public static final String DB_FIRST_NAME = "first_name";
    public static final String DB_LAST_NAME = "last_name";
    public static final String DB_USERNAME = "username";
    public static final String DB_PASSWORD = "password";
    public static final String DB_EMAIL = "email";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "user";
    public static final String DB_TABLE_INITIALS = "user";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String password, String email, Place place) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.username);
        hash = 19 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public static String getAllQuery() {
        return "SELECT " + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_USERNAME
                + "," + DB_TABLE_INITIALS + "." + DB_PASSWORD
                + "," + DB_TABLE_INITIALS + "." + DB_EMAIL
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID + ");";
    }

    public static String insertQuery() {
        return "INSERT INTO " + DB_TABLE + "("
                + DB_FIRST_NAME + ","
                + DB_LAST_NAME + ","
                + DB_USERNAME + ","
                + DB_PASSWORD + ","
                + DB_EMAIL + ","
                + DB_PLACE + ") VALUES(?,?,?,?,?,?);";
    }

    public static String findQuery() {
        return "SELECT " + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_USERNAME
                + "," + DB_TABLE_INITIALS + "." + DB_PASSWORD
                + "," + DB_TABLE_INITIALS + "." + DB_EMAIL
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID
                + ") WHERE " + DB_TABLE_INITIALS + "." + DB_USERNAME + "=?";
    }
    
    

}
