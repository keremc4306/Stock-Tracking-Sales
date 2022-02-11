package stockCrudR2;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		super("Welcome to Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 568);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeToProgram = new JLabel("WELCOME TO STOCK TRACKING PROGRAM");
		welcomeToProgram.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeToProgram.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		welcomeToProgram.setBounds(95, 34, 585, 68);
		contentPane.add(welcomeToProgram);
		
		JLabel designedByProgram = new JLabel("Designed by KEREM CANDANGIL Copyright 2022");
		designedByProgram.setHorizontalAlignment(SwingConstants.CENTER);
		designedByProgram.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		designedByProgram.setBounds(95, 454, 585, 41);
		contentPane.add(designedByProgram);
		
		JLabel photo = new JLabel("");
		photo.setBounds(52, 163, 155, 200);
		photo.setIcon(new ImageIcon("E:\\Java_egzersizler\\kodluyoruz\\stockCrudR2\\profile_picture\\photo.jpg"));
		contentPane.add(photo);
		
		JButton btnStockEntry = new JButton("STOCK ENTRY");
		btnStockEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginSc = new Login();
				loginSc.setVisible(true);	
				
			}
		});
		btnStockEntry.setBackground(new Color(72, 209, 204));
		btnStockEntry.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnStockEntry.setBounds(272, 146, 179, 56);
		contentPane.add(btnStockEntry);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("EXIT");
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to "
						+ "exit the program?", "EXIT",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBackground(new Color(255, 235, 205));
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnExit.setBounds(552, 208, 167, 56);
		contentPane.add(btnExit);
		
		JButton btnSalesEntry = new JButton("SALES");
		btnSalesEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesScreen salesSc = new SalesScreen();
				salesSc.setVisible(true);
			}
		});
		btnSalesEntry.setBackground(new Color(221, 160, 221));
		btnSalesEntry.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnSalesEntry.setBounds(272, 268, 179, 56);
		contentPane.add(btnSalesEntry);
		
		validate();
	}
}
