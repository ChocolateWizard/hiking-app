/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.classes;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Despot
 */
public class HikingActivity implements Serializable {

    private Integer orderNum;
    private String name;
    private String description;
    private GregorianCalendar date;
    private Place place;
    private HikingGroup hikingGroup;

    public static final String DB_HIKING_GROUP = "hiking_group_id";
    public static final String DB_ORDER_NUM = "order_num";
    public static final String DB_NAME = "name";
    public static final String DB_DESCRIPTION = "description";
    public static final String DB_DATE = "date";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "hiking_activity";
    public static final String DB_TABLE_INITIALS = "hiking_activity";

    public HikingActivity() {
    }

    public HikingActivity(Integer orderNum, String name, String description, GregorianCalendar date, Place place, HikingGroup hikingGroup) {
        this.orderNum = orderNum;
        this.name = name;
        this.description = description;
        this.date = date;
        this.place = place;
        this.hikingGroup = hikingGroup;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public HikingGroup getHikingGroup() {
        return hikingGroup;
    }

    public void setHikingGroup(HikingGroup hikingGroup) {
        this.hikingGroup = hikingGroup;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.orderNum);
        hash = 53 * hash + Objects.hashCode(this.hikingGroup);
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
        final HikingActivity other = (HikingActivity) obj;
        if (!Objects.equals(this.orderNum, other.orderNum)) {
            return false;
        }
        return Objects.equals(this.hikingGroup, other.hikingGroup);
    }

    @Override
    public String toString() {
        if (hikingGroup == null) {
            return name;
        }
        return "" + hikingGroup + ": " + name;
    }

    public static String getAllQuery() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + ","
                + DB_TABLE_INITIALS + "." + DB_ORDER_NUM + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_DATE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON(" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID + ")"
                + " WHERE " + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + "=?";
    }

    public static String insertQuery() {
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

    public static String findQuery() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + ","
                + DB_TABLE_INITIALS + "." + DB_ORDER_NUM + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_DATE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON(" + DB_TABLE_INITIALS + "." + DB_PLACE + "," + Place.DB_TABLE_INITIALS + "." + Place.DB_ID + ")"
                + " WHERE " + DB_TABLE_INITIALS + "." + DB_HIKING_GROUP + "=? && " + DB_ORDER_NUM + "=?;";
    }

    public static String deleteQuery() {
        return "DELETE FROM " + DB_TABLE + " WHERE " + DB_HIKING_GROUP + "=?;";
    }

    public static String updateQuery() {
        return "UPDATE " + DB_TABLE + " SET "
                + DB_ORDER_NUM + "=?,"
                + DB_NAME + "=?,"
                + DB_DESCRIPTION + "=?,"
                + DB_DATE + "=?,"
                + DB_PLACE + "=?"
                + " WHERE " + DB_HIKING_GROUP + "=? && " + DB_ORDER_NUM + "=?;";
    }

}
