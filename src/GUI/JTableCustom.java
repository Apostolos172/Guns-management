package GUI;

import javax.swing.JTable;

public class JTableCustom extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTableCustom(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		customizeTable();
	}

	private void customizeTable() {
		this.getColumnModel().getColumn(2).setPreferredWidth(115);
		this.getColumnModel().getColumn(3).setPreferredWidth(100);
		this.getColumnModel().getColumn(4).setPreferredWidth(100);
	}

	/*
	 * make cells of the table non editable
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	
}
