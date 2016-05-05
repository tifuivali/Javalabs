package Models;

import java.util.List;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableFileModel
  implements TableModel
{
  private String[] collums;
  private List<String[]> rows;
  private Vector<TableModelListener> listeners = new Vector();
  
  public TableFileModel(String[] collums, List<String[]> rows)
  {
    this.collums = collums;
    this.rows = rows;
  }
  
  public void addRow(String[] row)
  {
    this.rows.add(row);
  }
  
  @Override
  public int getRowCount()
  {
    return this.rows.size();
  }
  
  @Override
  public int getColumnCount()
  {
    return this.collums.length;
  }
  
  @Override
  public String getColumnName(int columnIndex)
  {
    return this.collums[columnIndex];
  }
  
  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    return this.collums[columnIndex].getClass();
  }
  
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex)
  {
    return false;
  }
  
  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    return ((String[])this.rows.get(rowIndex))[columnIndex];
  }
  
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {
    ((String[])this.rows.get(rowIndex))[columnIndex] = aValue.toString();
  }
  
  @Override
  public void addTableModelListener(TableModelListener l)
  {
    boolean add = this.listeners.add(l);
  }
  
  @Override
  public void removeTableModelListener(TableModelListener l)
  {
    this.listeners.remove(l);
  }
}
