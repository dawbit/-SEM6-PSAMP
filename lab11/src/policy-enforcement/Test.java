import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class Test {
    static DefaultTableModel tableModel = new DefaultTableModel();
    static DefaultListModel listModel = new DefaultListModel();

    public static void main(String[] args) throws Exception {
	setupListeners();
	for (int i = 0; i < 10; ++i) {
	    tableModel.fireTableDataChanged();
	    System.gc();
	    Thread.sleep(1000);
	}
    }

    public static void setupListeners() {
	TableModelListener testTableListener = new TableModelListener() {
		public void tableChanged(TableModelEvent e) {
		    System.out.println("tableChanged" + this);
		}
	    };

	tableModel.addTableModelListener(testTableListener);
	tableModel.addTableModelListener(testTableListener);
	tableModel.fireTableDataChanged();

	ListDataListener testListListener = new ListDataListener() {
		public void contentsChanged(ListDataEvent e) {
		    System.out.println("contentsChanged" + this);
		}
		public void intervalAdded(ListDataEvent e) {
		    System.out.println("intervalAdded" + this);
		}
		public void intervalRemoved(ListDataEvent e) {
		    System.out.println("intervalRemoved" + this);
		}
	    };

	listModel.addListDataListener(testListListener);
	listModel.addListDataListener(testListListener);
	listModel.addListDataListener(testListListener);
    }
}
