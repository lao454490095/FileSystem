package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.jar.Attributes.Name;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import movie.Movie;
import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;


public class AdminFrame extends JFrame {
	private List<Movie> movies = new LinkedList<Movie>();
	private List<Hall> halls = new LinkedList<Hall>();
	private List<Play> plays = new LinkedList<Play>();
	private JTabbedPane tabbedPane = new JTabbedPane();
	private static String adminName = new String();
	public AdminFrame(String adminName) {
		this.adminName = adminName;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try
				{
					//MyIO.outputHalls(halls);
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try
				{
					//MyIO.outputMovies(movies);
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// TODO Auto-generated constructor stub
//		try {
//			System.out.println("i am in!11111");
//			MyIO.inputHalls(halls);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			System.out.println("i am in!22222");
//			MyIO.inputMovies(movies);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream( new FileInputStream("halls.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			halls= (List<Hall>)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(halls.size()+"sdfffff");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSessionPage();
		setFilmPage();
//		setAccountPage();
		add(tabbedPane);
	}
	public void setFilmPage() {
		JPanel filmPanel = new JPanel();
		filmPanel.setLayout(null);
		final JList filmJList = new JList();
		final DefaultListModel filmModel = new DefaultListModel();
		filmJList.setModel(filmModel);
		filmJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (int i = 0; i < movies.size(); i++) {
			filmModel.addElement(movies.get(i).name);
		}
		JScrollPane filmScrollPane = new JScrollPane(filmJList);
		filmScrollPane.setBounds(50, 20, 150, 200);
		filmScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		filmPanel.add(filmScrollPane);
		
		final JPanel dataJPanel = new JPanel();
		dataJPanel.setBounds(250, 20, 300, 150);
		dataJPanel.setBackground(Color.white);
		dataJPanel.setBorder(new LineBorder(Color.gray));
		dataJPanel.setOpaque(true);
		filmJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) {
					return;
				}
				if(!filmJList.isSelectionEmpty()||filmJList.getMaxSelectionIndex()!=-1)
				setDataPanel(dataJPanel,filmJList.getSelectedIndex());
			}
		});
		JButton addFilmButton = new JButton("增加电影");
		JButton deleteFilmButton = new JButton("删除电影");
		JPanel buttonJPanel = new JPanel(new FlowLayout());
		buttonJPanel.add(addFilmButton);
		buttonJPanel.add(deleteFilmButton);
		buttonJPanel.setBounds(200, 180, 200, 50);
		
		filmPanel.add(dataJPanel);
		filmPanel.add(buttonJPanel);
		setAddFilmFrame(addFilmButton,filmModel);
		deleteFilmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!filmJList.isSelectionEmpty()) {
					Operation.removeMovie(movies, filmJList.getSelectedIndex());
					filmModel.remove(filmJList.getSelectedIndex());
				}
			}
		});
		tabbedPane.addTab("电影管理",filmPanel);
	}
	public void setDataPanel(JPanel dataJPanel,int index) {
		dataJPanel.removeAll();
		String[] dataStrings = Operation.getMessage(movies, index);
		JLabel nameJLabel = new JLabel(dataStrings[0]);
		JLabel tapJLabel = new JLabel(dataStrings[1]);
		JLabel priceJLabel = new JLabel(dataStrings[2]);
		JLabel timeJLabel = new JLabel(dataStrings[3]);
		dataJPanel.setLayout(new GridLayout(2,2));
		dataJPanel.add(nameJLabel);
		dataJPanel.add(tapJLabel);
		dataJPanel.add(priceJLabel);
		dataJPanel.add(timeJLabel);

		dataJPanel.updateUI();
	}
	public void setSessionPage() {
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
				}else if (column ==4) {
					text.setText(thisPlay.movie.time+"");
				}else if (column == 5) {
					text.setText(thisPlay.movie.price+"元");
				}else if (column == 6) {
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
		
//		JButton editFilmButton = new JButton("修改电影");
		JButton addSessionButton = new JButton("添加场次");
		JButton deleteSessionButton = new JButton("删除选中场次");
		deleteSessionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if (filmTable.getSelectedRow()!=-1) {
					Operation.removePlay(halls, (Play)filmTable.getValueAt(filmTable.getSelectedRow(),0));
					plays.removeAll(plays);
					Operation.sort(sortBox.getSelectedIndex(), halls, plays);
					filmTable.setModel(new FilmTableModel(plays));
				}
			}
		});
		JButton checkSeatButton = new JButton("查看座位表");
		setAddSessionFrame(addSessionButton,filmTable,sortBox.getSelectedIndex());
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
		buttonsPanel.add(addSessionButton);
		buttonsPanel.add(deleteSessionButton);
		buttonsPanel.add(checkSeatButton);
		
		sessionPagePanel.add(buttonsPanel,BorderLayout.SOUTH);
		tabbedPane.addTab("场次页", sessionPagePanel);
	}
	
	public void setAddFilmFrame(JButton addButton,final DefaultListModel listModel) {
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					final JFrame addFrame = new JFrame();
					addFrame.setLayout(new FlowLayout());
					addFrame.setSize(500,120);
					final JTextField name = new JTextField(10);
					name.setText("请输入电影名");
					name.setSelectionStart(0);
					final JComboBox kind = new JComboBox();
					kind.addItem("悬疑");
					kind.addItem("喜剧");
					kind.addItem("动作");
					kind.addItem("动画");
					kind.addItem("纪录片");
					kind.addItem("恐怖片");
					kind.addItem("科幻片");
					final JSpinner length = new JSpinner(new SpinnerNumberModel(60, 1, 1000, 1));
					length.setEditor(new JSpinner.NumberEditor(length,"#分钟"));
					final JFormattedTextField priceField = new JFormattedTextField(new DecimalFormat("#0"));
					priceField.setText(0+"");
					priceField.setPreferredSize(new Dimension(100,20));
			        priceField.addKeyListener(new java.awt.event.KeyAdapter() {  
			            public void keyReleased(java.awt.event.KeyEvent evt) {  
			                String old = priceField.getText();  
			                JFormattedTextField.AbstractFormatter formatter = priceField.getFormatter();  
			                if (!old.equals("")) {   
			                    if (formatter != null) {  
			                        String str = priceField.getText();  
			                        try {  
			                            long page = (Long) formatter.stringToValue(str);  
			                            priceField.setText(page + "");  
			                        } catch (ParseException pe) {  
			                            priceField.setText("1");//解析异常直接将文本框中值设置为1  
			                        }  
			                    }  
			                }  
			                else {
								priceField.setText(0+"");
							}
			            }  
			        });
			        
					JPanel selectPanel = new JPanel();
					JButton confirmButton = new JButton("确认");
					JButton backButton = new JButton("返回");
					selectPanel.add(confirmButton);
					selectPanel.add(backButton);
					selectPanel.setPreferredSize(new Dimension(200,100));
					confirmButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							Operation.addMovie(movies,name.getText(),Integer.parseInt(priceField.getText()),(Integer)length.getValue(),(String)kind.getSelectedItem());
							listModel.removeAllElements();
							for (int i = 0; i < movies.size(); i++) {
								listModel.addElement(movies.get(i).name);
							}
							addFrame.dispose();
						}
					});
					backButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							addFrame.dispose();
						}
					});
					addFrame.add(name);
					addFrame.add(kind);
					addFrame.add(length);
					addFrame.add(new JLabel("价格："));
					addFrame.add(priceField);
					addFrame.add(selectPanel);
					addFrame.setVisible(true);
					addFrame.setLocationRelativeTo(null);
			}
		});
	}
		
	public void setAddSessionFrame(JButton sessionButton,final JTable table,final int sortIndex) {
		sessionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFrame addFrame = new JFrame();
				addFrame.setSize(400,200);
				addFrame.setLocationRelativeTo(null);
				
				JButton confirmButton = new JButton("确认");
				final JComboBox filmName = new JComboBox();
				for (int i = 0; i < movies.size(); i++) {
					filmName.addItem(" "+movies.get(i).name);
				}
				final JComboBox place = new JComboBox();
				System.out.println(halls.size());
				for (int i = 0; i < halls.size(); i++) {
					place.addItem(i+1+"号影厅");
					System.out.println(halls.get(i).playList.get(0).movie.name);
					System.out.println("sbsbsb");
					
				}
				JPanel timePanel = new JPanel();
				final Calendar timeCalendar = Calendar.getInstance();
				final JSpinner yearSpinner = new JSpinner();
				yearSpinner.setModel(new SpinnerNumberModel(timeCalendar.get(Calendar.YEAR), 2000, 2100, 1));
				yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner, "0000年"));
				final JSpinner monthSpinner = new JSpinner(new SpinnerNumberModel(timeCalendar.get(Calendar.MONTH)+1, 1, 12, 1));
				monthSpinner.setEditor(new JSpinner.NumberEditor(monthSpinner, "00月"));
				final JSpinner daySpinner = new JSpinner(new SpinnerNumberModel(timeCalendar.get(Calendar.DAY_OF_MONTH), 1,timeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
				daySpinner.setEditor(new JSpinner.NumberEditor(daySpinner,"00日"));
				final JSpinner hourSpinner =new JSpinner (new SpinnerNumberModel(timeCalendar.get(Calendar.HOUR_OF_DAY), 0, 23, 1));
				hourSpinner.setEditor(new JSpinner.NumberEditor(hourSpinner,"00时"));
				final JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(timeCalendar.get(Calendar.MINUTE), 0, 59, 1));
				minuteSpinner.setEditor(new JSpinner.NumberEditor(minuteSpinner,"00分"));
				monthSpinner.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						JSpinner a = (JSpinner)e.getSource();
						int month = (Integer)a.getValue();
						timeCalendar.set(Calendar.MONTH, month-1);
						daySpinner.setModel(new SpinnerNumberModel(timeCalendar.get(Calendar.DAY_OF_MONTH), 1,timeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
					}
				});
				confirmButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (movies.size()!=0) {
							timeCalendar.set((Integer)yearSpinner.getValue(),(Integer)monthSpinner.getValue()-1,(Integer)daySpinner.getValue(), (Integer)hourSpinner.getValue(), (Integer)minuteSpinner.getValue());
							
							Operation.addPlay(halls, place.getSelectedIndex(),timeCalendar,movies.get(filmName.getSelectedIndex()));
							plays.removeAll(plays);
							Operation.sort(sortIndex, halls, plays);
							table.updateUI();
							addFrame.dispose();
						}
						else {
							addFrame.dispose();
						}
					}
				});
				timePanel.add(yearSpinner);
				timePanel.add(monthSpinner);
				timePanel.add(daySpinner);
				timePanel.add(hourSpinner);
				timePanel.add(minuteSpinner);
				addFrame.setLayout(new GridLayout(3, 1));
				addFrame.add(timePanel);
				JPanel filmJPanel = new JPanel();
				JPanel confirmJPanel = new JPanel();
				confirmJPanel.setLayout(null);
				confirmButton.setSize(100,30);
				confirmButton.setLocation(150,0);
				confirmJPanel.add(confirmButton);
				filmJPanel.add(filmName);
				filmJPanel.add(place);
				addFrame.add(filmJPanel);
				addFrame.add(confirmJPanel);
				addFrame.setVisible(true);
				
			}
		});
	}

//	public void setAccountPage() {
//		JPanel account = new JPanel();
//		account.setLayout(new BorderLayout());
//		JLabel userName = new JLabel("                登陆用户:"+adminName);
//		account.add(userName,BorderLayout.CENTER);
//		JButton addAdminButton = new JButton("添加账号");
//		JButton deleteAdminButton = new JButton("删除账号");
//		JPanel buttons = new JPanel();
//		buttons.setLayout(new FlowLayout());
//		buttons.add(addAdminButton);
//		buttons.add(deleteAdminButton);
//		account.add(buttons,BorderLayout.SOUTH);
//		tabbedPane.addTab("管理账户", account);
//	}
}
