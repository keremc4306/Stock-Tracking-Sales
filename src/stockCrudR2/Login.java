package stockCrudR2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 543);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User name:");
		lblUserName.setBounds(112, 108, 109, 37);
		lblUserName.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		contentPane.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(267, 115, 160, 30);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(112, 198, 109, 37);
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(267, 204, 160, 31);
		contentPane.add(pass);
		
		JCheckBox chboxShowPwd = new JCheckBox("Show password");
		chboxShowPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chboxShowPwd.isSelected()) {
					pass.setEchoChar((char)0);
				} else {
					pass.setEchoChar('*');
				}
			}
		});
		chboxShowPwd.setForeground(new Color(255, 0, 0));
		chboxShowPwd.setBounds(468, 201, 188, 30);
		chboxShowPwd.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(chboxShowPwd);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = "keremooo1453";
				String password = "Krm31206";
				
				if (txtUserName.getText().equals(userName) && pass.getText().equals(password)) {
					JOptionPane.showMessageDialog(null, "Login successful");
					StockScreen stockSc = new StockScreen();
					stockSc.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Try again !");
				}
				
			}
		});
		btnLogin.setBackground(new Color(50, 205, 50));
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		btnLogin.setBounds(112, 307, 121, 37);
		contentPane.add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText("");
				pass.setText("");
			}
		});
		btnClear.setBackground(new Color(253, 245, 230));
		btnClear.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		btnClear.setBounds(286, 307, 121, 37);
		contentPane.add(btnClear);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("CANCEL");
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to "
						+ "return to the main menu?", "CANCEL",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					MainScreen ms = new MainScreen();
					ms.setVisible(true);
				}
			}
		});
		btnCancel.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		btnCancel.setBackground(new Color(240, 128, 128));
		btnCancel.setBounds(468, 307, 121, 37);
		contentPane.add(btnCancel);
		
		JLabel lblWelcome = new JLabel("--- Welcome to Stock Screen ---");
		lblWelcome.setForeground(new Color(255, 0, 0));
		lblWelcome.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(112, 38, 509, 37);
		contentPane.add(lblWelcome);
	}
}
