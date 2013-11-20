package frames;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class SeatFrameNew extends JFrame {
	public SeatFrameNew(){
		JTable seatJTable = new JTable();
		seatJTable.setModel(new AbstractTableModel() {
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				JToggleButton seatButton = new JToggleButton(rowIndex+"ее"+columnIndex+"ап");
				return seatButton;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 10;
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 10;
			}
		});
		seatJTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				JToggleButton seat = (JToggleButton)value;
				JPanel seatPanel = new JPanel();
				seatPanel.add(seat);
				return seatPanel;
			}
		});
		TableCellEditor edt = new TableCellEditor() {
			
			@Override
			public boolean stopCellEditing() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean shouldSelectCell(EventObject anEvent) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void removeCellEditorListener(CellEditorListener l) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCellEditable(EventObject anEvent) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void cancelCellEditing() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addCellEditorListener(CellEditorListener l) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value,
					boolean isSelected, int row, int column) {
				// TODO Auto-generated method stub
				JPanel seatPanel = new JPanel();
				JToggleButton seat = (JToggleButton)value;
				seatPanel.add(seat);
				return seatPanel;
			}
		};
		seatJTable.setCellEditor(edt);
	    seatJTable.setRowHeight(60);
		add(seatJTable);
		setVisible(true);
		
	}
}
