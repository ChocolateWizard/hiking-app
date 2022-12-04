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
 * @author User
 */
public class Profile implements Serializable{

    private HikingGroup hikingGroup;
    private Hiker hiker;
    private GregorianCalendar dateOfEnrollment;
    private String note;

    public Profile() {
    }

    public Profile(HikingGroup hikingGroup) {
        this.hikingGroup = hikingGroup;
    }

    
    
    public Profile(HikingGroup hikingGroup, Hiker hiker, GregorianCalendar dateOfAdmission, String note) {
        this.hikingGroup = hikingGroup;
        this.hiker = hiker;
        this.dateOfEnrollment = dateOfAdmission;
        this.note = note;
    }

    public HikingGroup getHikingGroup() {
        return hikingGroup;
    }

    public void setHikingGroup(HikingGroup hikingGroup) {
        this.hikingGroup = hikingGroup;
    }

    public Hiker getHiker() {
        return hiker;
    }

    public void setHiker(Hiker hiker) {
        this.hiker = hiker;
    }

    public GregorianCalendar getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(GregorianCalendar dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.hikingGroup);
        hash = 37 * hash + Objects.hashCode(this.hiker);
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
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.hikingGroup, other.hikingGroup)) {
            return false;
        }
        return Objects.equals(this.hiker, other.hiker);
    }
    

}
