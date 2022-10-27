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

}
