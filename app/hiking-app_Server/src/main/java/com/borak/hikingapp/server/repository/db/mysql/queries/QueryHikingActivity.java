/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql.queries;

/**
 *
 * @author User
 */
public final class QueryHikingActivity {

    private QueryHikingActivity() {
    }

    public static final String DB_HIKING_GROUP = "hiking_group_id";
    public static final String DB_ORDER_NUM = "order_num";
    public static final String DB_NAME = "name";
    public static final String DB_DESCRIPTION = "description";
    public static final String DB_DATE = "date";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "hiking_activity";
    public static final String DB_TABLE_INITIALS = "hiking_activity";

    public static String getAllByHikingGroup() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + ","
                + DB_TABLE_INITIALS + "." + DB_ORDER_NUM + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_DATE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON(" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ")"
                + " WHERE " + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + "=?";
    }

    public static String insert() {
        return "INSERT INTO "
                + DB_TABLE + "("
                + DB_HIKING_GROUP + ","
                + DB_ORDER_NUM + ","
                + DB_NAME + ","
                + DB_DESCRIPTION + ","
                + DB_DATE + ","
                + DB_PLACE + ")"
                + " VALUES(?,?,?,?,?,?)";
    }

    public static String findByHikingGroupAndOrderNum() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + ","
                + DB_TABLE_INITIALS + "." + DB_ORDER_NUM + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_DATE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + QueryPlace.DB_TABLE + " " + QueryPlace.DB_TABLE_INITIALS
                + " ON(" + DB_TABLE_INITIALS + "." + DB_PLACE + "," + QueryPlace.DB_TABLE_INITIALS + "." + QueryPlace.DB_ID + ")"
                + " WHERE " + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + "=? && " + DB_ORDER_NUM + "=?;";
    }

    public static String deleteByHikingGroup() {
        return "DELETE FROM " + DB_TABLE + " WHERE " + DB_HIKING_GROUP + "=?;";
    }

    public static String updateByHikingGroupAndOrderNum() {
        return "UPDATE " + DB_TABLE + " SET "
                + DB_ORDER_NUM + "=?,"
                + DB_NAME + "=?,"
                + DB_DESCRIPTION + "=?,"
                + DB_DATE + "=?,"
                + DB_PLACE + "=?"
                + " WHERE " + DB_HIKING_GROUP + "=? && " + DB_ORDER_NUM + "=?;";
    }

}
