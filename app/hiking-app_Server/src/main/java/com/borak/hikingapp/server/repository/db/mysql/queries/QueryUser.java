/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql.queries;

/**
 *
 * @author User
 */
public final class QueryUser {

    private QueryUser() {
    }

    public static final String DB_ID = "id";
    public static final String DB_FIRST_NAME = "first_name";
    public static final String DB_LAST_NAME = "last_name";
    public static final String DB_USERNAME = "username";
    public static final String DB_PASSWORD = "password";
    public static final String DB_EMAIL = "email";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "user";
    public static final String DB_TABLE_INITIALS = "user";

    public static String getAll() {
        return "SELECT " + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_USERNAME
                + "," + DB_TABLE_INITIALS + "." + DB_PASSWORD
                + "," + DB_TABLE_INITIALS + "." + DB_EMAIL
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ");";
    }

    public static String insert() {
        return "INSERT INTO " + DB_TABLE + "("
                + DB_FIRST_NAME + ","
                + DB_LAST_NAME + ","
                + DB_USERNAME + ","
                + DB_PASSWORD + ","
                + DB_EMAIL + ","
                + DB_PLACE + ") VALUES(?,?,?,?,?,?);";
    }

    public static String findByUsername() {
        return "SELECT " + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_USERNAME
                + "," + DB_TABLE_INITIALS + "." + DB_PASSWORD
                + "," + DB_TABLE_INITIALS + "." + DB_EMAIL
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID
                + ") WHERE " + DB_TABLE_INITIALS + "." + DB_USERNAME + "=?;";
    }

}
