package com.sgs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.sgs.exception.InvalidRangeException;

/**
 * TScreen - This class for the third screen for the UI, In this screen shows
 * normalized scores of the entered students
 */
public class TScreen {

	public static final String INVALID_RANGE = "Invalid Input Range";

	/**
	 * showThirdScreen - this method shows the third screen in the UI & takes input
	 * table with scores from second screen
	 */
	public static void showThirdScreen(JTable table) throws InvalidRangeException {

		Map<String, Double> map = calculateFinalValues(table);

		JFrame frame = new JFrame("Student Grading System - Normalized Results");
		frame.setBounds(700, 300, 10, 10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JTable fTable = new JTable(new MyFinalTableModel(map));
		fTable.setPreferredScrollableViewportSize(new Dimension(800, 500));
		fTable.setFillsViewportHeight(true);
		fTable.setRowHeight(50);

		fTable.getColumnModel().getColumn(0).setPreferredWidth(270);
		fTable.getColumnModel().getColumn(1).setPreferredWidth(270);

		frame.add(fTable.getTableHeader(), BorderLayout.NORTH);
		frame.add(fTable, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * calculateFinalValues - This method takes input from second screen as a jTable
	 * and calculate final normalized score and returned a map of student name and
	 * the corresponding normalized score
	 */
	public static Map<String, Double> calculateFinalValues(JTable table) throws InvalidRangeException {

		int dataSize = table.getRowCount();
		LinkedHashMap<String, List<Integer>> nmap = new LinkedHashMap<String, List<Integer>>();

		for (int i = 0; i < dataSize; i++) {

			String memName = (String) table.getValueAt(i, 0);
			List<Integer> tlist = new ArrayList<Integer>();
			for (int j = 1; j <= 3; j++) {

				tlist.add(Integer.parseInt((String) table.getValueAt(i, j)));
			}
			nmap.put(memName, tlist);

		}

		return new TScreen().normalize(nmap);

	}

	/*
	 * normalize - This method takes the student and the corresponding list of 3
	 * scores, calculation of normalized scores is also calculated is also done
	 * Method returns map of student name and corresponding normalized score
	 */
	public Map<String, Double> normalize(Map<String, List<Integer>> nmap) throws InvalidRangeException {

		if (nmap == null) {
			return null;
		}

		if (nmap.size() > 7 || nmap.size() < 2) {
			throw new InvalidRangeException(INVALID_RANGE);
		}

		int dataSize = nmap.size();
		String[] names = new String[dataSize];
		double[] normalizedScores = new double[dataSize];
		int sum = 0;

		int idx = 0;
		for (String key : nmap.keySet()) {

			String memName = key;

			int lsum = 0;

			for (int num : nmap.get(key)) {
				if (num < 0 || num > 5)
					throw new InvalidRangeException(INVALID_RANGE);
				lsum += num;
			}
			names[idx] = memName;
			normalizedScores[idx] = lsum;
			sum += lsum;
			idx++;

		}

		for (int i = 0; i < normalizedScores.length; i++) {

			normalizedScores[i] = sum != 0 ? roundValue(normalizedScores[i] / sum, 2) : 0;

		}

		Map<String, Double> nMap = new LinkedHashMap<String, Double>();

		for (int i = 0; i < dataSize; i++) {

			nMap.put(names[i], normalizedScores[i]);

		}

		return nMap;
	}

	public static double roundValue(double value, int places) {

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();

	}

}

/*
 * * MyFinalTableModel - This class is used as a reference for jTable Model
 */
class MyFinalTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Name   ", "Score   " };

	public final Object[] longValues = { "Averylongvalue", " " };
	private Object[][] data;

	/**
	 * MyFinalTableModel - takes normalized scores and puts into the table
	 */
	public MyFinalTableModel(Map<String, Double> map) {

		data = new Object[map.size()][2];

		int idx = 0;

		for (String key : map.keySet()) {

			data[idx][0] = key;
			data[idx][1] = map.get(key);
			idx++;

		}

	}

	/**
	 * getColumnCount - get ColumnCount
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * getRowCount - get RowCount
	 */
	public int getRowCount() {
		return data.length;
	}

	/**
	 * getColumnName - get ColumnName corresponding to input row
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * getValueAt - get object value corresponding to input row and column
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
