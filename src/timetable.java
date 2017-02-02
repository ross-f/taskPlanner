import javax.swing.table.DefaultTableModel;

/**
 * Created by ross on 4/11/16.
 * Should be used as part of taskPlanner
 */
class timetable extends DefaultTableModel {
    private String[] dayNames;
    private Object[][] timetableData;

    timetable(String[] dayNames, Object[][] timetableData) {
        this.dayNames = dayNames;
        this.timetableData = timetableData;
    }

    @Override
    public int getRowCount() {
        return timetableData.length;
    }

    @Override
    public int getColumnCount() {
        return dayNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return dayNames[i];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return timetableData[row][col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return row > 1;
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        timetableData[row][col] = o;
        fireTableCellUpdated(row, col);
    }

    public int getDayCount() { return getColumnCount(); }
}