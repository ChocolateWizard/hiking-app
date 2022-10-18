/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Despot
 */
public class HikingGroup implements Serializable {

    private Long id;
    private String crn;//Company Registration Number. This is "maticni broj" in serbian
    private String name;
    private String description;
    private String resources;
    private boolean hasLiscence;
    private Place place;
    private List<HikingActivity> groupActivities;

    public static final String DB_ID = "id";
    public static final String DB_CRN = "crn";
    public static final String DB_NAME = "name";
    public static final String DB_DESCRIPTION = "description";
    public static final String DB_RESOURCES = "resources";
    public static final String DB_HAS_LISCENCE = "has_liscence";
    public static final String DB_PLACE = "place_id";
    public static final String DB_TABLE = "hiking_group";
    public static final String DB_TABLE_INITIALS = "hiking_group";

    public HikingGroup() {
    }

    public HikingGroup(String crn, String name, String description, String resources, boolean hasLiscence, Place place, List<HikingActivity> groupActivities) {
        this.crn = crn;
        this.name = name;
        this.description = description;
        this.resources = resources;
        this.hasLiscence = hasLiscence;
        this.place = place;
        this.groupActivities = groupActivities;
    }

    public HikingGroup(Long id, String crn, String name, String description, String resources, boolean hasLiscence, Place place, List<HikingActivity> groupActivities) {
        this.id = id;
        this.crn = crn;
        this.name = name;
        this.description = description;
        this.resources = resources;
        this.hasLiscence = hasLiscence;
        this.place = place;
        this.groupActivities = groupActivities;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
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

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public boolean isHasLiscence() {
        return hasLiscence;
    }

    public void setHasLiscence(boolean hasLiscence) {
        this.hasLiscence = hasLiscence;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<HikingActivity> getGroupActivities() {
        return groupActivities;
    }

    public void setGroupActivities(List<HikingActivity> groupActivities) {
        this.groupActivities = groupActivities;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.crn);
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
        final HikingGroup other = (HikingGroup) obj;
        return Objects.equals(this.crn, other.crn);
    }

    @Override
    public String toString() {
        return name;
    }

    public static String getAllQuery() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_ID + ","
                + DB_TABLE_INITIALS + "." + DB_CRN + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_RESOURCES + ","
                + DB_TABLE_INITIALS + "." + DB_HAS_LISCENCE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS + " ON"
                + "(" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID + ");";
    }

    public static String insertQuery() {
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

    public static String findQuery() {
        return "SELECT "
                + DB_TABLE_INITIALS + "." + DB_ID + ","
                + DB_TABLE_INITIALS + "." + DB_CRN + ","
                + DB_TABLE_INITIALS + "." + DB_NAME + ","
                + DB_TABLE_INITIALS + "." + DB_DESCRIPTION + ","
                + DB_TABLE_INITIALS + "." + DB_RESOURCES + ","
                + DB_TABLE_INITIALS + "." + DB_HAS_LISCENCE + ","
                + DB_TABLE_INITIALS + "." + DB_PLACE + ","
                + Place.DB_TABLE_INITIALS + "." + Place.DB_ID
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON(" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID
                + ") WHERE " + DB_TABLE_INITIALS + "." + DB_CRN + "=?;";
    }

    public static String deleteQuery() {
        return "DELETE FROM " + DB_TABLE + " WHERE " + DB_CRN + "=?;";
    }

    public static String updateQuery() {
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
