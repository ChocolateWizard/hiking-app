/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.borak.hikingapp.server.domain.enums;

/**
 *
 * @author Despot
 */
public enum DatabaseType {
    OM("Operative memory"),
    MYSQL("MySQL database");

    private final String name;

    private DatabaseType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static DatabaseType parseDatabaseType(String databaseType) {
        return switch (databaseType) {
            case "Operative memory" ->
                OM;
            case "om" ->
                OM;
            case "MySQL database" ->
                MYSQL;
            case "mysql" ->
                MYSQL;
            default ->
                null;
        };
    }

    public boolean hasParams() {
        return switch (this) {
            case OM ->
                false;
            case MYSQL ->
                true;
            default ->
                false;
        };
    }
}
