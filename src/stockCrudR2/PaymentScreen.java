package stockCrudR2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PaymentScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtCardNo;
	private JTextField txtCVC;
	private JTextField txtFullName;
	private JButton btnPay;

	String monthVal = "";
	String yearVal = "";

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentScreen frame = new PaymentScreen(0, 0);
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
	public PaymentScreen(int itemNoVal, int stockValue) {
		super("Payment Screen");
		System.out.println("Updated: " + stockValue);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCreditCardInfo = new JLabel("CREDIT CARD INFORMATION");
		lblCreditCardInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		lblCreditCardInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditCardInfo.setForeground(new Color(255, 0, 0));
		lblCreditCardInfo.setBackground(new Color(255, 255, 255));
		lblCreditCardInfo.setBounds(81, 47, 514, 67);
		contentPane.add(lblCreditCardInfo);

		JLabel lblCardNo = new JLabel("Card number:");
		lblCardNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardNo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCardNo.setBounds(47, 162, 143, 27);
		contentPane.add(lblCardNo);

		txtCardNo = new JTextField();
		txtCardNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9')) || (c == KeyEvent.VK_BACK_SPACE) 
						|| (c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}

				if (txtCardNo.getText().length() >= 19) {
					getToolkit().beep();
					e.consume();
				}

				StringBuilder result = new StringBuilder();
				String input = txtCardNo.getText().toString().replaceAll(" ", "");
				for (int i = 0; i < input.length(); i++) {
					if (i % 4 == 0 && i != 0) {
						result.append(" ");
					}
					result.append(input.charAt(i));
				}

				txtCardNo.setText(result.toString());
			}
		});
		txtCardNo.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		txtCardNo.setBounds(210, 166, 289, 23);
		contentPane.add(txtCardNo);
		txtCardNo.setColumns(10);

		JLabel lblExpiry = new JLabel("Expiry date:");
		lblExpiry.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExpiry.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblExpiry.setBounds(69, 283, 121, 28);
		contentPane.add(lblExpiry);

		JComboBox cbMonth = new JComboBox();
		cbMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbMonth.setModel(new DefaultComboBoxModel(
				new String[] { "", "01", "02", "03", "04", "05", 
						"06", "07", "08", "09", "10", "11", "12" }));
		cbMonth.setBounds(210, 283, 56, 28);
		contentPane.add(cbMonth);

		JComboBox cbYear = new JComboBox();
		cbYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbYear.setModel(new DefaultComboBoxModel(new String[] { "", "23", "24", "25", 
				"26", "27", "28", "29", "30" }));
		cbYear.setBounds(293, 283, 82, 28);
		contentPane.add(cbYear);

		JLabel lblCVC = new JLabel("CVC:");
		lblCVC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCVC.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCVC.setBounds(69, 347, 121, 27);
		contentPane.add(lblCVC);

		txtCVC = new JTextField();
		txtCVC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9')) || (c == KeyEvent.VK_BACK_SPACE) 
						|| (c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}

				if (txtCVC.getText().length() == 3) {
					getToolkit().beep();
					e.consume();
				}

			}
		});
		txtCVC.setBounds(210, 347, 82, 26);
		contentPane.add(txtCVC);
		txtCVC.setColumns(10);

		JLabel lblFullName = new JLabel("Name Surname:");
		lblFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullName.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblFullName.setBounds(47, 218, 143, 27);
		contentPane.add(lblFullName);

		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		txtFullName.setColumns(10);
		txtFullName.setBounds(210, 222, 289, 23);
		contentPane.add(txtFullName);

		btnPay = new JButton("PAY");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				monthVal = cbMonth.getSelectedItem().toString();
				yearVal = cbYear.getSelectedItem().toString();
				if (txtCardNo.getText().equals("") || txtFullName.getText().equals("") 
						|| monthVal.isEmpty() || yearVal.isEmpty() 
						|| txtCVC.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please fill complete information");
				} else {
					try {
						String sql = "UPDATE computers SET num_of_stock = ? WHERE item_no = ?; commit;";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbstok", 
								"root", "KCmerkez97.,");
						con.setAutoCommit(false);
						pst = con.prepareStatement(sql);
						pst.setInt(1, stockValue);
						pst.setInt(2, itemNoVal);
						System.out.println(pst.toString());
						rs = pst.executeQuery();
						con.close();
					} catch (SQLException ex) {

					}
					JOptionPane.showMessageDialog(null, "Successful");

					setVisible(false);
					
				}

			}
		});
		btnPay.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnPay.setBounds(441, 416, 99, 35);
		contentPane.add(btnPay);

	}
}
