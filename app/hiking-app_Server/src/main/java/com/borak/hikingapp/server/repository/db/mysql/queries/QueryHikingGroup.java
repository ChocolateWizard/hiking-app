/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql.queries;

/**
 *
 * @author User
 */
public final class QueryHikingGroup {

    private QueryHikingGroup() {
    }

    public static final String DB_ID = "id";
    public static final String DB_CRN = "crn";
    public static final String DB_NAME = "name";
    public static final String DB_DESCRIPTION = "description";
    public static final String DB_RESOURCES = "resources";
    public static final String DB_HAS_LISCENCE = "has_liscence";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "hiking_group";
    public static final String DB_TABLE_INITIALS = "hiking_group";

    public static String getAll() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_ID + ","
                + DB_TABLE_INITIALS + "." + DB_CRN + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_RESOURCES + ","
                + DB_TABLE_INITIALS + "." + DB_HAS_LISCENCE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS + " ON"
                + "(" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ");";
    }

    public static String insert() {
        return "INSERT INTO "
                + DB_TABLE + "("
                + DB_CRN + ","
                + DB_NAME + ","
                + DB_DESCRIPTION + ","
                + DB_RESOURCES + ","
                + DB_HAS_LISCENCE + ","
                + DB_PLACE + ")"
                + " VALUES(?,?,?,?,?,?);";
    }

    public static String findByCRN() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_ID + ","
                + DB_TABLE_INITIALS + "." + DB_CRN + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_RESOURCES + ","
                + DB_TABLE_INITIALS + "." + DB_HAS_LISCENCE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON(" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID
                + ") WHERE " + DB_TABLE_INITIALS + "." + DB_CRN + "=?;";
    }

    public static String deleteByCRN() {
        return "DELETE FROM " + DB_TABLE + " WHERE " + DB_CRN + "=?;";
    }

    public static String updateByCRN() {
        return "UPDATE " + DB_TABLE + " SET "
                + DB_CRN + "=?,"
                + DB_NAME + "=?,"
                + DB_DESCRIPTION + "=?,"
                + DB_RESOURCES + "=?,"
                + DB_HAS_LISCENCE + "=?,"
                + DB_PLACE + "=? "
                + "WHERE " + DB_CRN + "=?;";
    }

}
