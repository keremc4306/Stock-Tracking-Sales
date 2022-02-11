package stockCrudR2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class StockScreen extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtPrice;
	private JTextField txtNumOfStock;
	private JTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnRetrieve;
	private JScrollPane scrollPane;
	DefaultTableModel model;

	String brandValue = "";
	String proValue = "";
	String ramValue = "";
	String ssdValue = "";

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField txtItemNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockScreen frame = new StockScreen();
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
	public StockScreen() {
		super("Stock Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1367, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1456, 808);
		panel.setBackground(new Color(135, 206, 250));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel brand = new JLabel("Brand");
		brand.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		brand.setBounds(156, 47, 53, 34);
		panel.add(brand);

		JLabel processor = new JLabel("Processor");
		processor.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		processor.setBounds(306, 51, 88, 26);
		panel.add(processor);

		JLabel ram = new JLabel("RAM (GB)");
		ram.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		ram.setBounds(482, 47, 88, 32);
		panel.add(ram);

		JLabel ssd = new JLabel("SSD (GB)");
		ssd.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		ssd.setBounds(629, 48, 98, 32);
		panel.add(ssd);

		JLabel price = new JLabel("Price (TL)");
		price.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		price.setBounds(782, 53, 88, 24);
		panel.add(price);

		JLabel numberOfStock = new JLabel("Number of stock:");
		numberOfStock.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		numberOfStock.setBounds(896, 437, 171, 24);
		panel.add(numberOfStock);

		JLabel image = new JLabel("");
		image.setBounds(902, 290, 284, 121);
		panel.add(image);

		JComboBox comboBoxBrand = new JComboBox();
		comboBoxBrand.setModel(new DefaultComboBoxModel(new String[] {"All", "Apple", "Acer", 
				"Asus", "HP"}));
		comboBoxBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//comboBoxBrand.addItem("Selection");
		comboBoxBrand.setBounds(136, 101, 105, 23);
		panel.add(comboBoxBrand);

		JComboBox comboBoxProcessor = new JComboBox();
		comboBoxProcessor.setModel(new DefaultComboBoxModel(new String[] {"All", 
				"AMD Athlon", "AMD Ryzen 5", "AMD Ryzen 7", "Intel Celeron", 
				"Intel i3", "Intel i5", "Intel i7"}));
		
		comboBoxProcessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//comboBoxProcessor.addItem("Selection");
		comboBoxProcessor.setBounds(289, 101, 114, 23);
		panel.add(comboBoxProcessor);

		JComboBox comboBoxRam = new JComboBox();
		comboBoxRam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxRam.addItem("1");
		comboBoxRam.addItem("2");
		comboBoxRam.addItem("4");
		comboBoxRam.addItem("8");
		comboBoxRam.addItem("16");
		comboBoxRam.addItem("32");
		comboBoxRam.addItem("64");
		comboBoxRam.setBounds(465, 101, 114, 23);
		panel.add(comboBoxRam);

		JComboBox comboBoxSsd = new JComboBox();
		comboBoxSsd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSsd.addItem("128");
		comboBoxSsd.addItem("256");
		comboBoxSsd.addItem("480");
		comboBoxSsd.addItem("512");
		comboBoxSsd.addItem("1024");
		comboBoxSsd.setBounds(629, 101, 90, 23);
		panel.add(comboBoxSsd);

		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPrice.setColumns(10);
		txtPrice.setBounds(782, 105, 88, 19);
		panel.add(txtPrice);

		txtNumOfStock = new JTextField();
		txtNumOfStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumOfStock.setColumns(10);
		txtNumOfStock.setBounds(1088, 443, 98, 19);
		panel.add(txtNumOfStock);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(102, 204, 255));
		scrollPane.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane.setBounds(136, 172, 662, 433);
		panel.add(scrollPane);

		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setGridColor(new Color(255, 255, 153));
		table.setSelectionBackground(new Color(255, 0, 51));
		table.setRowHeight(20);
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = table.getSelectedRow();
				txtItemNo.setText(model.getValueAt(rowNo, 0).toString());
				comboBoxBrand.getSelectedItem().toString();
				comboBoxProcessor.getSelectedItem().toString();
				comboBoxRam.getSelectedItem().toString();
				comboBoxSsd.getSelectedItem().toString();
				txtPrice.setText(model.getValueAt(rowNo, 5).toString());
				txtNumOfStock.setText(model.getValueAt(rowNo, 6).toString());
				
				String brandLogo = table.getValueAt(rowNo, 1).toString();
				
				if (brandLogo.equals("Apple")) {
					ImageIcon i1 = new ImageIcon("E:\\Java_egzersizler\\kodluyoruz\\stockCrudR2\\image_icons\\Apple.jpg");
					image.setIcon(i1);
				}
				
				else if (brandLogo.equals("Acer")) {
					ImageIcon i2 = new ImageIcon("E:\\Java_egzersizler\\kodluyoruz\\stockCrudR2\\image_icons\\Acer.jpg");
					image.setIcon(i2);
				}
				
				else if (brandLogo.equals("Asus")) {
					ImageIcon i3 = new ImageIcon("E:\\Java_egzersizler\\kodluyoruz\\stockCrudR2\\image_icons\\Asus.jpg");
					image.setIcon(i3);
				}
				
				else if (brandLogo.equals("HP")) {
					ImageIcon i4 = new ImageIcon("E:\\Java_egzersizler\\kodluyoruz\\stockCrudR2\\image_icons\\HP.jpg");
					image.setIcon(i4);
				}
		
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(new Color(255, 255, 153));
		model = new DefaultTableModel();
		Object[] column = { "Item No", "Brand", "Processor", "RAM (GB)", "SSD (GB)", 
				"Price (TL)", "Num. of stock" };
		//final Object[] row = new Object[7];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "INSERT INTO computers (item_no, brand, processor, "
							+ "ram, ssd, price, num_of_stock) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbstok", 
							"root", "KCmerkez97.,");
					brandValue = comboBoxBrand.getSelectedItem().toString();
					proValue = comboBoxProcessor.getSelectedItem().toString();
					ramValue = comboBoxRam.getSelectedItem().toString();
					ssdValue = comboBoxSsd.getSelectedItem().toString();

					if (txtItemNo.getText().equals("") || brandValue.isEmpty() 
							|| proValue.isEmpty() || ramValue.isEmpty() || ssdValue.isEmpty() 
							|| txtPrice.getText().equals("") 
							|| txtNumOfStock.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill complete information");
					} else {
						pst = con.prepareStatement(sql);
						pst.setString(1, txtItemNo.getText());
						pst.setString(2, brandValue);
						pst.setString(3, proValue);
						pst.setString(4, ramValue);
						pst.setString(5, ssdValue);
						pst.setString(6, txtPrice.getText());
						pst.setString(7, txtNumOfStock.getText());
						pst.executeUpdate();

						model.setRowCount(0);
						String sql3 = "SELECT * FROM computers";
						pst = con.prepareStatement(sql3);
						rs = pst.executeQuery();

						while (rs.next()) {
							String itemNo = String.valueOf(rs.getInt("item_no"));
							String brand = rs.getString("brand");
							String processor = rs.getString("processor");
							String ram = String.valueOf(rs.getInt("ram"));
							String ssd = String.valueOf(rs.getInt("ssd"));
							String price = String.valueOf(rs.getInt("price"));
							String numberOfStock = String.valueOf(rs.getInt("num_of_stock"));

							String tbData[] = { itemNo, brand, processor, 
									ram, ssd, price, numberOfStock };
							model = (DefaultTableModel) table.getModel();

							model.addRow(tbData);

						}
						con.close();

						txtPrice.setText("");
						txtNumOfStock.setText("");
						txtItemNo.setText("");
						JOptionPane.showMessageDialog(null, "Saved successfully");
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnAdd.setBounds(136, 637, 114, 30);
		panel.add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbstok", 
							"root", "KCmerkez97.,");
					brandValue = comboBoxBrand.getSelectedItem().toString();
					proValue = comboBoxProcessor.getSelectedItem().toString();
					ramValue = comboBoxRam.getSelectedItem().toString();
					ssdValue = comboBoxSsd.getSelectedItem().toString();
					if (txtItemNo.getText().equals("") || brandValue.isEmpty() 
							|| proValue.isEmpty() || ramValue.isEmpty() 
							|| ssdValue.isEmpty() || txtPrice.getText().equals("") 
							|| txtNumOfStock.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill complete information");
					} else {
						String sql2 = "UPDATE computers SET brand = ?, "
								+ "processor = ?, ram = ?, ssd = ?, price = ?, "
								+ "num_of_stock = ? WHERE item_no = ?";

						pst = con.prepareStatement(sql2);
						pst.setString(7, txtItemNo.getText());
						pst.setString(1, brandValue);
						pst.setString(2, proValue);
						pst.setString(3, ramValue);
						pst.setString(4, ssdValue);
						pst.setString(5, txtPrice.getText());
						pst.setString(6, txtNumOfStock.getText());

						pst.executeUpdate();

						model.setRowCount(0);
						String sql3 = "SELECT * FROM computers";
						pst = con.prepareStatement(sql3);
						rs = pst.executeQuery();

						while (rs.next()) {
							String itemNo = String.valueOf(rs.getInt("item_no"));
							String brand = rs.getString("brand");
							String processor = rs.getString("processor");
							String ram = String.valueOf(rs.getInt("ram"));
							String ssd = String.valueOf(rs.getInt("ssd"));
							String price = String.valueOf(rs.getInt("price"));
							String numberOfStock = String.valueOf(rs.getInt("num_of_stock"));

							String tbData[] = { itemNo, brand, processor, ram, 
									ssd, price, numberOfStock };
							model = (DefaultTableModel) table.getModel();

							model.addRow(tbData);

						}
						con.close();

						txtItemNo.setText("");
						txtPrice.setText("");
						txtNumOfStock.setText("");
						JOptionPane.showMessageDialog(null, "Updated successfully");
					}
				}

				catch (SQLException ex) {

				}
			}
		});
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnUpdate.setBounds(287, 637, 114, 30);
		panel.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				try {

					if (i >= 0) {
						String sql = "DELETE FROM computers WHERE item_no = ?";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbstok"
								,"root", "KCmerkez97.,");
						pst = con.prepareStatement(sql);
						pst.setString(1, txtItemNo.getText());
						pst.executeUpdate();
						model.setRowCount(0);
						String sql3 = "SELECT * FROM computers";
						pst = con.prepareStatement(sql3);
						rs = pst.executeQuery();

						while (rs.next()) {
							String itemNo = String.valueOf(rs.getInt("item_no"));
							String brand = rs.getString("brand");
							String processor = rs.getString("processor");
							String ram = String.valueOf(rs.getInt("ram"));
							String ssd = String.valueOf(rs.getInt("ssd"));
							String price = String.valueOf(rs.getInt("price"));
							String numberOfStock = String.valueOf(rs.getInt("num_of_stock"));

							String tbData[] = { itemNo, brand, processor, ram, 
									ssd, price, numberOfStock };
							model = (DefaultTableModel) table.getModel();

							model.addRow(tbData);

						}
						con.close();
						JOptionPane.showMessageDialog(null, "Deleted successfully");
					} else {
						JOptionPane.showMessageDialog(null, "Please select a row");
					}
				} catch (SQLException ex) {

				}
			}
		});
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnDelete.setBounds(438, 637, 130, 30);
		panel.add(btnDelete);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtItemNo.setText("");
				txtPrice.setText("");
				txtNumOfStock.setText("");
			}
		});
		btnClear.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnClear.setBounds(629, 637, 130, 30);
		panel.add(btnClear);

		btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.setRowCount(0);
					String sql = "SELECT * FROM computers";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbstok", 
							"root", "KCmerkez97.,");
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();

					while (rs.next()) {
						String itemNo = String.valueOf(rs.getInt("item_no"));
						String brand = rs.getString("brand");
						String processor = rs.getString("processor");
						String ram = String.valueOf(rs.getInt("ram"));
						String ssd = String.valueOf(rs.getInt("ssd"));
						String price = String.valueOf(rs.getInt("price"));
						String numberOfStock = String.valueOf(rs.getInt("num_of_stock"));

						String tbData[] = { itemNo, brand, processor, ram, ssd, 
								price, numberOfStock };

						model = (DefaultTableModel) table.getModel();

						model.addRow(tbData);

					}
					con.close();
					
				} catch (SQLException ex) {

				}
			}
		});
		btnRetrieve.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnRetrieve.setBounds(846, 533, 130, 30);
		panel.add(btnRetrieve);

		JButton btnPdfGenerate = new JButton("Print");
		btnPdfGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Stock list");
				MessageFormat footer = new MessageFormat("");

				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnPdfGenerate.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnPdfGenerate.setBounds(1044, 533, 130, 30);
		panel.add(btnPdfGenerate);
		
		JLabel itemNo = new JLabel("Item No:");
		itemNo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		itemNo.setBounds(902, 217, 88, 34);
		panel.add(itemNo);
		
		txtItemNo = new JTextField();
		txtItemNo.setPreferredSize(new Dimension(7, 20));
		txtItemNo.setMinimumSize(new Dimension(7, 20));
		txtItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtItemNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtItemNo.setColumns(10);
		txtItemNo.setBounds(1007, 227, 83, 19);
		panel.add(txtItemNo);

	}
	
}
