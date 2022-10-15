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
public class Place implements Serializable {

    private Long id;
    private String name;

    public static final String DB_ID = "id";
    public static final String DB_NAME = "name";
    public static final String DB_TABLE = "place";
    public static final String DB_TABLE_INITIALS = "place";

    public Place() {
    }

    public Place(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Place other = (Place) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return name;
    }

    public static String getAllQuery() {
        return "SELECT " + DB_ID + "," + DB_NAME + " FROM " + DB_TABLE;
    }

}
