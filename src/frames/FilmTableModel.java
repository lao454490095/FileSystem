package frames;

import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import movie.Movie;
import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;

public class FilmTableModel implements TableModel {
	private List<Play> plays = new LinkedList<Play>();
	private int playCount ;
	public FilmTableModel(List<Play> playList) {
		this.plays = playList;
	}
	public void setPlay(List<Play> p) {
		this.plays = p;
	}
	private FilmTableModel() {
		
	}
	public int getRowCount(){
		playCount = plays.size();
		return playCount;
	}
	public int getColumnCount(){
		return 7;
	}
	public Class<?> getColumnClass(int columnIndex){
		return String.class;
	}
	public Object getValueAt(int rowIndex,int columnIndex){
		if (playCount==0) {
			return null;
		}
		else {
			return plays.get(rowIndex);
		}
	}
	public boolean isCellEditable(int rowIndex,int columnIndex) {
		return false;
	}
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Ӱ�����";
		}else if (columnIndex ==1) {
			return "��Ӱ����";
		}else if (columnIndex ==2) {
			return "��Ӱ����";
		}else if (columnIndex == 3) {
			return "��ʼʱ��";
		}else if (columnIndex == 4) {
			return "�۸�";
		}else if (columnIndex == 5) {
			return "ʱ��";
		}else if (columnIndex == 6) {
			return "��Ʊ��";
		}
		else {
			return "��Ч��";
		}
	}
	public void addTableModelListener(TableModelListener l) {
		
	}
	public void removeTableModelListener(TableModelListener l) {
		
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Play newPlay = (Play)aValue;
		plays.set(rowIndex, newPlay);
		System.out.print(newPlay.ticketLeft());
	}
}
