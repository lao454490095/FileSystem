package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import movie.Movie;
import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;

public class SeatFrame extends JFrame {
	private JToggleButton [][] seatButtons = new JToggleButton[10][10];
	public SeatFrame(){};
	public SeatFrame(final Play play,final JTable table){
		setLayout(new BorderLayout());
		JPanel seatPanel = new JPanel();
		seatPanel.setLayout(new GridLayout(10,10));
		JPanel selectPanel = new JPanel();
		JButton confirmButton = new JButton("确认订座");
		JButton backButton = new JButton("返回");
		selectPanel.add(confirmButton);
		selectPanel.add(backButton);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				seatButtons[i][j] = new JToggleButton((i+1)+"排"+(j+1)+"列");
				seatButtons[i][j].setOpaque(true);
				if (play.tickets[i][j]) {
					seatButtons[i][j].setBackground(Color.YELLOW);
					seatButtons[i][j].setEnabled(false);
				}
				seatPanel.add(seatButtons[i][j]);
			}
		}
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < seatButtons.length; i++) {
					for (int j = 0; j < seatButtons[0].length; j++) {
						if (seatButtons[i][j].isSelected()) {
							seatButtons[i][j].setSelected(false);
							seatButtons[i][j].setBackground(Color.YELLOW);
							seatButtons[i][j].setEnabled(false);
							play.ticketSell(i, j);
					
						}
					}
				}
				table.setValueAt(play, table.getSelectedRow(), 5);
				table.updateUI();
			}
		});
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		seatPanel.setPreferredSize(new Dimension(10*70,10*30));
		JScrollPane seatScrollPane = new JScrollPane(seatPanel);
		setSize(900,400);
		setResizable(false);
		add(seatScrollPane,BorderLayout.CENTER);
		add(selectPanel,BorderLayout.NORTH);
	}
}
