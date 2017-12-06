package com.sgs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class SScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showSecondScreen(4, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void showSecondScreen(int numMembers, boolean isPreviouslyEntered) {
		JFrame frame = new JFrame();
		frame.setBounds(700, 300, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JTable table = new JTable(new MyTableModel(numMembers, isPreviouslyEntered));
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		setUpColumns(table);
		initColumnSizes(table);

		JButton submit = new JButton("Submit");
		JPanel btnPnl = new JPanel(new BorderLayout());
		// JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPnl.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// lblNewLabel.setText(" Number of People Selected : " +
				// comboBox.getSelectedItem());
				frame.setVisible(false);
				TScreen.showThirdScreen(table);
				//TScreen.normalize(table);
				// SScreen.showSecondScreen((Integer)
				// comboBox.getSelectedItem(),chckbxPreviouslyEntered.isSelected());
				// SScreen secScreen = new SScreen((Integer)
				// comboBox.getSelectedItem(),chckbxPreviouslyEntered.isSelected());

			}
		});
		// btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

		frame.add(table.getTableHeader(), BorderLayout.NORTH);
		frame.add(table, BorderLayout.CENTER);
		frame.add(btnPnl, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public SScreen(int numMembers, boolean isPreviouslyEntered, JFrame frame) {
		// super(new GridLayout(1, 0));
		initialize(numMembers, isPreviouslyEntered, frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int numMembers, boolean isPreviouslyEntered, JFrame frame) {

		JTable table = new JTable(new MyTableModel(numMembers, isPreviouslyEntered));
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		// table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);

		setUpColumns(table);

		add(scrollPane);

		/*
		 * JPanel btnPnl = new JPanel(new BorderLayout()); JPanel bottombtnPnl =
		 * new JPanel(new FlowLayout(FlowLayout.CENTER)); bottombtnPnl.add(new
		 * JButton("Submit")); btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
		 * 
		 * frame.add(table.getTableHeader(), BorderLayout.NORTH);
		 * frame.add(table, BorderLayout.CENTER); frame.add(btnPnl,
		 * BorderLayout.SOUTH);
		 */

	}

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

		for (int i = 1; i <= 3; i++) {

			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItem("0");
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3");
			comboBox.addItem("4");
			comboBox.addItem("5");
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setCellEditor(new DefaultCellEditor(comboBox));
			column.sizeWidthToFit();
			// Set up tool tips for the sport cells.
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setToolTipText("Click for combo box");
			column.setCellRenderer(renderer);

		}

	}
}

class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] rnames = new String[] { "Rakesh", "Sourabh", "Yuhao", "Harsha", "Sukesh", "Dexter", "House" };
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
				data[idx][1] = " ";
				data[idx][2] = " ";
				data[idx][3] = " ";
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
