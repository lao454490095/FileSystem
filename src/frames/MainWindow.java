package frames;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import encode.Encode;


public class MainWindow extends JFrame {
	private JFrame adminFrame;
	private JFrame sellerFrame;
	private static JRadioButton admin = new JRadioButton("管理员");
	private static JRadioButton seller = new JRadioButton("售票员");
	private static ButtonGroup buttons = new ButtonGroup();
	private static JButton confirm = new JButton("确认登陆");
	private static JTextField name = new JTextField("请输入用户名");
	private static JPasswordField password = new JPasswordField("111111");
	private String adminName = new String("admin");
	private String adminPassword =Encode.generatePassword("admin");
	private String sellerName = new String("seller");
	private String sellerPassword =Encode.generatePassword("seller");
	public String getName(){
		return name.getText();
	}
	public MainWindow(){
		setLayout(new FlowLayout());
		name.setColumns(20);
		password.setColumns(20);
		name.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				name.setSelectionStart(0);
				name.setSelectionEnd(19);

			}
		});
		password.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				password.setSelectionStart(0);
				password.setSelectionEnd(19);
			}
		});
		buttons.add(admin);
		buttons.add(seller);
		add(new JLabel("用户名："));
		add(name);
		add(new JLabel("    密码："));
		add(password);
		add(admin);
		add(seller);
		add(confirm);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.isSelected()) {
					if (adminName.equals(name.getText())&&Encode.validatePassword(adminPassword, String.valueOf(password.getPassword()))) {
						adminFrame = new AdminFrame(name.getText());
						adminFrame.setSize(800,300);
						adminFrame.setLocationRelativeTo(null);
						adminFrame.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					}
				}
				if (seller.isSelected()) {
					if (sellerName.equals(name.getText())&&Encode.validatePassword(sellerPassword, String.valueOf(password.getPassword()))) {
						
						sellerFrame = new SellerFrame();
						sellerFrame.setVisible(true);
						sellerFrame.setSize(800,300);
						sellerFrame.setLocationRelativeTo(null);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					}
				}
			}
		});
//		add(adminButton);
//		add(sellerButton);
//		adminButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				adminFrame.setVisible(true);
//				dispose();
//			}
//		});
//		sellerButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				sellerFrame.setVisible(true);
//				dispose();
//			}
//		});
		


		
	}
	public static void main(String[] args){
		MainWindow mainWindow = new MainWindow();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setTitle("电影票务管理系统");
		mainWindow.setSize(300,150);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);
	}
}
