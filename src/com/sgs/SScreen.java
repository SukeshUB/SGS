package com.sgs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

//The Evaluation Screen
public class SScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	//Entry method from first Screen. Method takes the inputs -- number of Members and if there
	//are any previously entered values
	public static void showSecondScreen(int numMembers, boolean isPreviouslyEntered) {

		//Setting up JFrame
		JFrame frame = new JFrame();
		frame.setBounds(700, 300, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		//Setting up Jtable based on MyTableModel
		JTable table = new JTable(new MyTableModel(numMembers, isPreviouslyEntered));
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		
		//Setting up Columns as Drop down columns
		setUpColumns(table);
		//Adjusting Column Widths
		initColumnSizes(table);
		table.setRowHeight(40);

		JButton submit = new JButton("Submit");
		JPanel btnPnl = new JPanel(new BorderLayout());
		JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		bottombtnPnl.add(submit);
		submit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				//A Method to validate the table
				if (!isValidTable(table)) {
					errorLabel.setText(" Invalid Input. Please fill out all scores ");

				} else {
					frame.setVisible(false);
					TScreen.showThirdScreen(table);
				}

			}
		});
		btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
		btnPnl.add(errorLabel, BorderLayout.SOUTH);

		frame.add(table.getTableHeader(), BorderLayout.NORTH);
		frame.add(table, BorderLayout.CENTER);
		frame.add(btnPnl, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	//The method that validates the table whether inputs are valid
	private static boolean isValidTable(JTable table) {

		int dataSize = table.getRowCount();

		for (int i = 0; i < dataSize; i++) {

			for (int j = 1; j <= 3; j++) {
				String res = (String) table.getValueAt(i, j);
				if (res.length() == 0)
					return false;
			}

		}

		return true;
	}

	//Adjusts the column widths dynamically
	private static void initColumnSizes(JTable table) {
		MyTableModel model = (MyTableModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.longValues;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < 4; i++) {
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i],
					false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;

			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}

	public static void setUpColumns(JTable table) {

		TableColumnModel columnModel = table.getColumnModel();
		for (int i = 1; i <= 3; i++) {

			TableColumn column = columnModel.getColumn(i);
			JComboBox<String> comboBox = new JComboBox<String>();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			model.addElement("0");
			model.addElement("1");
			model.addElement("2");
			model.addElement("3");
			model.addElement("4");
			model.addElement("5");
			comboBox.setModel(model);
			column.setCellEditor(new DefaultCellEditor(comboBox));

			model = new DefaultComboBoxModel<String>();
			model.addElement("");
			model.addElement("0");
			model.addElement("1");
			model.addElement("2");
			model.addElement("3");
			model.addElement("4");
			model.addElement("5");

			ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
			renderer.setModel(model);
			column.setCellRenderer(renderer);

		}

	}

}

class ComboBoxTableCellRenderer extends JComboBox implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setSelectedItem(value);
		return this;
	}

}

class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] rnames = new String[] { "  Rakesh  ", "  Sourabh  ", "  Yuhao  ", "  Harsha  ", "  Sukesh  ",
			"  Dexter  ", "  House  " };
	private Random rand = new Random();
	private Random scr = new Random();
	private final int maxBound = 6;
	private String[] columnNames = { "Names   ", "Professionalism    ", "Meeting Participation   ",
			"Work Evaluation   " };
	private Object[][] data;

	public final Object[] longValues = { "Averylongvalue", " ", " ", " " };

	public MyTableModel(int numMembers, boolean isPreviouslyEntered) {

		data = new Object[numMembers][4];

		Set<String> set = new HashSet<String>();
		while (set.size() != numMembers) {
			set.add(rnames[rand.nextInt(rnames.length)]);
		}

		int idx = 0;
		for (String name : set) {

			data[idx][0] = name;
			if (!isPreviouslyEntered) {
				data[idx][1] = "";
				data[idx][2] = "";
				data[idx][3] = "";
			} else {

				data[idx][1] = String.valueOf(scr.nextInt(maxBound));
				data[idx][2] = String.valueOf(scr.nextInt(maxBound));
				data[idx][3] = String.valueOf(scr.nextInt(maxBound));

			}
			idx++;

		}

	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public boolean isCellEditable(int row, int col) {
		return col != 0;
	}

	public void setValueAt(Object value, int row, int col) {

		data[row][col] = value;
		fireTableCellUpdated(row, col);

	}

}
