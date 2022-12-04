/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.domain.classes;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mr. Poyo
 */
public class HikingGroupPlan implements Serializable {

    private List<Hiker> hikers;
    private List<HikingActivity> activities;

    public HikingGroupPlan() {
    }

    public HikingGroupPlan(List<Hiker> hikers, List<HikingActivity> activities) {
        this.hikers = hikers;
        this.activities = activities;
    }

    public List<Hiker> getHikers() {
        return hikers;
    }

    public void setHikers(List<Hiker> hikers) {
        this.hikers = hikers;
    }

    public List<HikingActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<HikingActivity> activities) {
        this.activities = activities;
    }
}
