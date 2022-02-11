package stockCrudR2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class SalesScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnSearch;
	private JButton btnIncr;
	private JButton btnDecr;
	private JButton btnAddQuantity;
	private JButton btnPurchase;
	DefaultTableModel model;

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	private JTable tableBasket;
	private JTextField txtQuantity;
	private JTextField txtTotal;
	
	int qtyVal = 0;
	String qtySVal;
	
	int updatedStockVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesScreen frame = new SalesScreen();
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
	public SalesScreen() {
		super("Sales Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1539, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 43, 654, 623);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item No", "Brand", "Processor", "RAM (GB)", "SSD (GB)", 
				"Price (TL)", "Number of stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, 
				Integer.class, Integer.class, Integer.class
			};
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.setBackground(new Color(255, 235, 205));
		model = new DefaultTableModel();
		scrollPane.setViewportView(table);

		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrand.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBrand.setBounds(23, 43, 76, 23);
		contentPane.add(lblBrand);

		JLabel lblProcessor = new JLabel("Processor");
		lblProcessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcessor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProcessor.setBounds(23, 165, 108, 23);
		contentPane.add(lblProcessor);

		JButton btnAddToBasket = new JButton("Add to basket");
		btnAddToBasket.setBackground(new Color(255, 165, 0));
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableModel m1 = table.getModel();
				int[] index = table.getSelectedRows();
				Object[] row = new Object[7];
				model = (DefaultTableModel) tableBasket.getModel();
				for (int i = 0; i < index.length; i++) {
					row[0] = m1.getValueAt(index[i], 0);
					row[1] = m1.getValueAt(index[i], 1);
					row[2] = m1.getValueAt(index[i], 2);
					row[3] = m1.getValueAt(index[i], 3);
					row[4] = m1.getValueAt(index[i], 4);
					row[5] = m1.getValueAt(index[i], 5);
					model.addRow(row);

				}
				
				Integer sum = 0;
				for (int i = 0; i < tableBasket.getRowCount(); i++) {
					sum += (Integer)tableBasket.getValueAt(i, 5); 
				}

				txtTotal.setText(sum.toString());
			}
		});
		btnAddToBasket.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnAddToBasket.setBounds(1081, 84, 145, 51);
		contentPane.add(btnAddToBasket);

		List<JCheckBox> selectedBrandCheckBoxList = new ArrayList<JCheckBox>();
		JCheckBox chckbxAcer = new JCheckBox("Acer");
		chckbxAcer.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		chckbxAcer.setBounds(32, 72, 76, 21);
		contentPane.add(chckbxAcer);
		selectedBrandCheckBoxList.add(chckbxAcer);

		JCheckBox chckbxApple = new JCheckBox("Apple");
		chckbxApple.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		chckbxApple.setBounds(153, 72, 93, 21);
		contentPane.add(chckbxApple);
		selectedBrandCheckBoxList.add(chckbxApple);

		JCheckBox chckbxAsus = new JCheckBox("Asus");
		chckbxAsus.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		chckbxAsus.setBounds(32, 114, 93, 21);
		contentPane.add(chckbxAsus);
		selectedBrandCheckBoxList.add(chckbxAsus);

		JCheckBox chckbxHP = new JCheckBox("HP");
		chckbxHP.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		chckbxHP.setBounds(153, 116, 93, 21);
		contentPane.add(chckbxHP);
		selectedBrandCheckBoxList.add(chckbxHP);

		List<JCheckBox> selectedProCheckBoxList = new ArrayList<JCheckBox>();
		JCheckBox cbxAmdAth = new JCheckBox("AMD Athlon");
		cbxAmdAth.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxAmdAth.setBounds(23, 194, 119, 21);
		contentPane.add(cbxAmdAth);
		selectedProCheckBoxList.add(cbxAmdAth);

		JCheckBox cbxRyzen5 = new JCheckBox("AMD Ryzen 5");
		cbxRyzen5.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRyzen5.setBounds(175, 194, 135, 21);
		contentPane.add(cbxRyzen5);
		selectedProCheckBoxList.add(cbxRyzen5);

		JCheckBox cbxRyzen7 = new JCheckBox("AMD Ryzen 7");
		cbxRyzen7.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRyzen7.setBounds(23, 232, 135, 21);
		contentPane.add(cbxRyzen7);
		selectedProCheckBoxList.add(cbxRyzen7);

		JCheckBox cbxCeleron = new JCheckBox("Intel Celeron");
		cbxCeleron.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxCeleron.setBounds(175, 232, 135, 21);
		contentPane.add(cbxCeleron);
		selectedProCheckBoxList.add(cbxCeleron);

		JCheckBox cbxi3 = new JCheckBox("Intel i3");
		cbxi3.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxi3.setBounds(23, 266, 85, 21);
		contentPane.add(cbxi3);
		selectedProCheckBoxList.add(cbxi3);

		JCheckBox cbxi5 = new JCheckBox("Intel i5");
		cbxi5.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxi5.setBounds(123, 266, 85, 21);
		contentPane.add(cbxi5);
		selectedProCheckBoxList.add(cbxi5);

		JCheckBox cbxi7 = new JCheckBox("Intel i7");
		cbxi7.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxi7.setBounds(238, 266, 85, 21);
		contentPane.add(cbxi7);
		selectedProCheckBoxList.add(cbxi7);

		List<JCheckBox> selectedRamCheckBoxList = new ArrayList();
		JLabel lblRam = new JLabel("RAM (GB)");
		lblRam.setHorizontalAlignment(SwingConstants.CENTER);
		lblRam.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRam.setBounds(23, 313, 108, 23);
		contentPane.add(lblRam);

		JCheckBox cbxRam1 = new JCheckBox("1 GB");
		cbxRam1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRam1.setBounds(23, 344, 85, 21);
		contentPane.add(cbxRam1);
		selectedRamCheckBoxList.add(cbxRam1);

		JCheckBox cbxRam2 = new JCheckBox("2 GB");
		cbxRam2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRam2.setBounds(112, 342, 85, 21);
		contentPane.add(cbxRam2);
		selectedRamCheckBoxList.add(cbxRam2);

		JCheckBox cbxRam4 = new JCheckBox("4 GB");
		cbxRam4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRam4.setBounds(225, 344, 85, 21);
		contentPane.add(cbxRam4);
		selectedRamCheckBoxList.add(cbxRam4);
		
		JCheckBox cbxRam8 = new JCheckBox("8 GB");
		cbxRam8.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRam8.setBounds(23, 382, 85, 21);
		contentPane.add(cbxRam8);
		selectedRamCheckBoxList.add(cbxRam8);
		
		JCheckBox cbxRam16 = new JCheckBox("16 GB");
		cbxRam16.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRam16.setBounds(112, 384, 85, 21);
		contentPane.add(cbxRam16);
		selectedRamCheckBoxList.add(cbxRam16);
		
		JCheckBox cbxRam32 = new JCheckBox("32 GB");
		cbxRam32.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxRam32.setBounds(225, 384, 85, 21);
		contentPane.add(cbxRam32);
		selectedRamCheckBoxList.add(cbxRam32);
		
		List<JCheckBox> selectedSsdCheckBoxList = new ArrayList();
		JLabel lblSsd = new JLabel("SSD (GB)");
		lblSsd.setHorizontalAlignment(SwingConstants.CENTER);
		lblSsd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSsd.setBounds(23, 429, 108, 23);
		contentPane.add(lblSsd);

		JCheckBox cbxSsd128 = new JCheckBox("128 GB");
		cbxSsd128.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxSsd128.setBounds(23, 472, 85, 21);
		contentPane.add(cbxSsd128);
		selectedSsdCheckBoxList.add(cbxSsd128);

		JCheckBox cbxSsd256 = new JCheckBox("256 GB");
		cbxSsd256.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxSsd256.setBounds(123, 472, 85, 21);
		contentPane.add(cbxSsd256);
		selectedSsdCheckBoxList.add(cbxSsd256);

		JCheckBox cbxSsd512 = new JCheckBox("512 GB");
		cbxSsd512.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbxSsd512.setBounds(225, 472, 85, 21);
		contentPane.add(cbxSsd512);
		selectedSsdCheckBoxList.add(cbxSsd512);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<String> selectedBrandList = selectedBrandCheckBoxList.stream()
						.filter(checkBox -> checkBox.isSelected())
						.map(checkBox -> checkBox.getText())
						.collect(Collectors.toList());
				
				List<String> selectedProList = selectedProCheckBoxList.stream()
						.filter(checkBox -> checkBox.isSelected())
						.map(checkBox -> checkBox.getText())
						.collect(Collectors.toList());
				
				List<String> selectedRamList = selectedRamCheckBoxList.stream()
						.filter(checkBox -> checkBox.isSelected())
						.map(checkBox -> checkBox.getText())
						.map(ram -> ram.split(" ")[0])
						.collect(Collectors.toList());
				
				List<String> selectedSsdList = selectedSsdCheckBoxList.stream()
						.filter(checkBox -> checkBox.isSelected())
						.map(checkBox -> checkBox.getText())
						.map(ram -> ram.split(" ")[0])
						.collect(Collectors.toList());
				
				populateTable(selectedBrandList, selectedProList, selectedRamList,
						selectedSsdList);
			}
		});
		btnSearch.setBackground(new Color(0, 191, 255));
		btnSearch.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnSearch.setBounds(93, 611, 153, 31);
		contentPane.add(btnSearch);
		
		JScrollPane sPaneBasket = new JScrollPane();
		sPaneBasket.setBounds(1042, 145, 435, 415);
		contentPane.add(sPaneBasket);
		
		tableBasket = new JTable();
		tableBasket.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item no", "Brand", "Processor", "RAM (GB)", 
				"SSD (GB)", "Price (TL)", "Quantity", "Number of stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Integer.class, 
				Integer.class, Integer.class, Integer.class 
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableBasket.getColumnModel().getColumn(0).setPreferredWidth(87);
		tableBasket.getColumnModel().getColumn(1).setPreferredWidth(87);
		tableBasket.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableBasket.getColumnModel().getColumn(3).setPreferredWidth(96);
		tableBasket.getColumnModel().getColumn(4).setPreferredWidth(96);
		tableBasket.getColumnModel().getColumn(5).setPreferredWidth(85);
		tableBasket.getColumnModel().getColumn(6).setPreferredWidth(85);
		tableBasket.getColumnModel().getColumn(7).setPreferredWidth(85);
		sPaneBasket.setViewportView(tableBasket);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableBasket.getSelectedRow();
				
				if (selectedRow >= 0) {
					JFrame frame = new JFrame("Delete");
					if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to "
							+ "delete item ?", "Delete",
							JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_CANCEL_OPTION) {
						JOptionPane.showMessageDialog(null, "The transaction "
								+ "has been cancelled.");
					} else {
						model.removeRow(tableBasket.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Product was deleted");
					}			
				}
				
				Integer sum = 0;
				for (int i = 0; i < tableBasket.getRowCount(); i++) {
					sum += (Integer)tableBasket.getValueAt(i, 5); 
				}

				txtTotal.setText(sum.toString());
			}
		});
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnDelete.setBackground(new Color(221, 160, 221));
		btnDelete.setBounds(1263, 84, 145, 51);
		contentPane.add(btnDelete);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblQuantity.setBounds(1042, 570, 76, 43);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuantity.setText("0");
		txtQuantity.setBounds(1097, 623, 45, 43);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		btnIncr = new JButton("+");
		btnIncr.setBackground(new Color(173, 255, 47));
		btnIncr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtySVal = txtQuantity.getText();
				qtyVal = Integer.parseInt(qtySVal);
				txtQuantity.setText(qtySVal);
				
				if (e.getSource() == btnIncr) {
					qtyVal++;
					txtQuantity.setText("" + qtyVal);
				}
			}
		});
		btnIncr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIncr.setBounds(1042, 623, 45, 43);
		contentPane.add(btnIncr);
		
		btnDecr = new JButton("-");
		btnDecr.setBackground(new Color(255, 99, 71));
		btnDecr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtySVal = txtQuantity.getText();
				qtyVal = Integer.parseInt(qtySVal);
				txtQuantity.setText(qtySVal);
				
				if (e.getSource() == btnDecr) {
					if (qtyVal > 0) {
						qtyVal--;
						txtQuantity.setText("" + qtyVal);
					}	
				}
			}
		});
		btnDecr.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDecr.setBounds(1152, 623, 45, 43);
		contentPane.add(btnDecr);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase");
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to "
						+ "make a payment ?", "Continue",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_CANCEL_OPTION) {
					
				} else {
					updatedStockVal = ((Integer) table.getValueAt(table.getSelectedRow(), 6)) 
							- ((Integer) tableBasket
									.getValueAt(tableBasket.getSelectedRow(), 6));
					
					PaymentScreen pyScr = new PaymentScreen((int)tableBasket.getValueAt(tableBasket.getSelectedRow(), 0), updatedStockVal);
					pyScr.setVisible(true);
				}
				
			}
		});
		btnPurchase.setBackground(new Color(255, 215, 0));
		btnPurchase.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnPurchase.setBounds(1247, 629, 114, 37);
		contentPane.add(btnPurchase);
		
		btnAddQuantity = new JButton("Add");
		btnAddQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantity = Integer.parseInt(txtQuantity.getText());		
				tableBasket.setValueAt(quantity, tableBasket.getSelectedRow(), 6);
				
				tableBasket.setValueAt(((Integer)table
						.getValueAt(table.getSelectedRow(), 5) * quantity), 
						tableBasket.getSelectedRow(), 5);
				
				tableBasket.setValueAt(((Integer)table
						.getValueAt(table.getSelectedRow(), 6) - quantity),
						tableBasket.getSelectedRow(), 7);
				
				Integer sum = 0;
				for (int i = 0; i < tableBasket.getRowCount(); i++) {
					sum += (Integer)tableBasket.getValueAt(i, 5); 
				}

				txtTotal.setText(sum.toString());
			}
		});
		btnAddQuantity.setBounds(1128, 570, 69, 38);
		contentPane.add(btnAddQuantity);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(1345, 570, 62, 43);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total price:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblTotal.setBounds(1236, 570, 99, 37);
		contentPane.add(lblTotal);
		
		JLabel lblTL = new JLabel("TL");
		lblTL.setHorizontalAlignment(SwingConstants.CENTER);
		lblTL.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblTL.setBounds(1414, 573, 31, 37);
		contentPane.add(lblTL);
		
		populateTable(null, null, null, null);

	}

	public void populateTable(List<String> selectedBrandList,
			List<String> selectedProList, List<String> selectedRamList,
			List<String> selectedSsdList) {
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		try {
			ArrayList<Computer> computers = getComputers(selectedBrandList,
					selectedProList, selectedRamList, selectedSsdList);
			for (Computer computer : computers) {
				Object[] row = { computer.getItemNo(), computer.getBrand(), 
						computer.getProcessor(), computer.getRam(),
						computer.getSsd(), computer.getPrice(), computer.getNumOfStock()};
				model.addRow(row);
			}
		} catch (SQLException ex) {

		}

	}

	public ArrayList<Computer> getComputers(List<String> selectedBrandList, 
			List<String> selectedProList, List<String> selectedRamList,
			List<String> selectedSsdList) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Computer> computers = null;
		String productQuery = "SELECT * FROM computers where "
			+ "(coalesce(:brandNames) is null or brand in (:brandNames)) "
			+ "AND (coalesce(:proNames) is null or processor in (:proNames)) "
			+ "AND (coalesce(:rams) is null or ram in (:rams)) "
			+ "AND (coalesce(:ssds) is null or ssd in (:ssds))";

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbstok", 
					"root", "KCmerkez97.,");
			st = con.createStatement();
			Map<String, List<String>> keyAndValues = new HashMap<String, List<String>>();
			keyAndValues.put(":brandNames", selectedBrandList);
			keyAndValues.put(":proNames", selectedProList);
			keyAndValues.put(":rams", selectedRamList);
			keyAndValues.put(":ssds", selectedSsdList);

			rs = st.executeQuery(getFormattedQuery(productQuery, keyAndValues));
			computers = new ArrayList<Computer>();
			while (rs.next()) {
				computers.add(new Computer(rs.getInt("item_no"), rs.getString("brand"), 
						rs.getString("processor"), rs.getInt("ram"), 
						rs.getInt("ssd"), rs.getInt("price"), 
						rs.getInt("num_of_stock")));
			}
		} catch (SQLException exception) {
			System.out.print(exception);
		} finally {
			st.close();
			con.close();
		}
		return computers;
	}

	private String getFormattedQuery(String query, Map<String, List<String>> keyAndValues) {
		String formattedQuery = query;

		for (Map.Entry<String, List<String>> entry : keyAndValues.entrySet()) {
			boolean listAll = Objects.isNull(entry.getValue()) || entry.getValue().isEmpty();
			formattedQuery = formattedQuery.replaceAll(entry.getKey(),
					listAll ? "null"
							: entry.getValue().stream()
							.map(value -> String.format("'%s'", value))
									.collect(Collectors.joining(", ")));
		}
		return formattedQuery;
	}
}