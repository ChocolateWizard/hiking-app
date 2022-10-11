/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompThreeComboBox;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

/**
 *
 * @author Despot
 */
public class CompDateAdvanced extends CompThreeComboBox<GregorianCalendar, Integer, Integer[]> {

    private boolean gate1 = true;
    private boolean gate2 = true;
    private boolean gate3 = true;

    public CompDateAdvanced() {
        super("day", "month", "year", null);
        initialize();
    }

    public CompDateAdvanced(String firstLabel, String secondLabel, String thirdLabel) {
        super(firstLabel, secondLabel, thirdLabel, null);
        initialize();
    }

    @Override
    public GregorianCalendar getValue() throws CustomException {
        int day = (int) cb1.getSelectedItem();
        int month = (int) cb2.getSelectedItem();
        int year = (int) cb3.getSelectedItem();

        Integer[] n = {day, month, year};

        if (validator != null) {
            validator.validate(n);//validate whether or not date is accurate
        }
        return new GregorianCalendar(year, month - 1, day);
    }

    @Override
    public void setValue(GregorianCalendar value) throws CustomException {
        if (value != null) {
            int day = value.get(GregorianCalendar.DAY_OF_MONTH);
            int month = value.get(GregorianCalendar.MONTH) + 1;
            int year = value.get(GregorianCalendar.YEAR);

            cb1.setSelectedItem(day);
            cb2.setSelectedItem(month);
            cb3.setSelectedItem(year);
        }
    }

    private void initialize() {
        GregorianCalendar d = new GregorianCalendar();
        int day = d.get(GregorianCalendar.DAY_OF_MONTH);
        int month = d.get(GregorianCalendar.MONTH) + 1;
        int year = d.get(GregorianCalendar.YEAR);

        for (int i = 1; i <= 31; i++) {
            cb1.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            cb2.addItem(i);
        }
        for (int i = 1950; i <= year; i++) {
            cb3.addItem(i);
        }
        cb1.setSelectedItem(day);
        cb2.setSelectedItem(month);
        cb3.setSelectedItem(year);
        addListeners();

    }

    private void addListeners() {
        cb1.addActionListener((ActionEvent e) -> {
            if (gate1) {
                gate2 ^= true;
                gate3 ^= true;
                cb1Pressed();
                gate2 ^= true;
                gate3 ^= true;
            }
        });
        cb2.addActionListener((ActionEvent e) -> {
            if (gate2) {
                gate1 ^= true;
                gate3 ^= true;
                cb2Pressed();
                gate1 ^= true;
                gate3 ^= true;
            }
        });
        cb3.addActionListener((ActionEvent e) -> {
            if (gate3) {
                gate1 ^= true;
                gate2 ^= true;
                cb3Pressed();
                gate1 ^= true;
                gate2 ^= true;
            }
        });
    }

    private void cb1Pressed() {

        int day = (int) cb1.getSelectedItem();
        int month = (int) cb2.getSelectedItem();
        int year = (int) cb3.getSelectedItem();
        cb2.removeAllItems();
        switch (day) {
            case 31:
                for (int i = 1; i <= 12; i += 2) {
                    cb2.addItem(i);
                    if (i == 7) {
                        cb2.addItem(++i);
                    }
                }
                if (isLongMonth(month)) {
                    //currently a long month is selected
                    //cb2.setSelectedItem(month);
                    cb2.setSelectedItem(month);
                } else {
                    //currently a short month is selected
                    cb2.setSelectedItem(1);
                }
                break;
            case 30:
                for (int i = 1; i <= 12; i++) {
                    if (i == 2) {
                        i++;
                    }
                    cb2.addItem(i);
                }
                if (month == 2) {
                    cb2.setSelectedItem(1);
                } else {
                    cb2.setSelectedItem(month);
                }
                break;
            case 29:
                if (isLeapYear(year)) {
                    for (int i = 1; i <= 12; i++) {
                        cb2.addItem(i);
                    }
                    cb2.setSelectedItem(month);
                } else {
                    for (int i = 1; i <= 12; i++) {
                        if (i == 2) {
                            i++;
                        }
                        cb2.addItem(i);
                    }
                    if (month == 2) {
                        cb2.setSelectedItem(1);
                    } else {
                        cb2.setSelectedItem(month);
                    }
                }
                break;
            default:
                for (int i = 1; i <= 12; i++) {
                    cb2.addItem(i);
                }
                cb2.setSelectedItem(month);
                break;
        }
    }

    private void cb2Pressed() {

        int day = (int) cb1.getSelectedItem();
        int month = (int) cb2.getSelectedItem();
        int year = (int) cb3.getSelectedItem();
        cb1.removeAllItems();
        if (isLongMonth(month)) {
            //Long month is selected
            for (int i = 1; i <= 31; i++) {
                cb1.addItem(i);
            }
            cb1.setSelectedItem(day);
        } else if (month == 2) {
            //February is selected
            if (isLeapYear(year)) {
                //year is leap=>February has 29 days
                for (int i = 1; i <= 29; i++) {
                    cb1.addItem(i);
                }
                if (day < 30) {
                    cb1.setSelectedItem(day);
                } else {
                    cb1.setSelectedItem(1);
                }
            } else {
                for (int i = 1; i <= 28; i++) {
                    cb1.addItem(i);
                }
                if (day < 29) {
                    cb1.setSelectedItem(day);
                } else {
                    cb1.setSelectedItem(1);
                }
            }
        } else {
            //short month is selected
            for (int i = 1; i <= 30; i++) {
                cb1.addItem(i);
            }
            if (day < 31) {
                cb1.setSelectedItem(day);
            } else {
                cb1.setSelectedItem(1);
            }
        }
    }

    private void cb3Pressed() {
        int day = (int) cb1.getSelectedItem();
        int month = (int) cb2.getSelectedItem();
        int year = (int) cb3.getSelectedItem();
        /*
        if (mesec == 2 && dan == 29 && !isPrestupna(godina)) {
            prvoCBPolje.setSelectedItem(28);
            prvoCBPolje.removeItem(29);
        }
         */
        cb2.removeAllItems();
        if (isLeapYear(year)) {
            if (day > 29) {
                for (int i = 1; i <= 12; i++) {
                    if (i == 2) {
                        i++;
                    }
                    cb2.addItem(i);
                }
                if (month != 2) {
                    cb2.setSelectedItem(month);
                }
            } else {
                for (int i = 1; i <= 12; i++) {
                    cb2.addItem(i);
                }
                cb2.setSelectedItem(month);
            }
        } else {
            if (day > 28) {

                for (int i = 1; i <= 12; i++) {
                    if (i == 2) {
                        i++;
                    }
                    cb2.addItem(i);
                }
                if (month != 2) {
                    cb2.setSelectedItem(month);
                }
            } else {
                for (int i = 1; i <= 12; i++) {
                    cb2.addItem(i);
                }
                cb2.setSelectedItem(month);
            }
        }
        cb2Pressed();
    }

    private boolean isLongMonth(int mesec) {
        switch (mesec) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return true;
            default:
                return false;

        }
    }

    private boolean isLeapYear(int godina) {
        return ((godina % 4 == 0) && (godina % 100 != 0)) || (godina % 400 == 0);
    }

}
