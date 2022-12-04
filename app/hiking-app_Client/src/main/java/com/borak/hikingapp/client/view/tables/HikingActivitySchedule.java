package com.borak.hikingapp.client.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.HikingActivity;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Mr. Poyo
 */
public class HikingActivitySchedule extends JScrollPane {

    private List<String> colNames = new LinkedList<>();
    private List<String> rowNames = new LinkedList<>();
    private String[][] dataMatrix;
    private TableModel model;
    private JTable table;

    private List<HikingActivity> mainHikingActivities;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");

    public HikingActivitySchedule(List<HikingActivity> hikingActivities) throws CustomException {
        if (hikingActivities == null) {
            this.mainHikingActivities = new LinkedList<>();
        } else {
            this.mainHikingActivities = hikingActivities;
        }
        if (this.mainHikingActivities.isEmpty()) {
            setVisible(false);
        } else {
            try {
                colNames = getUniqueDatesSorted(this.mainHikingActivities);
                rowNames = getPlaceNamesSorted(this.mainHikingActivities);
                dataMatrix = getDataMatrix(rowNames, colNames, this.mainHikingActivities);
                model = new TableModel();
                table = new JTable(model);
                setViewportView(table);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                resizeTable(table);
                JTable rowTable = new RowNumberTable(table);
                setRowHeaderView(rowTable);
                setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
                setPreferredSize(calculatePreferedSize());
            } catch (NullPointerException e) {
                throw new CustomException(ErrorType.INVALID_DATA_ERROR, "Value constraints not met with table data!", e);
            }
        }
    }

    private List<String> getUniqueDatesSorted(List<HikingActivity> hikingActivities) throws NullPointerException {
        List<GregorianCalendar> list = new LinkedList<>();
        for (HikingActivity hikingActivity : hikingActivities) {
            GregorianCalendar pom = hikingActivity.getDate();
            GregorianCalendar date = new GregorianCalendar(pom.get(Calendar.YEAR), pom.get(Calendar.MONTH), pom.get(Calendar.DAY_OF_MONTH));
            if (!list.contains(date)) {
                list.add(date);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).after(list.get(j))) {
                    GregorianCalendar pom = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, pom);
                }
            }
        }
        List<String> dates = new LinkedList<>();
        for (GregorianCalendar gregorianCalendar : list) {
            dates.add(dateFormater.format(gregorianCalendar.getTime()));
        }
        return dates;
    }

    private List<String> getPlaceNamesSorted(List<HikingActivity> hikingActivities) throws NullPointerException {
        List<String> places = new LinkedList<>();
        for (HikingActivity hikingActivity : hikingActivities) {
            places.add(hikingActivity.getPlace().getName());
        }
        for (int i = 0; i < places.size(); i++) {
            for (int j = i; j < places.size(); j++) {
                if (places.get(i).compareTo(places.get(j)) > 0) {
                    String pom = places.get(i);
                    places.set(i, places.get(j));
                    places.set(j, pom);
                }
            }
        }
        return places;
    }

    private String[][] getDataMatrix(List<String> rowNames, List<String> colNames, List<HikingActivity> mainHikingActivities) {
        String[][] matrix = new String[rowNames.size()][colNames.size()];
        for (int i = 0; i < rowNames.size(); i++) {
            for (int j = 0; j < colNames.size(); j++) {
                matrix[i][j] = "";
            }
        }
        for (int hI = 0; hI < mainHikingActivities.size(); hI++) {
            String date = dateFormater.format(mainHikingActivities.get(hI).getDate().getTime());
            String place = mainHikingActivities.get(hI).getPlace().getName();
            boolean gate = true;
            for (int i = 0; i < rowNames.size() && gate; i++) {
                for (int j = 0; j < colNames.size() && gate; j++) {
                    if (colNames.get(j).equals(date) && rowNames.get(i).equals(place) && isWholeRowEmpty(matrix, i, colNames.size())) {
                        matrix[i][j] = mainHikingActivities.get(hI).getName();
                        gate = false;
                    }
                }
            }

        }
        return matrix;
    }

    private void resizeTable(JTable table) {
        for (int i = 0; i < colNames.size(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setMinWidth(100);
        }
    }

    private Dimension calculatePreferedSize() {
        Dimension d = table.getPreferredSize();
        if (d.width > 700) {
            d.width = 700;
        }
        if(d.width<500){
            d.width=500;
        }
        if (d.height > 300) {
            d.height = 300;
        }
        if(d.height<100){
            d.height=100;
        }
        return d;
    }

    private boolean isWholeRowEmpty(String[][] matrix, int i, int size) {
        for (int k = 0; k < size; k++) {
            if (!matrix[i][k].isEmpty()) {
                return false;
            }
        }
        return true;
    }

//-------------------------------------------------------------------------------------------------    
    private class TableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return rowNames.size();
        }

        @Override
        public int getColumnCount() {
            return colNames.size();
        }

        @Override
        public String getColumnName(int column) {
            return colNames.get(column);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return dataMatrix[rowIndex][columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }
//-------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------

    /*
 *	Use a JTable as a renderer for row numbers of a given main table.
 *  This table must be added to the row header of the scrollpane that
 *  contains the main table.
     */
    private class RowNumberTable extends JTable implements ChangeListener, PropertyChangeListener, TableModelListener {

        private JTable main;

        public RowNumberTable(JTable table) {
            main = table;
            main.addPropertyChangeListener(this);
            main.getModel().addTableModelListener(this);

            setFocusable(false);
            setAutoCreateColumnsFromModel(false);
            setSelectionModel(main.getSelectionModel());

            TableColumn column = new TableColumn();
            column.setHeaderValue(" ");
            addColumn(column);
            column.setCellRenderer(new RowNumberRenderer());

            getColumnModel().getColumn(0).setPreferredWidth(calculateRowWidth());
            setPreferredScrollableViewportSize(getPreferredSize());

        }

        @Override
        public void addNotify() {
            super.addNotify();

            Component c = getParent();

            //  Keep scrolling of the row table in sync with the main table.
            if (c instanceof JViewport) {
                JViewport viewport = (JViewport) c;
                viewport.addChangeListener(this);
            }
        }

        //Delegate method to main table
        @Override
        public int getRowCount() {
            return main.getRowCount();
        }

        @Override
        public int getRowHeight(int row) {
            int rowHeight = main.getRowHeight(row);

            if (rowHeight != super.getRowHeight(row)) {
                super.setRowHeight(row, rowHeight);
            }

            return rowHeight;
        }

        //No model is being used for this table so just use the row number as the value of the cell.
        @Override
        public Object getValueAt(int row, int column) {
            return rowNames.get(row);
        }

        // Don't edit data in the main TableModel by mistake
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        //Do nothing since the table ignores the model
        @Override
        public void setValueAt(Object value, int row, int column) {
        }

        //Implement the ChangeListener
        @Override
        public void stateChanged(ChangeEvent e) {
            //  Keep the scrolling of the row table in sync with main table
            JViewport viewport = (JViewport) e.getSource();
            JScrollPane scrollPane = (JScrollPane) viewport.getParent();
            scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
        }

        //Implement the PropertyChangeListener
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            //  Keep the row table in sync with the main table
            if ("selectionModel".equals(e.getPropertyName())) {
                setSelectionModel(main.getSelectionModel());
            }

            if ("rowHeight".equals(e.getPropertyName())) {
                repaint();
            }

            if ("model".equals(e.getPropertyName())) {
                main.getModel().addTableModelListener(this);
                revalidate();
            }
        }

        //Implement the TableModelListener
        @Override
        public void tableChanged(TableModelEvent e) {
            revalidate();
        }

        //i added this method to format row width. 65/100 is chosen as it appears to fit row names quite well
        private int calculateRowWidth() {
            int max = 0;
            for (String rowName : rowNames) {
                if (rowName.length() > max) {
                    max = rowName.length();
                }
            }
            max *= (getFont().getSize());
            return max * 65 / 100;
        }

        //Attempt to mimic the table header renderer      
        private static class RowNumberRenderer extends DefaultTableCellRenderer {

            public RowNumberRenderer() {
                setHorizontalAlignment(JLabel.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (table != null) {
                    JTableHeader header = table.getTableHeader();
                    if (header != null) {
                        setForeground(header.getForeground());
                        setBackground(header.getBackground());
                        setFont(header.getFont());
                    }
                }
                if (isSelected) {
                    setFont(getFont().deriveFont(Font.BOLD));
                }
                setText((value == null) ? "" : value.toString());
                setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                return this;
            }
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------

}
