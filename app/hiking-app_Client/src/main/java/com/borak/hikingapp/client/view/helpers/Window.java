/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.helpers;

import com.borak.hikingapp.client.logic.controllers.Util;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Despot
 */
public final class Window {

    private Window() {
    }

    public static void successfulOperation(Component parent, String title, String message) {
        try {
            ImageIcon icon = new ImageIcon(Util.getInstance().getSuccessFavicon());
            JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (CustomException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void unSuccessfulOperation(Component parent, String title, String message) {
        try {
            ImageIcon icon = new ImageIcon(Util.getInstance().getErrorFavicon());
            JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE, icon);
        } catch (CustomException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean question(Component parent, String title, String message) {
        int i;
        try {
            ImageIcon icon = new ImageIcon(Util.getInstance().getQuestionFavicon());
            i = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        } catch (CustomException ex) {
            ex.printStackTrace();
            i = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        return (i == JOptionPane.YES_OPTION);
    }

}
