/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.borak.hikingapp.client.view.forms;

import com.borak.hikingapp.client.logic.controllers.ControllerForms;
import com.borak.hikingapp.client.logic.controllers.ControllerSO;
import com.borak.hikingapp.client.logic.controllers.Util;
import com.borak.hikingapp.client.view.components.CompDateAdvanced;
import com.borak.hikingapp.client.view.components.CompHikerInput;
import com.borak.hikingapp.client.view.components.CompHikingGroupInput;
import com.borak.hikingapp.client.view.components.CompStringInputLarge;
import com.borak.hikingapp.client.view.components.validators.factory.ValidatorFactory;
import com.borak.hikingapp.client.view.helpers.Window;
import com.borak.hikingapp.client.view.tables.HikingGroupTableModel;
import com.borak.hikingapp.commonlib.communication.TransferObject;
import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Profile;
import com.borak.hikingapp.commonlib.domain.enums.ResponseType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.api.IComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class FrmGroupMembership extends javax.swing.JDialog {

    private JPanel pnlHikingGroups;
    private JPanel pnlHikingGroupsComponent;
    private JPanel pnlHikingGroupsButtons;

    private JPanel pnlHikers;
    private JPanel pnlHikersView;
    private JPanel pnlHikersViewTable;
    private JPanel pnlHikersViewButtons;
    private JPanel pnlHikersAdd;
    private JPanel pnlHikersAddComponents;
    private JPanel pnlHikersAddButtons;

    private HikingGroupTableModel tblModel;
    private JTable tblProfiles;
    private JScrollPane scrollPane;

    private IComponent<HikingGroup> hikingGroupComponent;
    private IComponent<Hiker> hikerComponent;
    private IComponent<GregorianCalendar> dateComponent;
    private IComponent<String> noteComponent;

    private JButton btnConfigurationSave;
    private JButton btnProfileAdd;
    private JButton btnProfileEdit;
    private JButton btnProfileRemove;
    private JButton btnProfileSave;

    private List<HikingGroup> groups;
    private List<Hiker> hikers;
    private List<Profile> profiles;

    private Profile editableProfile;
    private boolean isAdding = false;
    private boolean isEditing = false;

    public FrmGroupMembership(java.awt.Frame parent, boolean modal) {
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
        setTitle("Configure memberships");
        MigLayout migMain = new MigLayout("", "[]0[]", "[]0[]");
        setLayout(migMain);
        try {
            ImageIcon favicon = new ImageIcon(Util.getInstance().getFrmProfileCreateFavicon());
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
        getData();
        setData();
        setPanels();
        setComponents();
        setTable();
        setButtons();
    }

    private void getData() {
        getHikingGroups();
        getHikers();
        getProfiles();
    }

    private void getHikingGroups() {
        try {
            TransferObject response = ControllerSO.getInstance().getAllHikingGroups();
            if (response.getResponseType() == ResponseType.SUCCESS) {
                groups = (List<HikingGroup>) response.getArgument();
            } else {
                throw response.getException();
            }
        } catch (CustomException ex) {
            ex.printStackTrace();
            groups = null;
        }
    }

    private void getHikers() {
        try {
            TransferObject response = ControllerSO.getInstance().getAllHikers();
            if (response.getResponseType() == ResponseType.SUCCESS) {
                hikers = (List<Hiker>) response.getArgument();
            } else {
                throw response.getException();
            }
        } catch (CustomException ex) {
            ex.printStackTrace();
            hikers = null;
        }
    }

    private void getProfiles() {
        try {
            TransferObject response = ControllerSO.getInstance().getAllProfiles();
            if (response.getResponseType() == ResponseType.SUCCESS) {
                profiles = (List<Profile>) response.getArgument();
            } else {
                throw response.getException();
            }
        } catch (CustomException ex) {
            ex.printStackTrace();
            profiles = null;
        }
    }

    private void setData() {
        if (groups != null && profiles != null) {
            for (HikingGroup group : groups) {
                for (Profile profile : profiles) {
                    if (group.equals(profile.getHikingGroup())) {
                        profile.setHikingGroup(group);
                        group.getProfiles().add(profile);
                    }
                }
            }
        }
        if (hikers != null && profiles != null) {
            for (Hiker hiker : hikers) {
                for (Profile profile : profiles) {
                    if (hiker.equals(profile.getHiker())) {
                        profile.setHiker(hiker);
                        hiker.getProfiles().add(profile);
                    }
                }
            }
        }
    }

    private void setPanels() {
        setGroupPanels();
        setHikerPanels();
    }

    private void setGroupPanels() {
        MigLayout migGroup = new MigLayout("insets 0 0 0 0", "", "[]0[]");
        MigLayout migGroupComponents = new MigLayout("insets 5 5 5 5", "", "");
        MigLayout migGroupButtons = new MigLayout("insets 0 5 5 0", "", "");

        pnlHikingGroups = new JPanel(migGroup);
        pnlHikingGroupsComponent = new JPanel(migGroupComponents);
        pnlHikingGroupsButtons = new JPanel(migGroupButtons);

        TitledBorder groupTitledBorder = BorderFactory.createTitledBorder("Hiking groups");
        groupTitledBorder.setTitleColor(Color.black);
        Border groupBorder = BorderFactory.createLineBorder(Color.black);
        groupTitledBorder.setBorder(groupBorder);
        pnlHikingGroups.setBorder(groupTitledBorder);

        pnlHikingGroups.add(pnlHikingGroupsComponent, "cell 0 0");
        add(pnlHikingGroups, "cell 0 0");
        add(pnlHikingGroupsButtons, "cell 1 0");
    }

    private void setHikerPanels() {
        MigLayout migHikers = new MigLayout("insets 0 0 0 0", "", "[]0[]");
        pnlHikers = new JPanel(migHikers);

        TitledBorder hikersTitledBorder = BorderFactory.createTitledBorder("Hikers");
        hikersTitledBorder.setTitleColor(Color.black);
        Border hikersBorder = BorderFactory.createLineBorder(Color.black);
        hikersTitledBorder.setBorder(hikersBorder);
        pnlHikers.setBorder(hikersTitledBorder);

        setHikersViewPanels();
        setHikersAddPanels();

        pnlHikers.add(pnlHikersView, "cell 0 0");
        pnlHikers.add(pnlHikersAdd, "cell 0 1");
        add(pnlHikers, "cell 0 1 2 1");

    }

    private void setHikersViewPanels() {
        MigLayout migHikersView = new MigLayout("insets 0 0 0 0", "", "[]0[]");
        MigLayout migHikersViewTable = new MigLayout("insets 0 5 0 0", "", "");
        MigLayout migHikersViewButtons = new MigLayout("insets 5 5 5 0", "[]8[]8[]", "");

        pnlHikersView = new JPanel(migHikersView);
        pnlHikersViewTable = new JPanel(migHikersViewTable);
        pnlHikersViewButtons = new JPanel(migHikersViewButtons);

        pnlHikersView.add(pnlHikersViewTable, "cell 0 0");
        pnlHikersView.add(pnlHikersViewButtons, "cell 0 1");
    }

    private void setHikersAddPanels() {
        MigLayout migHikersAdd = new MigLayout("insets 0 0 0 0", "", "[]0[]");
        MigLayout migHikersAddComponents = new MigLayout("insets 5 5 5 5", "[]10[]", "[]5[]");
        MigLayout migHikersAddButtons = new MigLayout("insets 0 5 5 0", "", "");

        pnlHikersAdd = new JPanel(migHikersAdd);
        pnlHikersAddComponents = new JPanel(migHikersAddComponents);
        pnlHikersAddButtons = new JPanel(migHikersAddButtons);

        TitledBorder hikersAddTitledBorder = BorderFactory.createTitledBorder("Hiker profile information");
        hikersAddTitledBorder.setTitleColor(Color.black);
        Border hikersAddBorder = BorderFactory.createLineBorder(Color.black);
        hikersAddTitledBorder.setBorder(hikersAddBorder);
        pnlHikersAdd.setBorder(hikersAddTitledBorder);

        pnlHikersAdd.add(pnlHikersAddComponents, "cell 0 0");
        pnlHikersAdd.add(pnlHikersAddButtons, "cell 0 1");
    }

    private void setComponents() {
        setGroupComponents();
        setHikersComponents();
    }

    private void setGroupComponents() {
        if (groups == null || groups.isEmpty()) {
            hikingGroupComponent = new CompHikingGroupInput(null, ValidatorFactory.getInstance().getHikingGroupValidator());
            hikingGroupComponent.setErrorMessageWidth(270);
            hikingGroupComponent.setEnabledInput(false);
            hikingGroupComponent.setErrorMessage("There are no hiking groups!");
        } else {
            HikingGroup[] pom = groups.toArray(HikingGroup[]::new);
            hikingGroupComponent = new CompHikingGroupInput(pom, ValidatorFactory.getInstance().getHikingGroupValidator());
            hikingGroupComponent.setErrorMessageWidth(270);
            hikingGroupComponent.setErrorMessage("");
        }
        hikingGroupComponent.setCaption("Configure profiles for:");
        hikingGroupComponent.setInputWidth(270);
        hikingGroupComponent.setCaptionWidth(140);
        pnlHikingGroupsComponent.add((JPanel) hikingGroupComponent, "cell 0 0");
    }

    private void setHikersComponents() {
        hikerComponent = new CompHikerInput(null, ValidatorFactory.getInstance().getHikerValidator());
        hikerComponent.setErrorMessageWidth(205);
        hikerComponent.setCaption("Hiker:");
        hikerComponent.setInputWidth(205);
        hikerComponent.setCaptionWidth(100);
        hikerComponent.setEnabledInput(false);
        if (hikers == null || hikers.isEmpty()) {
            hikerComponent.setErrorMessage("There are no hikers!");
        } else {
            hikerComponent.setErrorMessage("");
        }

        dateComponent = new CompDateAdvanced();
        dateComponent.setCaption("Date of enrollment:");
        dateComponent.setCaptionWidth(135);
        dateComponent.setErrorMessageWidth(120);
        dateComponent.setErrorMessage("");
        dateComponent.setEnabledInput(false);

        noteComponent = new CompStringInputLarge(ValidatorFactory.getInstance().getProfileNotesValidator());
        noteComponent.setCaption("Notes:");
        noteComponent.setCaptionWidth(50);
        noteComponent.setInputSize(225, 90);
        noteComponent.setErrorMessageWidth(200);
        noteComponent.setErrorMessage("");
        noteComponent.setEnabledInput(false);

        pnlHikersAddComponents.add((JPanel) hikerComponent, "cell 0 0");
        pnlHikersAddComponents.add((JPanel) dateComponent, "cell 0 1");
        pnlHikersAddComponents.add((JPanel) noteComponent, "cell 1 0 1 2");

    }

    private void setTable() {
        try {
            HikingGroup selectedGroup = hikingGroupComponent.getValue();
            tblModel = new HikingGroupTableModel(selectedGroup);
        } catch (CustomException ex) {
            tblModel = new HikingGroupTableModel();
        }
        tblProfiles = new JTable(tblModel);
        scrollPane = new JScrollPane(tblProfiles);
        Dimension d = scrollPane.getPreferredSize();
        d.setSize(605, 200);
        scrollPane.setPreferredSize(d);

        pnlHikersViewTable.add(scrollPane, "cell 0 0");

    }

    private void setButtons() {
        setGroupButtons();
        setHikerViewButtons();
        setHikerAddButtons();
        setAvailability();
        addListeners();
    }

    private void setGroupButtons() {
        btnConfigurationSave = new JButton("<html><b><center>Save all<br>changes</center></b></html>");
        btnConfigurationSave.setFocusable(false);
        pnlHikingGroupsButtons.add(btnConfigurationSave, "cell 0 0,gapleft 50");
    }

    private void setHikerViewButtons() {
        btnProfileAdd = new JButton("Add");
        btnProfileEdit = new JButton("Edit");
        btnProfileRemove = new JButton("Remove");
        btnProfileAdd.setFocusable(false);
        btnProfileEdit.setFocusable(false);
        btnProfileRemove.setFocusable(false);

        pnlHikersViewButtons.add(btnProfileAdd, "cell 0 0");
        pnlHikersViewButtons.add(btnProfileEdit, "cell 1 0");
        pnlHikersViewButtons.add(btnProfileRemove, "cell 2 0");
    }

    private void setHikerAddButtons() {
        btnProfileSave = new JButton("Save profile information");
        btnProfileSave.setFocusable(false);
        pnlHikersAddButtons.add(btnProfileSave, "cell 0 1");
    }

    private void setAvailability() {
        if (groups == null || groups.isEmpty() || hikers == null || hikers.isEmpty()) {
            btnConfigurationSave.setEnabled(false);
            btnProfileAdd.setEnabled(false);
            btnProfileEdit.setEnabled(false);
            btnProfileRemove.setEnabled(false);
        }
        btnProfileSave.setEnabled(false);

    }

    private void addListeners() {
        btnConfigurationSave.addActionListener(((ActionEvent) -> {
            btnGroupSavePressed();
        }));
        btnProfileAdd.addActionListener(((ActionEvent) -> {
            btnProfileAddPressed();
        }));
        btnProfileEdit.addActionListener(((ActionEvent) -> {
            btnProfileEditPressed();
        }));
        btnProfileRemove.addActionListener(((ActionEvent) -> {
            btnProfileRemovePressed();
        }));
        btnProfileSave.addActionListener(((ActionEvent) -> {
            btnProfileSavePressed();
        }));
        ((CompHikingGroupInput) hikingGroupComponent).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbGroupPressed();
            }
        });
    }
//===========================================================================================================
//=======================================BUTTONS=============================================================
//===========================================================================================================

    private void btnProfileAddPressed() {
        try {
            HikingGroup currentGroup = hikingGroupComponent.getValue();
            List<Hiker> hikersNotInGroup = getHikersNotInGroup(currentGroup);
            if (hikersNotInGroup.isEmpty()) {
                Window.unSuccessfulOperation(this, "Add profile", "No more hikers to add!");
            } else {
                isAdding = true;
                isEditing = false;
                Hiker[] h = hikersNotInGroup.toArray(Hiker[]::new);
                hikerComponent.loadValues(h);
                hikerComponent.setErrorMessage("");

                dateComponent.setValue(new GregorianCalendar());
                dateComponent.setErrorMessage("");

                noteComponent.setValue("");
                noteComponent.setErrorMessage("");

                hikerComponent.setEnabledInput(true);
                dateComponent.setEnabledInput(true);
                noteComponent.setEnabledInput(true);
                btnProfileSave.setEnabled(true);
            }
        } catch (CustomException ex) {
            ex.printStackTrace();
            Window.unSuccessfulOperation(this, "Add profile", "Unable to add profile!");
        }
    }

    private void btnProfileEditPressed() {
        int[] rows = tblProfiles.getSelectedRows();
        if (rows.length == 0) {
            Window.unSuccessfulOperation(this, "Edit profile error", "No selected profile!");
        } else if (rows.length > 1) {
            Window.unSuccessfulOperation(this, "Edit profile error", "Pick 1 profile you wish to edit!");
        } else {
            Profile selectedProfile = tblModel.getProfile(rows[0]);
            if (selectedProfile != null) {
                boolean gate = true;
                try {
                    HikingGroup currentGroup = hikingGroupComponent.getValue();
                    List<Hiker> hikersNotInGroup = getHikersNotInGroup(currentGroup);
                    Hiker[] h = hikersNotInGroup.toArray(Hiker[]::new);
                    hikerComponent.loadValues(h);
                    hikerComponent.setValue(selectedProfile.getHiker());
                } catch (CustomException ex) {
                    ex.printStackTrace();
                    gate = false;
                    hikerComponent.setErrorMessage("Unable to load hiker!");
                }
                try {
                    dateComponent.setValue(selectedProfile.getDateOfEnrollment());
                } catch (CustomException ex) {
                    ex.printStackTrace();
                    gate = false;
                    dateComponent.setErrorMessage("Unable to load date!");
                }
                try {
                    noteComponent.setValue(selectedProfile.getNote());
                } catch (CustomException ex) {
                    ex.printStackTrace();
                    gate = false;
                    noteComponent.setErrorMessage("Unable to load notes!");
                }
                if (gate) {
                    btnProfileSave.setEnabled(true);
                    hikerComponent.setEnabledInput(true);
                    dateComponent.setEnabledInput(true);
                    noteComponent.setEnabledInput(true);
                    editableProfile = selectedProfile;
                    isAdding = false;
                    isEditing = true;
                }
            } else {
                Window.unSuccessfulOperation(this, "Edit profile error", "Unable to load profile!");
            }
        }
    }

    private void btnProfileRemovePressed() {
        int[] rows = tblProfiles.getSelectedRows();
        if (rows.length == 0) {
            Window.unSuccessfulOperation(this, "Remove profile error", "No selected profile!");
        } else if (rows.length > 1) {
            Window.unSuccessfulOperation(this, "Remove profile error", "Pick 1 profile you wish to remove!");
        } else {
            Profile removedProfile = tblModel.removeProfile(rows[0]);
            if (isEditing) {
                if (editableProfile.equals(removedProfile)) {
                    try {
                        hikerComponent.loadValues(null);
                    } catch (CustomException ex) {
                        ex.printStackTrace();
                    }
                    hikerComponent.setEnabledInput(false);
                    hikerComponent.setErrorMessage("");
                    try {
                        dateComponent.setValue(new GregorianCalendar());
                        dateComponent.setEnabledInput(false);
                        dateComponent.setErrorMessage("");
                    } catch (CustomException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        noteComponent.setValue("");
                        noteComponent.setEnabledInput(false);
                        noteComponent.setErrorMessage("");
                    } catch (CustomException ex) {
                        ex.printStackTrace();
                    }
                    btnProfileSave.setEnabled(false);
                } else {
                    try {
                        hikerComponent.setValue(removedProfile.getHiker());
                    } catch (CustomException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (isAdding) {
                try {
                    hikerComponent.setValue(removedProfile.getHiker());
                } catch (CustomException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void btnProfileSavePressed() {
        boolean gate = true;
        Hiker h = null;
        GregorianCalendar date = null;
        String notes = null;
        try {
            h = hikerComponent.getValue();
        } catch (CustomException ex) {
            gate = false;
            hikerComponent.setErrorMessage(ex.getMessage());
        }
        try {
            date = dateComponent.getValue();
        } catch (CustomException ex) {
            gate = false;
            dateComponent.setErrorMessage(ex.getMessage());
        }
        try {
            notes = noteComponent.getValue();
        } catch (CustomException ex) {
            gate = false;
            noteComponent.setErrorMessage(ex.getMessage());
        }
        try {
            if (gate) {
                HikingGroup g = hikingGroupComponent.getValue();
                Profile p = new Profile(g, h, date, notes);
                if (!isEditing) {
                    tblModel.addProfile(p);
                } else {
                    int row = tblModel.getProfileRow(editableProfile);
                    tblModel.updateProfile(p, row);
                }
                try {
                    hikerComponent.loadValues(null);
                } catch (CustomException ex) {
                    ex.printStackTrace();
                }
                hikerComponent.setEnabledInput(false);
                hikerComponent.setErrorMessage("");
                try {
                    dateComponent.setValue(new GregorianCalendar());
                    dateComponent.setEnabledInput(false);
                    dateComponent.setErrorMessage("");
                } catch (CustomException ex) {
                    ex.printStackTrace();
                }
                try {
                    noteComponent.setValue("");
                    noteComponent.setEnabledInput(false);
                    noteComponent.setErrorMessage("");
                } catch (CustomException ex) {
                    ex.printStackTrace();
                }
                btnProfileSave.setEnabled(false);
                isAdding = false;
                isEditing = false;
            } else {
                Window.unSuccessfulOperation(this, "Save profile error", "Unable to save profile!");
            }
        } catch (CustomException e) {
            Window.unSuccessfulOperation(this, "Save profile error", "Unable to save profile!");
        }
    }

    public void cbGroupPressed() {
        try {
            HikingGroup g = hikingGroupComponent.getValue();
            tblModel.setGroup(g);
            try {
                hikerComponent.loadValues(null);
            } catch (CustomException ex) {
                ex.printStackTrace();
            }
            hikerComponent.setEnabledInput(false);
            hikerComponent.setErrorMessage("");
            try {
                dateComponent.setValue(new GregorianCalendar());
                dateComponent.setEnabledInput(false);
                dateComponent.setErrorMessage("");
            } catch (CustomException ex) {
                ex.printStackTrace();
            }
            try {
                noteComponent.setValue("");
                noteComponent.setEnabledInput(false);
                noteComponent.setErrorMessage("");
            } catch (CustomException ex) {
                ex.printStackTrace();
            }
            btnProfileSave.setEnabled(false);
            isAdding = false;
            isEditing = false;
        } catch (CustomException ex) {
            ex.printStackTrace();
            Window.unSuccessfulOperation(this, "Hiking group error", "Unable to change hiking group!");
        }
    }

    private void btnGroupSavePressed() {
        try {
            if (Window.question(this, "Save configuration", "Do you wish to save current configuration?")) {
                List<Profile> profilesToStore = getProfilesToStore();
                TransferObject response = ControllerSO.getInstance().saveProfiles(profilesToStore);
                if (response.getResponseType() == ResponseType.SUCCESS) {
                    Window.successfulOperation(this, "Successful configuration changes", "Profiles succesfully saved!");
                    ControllerForms.getInstance().closeFrmGroupMemberships();
                } else {
                    Window.unSuccessfulOperation(this, "Unsuccessful configuration changes", response.getException().getMessage());
                }
            }
        } catch (CustomException e) {
            e.printStackTrace();
            Window.unSuccessfulOperation(this, "Unsuccessful configuration changes", "Unable to save changes!");
        }
    }

//============================================================================================================
    private List<Hiker> getHikersNotInGroup(HikingGroup group) {
        List<Hiker> hikersNotInGroup = new LinkedList<>();
        for (Hiker hiker : hikers) {
            boolean isPresent = false;
            for (Profile profile : group.getProfiles()) {
                if (hiker.equals(profile.getHiker())) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                hikersNotInGroup.add(hiker);
            }
        }
        return hikersNotInGroup;
    }

    private List<Profile> getProfilesToStore() {
        List<Profile> p = new LinkedList<>();
        for (HikingGroup group : groups) {
            for (Profile profile : group.getProfiles()) {
                p.add(profile);
            }
        }
        return p;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
