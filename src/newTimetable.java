import javax.swing.table.DefaultTableModel;

/**
 * Created by ross on 4/11/16.
 * Should be used as part of taskPlanner
 */
class simpleTable extends DefaultTableModel {
    private String[] colNames;
    private Object[][] tableData;

    public simpleTable(String[] colNames, Object[][] tableData) {
        this.colNames = colNames;
        this.tableData = tableData;
    }

    @Override
    public int getRowCount() {
        return tableData.length;
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return colNames[i];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return tableData[row][col];
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
        tableData[row][col] = o;
        fireTableCellUpdated(row,col);
    }
}
