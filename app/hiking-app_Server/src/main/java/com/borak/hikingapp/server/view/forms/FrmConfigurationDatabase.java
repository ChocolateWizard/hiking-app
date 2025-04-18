/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.borak.hikingapp.server.view.forms;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.api.IComponent;
import com.borak.hikingapp.server.domain.enums.DatabaseType;
import com.borak.hikingapp.server.logic.controllers.ControllerForms;
import com.borak.hikingapp.server.logic.controllers.Util;
import com.borak.hikingapp.server.view.components.CompDatabasePicker;
import com.borak.hikingapp.commonlib.view.components.CompStringInput;
import com.borak.hikingapp.server.view.helpers.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Despot
 */
public class FrmConfigurationDatabase extends javax.swing.JDialog {

    private IComponent<DatabaseType> compDatabasePicker;

    private IComponent<String> compDatabaseUrl;
    private IComponent<String> compDatabaseUsername;
    private IComponent<String> compDatabasePassword;

    private JPanel pnlPicker;
    private JPanel pnlParams;
    private JPanel pnlButtons;

    private JButton btnSave;

    public FrmConfigurationDatabase(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initialize();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initialize() {
        setTitle("Configure database");
        MigLayout migMain = new MigLayout("insets 0 0 0 0", "[]0[]", "[]0[]");
        setLayout(migMain);
        try {
            ImageIcon favicon = new ImageIcon(Util.getInstance().getFrmConfigurationDatabaseFavicon());
            setIconImage(favicon.getImage());
        } catch (CustomException ex) {
            ex.printStackTrace();
        }

        setFormElements();

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void setFormElements() {
        setPanels();
        setComponents();
        setButtons();
    }

    private void setPanels() {
        MigLayout migPicker = new MigLayout("insets 15 15 0 0", "", "");
        MigLayout migButtons = new MigLayout("insets 0 15 0 15", "", "");
        MigLayout migParams = new MigLayout("insets 5 15 0 0", "", "[]0[]0[]");

        pnlPicker = new JPanel(migPicker);
        pnlButtons = new JPanel(migButtons);
        pnlParams = new JPanel(migParams);

        add(pnlPicker, "cell 0 0");
        add(pnlButtons, "cell 1 0");
        add(pnlParams, "cell 0 1 2 1");
    }

    private void setComponents() {
        setDatabasePicker();
        setDatabaseParams();
        fillComponents();
    }

    private void setDatabasePicker() {
        compDatabasePicker = new CompDatabasePicker();
        compDatabasePicker.setCaption("Current database:");
        compDatabasePicker.setCaptionWidth(120);
        compDatabasePicker.setErrorMessageWidth(200);
        compDatabasePicker.setInputWidth(200);
        compDatabasePicker.setErrorMessage("");

        ((CompDatabasePicker) compDatabasePicker).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbPickerPressed();
            }
        });
        pnlPicker.add((JPanel) compDatabasePicker, "cell 0 0");
    }

    private void setDatabaseParams() {
        compDatabaseUrl = new CompStringInput();
        compDatabaseUsername = new CompStringInput();
        compDatabasePassword = new CompStringInput();

        compDatabaseUrl.setCaption("Url:");
        compDatabaseUrl.setCaptionWidth(70);
        compDatabaseUrl.setInputWidth(330);
        compDatabaseUrl.setErrorMessageWidth(330);
        compDatabaseUrl.setErrorMessage("");
        compDatabaseUrl.setEnabledInput(false);

        compDatabaseUsername.setCaption("Username:");
        compDatabaseUsername.setCaptionWidth(70);
        compDatabaseUsername.setInputWidth(330);
        compDatabaseUsername.setErrorMessageWidth(330);
        compDatabaseUsername.setErrorMessage("");
        compDatabaseUsername.setEnabledInput(false);

        compDatabasePassword.setCaption("Password:");
        compDatabasePassword.setCaptionWidth(70);
        compDatabasePassword.setInputWidth(330);
        compDatabasePassword.setErrorMessageWidth(330);
        compDatabasePassword.setErrorMessage("");
        compDatabasePassword.setEnabledInput(false);

        pnlParams.add((JPanel) compDatabaseUrl, "cell 0 0");
        pnlParams.add((JPanel) compDatabaseUsername, "cell 0 1");
        pnlParams.add((JPanel) compDatabasePassword, "cell 0 2");
    }

    private void setButtons() {
        btnSave = new JButton("Save");
        btnSave.setFocusable(false);
        btnSave.addActionListener((e) -> {
            btnSavePressed();
        });
        pnlButtons.add(btnSave, "cell 0 0");
    }

    private void btnSavePressed() {
        try {
            DatabaseType type = compDatabasePicker.getValue();
            Util.getInstance().setCurrentDatabase(type);
            Window.successfulOperation(this, "Successfull save", "Configuration successfully saved!");
            ControllerForms.getInstance().closeFrmConfigurationDatabase();
        } catch (CustomException ex) {
            ex.printStackTrace();
            Window.unSuccessfulOperation(this, "Error", ex.getMessage());
        }
    }

    public void cbPickerPressed() {
        try {
            DatabaseType type = compDatabasePicker.getValue();
            if (type.hasParams()) {
                pnlParams.setVisible(true);
                fillParams(type);
            } else {
                pnlParams.setVisible(false);
            }
            this.pack();
        } catch (CustomException ex) {
            ex.printStackTrace();
        }
    }

    private void fillParams(DatabaseType databaseType) {
        try {
            String url = Util.getInstance().getDatabaseUrl(databaseType);
            String username = Util.getInstance().getDatabaseUsername(databaseType);
            String password = Util.getInstance().getDatabasePassword(databaseType);
            compDatabaseUrl.setValue(url);
            compDatabaseUsername.setValue(username);
            compDatabasePassword.setValue(password);
        } catch (CustomException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComponents() {
        try {
            DatabaseType t = Util.getInstance().getCurrentDatabase();
            compDatabasePicker.setValue(t);
        } catch (CustomException ex) {
            ex.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
