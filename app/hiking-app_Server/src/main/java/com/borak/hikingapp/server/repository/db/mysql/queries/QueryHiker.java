/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql.queries;

import com.borak.hikingapp.commonlib.domain.classes.Place;

/**
 *
 * @author User
 */
public final class QueryHiker {

    private QueryHiker() {
    }

    public static final String DB_ID = "id";
    public static final String DB_UCIN = "ucin";
    public static final String DB_FIRST_NAME = "first_name";
    public static final String DB_LAST_NAME = "last_name";
    public static final String DB_GENDER = "gender";
    public static final String DB_DATE_OF_BIRTH = "date_of_birth";
    public static final String DB_YEARS_OF_EXPERIENCE = "years_of_experience";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "hiker";
    public static final String DB_TABLE_INITIALS = "hiker";

    public static String getAll() {
        return "SELECT "
                + "" + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_UCIN
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_GENDER
                + "," + DB_TABLE_INITIALS + "." + DB_DATE_OF_BIRTH
                + "," + DB_TABLE_INITIALS + "." + DB_YEARS_OF_EXPERIENCE
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ")";
    }

    public static String insert() {
        return "INSERT INTO " + DB_TABLE + "("
                + DB_UCIN + ","
                + DB_FIRST_NAME + ","
                + DB_LAST_NAME + ","
                + DB_GENDER + ","
                + DB_DATE_OF_BIRTH + ","
                + DB_YEARS_OF_EXPERIENCE + ","
                + DB_PLACE + ") VALUES(?,?,?,?,?,?,?)";
    }

    public static String findByUCIN() {
        return "SELECT "
                + "" + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_UCIN
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_GENDER
                + "," + DB_TABLE_INITIALS + "." + DB_DATE_OF_BIRTH
                + "," + DB_TABLE_INITIALS + "." + DB_YEARS_OF_EXPERIENCE
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ")"
                + " WHERE  " + DB_TABLE_INITIALS + "." + DB_UCIN + "=?";
    }

    public static String deleteByUCIN() {
        return "DELETE FROM " + DB_TABLE + " WHERE " + DB_UCIN + "=?";
    }

    public static String updateByUCIN() {
        return "UPDATE " + DB_TABLE + " SET "
                + DB_UCIN + "=?,"
                + DB_FIRST_NAME + "=?,"
                + DB_LAST_NAME + "=?,"
                + DB_GENDER + "=?,"
                + DB_DATE_OF_BIRTH + "=?,"
                + DB_YEARS_OF_EXPERIENCE + "=?,"
                + DB_PLACE + "=? "
                + "WHERE " + DB_UCIN + "=?";
    }

}
