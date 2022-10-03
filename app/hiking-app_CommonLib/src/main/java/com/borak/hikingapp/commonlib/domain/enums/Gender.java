/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.enums;

/**
 *
 * @author Despot
 */
public enum Gender {

    MALE("male"),
    FEMALE("female");

    private final String name;

    private Gender(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
    public static Gender getGender(String gender){
        switch(gender){
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                return null;
        }
    }
    
    
}
