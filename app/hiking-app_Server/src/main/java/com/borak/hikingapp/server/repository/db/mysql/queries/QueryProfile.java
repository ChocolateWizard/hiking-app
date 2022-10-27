/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql.queries;

/**
 *
 * @author User
 */
public final class QueryProfile {

    private QueryProfile() {
    }

    public static final String DB_HIKING_GROUP = "hiking_group_id";
    public static final String DB_HIKER = "hiker_id";
    public static final String DB_DATE = "date_of_enrollment";
    public static final String DB_NOTE = "note";
    public static final String DB_TABLE = "profile";
    public static final String DB_TABLE_INITIALS = "profile";

    public static String getAll() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_CRN + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_NAME + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_DESCRIPTION + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_RESOURCES + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_HAS_LISCENCE + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_PLACE + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_TABLE_INITIALS + "_" + QueryPlace.DB_TABLE_INITIALS + "_" + QueryPlace.DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_HIKER + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_UCIN + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_FIRST_NAME + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_LAST_NAME + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_GENDER + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_DATE_OF_BIRTH + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_YEARS_OF_EXPERIENCE + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_PLACE + ","
                + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "_" + QueryPlace.DB_TABLE_INITIALS + "_" + QueryPlace.DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DATE + ","
                + DB_TABLE_INITIALS + "." + DB_NOTE
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN ("
                + "SELECT "
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_ID + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_CRN + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_NAME + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_DESCRIPTION + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_RESOURCES + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_HAS_LISCENCE + ","
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_PLACE + ","
                + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME + " AS " + QueryHikingGroup.DB_TABLE_INITIALS + "_" + QueryPlace.DB_TABLE_INITIALS + "_" + QueryPlace.DB_NAME
                + " FROM " + QueryHikingGroup.DB_TABLE + " " + QueryHikingGroup.DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS + " ON"
                + "(" + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ")"
                + ") " + QueryHikingGroup.DB_TABLE_INITIALS
                + " ON("
                + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + "=" + QueryHikingGroup.DB_TABLE_INITIALS + "."
                + QueryHikingGroup.DB_TABLE_INITIALS + "." + QueryHikingGroup.DB_ID + ")"
                + " INNER JOIN ("
                + "SELECT "
                + "" + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_ID
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_UCIN
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_FIRST_NAME
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_LAST_NAME
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_GENDER
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_DATE_OF_BIRTH
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_YEARS_OF_EXPERIENCE
                + "," + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_PLACE
                + "," + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME + " AS " + QueryHiker.DB_TABLE_INITIALS + "_" + QueryPlace.DB_TABLE_INITIALS + "_" + QueryPlace.DB_NAME
                + " FROM " + QueryHiker.DB_TABLE + " " + QueryHiker.DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON (" + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ")"
                + ") "
                + QueryHiker.DB_TABLE_INITIALS
                + " ON("
                + DB_TABLE_INITIALS + "." + DB_HIKER + "=" + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_TABLE_INITIALS + "." + QueryHiker.DB_ID + ");";
    }

    public static String insert() {
        return "INSERT INTO " + DB_TABLE + "("
                + DB_HIKING_GROUP + ","
                + DB_HIKER + ","
                + DB_DATE + ","
                + DB_NOTE + ") VALUES(?,?,?,?)";
    }
    

    public static String find() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    public static String delete() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    public static String deleteAll() {
        return "DELETE FROM " + DB_TABLE;
    }

    public static String updateQuery() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

}
