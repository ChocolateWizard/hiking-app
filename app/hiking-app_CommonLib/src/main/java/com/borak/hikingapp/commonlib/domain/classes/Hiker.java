/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.classes;

import com.borak.hikingapp.commonlib.domain.enums.Gender;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
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
    private List<Profile> profiles;

    public Hiker() {
        profiles = new LinkedList<>();
    }

    public Hiker(String ucin, String firstName, String lastName, Gender gender, GregorianCalendar dateOfBirth, Integer yearsOfExperience, Place place) {
        this.ucin = ucin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.yearsOfExperience = yearsOfExperience;
        this.place = place;
        profiles = new LinkedList<>();
    }

    public Hiker(Long id, String ucin, String firstName, String lastName, Gender gender, GregorianCalendar dateOfBirth, Integer yearsOfExperience, Place place) {
        this.id = id;
        this.ucin = ucin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.yearsOfExperience = yearsOfExperience;
        this.place = place;
        profiles = new LinkedList<>();
    }

    public Hiker(Long id, String ucin, String firstName, String lastName, Gender gender, GregorianCalendar dateOfBirth, Integer yearsOfExperience, Place place, List<Profile> profiles) {
        this.id = id;
        this.ucin = ucin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.yearsOfExperience = yearsOfExperience;
        this.place = place;
        this.profiles = profiles;
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

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
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

}
