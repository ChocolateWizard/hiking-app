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

    public static String toPropertyName(DatabaseType databaseType) {
        switch (databaseType) {
            case OM:
                return "om";
            case MYSQL:
                return "mysql";
            default:
                return null;
        }
    }

    public static DatabaseType parseDatabaseType(String databaseType) {
        switch (databaseType) {
            case "Operative memory":
                return OM;
            case "om":
                return OM;
            case "MySQL database":
                return MYSQL;
            case "mysql":
                return MYSQL;
            default:
                return null;
        }
    }

    public boolean hasParams() {
        switch (this) {
            case OM:
                return false;
            case MYSQL:
                return true;
            default:
                return false;
        }
    }
}
