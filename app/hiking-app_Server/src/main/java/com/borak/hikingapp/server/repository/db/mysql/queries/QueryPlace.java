/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql.queries;

/**
 *
 * @author User
 */
public final class QueryPlace {

    private QueryPlace() {
    }

    public static final String DB_ID = "id";
    public static final String DB_NAME = "name";
    public static final String DB_TABLE = "place";
    public static final String DB_TABLE_INITIALS = "place";

    public static String getAll() {
        return "SELECT " + DB_ID + "," + DB_NAME + " FROM " + DB_TABLE;
    }

}
