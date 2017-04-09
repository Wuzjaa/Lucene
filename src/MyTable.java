import javax.swing.*;
import javax.swing.table.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MyTable extends AbstractTableModel {
    Object[][] data = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
    String[] columnNames = {"序号","文件路径","分数"};
    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return columnNames.length;
    }
    public String getColumnName(int i){
        return columnNames[i];
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    public void clear(){
        for(int i=0;i<getRowCount();i++)
            for(int j=0;j<getColumnCount();j++)
                setValueAt("",i,j);
    }
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
