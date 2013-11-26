package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.rmi.Naming;
import java.util.List;

import data.movieServiceImpl;
import data.movieService;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import data.movieService;
import movie.Movie;
import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;

public class SellerFrame extends JFrame {
	private List<Hall> halls = new LinkedList<Hall>();
	private List<Movie> movies = new LinkedList<Movie>();
	private List<Play> plays = new LinkedList<Play>();
	private movieService movieservice;
	public SellerFrame() 
	{
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try
				{
					movieservice.getHall(halls);
					movieservice.getMvoie(movies);
					movieservice.writeBack();
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					System.out.println("error on closing window->sellerFrame");
				}
				
			}
		});
		
		//重点
		try
		{
			movieservice=(movieService)Naming.lookup("rmi://110.64.86.190:8000/TicketManager");
			List<Movie> temp=movieservice.distributeMovie();
			//System.out.println(temp);
			List<Hall> temp2=movieservice.distributeHall();

			movies=temp;
			halls=temp2;
		}
		catch(Exception e)
		{
			System.err.println(e.toString());
		}
		/*try 
		{
			MyIO.inputHalls(halls);
		}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			MyIO.inputMovies(movies);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		Operation.sort(0, halls, plays);
		FilmTableModel filmTableModel = new FilmTableModel(plays);
		final JTable filmTable = new JTable();
		filmTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		filmTable.setRowSelectionAllowed(true);
		filmTable.setModel(filmTableModel);
		filmTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				JLabel text = new JLabel();
				text.setBackground(Color.white);
				text.setOpaque(true);
				if (isSelected) {
					text.setBackground(Color.cyan);
				}
				if (value == null) {
					return text;
				}
				Play thisPlay = (Play)value;
				if (column == 0) {
					text.setText(thisPlay.hallNumber+1+"号影厅");
				}else if (column ==1) {
					text.setText(thisPlay.movie.name);
				}else if (column ==2) {
					text.setText(thisPlay.movie.tap);
				}else if (column == 3){
					SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日hh:mm");
					String d = f.format(thisPlay.calendar.getTime());
					text.setText(d);
				}else if (column == 4) {
					text.setText(thisPlay.movie.price+"      元");
				}else if (column == 5) {
					text.setText(thisPlay.ticketLeft()+"");
				}
				return text;
			}
		});
		
		JScrollPane filmScrollPane = new JScrollPane(filmTable);
		filmTable.setPreferredScrollableViewportSize(new Dimension(500,200));
		
		JPanel sessionPagePanel =new JPanel();
		sessionPagePanel.setLayout(new BorderLayout());
		final JComboBox sortBox = new JComboBox();
		sortBox.addItem("按影厅排序");
		sortBox.addItem("按时间排序");
		sortBox.addItem("按名称排序");
		sortBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				plays.removeAll(plays);
				Operation.sort(sortBox.getSelectedIndex(), halls, plays);
				filmTable.updateUI();
			}
		});
		sessionPagePanel.add(sortBox,BorderLayout.NORTH);
		
		sessionPagePanel.add(filmScrollPane,BorderLayout.CENTER);
		
		JButton checkSeatButton = new JButton("查看座位表");
        checkSeatButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (filmTable.getSelectedRow()!=-1) {
					SeatFrame seatFrame = new SeatFrame(plays.get(filmTable.getSelectedRow()),filmTable);
					seatFrame.setLocationRelativeTo(null);
					seatFrame.setVisible(true);
				}
			}
		});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(checkSeatButton);
		
		sessionPagePanel.add(buttonsPanel,BorderLayout.SOUTH);
		add(sessionPagePanel);
	}
}
