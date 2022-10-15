/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.classes;

import com.borak.hikingapp.commonlib.domain.enums.Gender;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Despot
 */
public class Hiker implements Serializable {

    private Long id;
    private String ucin;// Stands for "Unique Citizen Identification Number". This is "JMBG" in serbian
    private String firstName;
    private String lastName;
    private Gender gender;
    private GregorianCalendar dateOfBirth;
    private Integer yearsOfExperience;
    private Place place;

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

    public Hiker() {
    }

    public Hiker(String ucin, String firstName, String lastName, Gender gender, GregorianCalendar dateOfBirth, Integer yearsOfExperience, Place place) {
        this.ucin = ucin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.yearsOfExperience = yearsOfExperience;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUcin() {
        return ucin;
    }

    public void setUcin(String ucin) {
        this.ucin = ucin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.ucin);
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
        final Hiker other = (Hiker) obj;
        return Objects.equals(this.ucin, other.ucin);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public static String getAllQuery() {
        return "SELECT "
                + "" + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_UCIN
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_GENDER
                + "," + DB_TABLE_INITIALS + "." + DB_DATE_OF_BIRTH
                + "," + DB_TABLE_INITIALS + "." + DB_YEARS_OF_EXPERIENCE
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS
                + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID + ")";
    }

    public static String insertQuery() {
        return "INSERT INTO " + DB_TABLE + "("
                + DB_UCIN + ","
                + DB_FIRST_NAME + ","
                + DB_LAST_NAME + ","
                + DB_GENDER + ","
                + DB_DATE_OF_BIRTH + ","
                + DB_YEARS_OF_EXPERIENCE + ","
                + DB_PLACE + ") VALUES(?,?,?,?,?,?,?)";
    }

    public static String findQuery() {
        return "SELECT "
                + "" + DB_TABLE_INITIALS + "." + DB_ID
                + "," + DB_TABLE_INITIALS + "." + DB_UCIN
                + "," + DB_TABLE_INITIALS + "." + DB_FIRST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_LAST_NAME
                + "," + DB_TABLE_INITIALS + "." + DB_GENDER
                + "," + DB_TABLE_INITIALS + "." + DB_DATE_OF_BIRTH
                + "," + DB_TABLE_INITIALS + "." + DB_YEARS_OF_EXPERIENCE
                + "," + DB_TABLE_INITIALS + "." + DB_PLACE
                + "," + Place.DB_TABLE_INITIALS + "." + Place.DB_NAME
                + " FROM " + DB_TABLE + " " + DB_TABLE_INITIALS + " INNER JOIN " + Place.DB_TABLE + " " + Place.DB_TABLE_INITIALS
                + " ON (" + DB_TABLE_INITIALS + "." + DB_PLACE + "=" + Place.DB_TABLE_INITIALS + "." + Place.DB_ID + ")"
                + " WHERE  " + DB_TABLE_INITIALS + "." + DB_UCIN + "=?";
    }

    public static String deleteQuery() {
        return "DELETE FROM " + DB_TABLE + " WHERE " + DB_UCIN + "=?";
    }

    public static String updateQuery() {
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
